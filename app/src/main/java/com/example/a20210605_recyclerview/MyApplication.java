package com.example.a20210605_recyclerview;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Util.init(this);
    }

}
