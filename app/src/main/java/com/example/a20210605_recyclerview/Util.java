package com.example.a20210605_recyclerview;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

public class Util {

    private static Application application;

    public static void init(Application application) {
        Util.application = application;

    }

    public static float dipToPixels(float borderRadius) {
        DisplayMetrics metrics = application.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, borderRadius, metrics);
    }

    public static void loadImageOn(String imgUrl, ImageView imageView, int borderRadius) {

        if (borderRadius > 0) {

            Glide.with(application)
                    .load(imgUrl)
                    .transform(new CenterCrop(), new RoundedCorners((int)Util.dipToPixels(borderRadius)))
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
        loadImageOn(imgUrl, imageView, 0 );

    }

}
