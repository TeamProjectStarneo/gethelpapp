package com.example.gethelpapp.db.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gethelpapp.db.model.Inbox;
import com.example.gethelpapp.db.model.Messages;

import java.util.List;

@Dao
public interface InboxDao
{
    @Query("SELECT * FROM Inbox where specialistId = :specialistId and userId= :userId")
    Inbox getInbox(int specialistId, int userId);

    @Query("Select * FROM Inbox where  userId= :UserId")
    public List<Inbox> getInboxs(int UserId);
    @Insert
    void insert(Inbox inbox);

    @Update
    void update(Inbox inbox);

    @Delete
    void delete(Inbox inbox);
}
