package com.example.spirit.ting.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.spirit.ting.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {
    private ImageView ivAnim;
    private Timer timer;
    private AnimationDrawable drawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initUI();
    }

    private void initUI() {
        drawable = (AnimationDrawable) getResources().getDrawable(R.drawable
                .splash_anim);

        ivAnim.setBackgroundDrawable(drawable);
        drawable.setOneShot(false);
        drawable.start();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }

    private void initView() {
        ivAnim = findViewById(R.id.iv_anim);
    }

    @Override
    protected void onStop() {
        super.onStop();
        drawable.stop();
        timer.cancel();
    }
}
