package com.example.a20210605_recyclerview.ui;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a20210605_recyclerview.Util;

//BaseActivity가 대표해서 AppCompatActivity를 상속받는다.
//그래서 BaseActivity를 상속받는 다른 클래스들이 연달아 AppCompatActivity를 상속받게 된다.
//즉 현재 Activity(CurrentActivity)를 가져오기 위해 사용하는 "this" 대신 사용 가능.
//★현재 활성화 되고 있는 Activity는 하나이기 때문에 이렇게 가져오는 방법 가능
public class BaseActivity extends AppCompatActivity {

        //현재 Activity를 어디서는 받아 사용하게 하려면 onResume와 onPause 메소드를 Override 해야한다.
        @Override
        protected void onResume() {

            super.onResume();
            Util.setCurrnetActivity(this);

        }

    @Override
    protected void onPause() {

        super.onPause();
        Util.setCurrnetActivity(this);

    }


}
