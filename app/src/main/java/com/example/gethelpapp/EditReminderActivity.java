package com.example.gethelpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gethelpapp.db.data.ReminderDao;
import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.Reminder;
import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class EditReminderActivity extends AppCompatActivity {
    Button addButton;
    Button upButton;
    DatePicker datePicker;
    Spinner helpSpinner;
    String date;
    TimePicker timePicker;
    String name;
    Button downButton;
    //EditText emailField, phoneField, addressField, nameField, jobField;
    EditText whyLabel, whereLabel;
    private ReminderDao reminderDao;
    private SpecialistDao specialistDao;
    static int userId;
    static int reminderId;
    String time;
    String image;
    Inflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reminder);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getInt("UserId");
            reminderId = extras.getInt("reminderId");
        }
        Log.i("test", String.valueOf(userId));
        helpSpinner = findViewById(R.id.helperSpinner);


        timePicker = findViewById(R.id.timePicker);
        datePicker = findViewById(R.id.datePicker);
        addButton = findViewById(R.id.saveButton);
        upButton = findViewById(R.id.upButton);
        specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db")
                .allowMainThreadQueries().build().getSpecialistDao();
        reminderDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db")
                .allowMainThreadQueries().build().getReminderDao();
        List<String> specialists = new ArrayList<>();
        specialists = specialistDao.getSpecialistNames(userId);


        if(specialists.get(0).isEmpty()){
            finish();
        }

        Log.i("SpecialistName",specialists.get(0));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, specialists);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        helpSpinner.setAdapter(adapter);
        whyLabel = findViewById(R.id.whyField);
        whereLabel = findViewById(R.id.whereField);
        reminderDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getReminderDao();
        helpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));

                name = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();

                date = day + "/" + month + "/" + year;
                Log.d("date", date);

                String why = null;
                String where = null;
                String format;


                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                if (hour == 0) {
                    hour += 12;
                    format = "AM";
                } else if (hour == 12) {
                    format = "PM";
                } else if (hour > 12) {
                    hour -= 12;
                    format = "PM";
                } else {
                    format = "AM";
                }
                List<Specialist> specialistsImage = new ArrayList<>();
                specialistsImage = specialistDao.getImagesFromName(name,userId);
                Specialist specialist = specialistsImage.get(0);

                String images = specialist.getImage();
                Log.i("String",images);
                time = hour +":" + minute+ " " + format;
                Log.d("time", time);
                why = whyLabel.getText().toString().trim();
                where = whereLabel.getText().toString().trim();

                Log.i("Reminder id", String.valueOf(reminderId));
                Reminder reminder = reminderDao.getReminder(reminderId,userId);
                //og.i("Reminder doctor",reminder.getDoctorName());
                String time1 = reminder.getTime();
                String date1 = reminder.getDate();
                String name1 = reminder.getDoctorName();
                Log.i("Time",time);
                Log.i("Time",time1);

                if(name!=name1) {

                    reminder.setDoctorName(name);
                }

                if(date!=date1) {

                    reminder.setDate(date);
                }
                if(time1!=time) {

                    reminder.setTime(time);
                }
                if(why.length()>1) {

                    reminder.setWhy(why);
                }
                if(where.length()>1) {

                    reminder.setWhere(where);
                }
                if(images!=null) {

                    reminder.setImage(images);
                }
                reminderDao.update(reminder);
                setResult(RESULT_OK);
                finish();
            }
        });




        //specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries().build().getSpecialistDao();
    /*
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String userName = editTextUsername.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String name = nameField.getText().toString().trim();
                String phone = phoneField.getText().toString().trim();
                String address = addressField.getText().toString().trim();
                String job = jobField.getText().toString().trim();


                Specialist specialist = new Specialist(userId, name, phone, email, address, job);

                specialistDao.insert(specialist);
                Intent moveToMenu = new Intent(AddHelperActivity.this, MenuActivity.class);
                startActivity(moveToMenu);




            }

        });
    }
*/
    }

    public void loadSpinnerData(){

    }
    public void changeActivity(View v) {
        if(v.getId() == R.id.appHeader) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        if(v.getId() == R.id.backButton) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(v.getId() == R.id.saveButton) {
            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}