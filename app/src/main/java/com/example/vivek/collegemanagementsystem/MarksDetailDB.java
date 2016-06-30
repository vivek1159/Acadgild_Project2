package com.example.vivek.collegemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vivek on 19-06-2016.
 */
public class MarksDetailDB extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public static String DATABASE_NAME = "Marks.db";
    public static int DATABASE_VERSION = 1;

    /* MARKS_DETAILS TABLE details */
    public static String TABLE_NAME_M = "MARKS_DETAILS";
    public static String DEPARTMENT_M = "DEPARTMENT";
    public static String YEAR_M = "YEAR";
    public static String SECTION_M = "SECTION";
    public static String TEST_TYPE_M = "TEST_TYPE";
    public static String REGISTRATION_NUMBER_M = "REGISTRATION_NUMBER";
    public static String MARKS1_M = "MARKS1";
    public static String MARKS2_M = "MARKS2";
    public static String MARKS3_M = "MARKS3";
    public static String MARKS4_M = "MARKS4";
    public static String MARKS5_M = "MARKS5";
    public static String MARKS6_M = "MARKS6";
    public static String MARKS7_M = "MARKS7";


    String CREATE_TABLE_MARKS_DETAILS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_M + "(" +
            DEPARTMENT_M + " TEXT NOT NULL, " + YEAR_M + " TEXT NOT NULL, " +SECTION_M + " TEXT NOT NULL, "
            + TEST_TYPE_M + " TEXT NOT NULL, " + REGISTRATION_NUMBER_M + " INTEGER NOT NULL, "
            + MARKS1_M + " TEXT, " + MARKS2_M + " TEXT, " + MARKS3_M + " TEXT, " + MARKS4_M + " TEXT, "
            + MARKS5_M + " TEXT, " + MARKS6_M + " TEXT, " + MARKS7_M + " TEXT)";

    public MarksDetailDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("CMS: ", "Marks DB Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MARKS_DETAILS);
        Log.d("CMS: ",TABLE_NAME_M + " created ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LoginDB.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_M);
        onCreate(db);
    }

    public void initializeStudent(int reg_no, ContentValues values) {
        db = this.getWritableDatabase();

        ContentValues markDetails = new ContentValues();
        markDetails.put(DEPARTMENT_M,values.getAsString(DEPARTMENT_M));
        markDetails.put(YEAR_M,values.getAsString("CLASS_YEAR"));
        markDetails.put(SECTION_M,values.getAsString(SECTION_M));
        markDetails.put(TEST_TYPE_M,"Test 1");
        markDetails.put(REGISTRATION_NUMBER_M,reg_no);
        markDetails.put(MARKS1_M,"");
        markDetails.put(MARKS2_M,"");
        markDetails.put(MARKS3_M,"");
        markDetails.put(MARKS4_M,"");
        markDetails.put(MARKS5_M,"");
        markDetails.put(MARKS6_M,"");
        markDetails.put(MARKS7_M,"");
        db.insert(TABLE_NAME_M, null, markDetails);
        markDetails.clear();

        markDetails.put(DEPARTMENT_M, values.getAsString(DEPARTMENT_M));
        markDetails.put(YEAR_M,values.getAsString("CLASS_YEAR"));
        markDetails.put(SECTION_M,values.getAsString(SECTION_M));
        markDetails.put(TEST_TYPE_M,"Test 2");
        markDetails.put(REGISTRATION_NUMBER_M,reg_no);
        markDetails.put(MARKS1_M,"");
        markDetails.put(MARKS2_M,"");
        markDetails.put(MARKS3_M,"");
        markDetails.put(MARKS4_M,"");
        markDetails.put(MARKS5_M,"");
        markDetails.put(MARKS6_M,"");
        markDetails.put(MARKS7_M,"");
        db.insert(TABLE_NAME_M, null, markDetails);
        markDetails.clear();

        markDetails.put(DEPARTMENT_M, values.getAsString(DEPARTMENT_M));
        markDetails.put(YEAR_M,values.getAsString("CLASS_YEAR"));
        markDetails.put(SECTION_M,values.getAsString(SECTION_M));
        markDetails.put(TEST_TYPE_M,"Test 3");
        markDetails.put(REGISTRATION_NUMBER_M,reg_no);
        markDetails.put(MARKS1_M,"");
        markDetails.put(MARKS2_M,"");
        markDetails.put(MARKS3_M,"");
        markDetails.put(MARKS4_M,"");
        markDetails.put(MARKS5_M,"");
        markDetails.put(MARKS6_M,"");
        markDetails.put(MARKS7_M,"");
        db.insert(TABLE_NAME_M, null, markDetails);
        markDetails.clear();

        markDetails.put(DEPARTMENT_M, values.getAsString(DEPARTMENT_M));
        markDetails.put(YEAR_M,values.getAsString("CLASS_YEAR"));
        markDetails.put(SECTION_M,values.getAsString(SECTION_M));
        markDetails.put(TEST_TYPE_M,"Annual");
        markDetails.put(REGISTRATION_NUMBER_M,reg_no);
        markDetails.put(MARKS1_M,"");
        markDetails.put(MARKS2_M,"");
        markDetails.put(MARKS3_M,"");
        markDetails.put(MARKS4_M,"");
        markDetails.put(MARKS5_M,"");
        markDetails.put(MARKS6_M,"");
        markDetails.put(MARKS7_M,"");
        db.insert(TABLE_NAME_M, null, markDetails);
        markDetails.clear();

        db.close();
    }

    public ContentValues getStudentMarks(int reg_no, String test_type) {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        String query = "SELECT * FROM " + TABLE_NAME_M + " WHERE " + REGISTRATION_NUMBER_M + " = " + reg_no +
                " AND " + TEST_TYPE_M + " = '" + test_type + "'";
        Log.d("CMS: ", "Query " +query);
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            Log.d("CMS: ", "Marks Cursor moved to first");
            values.put(MARKS1_M, cursor.getString(cursor.getColumnIndex(MARKS1_M)));
            values.put(MARKS2_M, cursor.getString(cursor.getColumnIndex(MARKS2_M)));
            values.put(MARKS3_M, cursor.getString(cursor.getColumnIndex(MARKS3_M)));
            values.put(MARKS4_M, cursor.getString(cursor.getColumnIndex(MARKS4_M)));
            values.put(MARKS5_M, cursor.getString(cursor.getColumnIndex(MARKS5_M)));
            values.put(MARKS6_M, cursor.getString(cursor.getColumnIndex(MARKS6_M)));
            values.put(MARKS7_M, cursor.getString(cursor.getColumnIndex(MARKS7_M)));
            cursor.close();
            db.close();
            return values;
        }
        return null;
    }
}
