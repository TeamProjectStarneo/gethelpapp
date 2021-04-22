package com.example.gethelpapp.db.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Messages
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int messageid;


    private int userid;

    private Boolean sender;
    private String message;
    private int specialistId;
    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }



    public int getMessageid() {
        return messageid;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }





    public Boolean getSender() {
        return sender;
    }

    public void setSender(Boolean sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Messages(int userid, Boolean sender, String message, int specialistId) {

        this.userid = userid;
        this.sender = sender;
        this.message = message;
        this.specialistId = specialistId;
    }




}
