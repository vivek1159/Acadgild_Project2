package com.example.vivek.collegemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by vivek on 24-04-2016.
 */
public class StudentMasterDB extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public static String DATABASE_NAME = "StudentMaster.db";
    public static int DATABASE_VERSION = 1;
    String TAG = "CMS:";

    /* STUDENT MASTER Table details */
    public static String TABLE_NAME = "STUDENT_MASTER";
    public static String REGISTRATION_NUMBER = "REGISTRATION_NUMBER";
    public static String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static String FIRST_NAME = "FIRST_NAME";
    public static String LAST_NAME = "LAST_NAME";
    public static String DATE_OF_BIRTH = "DATE_OF_BIRTH";
    public static String GENDER = "GENDER";
    public static String MOBILE_NUMBER = "MOBILE_NUMBER";
    public static String ADDRESS = "ADDRESS";
    public static String DEPARTMENT = "DEPARTMENT";
    public static String CLASS_YEAR = "CLASS_YEAR";
    public static String SECTION = "SECTION";

    public static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + REGISTRATION_NUMBER +
            " INTEGER PRIMARY KEY, " + EMAIL_ADDRESS + " TEXT NOT NULL, " +
            FIRST_NAME + " TEXT NOT NULL," + LAST_NAME + " TEXT NOT NULL, " + DATE_OF_BIRTH + " INTEGER NOT NULL, " +
            GENDER + " TEXT NOT NULL, " + MOBILE_NUMBER + " TEXT NOT NULL, " + ADDRESS + " TEXT NOT NULL, " +
            DEPARTMENT + " TEXT NOT NULL, " + CLASS_YEAR + " TEXT NOT NULL, " + SECTION + " TEXT NOT NULL" + ")";

    public StudentMasterDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate SQL " + CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "DB created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(StudentMasterDB.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addStudent(ContentValues values) {
        db = this.getWritableDatabase();
        try {
            db.insert(TABLE_NAME, null, values);

            db.close();
            return true;
        } catch (SQLiteException e) {
            e.printStackTrace();
            db.close();
            return false;
        }
    }

    public boolean checkStudent(int reg_no) {
        db = this.getReadableDatabase();

        String query = "SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE " + REGISTRATION_NUMBER + " = " + reg_no;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            Log.d("TAG:", "Row fetched");
            db.close();
            cursor.close();
            return true;
        }
        Log.d("TAG:", "No Row fetched");
        db.close();
        cursor.close();
        return false;
    }

    public ContentValues getStudentDetails(int reg_no) {
        db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + REGISTRATION_NUMBER + " = " + reg_no;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        ContentValues studentValues = new ContentValues();
        studentValues.put(EMAIL_ADDRESS, cursor.getString(cursor.getColumnIndex(EMAIL_ADDRESS)));
        studentValues.put(FIRST_NAME, cursor.getString(cursor.getColumnIndex(FIRST_NAME)));
        studentValues.put(LAST_NAME, cursor.getString(cursor.getColumnIndex(LAST_NAME)));
        studentValues.put(DATE_OF_BIRTH, cursor.getString(cursor.getColumnIndex(DATE_OF_BIRTH)));
        studentValues.put(GENDER, cursor.getString(cursor.getColumnIndex(GENDER)));
        studentValues.put(MOBILE_NUMBER, cursor.getString(cursor.getColumnIndex(MOBILE_NUMBER)));
        studentValues.put(ADDRESS, cursor.getString(cursor.getColumnIndex(ADDRESS)));
        studentValues.put(DEPARTMENT, cursor.getString(cursor.getColumnIndex(DEPARTMENT)));
        studentValues.put(CLASS_YEAR, cursor.getString(cursor.getColumnIndex(CLASS_YEAR)));
        studentValues.put(SECTION, cursor.getString(cursor.getColumnIndex(SECTION)));

        cursor.close();
        db.close();
        return studentValues;
    }

    public boolean updateStudentDetails(ContentValues studentValues, String Email) {
        db = this.getWritableDatabase();

        try {
            db.update(TABLE_NAME, studentValues, EMAIL_ADDRESS + "=?", new String[]{Email});
            return true;
        } catch (SQLiteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getRegistrationNumber(String Email) {
        db = this.getReadableDatabase();

        String query = "SELECT " + REGISTRATION_NUMBER + " FROM " + TABLE_NAME + " WHERE " + EMAIL_ADDRESS + " = '" + Email + "';";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int reg_no = cursor.getInt(cursor.getColumnIndex(REGISTRATION_NUMBER));
        cursor.close();
        db.close();
        return reg_no;
    }

    public ContentValues getDepartmentDetails(int reg_no) {
        db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + REGISTRATION_NUMBER + " = " + reg_no;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        ContentValues studentValues = new ContentValues();
        studentValues.put(DEPARTMENT, cursor.getString(cursor.getColumnIndex(DEPARTMENT)));
        studentValues.put(CLASS_YEAR, cursor.getString(cursor.getColumnIndex(CLASS_YEAR)));
        studentValues.put(SECTION, cursor.getString(cursor.getColumnIndex(SECTION)));
        cursor.close();
        return studentValues;
    }

    public Cursor getStudentList(ContentValues values) {
        db = this.getReadableDatabase();

        String department = values.getAsString(DEPARTMENT);
        String year = values.getAsString(CLASS_YEAR);
        String section = values.getAsString(SECTION);

        String query = "SELECT " + REGISTRATION_NUMBER + "," + FIRST_NAME + "," + LAST_NAME  +
                " FROM " + TABLE_NAME + " WHERE " + DEPARTMENT + "='" + department + "' AND " +
                CLASS_YEAR + "='" + year + "' AND " + SECTION + "='" + section + "'";
        return db.rawQuery(query,null);
    }
}
