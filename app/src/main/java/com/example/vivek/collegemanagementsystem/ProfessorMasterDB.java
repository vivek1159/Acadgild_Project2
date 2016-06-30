package com.example.vivek.collegemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by vivek on 24-04-2016.
 */
public class ProfessorMasterDB extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public static String DATABASE_NAME = "ProfessorMaster.db";
    public static int DATABASE_VERSION = 1;
    String TAG = "StudentDB:";

    /* PROFESSOR MASTER Table details */
    public static String TABLE_NAME = "PROFESSOR_MASTER";
    public static String REGISTRATION_NUMBER = "REGISTRATION_NUMBER";
    public static String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static String FIRST_NAME = "FIRST_NAME";
    public static String LAST_NAME = "LAST_NAME";
    public static String DATE_OF_BIRTH = "DATE_OF_BIRTH";
    public static String GENDER = "GENDER";
    public static String MOBILE_NUMBER = "MOBILE_NUMBER";
    public static String ADDRESS = "ADDRESS";
    public static String DEPARTMENT = "DEPARTMENT";
    public static String HIGHEST_QUALIFICATION = "HIGHEST_QUALIFICATION";

    public static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + REGISTRATION_NUMBER +
            " INTEGER PRIMARY KEY, " + EMAIL_ADDRESS + " TEXT NOT NULL, " +
            FIRST_NAME + " TEXT NOT NULL," + LAST_NAME + " TEXT NOT NULL, " + DATE_OF_BIRTH + " NUMBER NOT NULL, " +
            GENDER + " TEXT NOT NULL, " + MOBILE_NUMBER + " TEXT NOT NULL, " + ADDRESS + " TEXT NOT NULL, " +
            DEPARTMENT + " TEXT NOT NULL, " + HIGHEST_QUALIFICATION + " TEXT NOT NULL)";

    public ProfessorMasterDB(Context context) {
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
        Log.w(ProfessorMasterDB.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addProfessor(ContentValues values) {
        db = this.getWritableDatabase();
        try {
            db.insert(TABLE_NAME, null, values);
            return true;
        } catch (SQLiteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkProfessor(int reg_no) {
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

    public ContentValues getProfessorDetails(int reg_no) {
        db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + REGISTRATION_NUMBER + " = " + reg_no;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        ContentValues professorValues = new ContentValues();
        professorValues.put(EMAIL_ADDRESS, cursor.getString(cursor.getColumnIndex(EMAIL_ADDRESS)));
        professorValues.put(FIRST_NAME, cursor.getString(cursor.getColumnIndex(FIRST_NAME)));
        professorValues.put(LAST_NAME, cursor.getString(cursor.getColumnIndex(LAST_NAME)));
        professorValues.put(DATE_OF_BIRTH, cursor.getString(cursor.getColumnIndex(DATE_OF_BIRTH)));
        professorValues.put(GENDER, cursor.getString(cursor.getColumnIndex(GENDER)));
        professorValues.put(MOBILE_NUMBER, cursor.getString(cursor.getColumnIndex(MOBILE_NUMBER)));
        professorValues.put(ADDRESS, cursor.getString(cursor.getColumnIndex(ADDRESS)));
        professorValues.put(DEPARTMENT, cursor.getString(cursor.getColumnIndex(DEPARTMENT)));
        professorValues.put(HIGHEST_QUALIFICATION, cursor.getString(cursor.getColumnIndex(HIGHEST_QUALIFICATION)));

        cursor.close();
        db.close();
        return professorValues;

    }

    public boolean updateProfessorDetails(ContentValues professorValues, String Email) {
        db = this.getWritableDatabase();

        try {
            db.update(TABLE_NAME, professorValues, EMAIL_ADDRESS + "=?", new String[]{Email});
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
}
