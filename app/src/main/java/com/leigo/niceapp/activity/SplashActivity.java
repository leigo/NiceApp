package com.leigo.niceapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.leigo.niceapp.R;

/**
 * Created by Administrator on 2015/3/20.
 */
public class SplashActivity extends Activity {

    private static final int SPLASH_DISPLAY_TIME = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }
}
