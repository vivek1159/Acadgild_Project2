package com.example.vivek.collegemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by vivek on 22-06-2016.
 */
public class ProfessorSubjectsDB extends SQLiteOpenHelper {

    SQLiteDatabase db;
    String TAG = "CMS:";

    public static String DATABASE_NAME = "ProfessorSubjects.db";
    public static int DATABASE_VERSION = 1;

    public static String TABLE_NAME = "SUBJECT_DETAILS";
    public static String REGISTRATION_NUMBER = "REGISTRATION_NUMBER";
    public static String DEPARTMENT = "DEPARTMENT";
    public static String YEAR = "YEAR";
    public static String SECTION = "SECTION";
    public static String SUBJECT = "SUBJECT";


    String CREATE_TABLE_SUBJECT_DETAILS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            REGISTRATION_NUMBER + " INTEGER NOT NULL, " + DEPARTMENT + " TEXT NOT NULL, " +
            YEAR + " TEXT NOT NULL, " + SECTION + " TEXT NOT NULL, " + SUBJECT +
            " TEXT NOT NULL, PRIMARY KEY (" + REGISTRATION_NUMBER + "," + DEPARTMENT + "," + YEAR + "," + SECTION + "))";

    public ProfessorSubjectsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "ProfessorSubjectsDB constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SUBJECT_DETAILS);
        Log.d(TAG, "ProfessorSubjectsDB onCreate");
        Log.d(TAG, "Query " + CREATE_TABLE_SUBJECT_DETAILS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ProfessorSubjectsDB.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertSubject(ContentValues departmentDetails) {
        db = this.getWritableDatabase();
        Log.d(TAG, "going to insert");
        long rowID = db.insert(TABLE_NAME, null, departmentDetails);
        if (rowID < 0) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }

    public Cursor getSubjects(int reg_no) {
        db = this.getReadableDatabase();

        ArrayList<String> subjects = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + REGISTRATION_NUMBER + " = " + reg_no;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}
