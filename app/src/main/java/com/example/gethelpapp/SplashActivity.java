package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch(Exception e) {
                } finally {
                    Intent i = new Intent(SplashActivity.this, LoginRegisterActivity.class);
                    startActivity(i);
                }
            }
        };
        t.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}