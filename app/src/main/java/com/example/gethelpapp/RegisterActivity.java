package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.User;
import com.example.gethelpapp.db.data.UserDao;
public class RegisterActivity extends AppCompatActivity  {


    Button registerButton;
    EditText emailField, registerPassword, registerConfirmPassword;

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        emailField = (EditText) findViewById(R.id.emailField);
        registerPassword = (EditText) findViewById(R.id.passwordField);
        registerConfirmPassword = (EditText) findViewById(R.id.passwordField2);

        registerButton = (Button) findViewById(R.id.signUpButton);


        userDao = Room.databaseBuilder(this, UserDataBase.class, "mi-database.db").allowMainThreadQueries()
                .build().getUserDao();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String userName = editTextUsername.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String password = registerPassword.getText().toString().trim();
                String passwordConf = registerConfirmPassword.getText().toString().trim();

                if (password.equals(passwordConf)) {
                    User user = new User("Name", password, email);
                    userDao.insert(user);
                    Intent moveToLogin = new Intent(RegisterActivity.this, LoginRegisterActivity.class);
                    startActivity(moveToLogin);

                } else {
                    Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}

