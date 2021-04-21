package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gethelpapp.db.data.ReminderDao;
import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.Reminder;
import com.example.gethelpapp.db.model.Specialist;

import java.util.ArrayList;
import java.util.List;

public class RemindersActivity extends AppCompatActivity {
    static int userid;
    private RecyclerView reminderRecyclerView;
    private ReminderRecyclerAdapter reminderRecyclerAdapter;
    private ReminderDao reminderDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            userid = extras.getInt("UserId");
        }
        reminderDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build()
                .getReminderDao();
        List<Reminder> reminders = new ArrayList<>();
        reminders = reminderDao.getReminders(userid);

        reminderRecyclerView = findViewById(R.id.remindersRecyclerView);
        reminderRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reminderRecyclerAdapter = new ReminderRecyclerAdapter(this,reminders );
        //when row  is pressed this function is executed.
        reminderRecyclerView.setAdapter(reminderRecyclerAdapter);
        ReminderRecyclerAdapter.addActionCallback(new ReminderRecyclerAdapter.ActionCallback() {

            @Override
            public void onLongClickListener(Reminder reminder) {
                Intent intent = new Intent(RemindersActivity.this, ReminderActivity.class);
                loadSpecialists();
                int reminderId = reminder.getReminderId();
                int userId = reminder.getUserId();
                intent.putExtra("ReminderId",reminderId);
                intent.putExtra("UserId",userId);
                startActivity(intent);

            }


        });


    }
    private void loadSpecialists() {
        reminderRecyclerAdapter.updateData(reminderDao.getReminders(userid));
    }


    public void changeActivity(View view) {
        if(view.getId() == R.id.messageButton) {
            Intent i = new Intent(this, InboxActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(view.getId() == R.id.addButton) {
            Log.i("test","test");
            Intent i = new Intent(this, AddReminderActivity.class);
            i.putExtra("UserId",userid);
            Log.i("test", String.valueOf(userid));
            int requestCode = 0;
            startActivityForResult(i,requestCode);
        }
        if(view.getId() == R.id.profileButton) {
            Intent i = new Intent(this, ProfileActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            i.putExtra("UserId",userid);
            startActivity(i);
        }
        if(view.getId() == R.id.settingsButton) {
            Intent i = new Intent(this, SettingsActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
        }
        if(view.getId() == R.id.menuButton) {
            Log.i("test","test");
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(view.getId() == R.id.editButton) {
            //Intent i = new Intent(this, EditReminderActivity.class);
            // startActivity(i);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_OK){
            loadSpecialists();
        }
        else{
            loadSpecialists();
        }
    }
}