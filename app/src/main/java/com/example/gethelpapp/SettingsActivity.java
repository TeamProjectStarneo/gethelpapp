package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void changeActivity(View v) {
        if(v.getId() == R.id.profileButton) {
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);
        }
        if(v.getId() == R.id.getHelpButton || v.getId() == R.id.menuButton) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        if(v.getId() == R.id.themeButton) {
            Intent i = new Intent(this, ThemeActivity.class);
            startActivity(i);
        }
        if(v.getId() == R.id.helpButton) {
            Intent i = new Intent(this, HelpActivity.class);
            startActivity(i);
        }
        if(v.getId() == R.id.messageButton) {
            Intent i = new Intent(this, InboxActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(v.getId() == R.id.remindersButton) {
            Intent i = new Intent(this, RemindersActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}