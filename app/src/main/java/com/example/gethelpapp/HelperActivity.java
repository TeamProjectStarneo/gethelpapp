package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HelperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);
    }

    public void changeActivity(View v) {
        if(v.getId() == R.id.appHeader || v.getId() == R.id.backButton) {
            finish();
        }
        if(v.getId() == R.id.editButton) {
            Intent i = new Intent(this, EditHelperActivity.class);
            startActivity(i);
        }
        if(v.getId() == R.id.messageButton) {
            Intent i = new Intent(this, MessageActivity.class);
            startActivity(i);
        }
    }
}