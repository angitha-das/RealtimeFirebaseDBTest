package com.example.angitha.realtimefirebasedbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

//        String attendenceId = mDatabase.child("Attendence").push().getKey();
//        AttendenceModel attendenceModel = new AttendenceModel(
//                13, "08-May-2015",2);
//        mDatabase.child("Attendence").child(attendenceId).setValue(attendenceModel);


        //Fetch attendence list of students in 1st year in department cs
//==================================

//ADD LISTENER FOR SINGLE VALUE EVENT

//==================================
//        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("RootNode").child("Student");
//        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot studSnapShot :dataSnapshot.getChildren()){
//                    StudentModel studentModel = studSnapShot.getValue(StudentModel.class);
//                    textView.append(studentModel.getName()+"-"+studentModel.getEmail()+"\n");
//                }
//                //collectStudentDetails((Map<String,Object>) dataSnapshot.getValue());
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//==================================
        //To populate list of students for getting attendence list

//        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("RootNode").child("Student");
//        ref3.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot studSnapShot :dataSnapshot.getChildren()){
//                    StudentModel studentModel = studSnapShot.getValue(StudentModel.class);
//                    if(studentModel.getAccademicyear().equalsIgnoreCase("2nd yr")&&
//                            studentModel.getDepartment().equalsIgnoreCase("maths")){
//                        textView.append(studentModel.getName()+"-"+studentModel.getEmail()+"\n");
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        //Join 2 tables attendence and Student to calculate monthly report.
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("RootNode").child("Attendence");

        ref.orderByChild("date").startAt("05-May-2015").endAt("08-May-2015").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot attendenceSnapShot :dataSnapshot.getChildren()){
                    AttendenceModel attendenceModel = attendenceSnapShot.getValue(AttendenceModel.class);

                    Map<String, Object> valuesMap = (HashMap<String, Object>) attendenceSnapShot.getValue();


                    DatabaseReference ref2 = (DatabaseReference) FirebaseDatabase.getInstance().getReference("RootNode").child("Student").orderByKey().equalTo(attendenceModel.getStudentid());
                    ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot studSnapShot :dataSnapshot.getChildren()){
                                StudentModel studentModel = studSnapShot.getValue(StudentModel.class);

                        textView.append(studentModel.getName()+"-"+studentModel.getEmail()+"\n"+"-"+
                                studentModel.getAccademicyear()+"-"+studentModel.getDepartment()+"\n\n");

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//==================================

        //ADD CHILD EVENT LISTENER

//==================================

//        mDatabase.child("Attendence").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });

//==================================
// ADD VALUE EVENT LISTENER
// ==================================

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
    private void collectStudentDetails(Map<String,Object> student) {
        ArrayList<String> studentList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : student.entrySet()){
            Map singleStudent = (Map) entry.getValue();
            studentList.add((String) singleStudent.get("name"));
        }
        textView.setText(studentList.toString());
    }
}
