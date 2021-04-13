package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EditReminderActivity extends AppCompatActivity {
    // Temporary
    private String helpers[] = {"Dentist: Alice Miraci", "Therapist: Kathleen Jones", "Nutritionist: Sally Claiss", "Doctor: Dr Pepper"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reminder);

        // Temporary
        Spinner helperSpinner = findViewById(R.id.helperSpinner);
        ArrayAdapter<String> helperSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, helpers);
        helperSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        helperSpinner.setAdapter(helperSpinnerAdapter);
    }

    public void changeActivity(View v) {
        if(v.getId() == R.id.backButton) {
            finish();
        }
        if(v.getId() == R.id.appHeader) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(v.getId() == R.id.downButton) {
            Intent i = new Intent(this, EditReminderPart2Activity.class);
            startActivity(i);
        }
    }
}