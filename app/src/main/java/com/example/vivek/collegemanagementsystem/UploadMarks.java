package com.example.vivek.collegemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UploadMarks extends AppCompatActivity {

    ListView listView;
    StudentMasterDB studentMasterDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_marks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView)findViewById(R.id.listView);
        studentMasterDB = new StudentMasterDB(getApplicationContext());

        Bundle extras = getIntent().getExtras();

        ContentValues values = new ContentValues();
        values.put("DEPARTMENT",extras.getString("DEPARTMENT"));
        values.put("YEAR",extras.getString("YEAR"));
        values.put("SECTION",extras.getString("SECTION"));

        Cursor cursor = studentMasterDB.getStudentList(values);
        ArrayList<String> names = new ArrayList<>();

        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                String name = cursor.getString(cursor.getColumnIndex("FIRST_NAME")) + " " + cursor.getString(cursor.getColumnIndex("LAST_NAME"));
                names.add(name);
            }
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(UploadMarks.this,android.R.layout.simple_selectable_list_item,names);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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

            Intent login = new Intent(UploadMarks.this, Login.class);
            startActivity(login);
        }

        return super.onOptionsItemSelected(item);
    }

}
