package com.example.gethelpapp.db.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import java.util.Date;
@Entity
public class Reminder implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int reminderId;
    private int userId;
    private String doctorName;



    public String date;
    private String time ;
    private String where;
    private String why;

    public Reminder( int userId, String doctorName, String date, String time, String where, String why) {

        this.userId = userId;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
        this.where = where;
        this.why = why;
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        time = time;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }




    @Override
    public String toString() {
        return "Reminder{" +
                "userId=" + userId +
                ", doctorName=" + doctorName +
                ", date=" + date +
                ", Time='" + time + '\'' +
                ", where='" + where + '\'' +
                ", why='" + why + '\'' +
                ", reminderId=" + reminderId +
                '}';
    }





}
