package com.example.gethelpapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.gethelpapp.db.data.InboxDao;
import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.Inbox;
import com.example.gethelpapp.db.model.Specialist;

import java.util.List;

public class InboxActivity extends AppCompatActivity {

    static int userid;
    static int specialistId;
    List<Inbox> inbox;
    private RecyclerView inboxRecyclerView;
    private InboxRecyclerAdapter inboxRecyclerAdapter;
    InboxDao inboxDao;
    SpecialistDao specialistDao;
    Specialist specialist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userid = extras.getInt("UserId");

        }
        specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getSpecialistDao();
        inboxDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getInboxDao();
        inbox = inboxDao.getInboxs(userid);
        inboxRecyclerView = findViewById(R.id.inbox_recycler);
        inboxRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        inboxRecyclerAdapter = new InboxRecyclerAdapter(this, inbox);
        inboxRecyclerView.setAdapter(inboxRecyclerAdapter);
        if (extras != null) {
            userid = extras.getInt("UserId");
            specialistId = extras.getInt("specialistId");
        }
        inboxRecyclerAdapter.addActionCallback(new InboxRecyclerAdapter.ActionCallback() {

            @Override
            public void onLongClickListener(Inbox inbox) {
                Intent intent = new Intent(InboxActivity.this, MessageActivity.class);
                int specialistId = inbox.getSpecialistId();
                int userId = inbox.getUserid();

                specialist = specialistDao.getSpecialist(specialistId,userId);
                intent.putExtra("SpecialistId",specialistId);
                intent.putExtra("UserId",userId);
                intent.putExtra("Name",specialist.getName());
                intent.putExtra("Phone",specialist.getPhone());
                startActivity(intent);

            }


        });
    }

    public void changeActivity(View view) {
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
        if(view.getId() == R.id.menuButton || view.getId() == R.id.appHeader) {
            Intent i = new Intent(this, MenuActivity.class);

            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
        }
        if(view.getId() == R.id.remindersButton) {
            Intent i = new Intent(this, RemindersActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            i.putExtra("UserId",userid);
            Log.i("test", String.valueOf(userid));
            startActivity(i);
        }
    }


}