package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {
    TextView helperNameView;
    static String name;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            name = extras.getString("Name");
        }
        helperNameView = (TextView) findViewById(R.id.helperNameView);

        helperNameView.setText(name);
    }

    public void changeActivity(View v) {
        if(v.getId() == R.id.backButton) {
            finish();
        }
    }
}