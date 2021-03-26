package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    Button registerButton;
    EditText registerEmail, registerPassword,registerConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerEmail = (EditText) findViewById(R.id.emailFieldRegister);
        registerPassword = (EditText) findViewById(R.id.passwordFieldRegister);
        registerConfirmPassword = (EditText) findViewById(R.id.passwordFieldRegister2);

        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(this);

    }

    public void changeActivity(View view) {
        if(view.getId() == R.id.registerButton) {
            Intent i = new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.registerButton:
                break;
        }
    }
}