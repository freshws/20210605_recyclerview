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

        //데이터와 어뎁터가 연결되지 않은, 빈껍데이 리싸이클러 뷰 어댑터 생성
        final RecyclerViewPokemonAdapter recyclerViewPokemonAdapter = new RecyclerViewPokemonAdapter();

        //메인 액티비티와 어댑터 연결
        recyclerViewPokemon.setAdapter(recyclerViewPokemonAdapter);

        //"더 보기" 버튼이 클릭 되었을 때 통신이 발생하고 리스트가 뿌려지도록 변경
        recyclerViewPokemonAdapter.setOnclickLoadMore(v -> {

            //더 보기 버튼을 누를 때 마다 리스트를 계속해서 가져오는 코드
            //처음에는 가져온 데이터가 없으니 recyclerViewPokemonAdapter.getDataSize() 가 0 이지만 두번째 더 보기를 누룬 후엔
            //recyclerViewPokemonAdapter.getDataSize() 에 의해서 가져온 것 만큼 뒤의 데이터가 와야 한다.
            //결극 recyclerViewPokemonAdapter.getDataSize(), recyclerViewPokemonAdapter.getDataSize() 메소드는 getPokemons 메소드의 매개변수 이다.
            pokemonService.getPokemons(recyclerViewPokemonAdapter.getDataSize(),recyclerViewPokemonAdapter.getDataSize(),responseBody -> {

                recyclerViewPokemonAdapter.addPokemons(responseBody.getResults());
            });

        });


    }
}