package com.example.a20210605_recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Integer> data = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {

            data.add(i);

        }

        RecyclerView recyclerViewPokemon = findViewById(R.id.activity_main__recyclerViewPokemon);
        recyclerViewPokemon.setAdapter(new RecyclerViewPokemonAdapter(data));
    }
}