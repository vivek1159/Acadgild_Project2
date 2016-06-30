package com.example.vivek.collegemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;
import java.util.Objects;

public class ProfessorRegistration extends AppCompatActivity {

    EditText FirstName, LastName, Date, Month, Year, PhoneNumber, Address, EmailAddress, HighestQualification;
    Button SubmitButton, ClearButton;
    Spinner Gender, Department;

    String gender, department = "";

    String genPass;

    ArrayList<String> GenderList, DepartmentList;

    ProfessorMasterDB professorMasterDB;
    MarksDetailDB marksDetailDB;
    LoginDB loginDB;

    Bundle data;
    Boolean updateProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializing View controls
        FirstName = (EditText) findViewById(R.id.ProfessorRegistration_FirstName);
        LastName = (EditText) findViewById(R.id.ProfessorRegistration_LastName);
        Date = (EditText) findViewById(R.id.ProfessorRegistration_Date);
        Month = (EditText) findViewById(R.id.ProfessorRegistration_Month);
        Year = (EditText) findViewById(R.id.ProfessorRegistration_Year);
        Gender = (Spinner) findViewById(R.id.ProfessorRegistration_Gender);
        PhoneNumber = (EditText) findViewById(R.id.ProfessorRegistration_PhoneNumber);
        Address = (EditText) findViewById(R.id.ProfessorRegistration_Address);
        EmailAddress = (EditText) findViewById(R.id.ProfessorRegistration_EmailAddress);
        Department = (Spinner) findViewById(R.id.ProfessorRegistration_Department);
        HighestQualification = (EditText) findViewById(R.id.ProfessorRegistration_HighestQualification);
        SubmitButton = (Button) findViewById(R.id.ProfessorRegistration_SubmitButton);
        ClearButton = (Button) findViewById(R.id.ProfessorRegistration_ClearButton);


        // Initializing the data-source, adapter and click-listeners for the Spinner fields
        GenderList = new ArrayList<String>();
        GenderList.add("Male");
        GenderList.add("Female");
        GenderList.add("Other");
        ArrayAdapter<String> GenderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, GenderList);
        GenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Gender.setAdapter(GenderAdapter);
        Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        //Creating Database object
        professorMasterDB = new ProfessorMasterDB(getApplicationContext());
        marksDetailDB = new MarksDetailDB(getApplicationContext());
        loginDB = new LoginDB(getApplicationContext());

        data = getIntent().getExtras();
        updateProfessor = data.getString("UpdateProfessor").equals("Y");

        if (updateProfessor) {
            int reg_no = data.getInt("Reg_No");
            populateValues(reg_no);
        }
    }

    private void populateValues(int reg_no) {

        ContentValues professorValues = professorMasterDB.getProfessorDetails(reg_no);

        FirstName.setText(professorValues.getAsString("FIRST_NAME"));
        LastName.setText(professorValues.getAsString("LAST_NAME"));
        EmailAddress.setText(professorValues.getAsString("EMAIL_ADDRESS"));
        PhoneNumber.setText(professorValues.getAsString("MOBILE_NUMBER"));
        Address.setText(professorValues.getAsString("ADDRESS"));
        Gender.setSelection(GenderList.indexOf(professorValues.getAsString("GENDER")));
        Department.setSelection(DepartmentList.indexOf(professorValues.getAsString("DEPARTMENT")));
        HighestQualification.setText(professorValues.getAsString("HIGHEST_QUALIFICATION"));

        String DOB = String.valueOf(professorValues.getAsInteger("DATE_OF_BIRTH"));
        Log.d("CMS:", "DOB " + DOB);

        String date = DOB.substring(6, 8);
        String month = DOB.substring(4, 6);
        String year = DOB.substring(0, 4);
        Log.d("CMS:", "DOB " + date + " " + month + " " + year);
        Date.setText(date);
        Month.setText(month);
        Year.setText(year);
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
        } else if (HighestQualification.getText().length() < 1) {
            HighestQualification.setError("Please enter the Highest Qualification!");
        } else if (!dateValidation(Date, Month, Year)) {
            Toast.makeText(ProfessorRegistration.this, "Registration NOT Successful. See Error!", Toast.LENGTH_SHORT).show();
        } else if (loginDB.checkIfUserExists(EmailAddress.getText().toString())) {
            Toast.makeText(this, "User already exists. Please check", Toast.LENGTH_LONG).show();
        } else {
            ContentValues professorValues = new ContentValues();
            professorValues.put("EMAIL_ADDRESS", EmailAddress.getText().toString());
            professorValues.put("FIRST_NAME", FirstName.getText().toString());
            professorValues.put("LAST_NAME", LastName.getText().toString());
            professorValues.put("DATE_OF_BIRTH", Integer.parseInt((Year.getText().toString() + Month.getText().toString() + Date.getText().toString())));
            professorValues.put("GENDER", gender);
            professorValues.put("MOBILE_NUMBER", PhoneNumber.getText().toString());
            professorValues.put("ADDRESS", Address.getText().toString());
            professorValues.put("DEPARTMENT", department);
            professorValues.put("HIGHEST_QUALIFICATION", HighestQualification.getText().toString());

            ContentValues loginValues = new ContentValues();
            loginValues.put("EMAIL_ADDRESS", EmailAddress.getText().toString());
            loginValues.put("PASSWORD", generatePassword());
            loginValues.put("FIRST_NAME", FirstName.getText().toString());
            loginValues.put("LAST_NAME", LastName.getText().toString());
            loginValues.put("MOBILE_NUMBER", PhoneNumber.getText().toString());
            loginValues.put("USER_TYPE", "T");
            loginValues.put("NEW_USER", "Y");

            if (updateProfessor) {

                if (professorMasterDB.updateProfessorDetails(professorValues, EmailAddress.getText().toString())) {
                    Toast.makeText(ProfessorRegistration.this, "User updated successfully", Toast.LENGTH_LONG).show();
                    Intent adminHome = new Intent(ProfessorRegistration.this, AdminHome.class);
                    startActivity(adminHome);
                } else {
                    Toast.makeText(ProfessorRegistration.this, "Some problem occurred!", Toast.LENGTH_LONG).show();
                }

            } else if (loginDB.addUser(loginValues) && professorMasterDB.addProfessor(professorValues)) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    final GMailSender autoMail = new GMailSender();
                    final String Subject = "Welcome to Riverside Academy Inter College!";
                    final String Body = "Hi " + FirstName.getText().toString() + ",\n" +
                            "We are happy to have you as a part of our school." + "Please note your temporary password. \n" +
                            "Password: " + genPass;
                    final String to = EmailAddress.getText().toString();
                    try {
                        if (autoMail.sendMail(Subject, Body, "rsaic2000@gmail.com", to)) {
                            Toast.makeText(ProfessorRegistration.this, "Registration Successful.", Toast.LENGTH_SHORT).show();

                            Intent adminHome = new Intent(ProfessorRegistration.this, AdminHome.class);
                            startActivity(adminHome);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(ProfessorRegistration.this, "Registration Successful. Unable to send Email as network is not available", Toast.LENGTH_SHORT).show();
                    Intent adminHome = new Intent(ProfessorRegistration.this, AdminHome.class);
                    startActivity(adminHome);
                }
            }
        }
    }

    private String generatePassword() {
        String chars = "ABC0DEF1GH2IJ3KL4M5N6O7P8QR9STUVWXYZ";
        genPass = "";
        for (int i = 1; i <= 8; i++) {
            genPass += chars.charAt((int) (25 * Math.random()));
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
        HighestQualification.setText("");
        Toast.makeText(ProfessorRegistration.this, "Contents Cleared", Toast.LENGTH_SHORT).show();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
