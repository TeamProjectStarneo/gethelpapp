package com.example.gethelpapp.db.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Inbox {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int inboxid;
    private int userid;
    private String specialistJob;

    private String specialistName;
    private String lastmessage;
    private int specialistId;
    public Inbox(int userid, String specialistJob, String specialistName, int specialistId) {
        this.userid = userid;
        this.specialistJob = specialistJob;
        this.specialistName = specialistName;
        this.lastmessage = lastmessage;
        this.specialistId = specialistId;
    }
    public int getInboxid(){
        return inboxid;
    }
    public void setInboxid(int inboxid) {
        this.inboxid = inboxid;
    }
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getSpecialistJob() {
        return specialistJob;
    }

    public void setSpecialistJob(String specialistJob) {
        this.specialistJob = specialistJob;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }

}
