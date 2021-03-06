package com.example.a20210605_recyclerview;

import android.app.Application;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.a20210605_recyclerview.ui.BaseActivity;

import java.util.concurrent.Delayed;

public class Util {

    private static Application application;
    private static BaseActivity currentActivity;

    public static void init(Application application) {
        Util.application = application;

    }

    public static void log(String msg) {
        Log.d("AA1", msg);
    }

    public static float dipToPixels(float borderRadius) {
        DisplayMetrics metrics = application.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, borderRadius, metrics);
    }

    public static void loadImageOn(String imgUrl, ImageView imageView, int borderRadius) {

        if (borderRadius > 0) {

            Glide.with(application)
                    .load(imgUrl)
                    .transform(new CenterCrop(), new RoundedCorners((int) Util.dipToPixels(borderRadius)))
                    .into(imageView);

        } else {

            Glide.with(application)
                    .load(imgUrl)
                    .into(imageView);

        }

    }

    //Overload 된 메소드
    public static void loadImageOn(String imgUrl, ImageView imageView) {

        //★Overload 된 메서드도 다른 Overload된 메소드에서 호출 가능!!!!
        loadImageOn(imgUrl, imageView, 0);

    }

    // 몇초 뒤에 실행시켜주는 쓰레드 구문
    public static void setTimeout(Runnable r, int delay) {

        new android.os.Handler(Looper.getMainLooper()).postDelayed(r, delay);

    }

    //CurrnetActivity를 사용하기 위한 setter
    public static void setCurrnetActivity(BaseActivity baseActivity) {

        Util.currentActivity = baseActivity;
    }

    //CurrnetActivity를 사용하기 위한 getter
    public static <T> T getCurrentActivity() {

       return (T)Util.currentActivity;
    }

}
