package com.example.gethelpapp.db.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gethelpapp.db.model.Messages;

import java.util.List;

@Dao
public interface MessageDao
{
    @Query("SELECT * FROM Messages where specialistId = :specialistId and userId= :userId")
    Messages getMessage(int specialistId, int userId);

    @Query("Select * FROM Messages where specialistId = :specialistId and userId= :UserId")
    public List<Messages> getMessages(int specialistId,int UserId);
    @Insert
    void insert(Messages messages);

    @Update
    void update(Messages messages);

    @Delete
    void delete(Messages messages);
}
