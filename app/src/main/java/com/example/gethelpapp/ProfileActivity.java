package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gethelpapp.db.data.UserDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.User;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProfileActivity extends AppCompatActivity {
    static int userid;
    TextView nameLabel,emailLabel,addressLabel,phoneLabel;
    private UserDao userDao;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    ImageView image;
    Uri selectedImageUri;
    String imagepath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            userid = extras.getInt("UserId");
        }
        userid = Global.userid;
        userDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getUserDao();
        image = (ImageView) findViewById(R.id.profileImage);

        refreshDetails();



    }
    public void refreshDetails(){
        User user = userDao.retrieveUserDetails(userid);
        nameLabel = (TextView) findViewById(R.id.nameLabel3);
        emailLabel = (TextView) findViewById(R.id.emailLabel3);
        addressLabel = (TextView) findViewById(R.id.addressLabel3);
        phoneLabel = (TextView) findViewById(R.id.phoneLabel3);
        image = (ImageView) findViewById(R.id.profileImage);
        String name = user.getName();
        String email = user.getEmail();
        String address = user.getAddress();
        String phone = user.getPhone();
        String image = user.getImage();
        nameLabel.setText(name);
        emailLabel.setText(email);
        addressLabel.setText(address);
        phoneLabel.setText(phone);
        //Log.i("Image from user",image);
        if(image!=null) {
            loadImageFromStorage(image);
       }
    }
    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            image.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

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