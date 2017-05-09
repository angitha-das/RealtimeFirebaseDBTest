package com.example.angitha.realtimefirebasedbtest;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by angitha on 8/5/17.
 */

@IgnoreExtraProperties
public class AttendenceModel {
    int studentid;
    String date;
    int hour;//7 hours per day
    //Storing only absenties list
    public AttendenceModel(){

    }
    public String getDate()
    {
        return this.date;
    }
    public int getStudentid()
    {
        return this.studentid;
    }
    public int getHour()
    {
        return this.hour;
    }
    public AttendenceModel(int studentid, String date, int hour) {
        this.studentid = studentid;
        this.date = date;
        this.hour = hour;
    }

}
