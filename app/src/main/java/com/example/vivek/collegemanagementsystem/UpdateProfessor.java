package com.example.vivek.collegemanagementsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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

public class UpdateProfessor extends AppCompatActivity {

    EditText RegistrationNumber;
    ProfessorMasterDB professorMasterDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_professor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RegistrationNumber = (EditText) findViewById(R.id.ProfessorUpdate_RegistrationNumber);
        professorMasterDB = new ProfessorMasterDB(getApplicationContext());
    }

    public void onClickSubmitButton(View view) {
        if (RegistrationNumber.getText().toString().length() > 0) {
            final int reg_no = Integer.parseInt(RegistrationNumber.getText().toString());

            Log.d("TAG:", "Check Student");
            if (professorMasterDB.checkProfessor(reg_no)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateProfessor.this);
                builder.setTitle("Update Professor Details");
                builder.setMessage("What details do you want to update?");
                builder.setPositiveButton("Personal Details", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent Professor_Registration = new Intent(UpdateProfessor.this, ProfessorRegistration.class);
                        Professor_Registration.putExtra("UpdateProfessor", "Y");
                        Professor_Registration.putExtra("Reg_No", reg_no);
                        startActivity(Professor_Registration);
                    }
                });
                builder.setNegativeButton("Subject Details", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent Professor_Registration = new Intent(UpdateProfessor.this, SubjectDetails.class);
                        Professor_Registration.putExtra("Reg_No", reg_no);
                        startActivity(Professor_Registration);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Toast.makeText(UpdateProfessor.this, "Registration Number is invalid", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(UpdateProfessor.this, "Registration Number cannot be blank", Toast.LENGTH_LONG).show();
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

            Intent login = new Intent(UpdateProfessor.this, Login.class);
            startActivity(login);
        }

        return super.onOptionsItemSelected(item);
    }
}
