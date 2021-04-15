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



    public Date date;
    private String Time ;
    private String where;
    private String why;


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

    public Date getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = new Date(date);
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
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
                ", Time='" + Time + '\'' +
                ", where='" + where + '\'' +
                ", why='" + why + '\'' +
                ", reminderId=" + reminderId +
                '}';
    }





}
