package com.example.gethelpapp.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.gethelpapp.db.UserAccount;

import java.util.List;

@Dao
public interface AccountDAO {

    @Query("SELECT * FROM user")
    List<UserAccount> getAll();

    @Query("SELECT * FROM user where email LIKE  :email AND password LIKE :password")
    UserAccount findByName(String email, String password);


    @Query("SELECT * FROM user where email LIKE  :email")
    UserAccount findByEmail(String email);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Insert
    void insertAll(UserAccount... users);

    @Delete
    void delete(UserAccount user);
}