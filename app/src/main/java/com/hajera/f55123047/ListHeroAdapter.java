package com.hajera.f55123047;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListHeroAdapter extends RecyclerView.Adapter<ListHeroAdapter.ListViewHolder> {
    private ArrayList<Hore> listHero;

    private OnitemClickCallback onitemClickCallback;
    public void setOnitemClickCallback(OnitemClickCallback onitemClickCallback) {
        this.onitemClickCallback = onitemClickCallback;
    }

    public ListHeroAdapter(ArrayList<Hore> list){
        this.listHero = list;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_row_hero, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Hore hero = listHero.get(position);
        holder.imgPhoto.setImageResource(hero.getPhoto());
        holder.tvName.setText(hero.getName());
        holder.tvDescription.setText(hero.getDescription());
        holder.itemView.setOnClickListener(v -> {
                Toast.makeText(holder.itemView.getContext(),"Kamu memilih" + listHero.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
        });
        holder.itemView.setOnClickListener(v -> onitemClickCallback.onItemClicked(listHero.get(holder.getAdapterPosition())));
    }
    public interface OnitemClickCallback {
        void onItemClicked(Hore data);
    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDescription;

        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
        }
    }
}
