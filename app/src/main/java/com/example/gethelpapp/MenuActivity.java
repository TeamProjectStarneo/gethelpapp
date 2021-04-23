package com.example.gethelpapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.Specialist;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    static int userid;
    private RecyclerView helperRecyclerView;
    private HelperRecyclerAdapter helperRecyclerAdapter;
    private SpecialistDao specialistDao;
    List<Specialist> specialists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //int theme = getThemeFromPreferences(); // R.style.AppTheme_RED


        setContentView(R.layout.activity_menu);
        Bundle extras = getIntent().getExtras();

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, 1000);
        }
        if (extras != null) {
            userid = extras.getInt("userId");
        }
        Global.userid = userid;
        specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build()
                .getSpecialistDao();
        specialists = new ArrayList<>();
        specialists = specialistDao.getSpecialists(userid);

        helperRecyclerView = findViewById(R.id.helperRecyclerView);
        helperRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        helperRecyclerAdapter = new HelperRecyclerAdapter(this, specialists);
        //when row  is pressed this function is executed.
        helperRecyclerView.setAdapter(helperRecyclerAdapter);
        HelperRecyclerAdapter.addActionCallback(new HelperRecyclerAdapter.ActionCallback() {

            @Override
            public void onLongClickListener(Specialist specialist) {
                Intent intent = new Intent(MenuActivity.this, HelperActivity.class);
                loadSpecialists();
                int specialistId = specialist.getSpecialistId();
                int userId = specialist.getUserId();
                intent.putExtra("SpecialistId",specialistId);
                intent.putExtra("UserId",userId);
                startActivity(intent);

            }


        });


    }
    private void loadSpecialists() {
        helperRecyclerAdapter.updateData(specialistDao.getSpecialists(userid));
    }
    public void changeActivity(View view) {

        if(view.getId() == R.id.addButton) {
            Intent i = new Intent(this, AddHelperActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            i.putExtra("UserId",userid);
            Log.i("test", String.valueOf(userid));
            startActivity(i);
        }
        if(view.getId() == R.id.messageButton) {
            Intent i = new Intent(this, InboxActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            i.putExtra("UserId",userid);
            //i.putExtra("SpecialistId",specialistId);
            Log.i("test", String.valueOf(userid));
            startActivity(i);
        }
        if(view.getId() == R.id.remindersButton) {
            Intent i = new Intent(this, RemindersActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            i.putExtra("UserId",userid);
            Log.i("test", String.valueOf(userid));
            startActivity(i);
        }
        if(view.getId() == R.id.profileButton) {
            Intent i = new Intent(this, ProfileActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            i.putExtra("UserId",userid);
            startActivity(i);
        }
        if(view.getId() == R.id.messageButton) {
            Intent i = new Intent(this, InboxActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            Log.i("test", String.valueOf(userid));
            i.putExtra("UserId",userid);
            startActivity(i);
        }
        if(view.getId() == R.id.settingsButton) {
            Intent i = new Intent(this, SettingsActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
            if(requestCode==1000){
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"permission granted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"permission not granted",Toast.LENGTH_SHORT).show();
                }
            }
        }
    public void showEmergency(View v) {
        AlertDialog.Builder emergencyAlert = new AlertDialog.Builder(MenuActivity.this);
        emergencyAlert.setTitle("Emergency");
        emergencyAlert.setMessage("Contact Garda and SJOG?");
        emergencyAlert.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MenuActivity.this, "Done! Calling the Garda now to assist", Toast.LENGTH_LONG).show();
            }
        });
        emergencyAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MenuActivity.this, "Ok buddy", Toast.LENGTH_LONG).show();
            }
        });
        emergencyAlert.create().show();
    }
}