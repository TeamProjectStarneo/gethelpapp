package com.example.gethelpapp.db.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gethelpapp.db.model.Reminder;
import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;

import java.util.List;


@Dao
public interface ReminderDao {
    @Query("SELECT * FROM Reminder where reminderId= :reminderId and userId= :userId")
    Reminder getReminder(int reminderId,int userId);

    @Query("Select * FROM Reminder where userId= :UserId")
    public List<Reminder> getReminders(int UserId);

    @Query("Select * FROM Reminder where doctorName= :doctorName")
    public List<Reminder> getRemindersbySpecialist(String doctorName);
    @Insert
    void insert(Reminder reminder);

    @Update
    void update(Reminder reminder);

    @Delete
    void delete(Reminder reminder);

}
