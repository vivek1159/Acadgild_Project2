package com.example.vivek.collegemanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProfessorHome extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> classes;
    ProfessorSubjectsDB professorSubjectsDB;
    ProfessorMasterDB professorMasterDB;
    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        data = getIntent().getExtras();
        professorSubjectsDB = new ProfessorSubjectsDB(getApplicationContext());
        professorMasterDB = new ProfessorMasterDB(getApplicationContext());

        String Email = data.getString("Email");
        int reg_no = professorMasterDB.getRegistrationNumber(Email);
        Cursor classData = professorSubjectsDB.getSubjects(reg_no);
        ArrayList<String> list = new ArrayList<>();

        String department,year,section;
        final ArrayList<String> departmentList,yearList,sectionList;
        departmentList=yearList=sectionList = new ArrayList<>();

        while (classData.moveToNext()) {
            department = classData.getString(classData.getColumnIndex("DEPARTMENT"));
            year = classData.getString(classData.getColumnIndex("YEAR"));
            section = classData.getString(classData.getColumnIndex("SECTION"));
            departmentList.add(department);
            yearList.add(year);
            sectionList.add(section);
            list.add(department + " " + year + "-" + section);
        }
        classData.close();
        listView = (ListView)findViewById(R.id.listView);
        classes = new ArrayAdapter<>(ProfessorHome.this,android.R.layout.simple_selectable_list_item,list);
        listView.setAdapter(classes);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent uploadMarks = new Intent(ProfessorHome.this,UploadMarks.class);
                uploadMarks.putExtra("DEPARTMENT", departmentList.get(position));
                uploadMarks.putExtra("YEAR", yearList.get(position));
                uploadMarks.putExtra("SECTION", sectionList.get(position));
                startActivity(uploadMarks);
            }
        });
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

            Intent login = new Intent(ProfessorHome.this, Login.class);
            startActivity(login);
        }

        return super.onOptionsItemSelected(item);
    }
}
