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
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gethelpapp.db.data.ReminderDao;
import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.Reminder;
import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ReminderActivity extends AppCompatActivity {
    static int userId;
    static int specialistId;
    static int reminderId;
    TextView datelabel,timelabel,wherelabel,whylabel, appointmentLabel;
    ImageView helperImage;
    private ReminderDao reminderDao;
    String image;
    private SpecialistDao specialistDao;
    List<Specialist> specialistsImage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Bundle extras = getIntent().getExtras();

        if(extras !=null){
            userId = extras.getInt("UserId");
           // specialistId = extras.getInt("SpecialistId");
            reminderId = extras.getInt("ReminderId");
        }
        reminderDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getReminderDao();

        specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db")
                .allowMainThreadQueries().build().getSpecialistDao();
        refreshDetails();
    }

    private void refreshDetails() {
        Reminder reminder = reminderDao.getReminder(reminderId,userId);
        datelabel = (TextView) findViewById(R.id.dateLabel);
        timelabel = (TextView) findViewById(R.id.timeLabel);
        whylabel = (TextView) findViewById(R.id.whyLabel);
        wherelabel = (TextView) findViewById(R.id.whereLabel);
        appointmentLabel=(TextView) findViewById(R.id.appointmentLabel);
        helperImage = (ImageView) findViewById(R.id.helperImage);




        String date = reminder.getDate();
        String time = reminder.getTime();
        String why = reminder.getWhy();
        String where = reminder.getWhere();
        String doctorName = reminder.getDoctorName();
        specialistsImage = specialistDao.getImagesFromName(doctorName,userId);
        Specialist specialist = specialistsImage.get(0);

        String images = specialist.getImage();
        Log.d("This is path to real image",images);
        datelabel.setText("Date: " + date);
        timelabel.setText("Time: " +time);
        whylabel.setText("Date: "+why);
        wherelabel.setText("Where: " +where);
        appointmentLabel.setText("Appointment with : " + doctorName);


       // String image = specialist.getImage();
       loadImageFromStorage(images);
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
        if(v.getId() == R.id.backButton || v.getId() == R.id.remindersButton) {
            finish();
        }
        if(v.getId() == R.id.menuButton) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(v.getId() == R.id.appHeader) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(v.getId() == R.id.editButton) {
            Intent i = new Intent(this, EditReminderActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            i.putExtra("UserId",userId);
            i.putExtra("reminderId",reminderId);
            startActivityForResult(i,0);
        }
        if(v.getId() == R.id.messageButton) {
            Intent i = new Intent(this, InboxActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(v.getId() == R.id.backButton2) {

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