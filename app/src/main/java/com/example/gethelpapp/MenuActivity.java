package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void changeActivity(View view) {
        if(view.getId() == R.id.messageButton) {
            Intent msgI = new Intent(this, MessageActivity.class);
            startActivity(msgI);
        }
        /*
        if(view.getId() == R.id.menuButton) {
            Intent mnI = new Intent(this, MenuActivity.class);
            startActivity(mnI);
        }*/
        if(view.getId() == R.id.plannerButton) {
            Intent pI = new Intent(this, PlannerActivity.class);
            startActivity(pI);
        }
    }
}