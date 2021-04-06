package com.example.gethelpapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void changeActivity(View view) {
        if(view.getId() == R.id.messageButton) {
            Intent i = new Intent(this, MessageActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(view.getId() == R.id.plannerButton) {
            Intent i = new Intent(this, PlannerActivity.class);
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
}