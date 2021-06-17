package com.example.a20210605_recyclerview;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pokemon {

    private int id;
    private String name;
    private String url;

    public Pokemon(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getImgUrl() {

        return "https://pokeres.bastionbot.org/images/pokemon/" + id + ".png";

    }

    //https://pokeapi.co/api/v2/pokemon 여기에 있는 name과 url의 변수명을 맞추기 위해 생성자 만듬
    @JsonCreator
    public Pokemon(@JsonProperty("name") String name, @JsonProperty("url") String url) {
        this.name = name;
        //URL을 쪼개는 작업 "/"를 기준으로 쪼갰고 8조각이 됨, 참고로 맨뒤 "/"는 인식 않함
        String[] urlBits = url.split("/");
        this.id = Integer.parseInt(urlBits[urlBits.length - 1]);
        this.url = url;
    }

}
