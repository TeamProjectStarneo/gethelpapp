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

public class LoginActivity extends AppCompatActivity {


    Button loginButton;
    EditText loginEmail,loginPassword;
    UserDataBase dataBase;
    UserDao db;
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = (EditText) findViewById(R.id.emailField);
        loginPassword = (EditText) findViewById(R.id.passwordField);
        loginButton = (Button) findViewById(R.id.loginButton);

        dataBase = Room.databaseBuilder(this, UserDataBase.class, "atabase.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        db = dataBase.getUserDao();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                User user = db.getUser(email, password);

                if (user != null) {
                    int id = user.getId();
                    Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                    i.putExtra("User", user);
                    i.putExtra("userId",id);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }






}