package com.example.vivek.collegemanagementsystem;

import android.support.v4.app.Fragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vivek on 19-06-2016.
 */
public class StudentMarksFragment extends Fragment {

    int reg_no;
    MarksDetailDB marksDetailDB;
    StudentMasterDB studentMasterDB;
    LoginDB loginDB;

    TextView Subject1,Subject2,Subject3,Subject4,Subject5,Subject6,Subject7;
    TextView Marks11,Marks12,Marks13,Marks14,Marks15,Marks16,Marks17;
    TextView Marks21,Marks22,Marks23,Marks24,Marks25,Marks26,Marks27;
    TextView Marks31,Marks32,Marks33,Marks34,Marks35,Marks36,Marks37;
    TextView Marks41,Marks42,Marks43,Marks44,Marks45,Marks46,Marks47;
    
    public StudentMarksFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_marks, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        
        initializeTextViews(view);
        setSubjects();
        setMarks();

        super.onViewCreated(view, savedInstanceState);
    }

    private void initializeTextViews(View view) {
        Subject1 = (TextView)view.findViewById(R.id.Subject1);
        Subject2 = (TextView)view.findViewById(R.id.Subject2);
        Subject3 = (TextView)view.findViewById(R.id.Subject3);
        Subject4 = (TextView)view.findViewById(R.id.Subject4);
        Subject5 = (TextView)view.findViewById(R.id.Subject5);
        Subject6 = (TextView)view.findViewById(R.id.Subject6);
        Subject7 = (TextView)view.findViewById(R.id.Subject7);

        Marks11 = (TextView)view.findViewById(R.id.Marks11);
        Marks12 = (TextView)view.findViewById(R.id.Marks12);
        Marks13 = (TextView)view.findViewById(R.id.Marks13);
        Marks14 = (TextView)view.findViewById(R.id.Marks14);
        Marks15 = (TextView)view.findViewById(R.id.Marks15);
        Marks16 = (TextView)view.findViewById(R.id.Marks16);
        Marks17 = (TextView)view.findViewById(R.id.Marks17);

        Marks21 = (TextView)view.findViewById(R.id.Marks21);
        Marks22 = (TextView)view.findViewById(R.id.Marks22);
        Marks23 = (TextView)view.findViewById(R.id.Marks23);
        Marks24 = (TextView)view.findViewById(R.id.Marks24);
        Marks25 = (TextView)view.findViewById(R.id.Marks25);
        Marks26 = (TextView)view.findViewById(R.id.Marks26);
        Marks27 = (TextView)view.findViewById(R.id.Marks27);

        Marks31 = (TextView)view.findViewById(R.id.Marks31);
        Marks32 = (TextView)view.findViewById(R.id.Marks32);
        Marks33 = (TextView)view.findViewById(R.id.Marks33);
        Marks34 = (TextView)view.findViewById(R.id.Marks34);
        Marks35 = (TextView)view.findViewById(R.id.Marks35);
        Marks36 = (TextView)view.findViewById(R.id.Marks36);
        Marks37 = (TextView)view.findViewById(R.id.Marks37);

        Marks41 = (TextView)view.findViewById(R.id.Marks41);
        Marks42 = (TextView)view.findViewById(R.id.Marks42);
        Marks43 = (TextView)view.findViewById(R.id.Marks43);
        Marks44 = (TextView)view.findViewById(R.id.Marks44);
        Marks45 = (TextView)view.findViewById(R.id.Marks45);
        Marks46 = (TextView)view.findViewById(R.id.Marks46);
        Marks47 = (TextView)view.findViewById(R.id.Marks47);
    }

    private void setSubjects() {
        ContentValues departmentDetails = studentMasterDB.getDepartmentDetails(reg_no);
        ContentValues subjects = (loginDB.getSubjects(departmentDetails));
        Subject1.setText(subjects.getAsString("SUBJECT1"));
        Subject2.setText(subjects.getAsString("SUBJECT2"));
        Subject3.setText(subjects.getAsString("SUBJECT3"));
        Subject4.setText(subjects.getAsString("SUBJECT4"));
        Subject5.setText(subjects.getAsString("SUBJECT5"));
        Subject6.setText(subjects.getAsString("SUBJECT6"));
        Subject7.setText(subjects.getAsString("SUBJECT7"));
    }

    private void setMarks() {
        ContentValues values;
        values = marksDetailDB.getStudentMarks(reg_no,"Test 1");
        Marks11.setText(values.getAsString("MARKS1"));
        Marks12.setText(values.getAsString("MARKS2"));
        Marks13.setText(values.getAsString("MARKS3"));
        Marks14.setText(values.getAsString("MARKS4"));
        Marks15.setText(values.getAsString("MARKS5"));
        Marks16.setText(values.getAsString("MARKS6"));
        Marks17.setText(values.getAsString("MARKS7"));
        values.clear();

        values = marksDetailDB.getStudentMarks(reg_no,"Test 2");
        Marks21.setText(values.getAsString("MARKS1"));
        Marks22.setText(values.getAsString("MARKS2"));
        Marks23.setText(values.getAsString("MARKS3"));
        Marks24.setText(values.getAsString("MARKS4"));
        Marks25.setText(values.getAsString("MARKS5"));
        Marks26.setText(values.getAsString("MARKS6"));
        Marks27.setText(values.getAsString("MARKS7"));
        values.clear();

        values = marksDetailDB.getStudentMarks(reg_no,"Test 3");
        Marks31.setText(values.getAsString("MARKS1"));
        Marks32.setText(values.getAsString("MARKS2"));
        Marks33.setText(values.getAsString("MARKS3"));
        Marks34.setText(values.getAsString("MARKS4"));
        Marks35.setText(values.getAsString("MARKS5"));
        Marks36.setText(values.getAsString("MARKS6"));
        Marks37.setText(values.getAsString("MARKS7"));
        values.clear();

        values = marksDetailDB.getStudentMarks(reg_no,"Annual");
        Marks41.setText(values.getAsString("MARKS1"));
        Marks42.setText(values.getAsString("MARKS2"));
        Marks43.setText(values.getAsString("MARKS3"));
        Marks44.setText(values.getAsString("MARKS4"));
        Marks45.setText(values.getAsString("MARKS5"));
        Marks46.setText(values.getAsString("MARKS6"));
        Marks47.setText(values.getAsString("MARKS7"));
        values.clear();
    }
}
