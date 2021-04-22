package com.example.gethelpapp.db.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.gethelpapp.db.model.Messages;
import com.example.gethelpapp.db.model.Reminder;
import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;
import com.example.gethelpapp.db.typeconverters.DateTypeConverter;


@Database(entities = {User.class, Specialist.class, Reminder.class, Messages.class}, version = 8, exportSchema = false)
@TypeConverters({DateTypeConverter.class})
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDao getUserDao();
    public abstract SpecialistDao getSpecialistDao();
    public abstract ReminderDao getReminderDao();
    public abstract MessageDao getMessageDao();

}

