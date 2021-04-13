package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gethelpapp.db.data.UserDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.User;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {
    static int userid;
    TextView nameLabel,emailLabel,addressLabel,phoneLabel;
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            userid = extras.getInt("UserId");
        }
        userDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getUserDao();

        refreshDetails();



    }
    public void refreshDetails(){
        User user = userDao.retrieveUserDetails(userid);
        nameLabel = (TextView) findViewById(R.id.nameLabel3);
        emailLabel = (TextView) findViewById(R.id.emailLabel3);
        addressLabel = (TextView) findViewById(R.id.addressLabel3);
        phoneLabel = (TextView) findViewById(R.id.phoneLabel3);

        String name = user.getName();
        String email = user.getEmail();
        String address = user.getAddress();
        String phone = user.getPhone();
        nameLabel.setText(name);
        emailLabel.setText(email);
        addressLabel.setText(address);
        phoneLabel.setText(phone);
    }

    public void changeActivity(View v) {
        if(v.getId() == R.id.appHeader || v.getId() == R.id.backButton) {
            finish();
        }
        if(v.getId() == R.id.editButton) {
            Intent i = new Intent(this, EditProfileActivity.class);
            i.putExtra("UserId",userid);

            int requestCode = 0;
            startActivityForResult(i,requestCode);
        }
        if(v.getId() == R.id.backButton) {

            super.onBackPressed();

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_OK){
            refreshDetails();
        }
        else{
            refreshDetails();
        }
    }
}