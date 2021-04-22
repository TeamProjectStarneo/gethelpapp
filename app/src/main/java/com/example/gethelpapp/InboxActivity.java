package com.example.gethelpapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gethelpapp.db.model.Specialist;

import java.util.List;

public class InboxActivity extends AppCompatActivity {

    static int userid;
    static int specialistId;
    List<Specialist> specialists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            userid = extras.getInt("UserId");
            specialistId = extras.getInt("specialistId");
        }
    }

    public void changeActivity(View view) {
        if(view.getId() == R.id.profileButton) {
            Intent i = new Intent(this, ProfileActivity.class);
            i.putExtra("UserId",userid);
            startActivity(i);
        }
        if(view.getId() == R.id.settingsButton) {
            Intent i = new Intent(this, SettingsActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(view.getId() == R.id.menuButton || view.getId() == R.id.appHeader) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(view.getId() == R.id.remindersButton) {
            Intent i = new Intent(this, RemindersActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            i.putExtra("UserId",userid);
            Log.i("test", String.valueOf(userid));
            startActivity(i);
        }
    }


}