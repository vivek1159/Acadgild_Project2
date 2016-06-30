package com.example.vivek.collegemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.Objects;

public class StudentRegistration extends AppCompatActivity {


    EditText FirstName, LastName, Date, Month, Year, PhoneNumber, Address, EmailAddress;
    Button SubmitButton, ClearButton;
    Spinner Gender, Department, ClassYear, ClassSection;

    String gender, department, classYear, classSection = "";

    String genPass;

    ArrayList<String> GenderList, DepartmentList, ClassYearList, ClassSectionList;

    StudentMasterDB studentMasterDB;
    MarksDetailDB marksDetailDB;
    LoginDB loginDB;

    Bundle data;
    Boolean updateStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        // Initializing View controls
        FirstName = (EditText) findViewById(R.id.StudentRegistration_FirstName);
        LastName = (EditText) findViewById(R.id.StudentRegistration_LastName);
        Date = (EditText) findViewById(R.id.StudentRegistration_Date);
        Month = (EditText) findViewById(R.id.StudentRegistration_Month);
        Year = (EditText) findViewById(R.id.StudentRegistration_Year);
        Gender = (Spinner) findViewById(R.id.StudentRegistration_Gender);
        PhoneNumber = (EditText) findViewById(R.id.StudentRegistration_PhoneNumber);
        Address = (EditText) findViewById(R.id.StudentRegistration_Address);
        EmailAddress = (EditText) findViewById(R.id.StudentRegistration_EmailAddress);
        Department = (Spinner) findViewById(R.id.StudentRegistration_Department);
        ClassYear = (Spinner) findViewById(R.id.StudentRegistration_ClassYear);
        ClassSection = (Spinner) findViewById(R.id.StudentRegistration_ClassSection);
        SubmitButton = (Button) findViewById(R.id.StudentRegistration_SubmitButton);
        ClearButton = (Button) findViewById(R.id.StudentRegistration_ClearButton);

        // Initializing the data-source, adapter and click-listeners for the Spinner fields
        GenderList = new ArrayList<String>();
        GenderList.add("Male");
        GenderList.add("Female");
        GenderList.add("Other");
        ArrayAdapter<String> GenderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, GenderList);
        GenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Gender.setAdapter(GenderAdapter);
        Gender.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender = parent.getItemAtPosition(0).toString();
            }
        });


        DepartmentList = new ArrayList<String>();
        DepartmentList.add("Electrical");
        DepartmentList.add("Mechanical");
        DepartmentList.add("Civil");
        ArrayAdapter<String> DepartmentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DepartmentList);
        DepartmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Department.setAdapter(DepartmentAdapter);
        Department.setOnItemSelectedListener(new OnItemSelectedListener() {
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
        ClassYear.setOnItemSelectedListener(new OnItemSelectedListener() {
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
        ClassSection.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classSection = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                classSection = parent.getItemAtPosition(0).toString();
            }
        });

        //Creating Database object
        studentMasterDB = new StudentMasterDB(getApplicationContext());
        loginDB = new LoginDB(getApplicationContext());
        marksDetailDB = new MarksDetailDB(getApplicationContext());

        data = getIntent().getExtras();
        updateStudent = data.getString("UpdateStudent").equals("Y");

        if (updateStudent) {
            int reg_no = data.getInt("Reg_No");
            populateValues(reg_no);
        }

    }

    private void populateValues(int reg_no) {

        ContentValues studentValues = studentMasterDB.getStudentDetails(reg_no);

        FirstName.setText(studentValues.getAsString("FIRST_NAME"));
        LastName.setText(studentValues.getAsString("LAST_NAME"));
        EmailAddress.setText(studentValues.getAsString("EMAIL_ADDRESS"));
        PhoneNumber.setText(studentValues.getAsString("MOBILE_NUMBER"));
        Address.setText(studentValues.getAsString("ADDRESS"));
        Gender.setSelection(GenderList.indexOf(studentValues.getAsString("GENDER")));
        Department.setSelection(DepartmentList.indexOf(studentValues.getAsString("DEPARTMENT")));
        ClassYear.setSelection(ClassYearList.indexOf(studentValues.getAsString("CLASS_YEAR")));
        ClassSection.setSelection(ClassSectionList.indexOf(studentValues.getAsString("SECTION")));

        String DOB = String.valueOf(studentValues.getAsInteger("DATE_OF_BIRTH"));
        Log.d("TAG:", "DOB " + DOB);

        String date = DOB.substring(6, 8);
        String month = DOB.substring(4, 6);
        String year = DOB.substring(0, 4);
        Log.d("TAG:", "DOB " + date + " " + month + " " + year);
        Date.setText(date);
        Month.setText(month);
        Year.setText(year);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent adminHome = new Intent(StudentRegistration.this, AdminHome.class);
        startActivity(adminHome);
    }

    public void onClickSubmitButton(View v) {
        if ((FirstName.getText().length() < 1) || Objects.equals(FirstName.getText().toString(), " ")) {
            FirstName.setError("First Name cannot be blank");
        } else if (LastName.getText().length() < 1 || Objects.equals(LastName.getText().toString(), " ")) {
            LastName.setError("Last Name cannot be blank");
        } else if (Date.getText().length() < 1 && Month.getText().length() < 1 && Year.getText().length() < 1) {
            Date.setError("Please enter valid Date of Birth");
        } else if (PhoneNumber.getText().length() < 10) {
            PhoneNumber.setError("Please enter a 10 digit phone number");
        } else if (Objects.equals(Address.getText().toString(), " ") || Address.getText().length() < 1) {
            Address.setError("Address field cannot be left blank");
        } else if (EmailAddress.getText().length() < 1) {
            EmailAddress.setError("Please enter a valid Email address");
        } else if (!dateValidation(Date, Month, Year)) {
            Toast.makeText(StudentRegistration.this, "Registration NOT Successful. See Error!", Toast.LENGTH_SHORT).show();
        } else if (!(loginDB.checkIfUserExists(EmailAddress.getText().toString()) || updateStudent)) {
            Toast.makeText(this, "User already exists. Please check", Toast.LENGTH_LONG).show();
        } else {
            ContentValues studentValues = new ContentValues();
            studentValues.put("EMAIL_ADDRESS", EmailAddress.getText().toString());
            studentValues.put("FIRST_NAME", FirstName.getText().toString());
            studentValues.put("LAST_NAME", LastName.getText().toString());
            studentValues.put("DATE_OF_BIRTH", Integer.parseInt((Year.getText().toString() + Month.getText().toString() + Date.getText().toString())));
            studentValues.put("GENDER", gender);
            studentValues.put("MOBILE_NUMBER", PhoneNumber.getText().toString());
            studentValues.put("ADDRESS", Address.getText().toString());
            studentValues.put("DEPARTMENT", department);
            studentValues.put("CLASS_YEAR", classYear);
            studentValues.put("SECTION", classSection);

            ContentValues loginValues = new ContentValues();
            loginValues.put("EMAIL_ADDRESS", EmailAddress.getText().toString());
            loginValues.put("PASSWORD", generatePassword());
            loginValues.put("FIRST_NAME", FirstName.getText().toString());
            loginValues.put("LAST_NAME", LastName.getText().toString());
            loginValues.put("MOBILE_NUMBER", PhoneNumber.getText().toString());
            loginValues.put("USER_TYPE", "S");
            loginValues.put("NEW_USER", "Y");

            if (updateStudent) {

                if (studentMasterDB.updateStudentDetails(studentValues, EmailAddress.getText().toString())) {
                    Toast.makeText(StudentRegistration.this, "User updated successfully", Toast.LENGTH_SHORT).show();
                    Intent adminHome = new Intent(StudentRegistration.this, AdminHome.class);
                    startActivity(adminHome);
                } else {
                    Toast.makeText(StudentRegistration.this, "Some problem occurred!", Toast.LENGTH_SHORT).show();
                }

            } else if (loginDB.addUser(loginValues) && studentMasterDB.addStudent(studentValues)) {
                int reg_no = studentMasterDB.getRegistrationNumber(EmailAddress.getText().toString());
                marksDetailDB.initializeStudent(reg_no,studentValues);

                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    final GMailSender autoMail = new GMailSender();
                    final String Subject = "Welcome to Riverside Academy Inter College!";
                    final String Body = "Hi " + FirstName.getText().toString() + ",\n" +
                            "We are happy to have you as a part of our school." + "Please note your temporary password. \n" +
                            "Password: " + genPass;
                    final String to = EmailAddress.getText().toString();
                    try {
                        if (autoMail.sendMail(Subject, Body, "rsaic2000@gmail.com", to)) {
                            Toast.makeText(StudentRegistration.this, "Registration Successful.", Toast.LENGTH_SHORT).show();

                            Intent adminHome = new Intent(StudentRegistration.this, AdminHome.class);
                            startActivity(adminHome);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(StudentRegistration.this, "Registration Successful. Unable to send Email as network is not available", Toast.LENGTH_SHORT).show();
                    Intent adminHome = new Intent(StudentRegistration.this, AdminHome.class);
                    startActivity(adminHome);
                }
            }
        }
    }

    private String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int min = 0;
        int max = 35;
        int generator;
        genPass = "";
        for (int i = 1; i <= 8; i++) {
            generator = (int) (min + (max - min) * (Math.random()));
            genPass += chars.charAt(generator);
        }
        return genPass;
    }

    public void onClickClearButton(View v) {
        FirstName.setText("");
        LastName.setText("");
        Date.setText("");
        Month.setText("");
        Year.setText("");
        PhoneNumber.setText("");
        Address.setText("");
        EmailAddress.setText("");

        Toast.makeText(StudentRegistration.this, "Contents Cleared", Toast.LENGTH_SHORT).show();
    }

    public boolean dateValidation(EditText Date, EditText Month, EditText Year) {
        Integer date = Integer.parseInt(Date.getText().toString());
        Integer month = Integer.parseInt(Month.getText().toString());
        Integer year = Integer.parseInt(Year.getText().toString());

        if (year >= 1900 && year <= 2016) {
            if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
                if (date >= 1 && date <= 31) {
                    return true;
                } else {
                    this.Date.setError("Please enter valid date");
                    return false;
                }
            } else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
                if (date >= 1 && date <= 30) {
                    return true;
                } else {
                    this.Date.setError("Please enter valid date");
                    return false;
                }
            } else if (month == 2) {
                //Leap year check
                if (year % 4 == 0) {
                    if (date >= 1 && date <= 29) {
                        return true;
                    } else {
                        this.Date.setError("Please enter valid date");
                        return false;
                    }
                } else {
                    if (date >= 1 && date <= 28) {
                        return true;
                    } else {
                        this.Date.setError("Please enter valid date");
                        return false;
                    }
                }
            } else {
                this.Month.setError("Please enter a valid Month");
                return false;
            }
        } else {
            this.Year.setError("Please enter year between 1900 and 2016");
            return false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_registration, menu);
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

            Intent login = new Intent(StudentRegistration.this, Login.class);
            startActivity(login);
        }

        return super.onOptionsItemSelected(item);
    }

}
