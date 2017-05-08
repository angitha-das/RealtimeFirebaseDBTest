package com.example.angitha.realtimefirebasedbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);

        //Registration by teacher!
        //Must include signUp model for registration.

        mDatabase= FirebaseDatabase.getInstance().getReference("RootNode");

//        String userId = mDatabase.child("Teacher").push().getKey();
//        TeacherModel teacherModel = new TeacherModel(
//                3,"Vaishak OK", "vaishak.ok@cabotsolutions.com");
//        mDatabase.child("Teacher").child(userId).setValue(teacherModel);

        //Teacher Adds Students

//        String studId = mDatabase.child("Student").push().getKey();
//        StudentModel studentModel = new StudentModel(
//                15,"John", "john@gmail.com","2nd yr","maths");
//        mDatabase.child("Student").child(studId).setValue(studentModel);

        //Teacher marks attendence of 1st year cs students for 1st hour today.

        String attendenceId = mDatabase.child("Attendence").push().getKey();
        AttendenceModel attendenceModel = new AttendenceModel(
                13, "08-May-2015",2);
        mDatabase.child("Attendence").child(attendenceId).setValue(attendenceModel);

        //Fetch attendence list of students in 1st year in department cs
        mDatabase.child("Attendence").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AttendenceModel newAttendance = dataSnapshot.getValue(AttendenceModel.class);
                textView.setText(" Student id: " + newAttendance.studentid+
                        " Date: " + newAttendance.date+" Hour: " + newAttendance.hour);


                // New child added, increment count from rootnode
//                final AtomicInteger count = new AtomicInteger();
//                int newCount = count.incrementAndGet();
//                System.out.println("Added " + dataSnapshot.getKey() + ", count is " + newCount);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                Post changedPost = dataSnapshot.getValue(Post.class);
//                System.out.println("The updated post title is: " + changedPost.title);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                Post removedPost = dataSnapshot.getValue(Post.class);
//                System.out.println("The blog post titled " + removedPost.title + " has been deleted");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("Attendence").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                final AtomicInteger count = new AtomicInteger();
//                long numChildren = dataSnapshot.getChildrenCount();
//                System.out.println(count.get() + " == " + numChildren);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("Attendence").orderByChild("studentid").limitToFirst(3).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AttendenceModel newAttendance = dataSnapshot.getValue(AttendenceModel.class);
                textView.setText(dataSnapshot.getKey()+" Student id: " + newAttendance.studentid+
                        " Date: " + newAttendance.date+" Hour: " + newAttendance.hour);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

                //Adding value listener.
//        mDatabase.child("Attendence").child(attendenceId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                AttendenceModel record = dataSnapshot.getValue(AttendenceModel.class);
//                textView.setText(record.studentid+" "+record.date+" "+record.hour);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("Attendence Record", "Failed to read value.", error.toException());
//            }
//        });







    }
}
