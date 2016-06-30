package com.example.vivek.collegemanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateStudent extends AppCompatActivity {

    EditText RegistrationNumber;
    StudentMasterDB studentMasterDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        studentMasterDB = new StudentMasterDB(getApplicationContext());
        RegistrationNumber = (EditText) findViewById(R.id.StudentUpdate_RegistrationNumber);
    }

    public void onClickSubmitButton(View v) {

        if (RegistrationNumber.getText().toString().length() > 0) {
            int reg_no = Integer.parseInt(RegistrationNumber.getText().toString());

            Log.d("TAG:", "Check Student");
            if (studentMasterDB.checkStudent(reg_no)) {
                Log.d("TAG:","True returned");
                Intent Student_Registration = new Intent(UpdateStudent.this, StudentRegistration.class);
                Student_Registration.putExtra("UpdateStudent", "Y");
                Student_Registration.putExtra("Reg_No", reg_no);
                startActivity(Student_Registration);
            }
            else
            {
                Toast.makeText(UpdateStudent.this,"Registration NUmber is invalid",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(UpdateStudent.this, "Registration number cannot be blank", Toast.LENGTH_LONG).show();
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

            Intent login = new Intent(UpdateStudent.this, Login.class);
            startActivity(login);
        }

        return super.onOptionsItemSelected(item);
    }
}
