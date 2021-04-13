package com.example.gethelpapp.db.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;


@Database(entities = {User.class, Specialist.class}, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDao getUserDao();
    public abstract SpecialistDao getSpecialistDao();

}

