package com.example.a20210605_recyclerview.ui;

import androidx.lifecycle.MutableLiveData;

import com.example.a20210605_recyclerview.Pokemon;

public class DetailViewModel {
    public MutableLiveData<Pokemon> lvPokemon = new MutableLiveData<>();

    //실시간으로 변경 사항을 알아 챌 수 있는 통신이 이루어지는 MutableLiveData 사용
    //원래는 public String bottomImgUrl; 이었음
    public MutableLiveData<String> lvBottomImgUrl = new MutableLiveData<>();
}
