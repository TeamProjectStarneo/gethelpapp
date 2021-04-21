package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HelperActivity extends AppCompatActivity {
    static int userId;
    static int specialistId;
    TextView nameLabel,emailLabel,addressLabel,phoneLabel,jobLabel;
    ImageView helperImage;
    private SpecialistDao specialistDao;
    static String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);

        Bundle extras = getIntent().getExtras();

        if(extras !=null){
            userId = extras.getInt("UserId");
            specialistId = extras.getInt("SpecialistId");
        }
        specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getSpecialistDao();
        refreshDetails();
    }

    private void refreshDetails() {
        Specialist specialist = specialistDao.getSpecialist(specialistId,userId);
        nameLabel = (TextView) findViewById(R.id.nameLabel2);
        emailLabel = (TextView) findViewById(R.id.emailLabel2);
        addressLabel = (TextView) findViewById(R.id.addressLabel2);
        phoneLabel = (TextView) findViewById(R.id.phoneLabel2);
        jobLabel= (TextView) findViewById(R.id.jobLabel2);
        helperImage = (ImageView) findViewById(R.id.helperImage);


        name = specialist.getName();
        String email = specialist.getEmail();
        String address = specialist.getAddress();
        String phone = specialist.getPhone();
        String job = specialist.getJob();
        nameLabel.setText(name);
        emailLabel.setText(email);
        addressLabel.setText(address);
        phoneLabel.setText(phone);
        jobLabel.setText(job);
        String image = specialist.getImage();
        Log.i("This is path to Images",image);
        loadImageFromStorage(image);

    }
    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            helperImage.setImageBitmap(b);
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
        if(v.getId() == R.id.messageButton) {
            Intent i = new Intent(this, MessageActivity.class);
            i.putExtra("Name",name);
            startActivity(i);
        }
        if(v.getId() == R.id.editButton) {
            Intent i = new Intent(this, EditHelperActivity.class);
            i.putExtra("UserId",userId);
            i.putExtra("SpecialistId",specialistId);
            Log.i("userId", String.valueOf(userId));
            Log.i("specialistId", String.valueOf(specialistId));
            int requestCode = 0;
            startActivityForResult(i,requestCode);
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