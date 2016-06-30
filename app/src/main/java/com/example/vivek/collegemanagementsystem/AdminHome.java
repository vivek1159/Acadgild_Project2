package com.example.vivek.collegemanagementsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    public void onClickStudentDetails(View v) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(AdminHome.this);

        dialogBuilder.setMessage("What action do you want to perform?");

        dialogBuilder.setPositiveButton("Add New", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent addStudent = new Intent(AdminHome.this, StudentRegistration.class);
                addStudent.putExtra("UpdateStudent", "N");
                startActivity(addStudent);
            }
        });

        dialogBuilder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent updateStudent = new Intent(AdminHome.this, UpdateStudent.class);
                updateStudent.putExtra("UpdateStudent", "Y");
                startActivity(updateStudent);
            }
        });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    public void onClickProfessorDetails(View v) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(AdminHome.this);

        dialogBuilder.setMessage("What action do you want to perform?");

        dialogBuilder.setPositiveButton("Add New", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent addProfessor = new Intent(AdminHome.this, ProfessorRegistration.class);
                addProfessor.putExtra("UpdateProfessor", "N");
                startActivity(addProfessor);
            }
        });

        dialogBuilder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent updateProfessor = new Intent(AdminHome.this, UpdateProfessor.class);
                updateProfessor.putExtra("UpdateProfessor", "Y");
                startActivity(updateProfessor);
            }
        });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    public void onClickParentDetails(View v) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(AdminHome.this);

        dialogBuilder.setMessage("What action do you want to perform?");

        dialogBuilder.setPositiveButton("Add New", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent addStudent = new Intent(AdminHome.this, StudentRegistration.class);
                startActivity(addStudent);
            }
        });

        dialogBuilder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent updateStudent = new Intent(AdminHome.this, UpdateStudent.class);
                startActivity(updateStudent);
            }
        });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent login = new Intent(AdminHome.this, Login.class);
        startActivity(login);
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

            Intent login = new Intent(AdminHome.this, Login.class);
            startActivity(login);
        }

        return super.onOptionsItemSelected(item);
    }
}



