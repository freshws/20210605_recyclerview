package com.example.a20210605_recyclerview.api;

import com.example.a20210605_recyclerview.Pokemon;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//API로부터 Json 형태로 데이터를 가져올 때 모든 데이터 변수를 정의 해야되나 일부 데이터만 가져올 거라면
//아래 @JsonIgnoreProperties(ignoreUnknown = true) 어노테이션을 정의 해줘야한다.
@JsonIgnoreProperties(ignoreUnknown = true)

public class PokeApi__getPokemon__ResponseBody {

    //원래 api 데이터 명은 public int base_experience; 이지만 다른 명으로 사용 하고 싶다면
    //위에 @JsonProperty를 적어 준다
    @JsonProperty("base_Experience")
    public int baseExperience;
    public int weight;
    public String name;
    int id;


    public Pokemon getPokemon() {

        return new Pokemon(id, name);

    }
}
