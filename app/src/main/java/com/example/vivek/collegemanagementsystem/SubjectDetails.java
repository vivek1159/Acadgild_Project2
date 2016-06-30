package com.example.vivek.collegemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SubjectDetails extends AppCompatActivity {
    
    Spinner  Department, ClassYear, ClassSection, Subject;
    ArrayList<String> DepartmentList,ClassYearList,ClassSectionList,SubjectList;
    String department,classYear,classSection,subject;
    
    LoginDB loginDB;
    ProfessorSubjectsDB professorSubjectsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);
        
        Department = (Spinner)findViewById(R.id.ProfessorUpdate_Department);
        ClassYear = (Spinner)findViewById(R.id.ProfessorUpdate_ClassYear);
        ClassSection = (Spinner)findViewById(R.id.ProfessorUpdate_ClassSection);
        Subject = (Spinner)findViewById(R.id.ProfessorUpdate_Subject);
        loginDB = new LoginDB(getApplicationContext());
        professorSubjectsDB = new ProfessorSubjectsDB(getApplicationContext());

        DepartmentList = new ArrayList<String>();
        DepartmentList.add("Electrical");
        DepartmentList.add("Mechanical");
        DepartmentList.add("Civil");
        ArrayAdapter<String> DepartmentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DepartmentList);
        DepartmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Department.setAdapter(DepartmentAdapter);
        Department.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!(hasFocus)) {
                    updateSubjects();
                }
            }
        });
        Department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                department = parent.getItemAtPosition(0).toString();
            }

        });

        ClassYearList = new ArrayList<String>();
        ClassYearList.add("I");
        ClassYearList.add("II");
        ClassYearList.add("III");
        ClassYearList.add("IV");
        ArrayAdapter<String> ClassYearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ClassYearList);
        ClassYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ClassYear.setAdapter(ClassYearAdapter);
        ClassYear.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!(hasFocus)) {
                    updateSubjects();
                }
            }
        });
        ClassYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classYear = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                classYear = parent.getItemAtPosition(0).toString();
            }
        });

        ClassSectionList = new ArrayList<String>();
        ClassSectionList.add("A");
        ClassSectionList.add("B");
        ArrayAdapter<String> ClassSectionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ClassSectionList);
        ClassSectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ClassSection.setAdapter(ClassSectionAdapter);
        ClassSection.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!(hasFocus)) {
                    updateSubjects();
                }
            }
        });
        ClassSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classSection = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                classSection = parent.getItemAtPosition(0).toString();
            }
        });
    }

    public void updateSubjects(){
        ContentValues departmentDetails = new ContentValues();
        Log.d("CMS: ", "Department " + department + " Class year " + classYear + " Class section " + classSection);
        departmentDetails.put("DEPARTMENT", department);
        departmentDetails.put("CLASS_YEAR", classYear);
        departmentDetails.put("SECTION", classSection);
        ContentValues SubjectValues = loginDB.getSubjects(departmentDetails);

        SubjectList = new ArrayList<>();
        if (!(SubjectValues.getAsString("SUBJECT1").equals(""))) {
            SubjectList.add(SubjectValues.getAsString("SUBJECT1"));
        }
        if (!(SubjectValues.getAsString("SUBJECT2").equals(""))) {
            SubjectList.add(SubjectValues.getAsString("SUBJECT2"));
        }
        if (!(SubjectValues.getAsString("SUBJECT3").equals(""))) {
            SubjectList.add(SubjectValues.getAsString("SUBJECT3"));
        }
        if (!(SubjectValues.getAsString("SUBJECT4").equals(""))) {
            SubjectList.add(SubjectValues.getAsString("SUBJECT4"));
        }
        if (!(SubjectValues.getAsString("SUBJECT5").equals(""))) {
            SubjectList.add(SubjectValues.getAsString("SUBJECT5"));
        }
        if (!(SubjectValues.getAsString("SUBJECT6").equals(""))) {
            SubjectList.add(SubjectValues.getAsString("SUBJECT6"));
        }
        if (!(SubjectValues.getAsString("SUBJECT7").equals(""))) {
            SubjectList.add(SubjectValues.getAsString("SUBJECT7"));
        }
        ArrayAdapter<String> SubjectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SubjectList);
        SubjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Subject.setAdapter(SubjectAdapter);
        Subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subject = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                subject = parent.getItemAtPosition(0).toString();
            }
        });
    }

    public void onClickSaveButton(View view){
        Bundle data = getIntent().getExtras();
        int reg_no = data.getInt("Reg_No");

        ContentValues departmentDetails = new ContentValues();
        departmentDetails.put("REGISTRATION_NUMBER",reg_no);
        Log.d("CMS: ", "REGISTRATION_NUMBER " + reg_no);
        departmentDetails.put("DEPARTMENT", department);
        Log.d("CMS: ", "DEPARTMENT " + department);
        departmentDetails.put("YEAR", classYear);
        Log.d("CMS: ", "YEAR " + classYear);
        departmentDetails.put("SECTION", classSection);
        Log.d("CMS: ", "SECTION " + classSection);
        departmentDetails.put("SUBJECT",subject);
        Log.d("CMS: ", "SUBJECT " + subject);

        if (professorSubjectsDB.insertSubject(departmentDetails)){
            Toast.makeText(SubjectDetails.this,"Subject added!",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(SubjectDetails.this,"Cannot insert same subject or more than 1 subject for any Class-Section!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            SharedPreferences loginPreferences = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = loginPreferences.edit();
            editor.clear();
            editor.apply();

            Intent login = new Intent(SubjectDetails.this, Login.class);
            startActivity(login);
        }

        return super.onOptionsItemSelected(item);
    }
}
