package com.example.a20210605_recyclerview.binding;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.example.a20210605_recyclerview.Util;

//BindingAdapters 클래스는 XML에서 사용한 변수를 어떻게 사용할지 사용방법을 정의해둔 클래스 이다.
public class BindingAdapters {

    //XML 내부에 사용할 변수의 사용 방법을 정의 해둔 것
    @BindingAdapter({"numberText"})
    public static void numberText(TextView view, int number){

        view.setText(number + "");

    }

    @BindingAdapter({"imgUrl"})
    public static void imgUrl(ImageView view, String imgUrl){

        Util.loadImageOn(imgUrl, view);

    }


}
