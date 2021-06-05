package com.example.a20210605_recyclerview.api;

import com.example.a20210605_recyclerview.Pokemon;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokeApi {
    
    //base URL은 항상 끝이 "/"으로 끝나야함
    String baseUrl = "https://pokeapi.co/api/v2/";

    @GET("pokemon")
    Observable<PokeApi__getPokemons__ResponseBody> getPokemons();

}
