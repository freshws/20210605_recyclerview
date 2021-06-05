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

        List<Integer> data = new ArrayList<>();

        data.add(1);
        data.add(2);
        data.add(3);

        RecyclerView recyclerViewPokemon = findViewById(R.id.activity_main__recyclerViewPokemon);
        recyclerViewPokemon.setAdapter(new RecyclerViewPokemonAdapter(data));


    }
}