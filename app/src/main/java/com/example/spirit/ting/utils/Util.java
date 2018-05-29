package com.example.spirit.ting.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.spirit.ting.application.MyApplication;

public class Util {
    public static Context getContext() {
        return MyApplication.getmContext();
    }

    public static Bitmap scaleBitmap(int id) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getContext().getResources(), id, options);
        float outWidth = options.outWidth;
        float outHeight = options.outHeight;

        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), id);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        System.out.println(outWidth + ", " + width);

        int sampleSize = 2;
        if (outHeight > height || outWidth > width) {
            if (outWidth > outHeight) {
                sampleSize = Math.round(outHeight / height);
            } else {
                sampleSize = Math.round(outWidth / width);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeResource(getContext().getResources(), id, options);
    }

    public static float getStatusBarHeight() {
        int identifier = getContext().getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        return getContext().getResources().getDimension(identifier);
    }

    public static Bitmap getBitmap(int id) {
        return BitmapFactory.decodeResource(getContext().getResources(), id);
    }
}
