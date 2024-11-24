package com.hajera.f55123047;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private RecyclerView rvHeroes;
    private ArrayList<Hore> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvHeroes = findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);

        list.addAll(getListHeroes());
        showRecyclerList();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == R.id.action_list) {
            rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        } else if (item.getItemId() == R.id.action_grid) {
            rvHeroes.setLayoutManager(new GridLayoutManager(this, 2));
        }
        return super.onOptionsItemSelected(item);
    }
    public ArrayList<Hore> getListHeroes() {
        String[] dataName = getResources().getStringArray(R.array.dara_name);
        String[] dataDescription = getResources().getStringArray(R.array.data_description);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
        ArrayList<Hore> listHero = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++){
            Hore hore = new Hore();
            hore.setName(dataName[i]);
            hore.setDescription(dataDescription[i]);
            hore.setPhoto(dataPhoto.getResourceId(i, -1));

            listHero.add(hore);
        }
        return listHero;
    }
    private void showRecyclerList(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter listHeroAdapter = new ListHeroAdapter(list);
        rvHeroes.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnitemClickCallback(this::showSelectedHero);
    }
    private void showSelectedHero(Hore hore) {
        Toast.makeText(this,"Kamu memilih" + hore.getName(), Toast.LENGTH_SHORT).show();
    }
}