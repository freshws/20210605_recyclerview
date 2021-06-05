package com.example.a20210605_recyclerview.service;

import androidx.annotation.NonNull;

import com.example.a20210605_recyclerview.BuildConfig;
import com.example.a20210605_recyclerview.Util;
import com.example.a20210605_recyclerview.api.PokeApi;
import com.example.a20210605_recyclerview.api.PokeApi__getPokemons__ResponseBody;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PokemonService {

    private PokeApi pokeApi;

    public PokemonService() {

        //Retrofit 빌더객체 생성
        OkHttpClient okHttpClient = null;

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {

            // Stetho Interceptor 추가해야 Chrome Inspect tool 에서 확인 가능, 필수 아님
            okHttpClientBuilder.addNetworkInterceptor(new StethoInterceptor());

        }

        okHttpClient = okHttpClientBuilder.build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create()) // Jackson 사용
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()); // RX Java 사용

        retrofitBuilder.client(okHttpClient);


        //Retrofit 객체 생성
        Retrofit retrofit = retrofitBuilder
                .baseUrl(PokeApi.baseUrl)
                .build();

        pokeApi = retrofit.create(PokeApi.class);

    }

    public void getPokemons( @NonNull Consumer<? super PokeApi__getPokemons__ResponseBody> onNext) {
        pokeApi.getPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, throwable -> {
                    Util.log("throwable : " + throwable.getMessage());
                });
    }

}


