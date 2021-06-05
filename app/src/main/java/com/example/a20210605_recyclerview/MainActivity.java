package com.example.a20210605_recyclerview;

import android.os.Bundle;
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


        RecyclerView recyclerViewPokemon = findViewById(R.id.activity_main__recyclerViewPokemon);

        PokemonService pokemonService = new PokemonService();

        //인터넷 통신을 통해 API와 PokeApi__getPokemons__ResponseBody 간에 짝이 맞는 변수? 내용? 을 가져온다.
        //가져온 내용은 responseBody에 들어가게 됨.
        //PokeApi__getPokemons__ResponseBody가 API 내용을 받을 수 있게 변수명을 동일하게 만들어져있다.
        pokemonService.getPokemons(responseBody -> {


            recyclerViewPokemon.setAdapter(new RecyclerViewPokemonAdapter(responseBody.getResults()));

        });




    }
}