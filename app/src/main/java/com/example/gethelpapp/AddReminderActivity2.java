package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gethelpapp.db.data.ReminderDao;
import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class AddReminderActivity2 extends AppCompatActivity {
    Button addButton;
    Button upButton;
    DatePicker datePicker;
    Spinner helpSpinner;
    String date;
    Button downButton;
    //EditText emailField, phoneField, addressField, nameField, jobField;
    private ReminderDao reminderDao;
    private SpecialistDao specialistDao;
    static int userId;
    Inflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getInt("UserId");
        }
        Log.i("test", String.valueOf(userId));
        helpSpinner = findViewById(R.id.helperSpinner);

        datePicker = findViewById(R.id.datePicker);
        addButton = findViewById(R.id.saveButton);
        upButton = findViewById(R.id.upButton);

        //setContentView(R.layout.activity_add_reminder2);
        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                Intent i = new Intent(AddReminderActivity2.this, AddReminderActivity.class);
                startActivity(i);
            }
        });


        //specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries().build().getSpecialistDao();
    /*
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String userName = editTextUsername.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String name = nameField.getText().toString().trim();
                String phone = phoneField.getText().toString().trim();
                String address = addressField.getText().toString().trim();
                String job = jobField.getText().toString().trim();


                Specialist specialist = new Specialist(userId, name, phone, email, address, job);

                specialistDao.insert(specialist);
                Intent moveToMenu = new Intent(AddHelperActivity.this, MenuActivity.class);
                startActivity(moveToMenu);




            }

        });
    }
*/
    }


    public void changeActivity(View v) {
        if(v.getId() == R.id.appHeader) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        if(v.getId() == R.id.backButton) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(v.getId() == R.id.saveButton) {
            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}