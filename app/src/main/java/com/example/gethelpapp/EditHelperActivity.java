package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;

public class EditHelperActivity extends AppCompatActivity {

    TextView nameField,jobField,emailField,phoneField,addressField;
    Button saveButton;
    private SpecialistDao specialistDao;
    static int userId;
    static int specialistId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_helper);
        Bundle extras = getIntent().getExtras();

        if(extras !=null){
            userId = extras.getInt("UserId");
            specialistId = extras.getInt("SpecialistId");
        }

        nameField = (EditText) findViewById(R.id.nameField);
        emailField = (EditText) findViewById(R.id.emailField);
        addressField = (EditText) findViewById(R.id.addressField);
        phoneField = (EditText) findViewById(R.id.phoneField);

        saveButton = (Button) findViewById(R.id.saveButton);
        specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getSpecialistDao();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Specialist specialist = specialistDao.getSpecialist(specialistId,userId);
                //    String email = emailField.getText().toString().trim();
                //   String password = registerPassword.getText().toString().trim();
                //  String passwordConf = registerConfirmPassword.getText().toString().trim();

                String name = nameField.getText().toString();
                String email = emailField.getText().toString().trim();
                String address = addressField.getText().toString().trim();
                String phone = phoneField.getText().toString().trim();
                if(name.length()>1) {

                    specialist.setName(name);
                }
                if(email.length()>1) {

                    specialist.setEmail(name);
                }
                if(address.length()>1) {

                    specialist.setAddress(name);
                }
                if(phone.length()>1) {

                    specialist.setPhone(name);
                }


                Log.d("test","test");

                //Log.d("test",user.getEmail());
                specialistDao.update(specialist);
                //userDao.insert(user);
                setResult(RESULT_OK);
                finish();


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
        if(v.getId() == R.id.saveButton) {
            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}