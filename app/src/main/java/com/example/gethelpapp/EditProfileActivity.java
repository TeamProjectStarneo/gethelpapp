package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gethelpapp.db.data.UserDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.User;

public class EditProfileActivity extends AppCompatActivity {

    EditText nameField,emailField,addressField,phoneField;
    Button saveButton;
    static int userid;
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            userid = extras.getInt("UserId");
        }
        nameField = (EditText) findViewById(R.id.nameField);
        emailField = (EditText) findViewById(R.id.emailField);
        addressField = (EditText) findViewById(R.id.addressField);
        phoneField = (EditText) findViewById(R.id.phoneField);

        saveButton = (Button) findViewById(R.id.saveButton);

        userDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getUserDao();



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = userDao.retrieveUserDetails(userid);
            //    String email = emailField.getText().toString().trim();
             //   String password = registerPassword.getText().toString().trim();
              //  String passwordConf = registerConfirmPassword.getText().toString().trim();

                String name = nameField.getText().toString();
                String email = emailField.getText().toString().trim();
                String address = addressField.getText().toString().trim();
                String phone = phoneField.getText().toString().trim();
                if(name.length()>1) {

                    user.setName(name);
                }
                if(email.length()>1) {

                    user.setEmail(email);
                }
                if(address.length()>1) {

                    user.setAddress(address);
                }
                if(phone.length()>1) {

                    user.setPhone(phone);
                }


                Log.d("test","test");

                Log.d("test",user.getEmail());
                userDao.update(user);
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