package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gethelpapp.db.AppDatabase;
import com.example.gethelpapp.db.UserAccount;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private UserAccount user;
    private AppDatabase appDatabase;
    Button registerButton;
    EditText emailField, registerPassword,registerConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailField = (EditText) findViewById(R.id.emailField);
        registerPassword = (EditText) findViewById(R.id.passwordField);
        //registerConfirmPassword = (EditText) findViewById(R.id.confirmpasswordfield);

        registerButton = (Button) findViewById(R.id.signUpButton);

        registerButton.setOnClickListener(this);

    }

    public void changeActivity(View view) {
        // if(view.getId() == R.id.registerButton) {
        //     Intent i = new Intent(this, LoginActivity.class);
        //   i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //     startActivity(i);
    }
    // }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signUpButton:
                postData();
                break;
        }
    }

    private void postData() {
        UserAccount user = appDatabase.accountDAO().findByEmail(emailField.getText().toString().trim());
        if (user != null && !user.getEmail().equalsIgnoreCase(emailField.getText().toString().trim()))
        {
            //post data

        }
        else{
            //show alert "email already in use"
        }
    }
}