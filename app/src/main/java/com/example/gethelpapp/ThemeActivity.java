package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class ThemeActivity extends AppCompatActivity {

    Switch switchTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        switchTheme = (Switch) findViewById(R.id.darkModeSwitch);
        final Boolean switchstate =switchTheme.isChecked();

        Button back= (Button) findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchTheme.isChecked()){
                    switchTheme.setChecked(true);
                }
                if(!switchTheme.isChecked()){
                    switchTheme.setChecked(false);

                }
            }


            });
    }





    public void changeActivity(View v) {
        if(v.getId() == R.id.appHeader) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(v.getId() == R.id.backButton) {
            finish();
        }
    }
}