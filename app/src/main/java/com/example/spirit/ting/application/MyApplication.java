package com.example.spirit.ting.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

public class MyApplication extends Application {
    private static Context mContext;
    private static Handler mHandler;
    private static int mThreadId;

    public static Context getmContext() {
        return mContext;
    }

    public static int getmThreadId() {
        return mThreadId;
    }

    public static Handler getmHandler() {
        return mHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mHandler = new Handler();
        mThreadId = android.os.Process.myTid();
    }
}
