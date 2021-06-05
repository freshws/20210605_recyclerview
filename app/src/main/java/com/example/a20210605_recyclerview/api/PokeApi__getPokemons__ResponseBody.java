package com.example.a20210605_recyclerview.api;

import com.example.a20210605_recyclerview.Pokemon;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PokeApi__getPokemons__ResponseBody {

    public int count;
    public String next;
    public String previous;
    public List<Pokemon> results;

}
