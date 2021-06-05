package com.example.a20210605_recyclerview;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pokemon {

    private int id;
    private String name;

    public String getImgUrl() {

        return "https://pokeres.bastionbot.org/images/pokemon/" + id + ".png";

    }

}
