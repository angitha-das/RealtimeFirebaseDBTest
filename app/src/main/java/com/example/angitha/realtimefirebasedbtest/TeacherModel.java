package com.example.angitha.realtimefirebasedbtest;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by angitha on 8/5/17.
 */
@IgnoreExtraProperties
public class TeacherModel {
    public int teacherid;
    public String name;
    public String email;

    public TeacherModel(int teacherid, String name, String email) {
        this.teacherid = teacherid;
        this.name = name;
        this.email = email;
    }
}
