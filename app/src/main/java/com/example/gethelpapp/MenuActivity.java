package com.example.gethelpapp;

import androidx.appcompat.app.AlertDialog;


import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuActivity extends ListActivity {
    // Temporary
    private String[] helpers = {"Dentist\nAlice Miraci", "Therapist\nKathleen Jones", "Nutritionist\nSally Claiss", "Doctor\nDr Pepper"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Temporary
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, helpers));
    }

    public void changeActivity(View view) {
        if(view.getId() == R.id.profileButton) {
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);
        }
        if(view.getId() == R.id.settingsButton) {
            Intent i = new Intent(this, SettingsActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(view.getId() == R.id.messageButton) {
            Intent i = new Intent(this, InboxActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(view.getId() == R.id.remindersButton) {
            Intent i = new Intent(this, RemindersActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }

    public void showEmergency(View v) {
        AlertDialog.Builder emergencyAlert = new AlertDialog.Builder(MenuActivity.this);
        emergencyAlert.setTitle("Emergency");
        emergencyAlert.setMessage("Contact Garda and SJOG?");
        emergencyAlert.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MenuActivity.this, "Done! Calling the Garda now to assist", Toast.LENGTH_LONG).show();
            }
        });
        emergencyAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MenuActivity.this, "Ok buddy", Toast.LENGTH_LONG).show();
            }
        });
        emergencyAlert.create().show();
    }

    // Temporary
    protected void onListItemClick(ListView lv, View v, int pos, long id) {
        Intent i = new Intent(this, HelperActivity.class);
        startActivity(i);
    }
}