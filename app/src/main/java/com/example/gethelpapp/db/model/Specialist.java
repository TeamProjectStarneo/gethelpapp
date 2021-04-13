package com.example.gethelpapp.db.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Specialist implements Serializable {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int specialistId;
    private String name;

    public int getUserId() {
        return userId;
    }

    private int userId;
    private String phone;
    private String email ;
    private String address;
    private String job;

    public Specialist(int userId,String name,String phone,String email,String address,String job) {
        this.userId = userId;
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
