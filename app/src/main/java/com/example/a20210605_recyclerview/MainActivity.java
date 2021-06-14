package com.example.a20210605_recyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20210605_recyclerview.api.PokeApi;
import com.example.a20210605_recyclerview.service.PokemonService;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RecyclerView recyclerViewPokemon = findViewById(R.id.activity_main__recyclerViewPokemon);

        final PokemonService pokemonService = new PokemonService();

        final RecyclerViewPokemonAdapter recyclerViewPokemonAdapter = new RecyclerViewPokemonAdapter();
        recyclerViewPokemon.setAdapter(recyclerViewPokemonAdapter);

        //"더 보기" 버튼이 클릭 되었을 때 통신이 발생하고 리스트가 뿌려지도록 변경
        recyclerViewPokemonAdapter.setOnclickLoadMore(v -> {

            pokemonService.getPokemons(responseBody -> {

                recyclerViewPokemonAdapter.addPokemons(responseBody.getResults());
            });

        });


    }
}