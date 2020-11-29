package com.airticket.client.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.airticket.client.R;

/**
 * class desc :
 *
 * @author :
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }).start();
    }
}
