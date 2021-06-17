package com.example.a20210605_recyclerview.api;

import com.example.a20210605_recyclerview.Pokemon;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApi {
    
    //base URL은 항상 끝이 "/"으로 끝나야함
    String baseUrl = "https://pokeapi.co/api/v2/";

    @GET("pokemon")
    Observable<PokeApi__getPokemons__ResponseBody> getPokemons(@Query("offset") int offset, @Query("limit")int limit);

    //@Query("offset")과 @Query("limit")을 사용 하면
    //https://pokeapi.co/api/v2/pokemon/?offset=(입력한 값)&limit=(입력한 값)
    //https://pokeapi.co/api/v2/pokemon/?offset=0&limit=5
    //예로 offset = 1, limit = 5 는 1번 데이터 건너뛰고 이후 5개 데이터를 가져오라는 것, 즉 2 ~ 6번 데이터를 가져온다.

    @GET("pokemon/{id}")
    Observable<PokeApi__getPokemon__ResponseBody> getPokemon(@Path("id") int id);

}
