package com.example.gethelpapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InboxActivity extends ListActivity {
    // Temporary
    private String[] helpers = {"Dentist: Alice Miraci\nLast Message: Today at 1:00 PM", "Doctor: Dr Pepper\nLast Message: Yesterday at 2:00 AM"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

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
        if(view.getId() == R.id.menuButton || view.getId() == R.id.appHeader) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(view.getId() == R.id.remindersButton) {
            Intent i = new Intent(this, RemindersActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }

    // Temporary
    protected void onListItemClick(ListView lv, View v, int pos, long id) {
        Intent i = new Intent(this, MessageActivity.class);
        startActivity(i);
    }
}