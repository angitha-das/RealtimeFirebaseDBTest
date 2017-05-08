package com.example.angitha.realtimefirebasedbtest;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by angitha on 8/5/17.
 */
@IgnoreExtraProperties
public class StudentModel {
    public int studentid;
    public String name;
    public String email;
    public String accademicyear;
    public String department;

    public StudentModel(int studentid, String name, String email, String accademicyear, String department) {
        this.studentid = studentid;
        this.name = name;
        this.email = email;
        this.accademicyear=accademicyear;
        this.department=department;
    }
}
