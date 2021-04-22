package com.example.gethelpapp.db.model;


import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Specialist")
public class Specialist implements Serializable {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int specialistId;
    @ColumnInfo(name = "name")
    private String name;

    public int getUserId() {
        return userId;
    }



    @ColumnInfo(name = "image")
    private String image;
    private int userId;
    private String phone;
    private String email ;
    private String address;
    private String job;



    private String lastMessage;
    public Specialist(int userId,String image,String name,String phone,String email,String address,String job) {
        this.userId = userId;
        this.image = image;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.job = job;
    }



    @NonNull
    public int getUserid() {
        return userId;
    }

    public void setUserid(@NonNull int userId) {
        this.userId = userId;
    }

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(@NonNull int specialistId) {
        this.specialistId = specialistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
    @Override
    public String toString() {
        return "Specialist{" +
                "userid=" + userId +
                ", specialistId=" + specialistId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
