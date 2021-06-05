package com.example.a20210605_recyclerview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //List에 들어가는 객체는 data 하나당 Pokemon 객체
        List<Pokemon> data = new ArrayList<>();

        data.add(new Pokemon(1, "bulbasaur"));
        data.add(new Pokemon(2, "ivysaur"));
        data.add(new Pokemon(3, "venusaur"));



        RecyclerView recyclerViewPokemon = findViewById(R.id.activity_main__recyclerViewPokemon);
        recyclerViewPokemon.setAdapter(new RecyclerViewPokemonAdapter(data));


    }
}