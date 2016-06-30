package com.example.vivek.collegemanagementsystem;

import android.content.ContentValues;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vivek on 19-06-2016.
 */
public class StudentAttendanceFragment extends Fragment {

    int reg_no;
    MarksDetailDB marksDetailDB;
    StudentMasterDB studentMasterDB;
    LoginDB loginDB;

    TextView Subject1_attendance,Subject2_attendance,Subject3_attendance,Subject4_attendance,
            Subject5_attendance,Subject6_attendance,Subject7_attendance;
    TextView Attendance1,Attendance2,Attendance3,Attendance4,Attendance5,Attendance6,Attendance7;

    public StudentAttendanceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle data = getArguments();
        marksDetailDB = new MarksDetailDB(getContext());
        studentMasterDB = new StudentMasterDB(getContext());
        loginDB = new LoginDB(getContext());

        String Email = data.getString("Email");
        reg_no = studentMasterDB.getRegistrationNumber(Email);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_attendance, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        initializeTextViews(view);
        setSubjects();
        markAttendance();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initializeTextViews(View view) {

        Subject1_attendance = (TextView)view.findViewById(R.id.Subject1_attendance);
        Subject2_attendance = (TextView)view.findViewById(R.id.Subject2_attendance);
        Subject3_attendance = (TextView)view.findViewById(R.id.Subject3_attendance);
        Subject4_attendance = (TextView)view.findViewById(R.id.Subject4_attendance);
        Subject5_attendance = (TextView)view.findViewById(R.id.Subject5_attendance);
        Subject6_attendance = (TextView)view.findViewById(R.id.Subject6_attendance);
        Subject7_attendance = (TextView)view.findViewById(R.id.Subject7_attendance);

        Attendance1 = (TextView)view.findViewById(R.id.Attendance1);
        Attendance2 = (TextView)view.findViewById(R.id.Attendance2);
        Attendance3 = (TextView)view.findViewById(R.id.Attendance3);
        Attendance4 = (TextView)view.findViewById(R.id.Attendance4);
        Attendance5 = (TextView)view.findViewById(R.id.Attendance5);
        Attendance6 = (TextView)view.findViewById(R.id.Attendance6);
        Attendance7 = (TextView)view.findViewById(R.id.Attendance7);
    }

    private void setSubjects() {
        ContentValues departmentDetails = studentMasterDB.getDepartmentDetails(reg_no);
        ContentValues subjects = (loginDB.getSubjects(departmentDetails));
        Subject1_attendance.setText(subjects.getAsString("SUBJECT1"));
        Subject2_attendance.setText(subjects.getAsString("SUBJECT2"));
        Subject3_attendance.setText(subjects.getAsString("SUBJECT3"));
        Subject4_attendance.setText(subjects.getAsString("SUBJECT4"));
        Subject5_attendance.setText(subjects.getAsString("SUBJECT5"));
        Subject6_attendance.setText(subjects.getAsString("SUBJECT6"));
        Subject7_attendance.setText(subjects.getAsString("SUBJECT7"));
    }

    private void markAttendance() {
        //Method to generate random percentage values for representation
        int percentage = generateAttendance();
        Attendance1.setText(String.valueOf(percentage));
        if (percentage < 75) {
            Attendance1.setBackgroundColor(getResources().getColor(R.color.Red));
        } else {
            Attendance1.setBackgroundColor(getResources().getColor(R.color.LightGreen));
        }

        percentage = generateAttendance();
        Attendance2.setText(String.valueOf(percentage));
        if (percentage < 75) {
            Attendance2.setBackgroundColor(getResources().getColor(R.color.Red));
        } else {
            Attendance2.setBackgroundColor(getResources().getColor(R.color.LightGreen));
        }

        percentage = generateAttendance();
        Attendance3.setText(String.valueOf(percentage));
        if (percentage < 75) {
            Attendance3.setBackgroundColor(getResources().getColor(R.color.Red));
        } else {
            Attendance3.setBackgroundColor(getResources().getColor(R.color.LightGreen));
        }

        percentage = generateAttendance();
        Attendance4.setText(String.valueOf(percentage));
        if (percentage < 75) {
            Attendance4.setBackgroundColor(getResources().getColor(R.color.Red));
        } else {
            Attendance4.setBackgroundColor(getResources().getColor(R.color.LightGreen));
        }

        percentage = generateAttendance();
        Attendance5.setText(String.valueOf(percentage));
        if (percentage < 75) {
            Attendance5.setBackgroundColor(getResources().getColor(R.color.Red));
        } else {
            Attendance5.setBackgroundColor(getResources().getColor(R.color.LightGreen));
        }

        percentage = generateAttendance();
        Attendance6.setText(String.valueOf(percentage));
        if (percentage < 75) {
            Attendance6.setBackgroundColor(getResources().getColor(R.color.Red));
        } else {
            Attendance6.setBackgroundColor(getResources().getColor(R.color.LightGreen));
        }

        percentage = generateAttendance();
        Attendance7.setText(String.valueOf(percentage));
        if (percentage < 75) {
            Attendance7.setBackgroundColor(getResources().getColor(R.color.Red));
        } else {
            Attendance7.setBackgroundColor(getResources().getColor(R.color.LightGreen));
        }
    }

    public int generateAttendance(){
        int min  = 70, max = 100;
        return (int) (min + (max-min)*Math.random());
    }
}
