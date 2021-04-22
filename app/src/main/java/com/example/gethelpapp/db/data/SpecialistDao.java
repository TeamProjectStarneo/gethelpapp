package com.example.gethelpapp.db.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;

import java.util.List;


@Dao
public interface SpecialistDao {
    @Query("SELECT * FROM Specialist where specialistId= :specialistId and userId= :userId")
    Specialist getSpecialist(int specialistId,int userId);
    @Query("Select specialistId FROM Specialist where phone like :phone")
    public int getSpecialistIdFromPhone(String phone);
    @Query("Select name FROM Specialist where userId =:userId ")
    public List<String> getSpecialistNames(int userId);
    @Query("Select * FROM Specialist where name =:name and userId = :userId")

    public List<Specialist> getImagesFromName(String name,int userId);
    @Query("Select * FROM Specialist where userId= :UserId")
    public List<Specialist> getSpecialists(int UserId);
    @Insert
    void insert(Specialist specialist);

    @Update
    void update(Specialist specialist);

    @Delete
    void delete(Specialist specialist);

}
