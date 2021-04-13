package com.example.gethelpapp.db.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;

import java.util.List;


@Dao
public interface SpecialistDao {
    @Query("SELECT * FROM Specialist where email= :email and userId= :userId")
    Specialist getSpecialist(String email,int userId);

    @Query("Select * FROM Specialist where userId= :UserId")
    public List<Specialist> getSpecialists(int UserId);
    @Insert
    void insert(Specialist specialist);

    @Update
    void update(Specialist specialist);

    @Delete
    void delete(Specialist specialist);

}
