package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
    }

    public void changeActivity(View view) {
        if(view.getId() == R.id.loginButton) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        } if(view.getId() == R.id.signUpButton) {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        } if(view.getId() ==R.id.helpButton) {
            Intent i = new Intent(this, HelpActivity.class);
            startActivity(i);
        }
    }
}