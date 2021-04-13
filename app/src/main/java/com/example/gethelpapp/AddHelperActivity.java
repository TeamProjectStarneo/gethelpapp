package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;

public class AddHelperActivity extends AppCompatActivity {
    Button saveButton;
    EditText emailField, phoneField, addressField, nameField, jobField;
    private SpecialistDao specialistDao;
    static int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_helper);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getInt("UserId");
        }
        Log.i("test", String.valueOf(userId));
        nameField = (EditText) findViewById(R.id.nameField1);
        emailField = (EditText) findViewById(R.id.emailField1);
        jobField = (EditText) findViewById(R.id.jobField1);
        addressField = (EditText) findViewById(R.id.addressField1);
        phoneField = (EditText) findViewById(R.id.phoneField1);

        saveButton = (Button) findViewById(R.id.addButton1);

        specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries().build().getSpecialistDao();

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