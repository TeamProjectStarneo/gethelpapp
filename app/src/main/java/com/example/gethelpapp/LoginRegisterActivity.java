package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

public class LoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Ytheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);

    }
    @Override
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();
        theme.applyStyle(R.style.Ytheme, true);
        return theme;
    }
    public void changeActivity(View view) {
        if(view.getId() == R.id.loginButton) {

            Intent i = new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
        } if(view.getId() == R.id.signUpButton) {

            Intent i = new Intent(this, RegisterActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
        } if(view.getId() ==R.id.helpButton) {

            Intent i = new Intent(this, HelpActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
        }
    }
}