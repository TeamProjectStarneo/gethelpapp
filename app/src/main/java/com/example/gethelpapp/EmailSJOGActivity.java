package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class EmailSJOGActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailsjog);
    }

    public void changeActivity(View v) {
        if(v.getId() == R.id.backButton) {
            finish();
        }
    }
}