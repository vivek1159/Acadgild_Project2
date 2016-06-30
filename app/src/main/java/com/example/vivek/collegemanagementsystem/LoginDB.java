package com.example.vivek.collegemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vivek on 23-04-2016.
 */
public class LoginDB extends SQLiteOpenHelper {

    SQLiteDatabase db;
    String TAG = "CMS:";

    public static String DATABASE_NAME = "Login.db";
    public static int DATABASE_VERSION = 1;

    /* USER_LOGIN TABLE details */
    public static String TABLE_NAME = "USER_LOGIN";
    public static String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static String PASSWORD = "PASSWORD";
    public static String FIRST_NAME = "FIRST_NAME";
    public static String LAST_NAME = "LAST_NAME";
    public static String MOBILE_NUMBER = "MOBILE_NUMBER";
    public static String USER_TYPE = "USER_TYPE";
    public static String NEW_USER = "NEW_USER";


    /* DEPARTMENT_DETAILS TABLE details */
    public static String TABLE_NAME_D = "DEPARTMENT_DETAILS";
    public static String DEPARTMENT_D = "DEPARTMENT";
    public static String YEAR_D = "YEAR";
    public static String SECTION_D = "SECTION";
    public static String SUBJECT1_D = "SUBJECT1";
    public static String SUBJECT2_D = "SUBJECT2";
    public static String SUBJECT3_D = "SUBJECT3";
    public static String SUBJECT4_D = "SUBJECT4";
    public static String SUBJECT5_D = "SUBJECT5";
    public static String SUBJECT6_D = "SUBJECT6";
    public static String SUBJECT7_D = "SUBJECT7";


    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + EMAIL_ADDRESS + " TEXT PRIMARY KEY," +
            PASSWORD + " TEXT NOT NULL," + FIRST_NAME + " TEXT NOT NULL," + LAST_NAME + " TEXT NOT NULL," +
            MOBILE_NUMBER + " TEXT NOT NULL," + USER_TYPE + " TEXT NOT NULL," + NEW_USER + " TEXT NOT NULL" + ")";

    String CREATE_TABLE_DEPARTMENT_DETAILS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_D + "(" +
            DEPARTMENT_D + " TEXT NOT NULL, " + YEAR_D + " TEXT NOT NULL, " + SECTION_D + " TEXT NOT NULL, "
            + SUBJECT1_D + " TEXT, " + SUBJECT2_D + " TEXT, " + SUBJECT3_D + " TEXT, " + SUBJECT4_D + " TEXT, "
            + SUBJECT5_D + " TEXT, " + SUBJECT6_D + " TEXT, " + SUBJECT7_D + " )";


    public LoginDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Login DB constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        ContentValues values = new ContentValues();
        values.put(EMAIL_ADDRESS, "admin@gmail.com");
        values.put(PASSWORD, "admin123");
        values.put(FIRST_NAME, "admin");
        values.put(LAST_NAME, "admin");
        values.put(MOBILE_NUMBER, "7894561200");
        values.put(USER_TYPE, "A");
        values.put(NEW_USER, "N");
        db.insert(TABLE_NAME, null, values);
        values.clear();

        db.execSQL(CREATE_TABLE_DEPARTMENT_DETAILS);
        values.put(DEPARTMENT_D, "ELECTRICAL");
        values.put(YEAR_D, "I");
        values.put(SECTION_D, "A");
        values.put(SUBJECT1_D, "MATHS-I");
        values.put(SUBJECT2_D, "ENGLISH-I");
        values.put(SUBJECT3_D, "PHYSICS-I");
        values.put(SUBJECT4_D, "CHEMISTRY-I");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-I");
        values.put(SUBJECT6_D, "BIOLOGY");
        values.put(SUBJECT7_D, "ENGINEERING GRAPHICS");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "ELECTRICAL");
        values.put(YEAR_D, "I");
        values.put(SECTION_D, "B");
        values.put(SUBJECT1_D, "MATHS-I");
        values.put(SUBJECT2_D, "ENGLISH-I");
        values.put(SUBJECT3_D, "PHYSICS-I");
        values.put(SUBJECT4_D, "CHEMISTRY-I");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-I");
        values.put(SUBJECT6_D, "BIOLOGY");
        values.put(SUBJECT7_D, "ENGINEERING GRAPHICS");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "ELECTRICAL");
        values.put(YEAR_D, "II");
        values.put(SECTION_D, "A");
        values.put(SUBJECT1_D, "MATHS-II");
        values.put(SUBJECT2_D, "MATERIAL SCIENCE");
        values.put(SUBJECT3_D, "ELECTRICAL MACHINES-I");
        values.put(SUBJECT4_D, "DIGITAL SYSTEMS");
        values.put(SUBJECT5_D, "FLUID MECHANICS");
        values.put(SUBJECT6_D, "ELECTRICAL CIRCUITS");
        values.put(SUBJECT7_D, "PERSONALITY DEVELOPMENT-II");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "ELECTRICAL");
        values.put(YEAR_D, "II");
        values.put(SECTION_D, "B");
        values.put(SUBJECT1_D, "MATHS-II");
        values.put(SUBJECT2_D, "MATERIAL SCIENCE");
        values.put(SUBJECT3_D, "ELECTRICAL MACHINES-I");
        values.put(SUBJECT4_D, "DIGITAL SYSTEMS");
        values.put(SUBJECT5_D, "FLUID MECHANICS");
        values.put(SUBJECT6_D, "ELECTRICAL CIRCUITS");
        values.put(SUBJECT7_D, "PERSONALITY DEVELOPMENT-II");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "ELECTRICAL");
        values.put(YEAR_D, "III");
        values.put(SECTION_D, "A");
        values.put(SUBJECT1_D, "MATHS-III");
        values.put(SUBJECT2_D, "DIGITIAL SIGNAL PROCESSING");
        values.put(SUBJECT3_D, "ELECTRICAL MACHINES-II");
        values.put(SUBJECT4_D, "ELECRTONIC CIRCUITS");
        values.put(SUBJECT5_D, "POWER SYSTEMS");
        values.put(SUBJECT6_D, "PERSONALITY DEVELOPMENT-III");
        values.put(SUBJECT7_D, "");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "ELECTRICAL");
        values.put(YEAR_D, "III");
        values.put(SECTION_D, "B");
        values.put(SUBJECT1_D, "MATHS-III");
        values.put(SUBJECT2_D, "DIGITIAL SIGNAL PROCESSING");
        values.put(SUBJECT3_D, "ELECTRICAL MACHINES-II");
        values.put(SUBJECT4_D, "MEASUREMENT AND INSTRUMENTATION");
        values.put(SUBJECT5_D, "SWITCH GEAR");
        values.put(SUBJECT6_D, "PERSONALITY DEVELOPMENT-III");
        values.put(SUBJECT7_D, "");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "ELECTRICAL");
        values.put(YEAR_D, "IV");
        values.put(SECTION_D, "A");
        values.put(SUBJECT1_D, "FACTS");
        values.put(SUBJECT2_D, "RENEWABLE ENERGY SYSTEMS");
        values.put(SUBJECT3_D, "OPERATIONS RESEARCH");
        values.put(SUBJECT4_D, "");
        values.put(SUBJECT5_D, "");
        values.put(SUBJECT6_D, "");
        values.put(SUBJECT7_D, "");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "ELECTRICAL");
        values.put(YEAR_D, "IV");
        values.put(SECTION_D, "B");
        values.put(SUBJECT1_D, "COMPUTER SYSTEMS");
        values.put(SUBJECT2_D, "OPERATIONS RESEARCH");
        values.put(SUBJECT3_D, "ARTIFICIAL NEURAL NETWORKS");
        values.put(SUBJECT4_D, "");
        values.put(SUBJECT5_D, "");
        values.put(SUBJECT6_D, "");
        values.put(SUBJECT7_D, "");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "MECHANICAL");
        values.put(YEAR_D, "I");
        values.put(SECTION_D, "A");
        values.put(SUBJECT1_D, "MATHS-I");
        values.put(SUBJECT2_D, "ENGLISH-I");
        values.put(SUBJECT3_D, "PHYSICS-I");
        values.put(SUBJECT4_D, "CHEMISTRY-I");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-I");
        values.put(SUBJECT6_D, "BIOLOGY");
        values.put(SUBJECT7_D, "ENGINEERING GRAPHICS");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "MECHANICAL");
        values.put(YEAR_D, "I");
        values.put(SECTION_D, "B");
        values.put(SUBJECT1_D, "MATHS-I");
        values.put(SUBJECT2_D, "ENGLISH-I");
        values.put(SUBJECT3_D, "PHYSICS-I");
        values.put(SUBJECT4_D, "CHEMISTRY-I");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-I");
        values.put(SUBJECT6_D, "BIOLOGY");
        values.put(SUBJECT7_D, "ENGINEERING GRAPHICS");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "MECHANICAL");
        values.put(YEAR_D, "II");
        values.put(SECTION_D, "A");
        values.put(SUBJECT1_D, "THERMODYNAMICS");
        values.put(SUBJECT2_D, "FOREIGN LANGUAGE");
        values.put(SUBJECT3_D, "MACHINES");
        values.put(SUBJECT4_D, "MATHS-II");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-II");
        values.put(SUBJECT6_D, "HEAT AND POWER");
        values.put(SUBJECT7_D, "ENGINEERING GRAPHICS-II");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "MECHANICAL");
        values.put(YEAR_D, "II");
        values.put(SECTION_D, "B");
        values.put(SUBJECT1_D, "THERMODYNAMICS");
        values.put(SUBJECT2_D, "FOREIGN LANGUAGE");
        values.put(SUBJECT3_D, "MACHINES");
        values.put(SUBJECT4_D, "MATHS-II");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-II");
        values.put(SUBJECT6_D, "HEAT AND POWER");
        values.put(SUBJECT7_D, "ENGINEERING GRAPHICS-II");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "MECHANICAL");
        values.put(YEAR_D, "III");
        values.put(SECTION_D, "A");
        values.put(SUBJECT1_D, "THERMODYNAMICS");
        values.put(SUBJECT2_D, "THERMAL POWER");
        values.put(SUBJECT3_D, "MACHINES LAB");
        values.put(SUBJECT4_D, "MATHS-III");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-III");
        values.put(SUBJECT6_D, "");
        values.put(SUBJECT7_D, "");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "MECHANICAL");
        values.put(YEAR_D, "III");
        values.put(SECTION_D, "B");
        values.put(SUBJECT1_D, "THERMODYNAMICS-II");
        values.put(SUBJECT2_D, "THERMAL POWER");
        values.put(SUBJECT3_D, "MACHINES LAB");
        values.put(SUBJECT4_D, "MATHS-III");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-III");
        values.put(SUBJECT6_D, "");
        values.put(SUBJECT7_D, "");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "MECHANICAL");
        values.put(YEAR_D, "IV");
        values.put(SECTION_D, "A");
        values.put(SUBJECT1_D, "SOLID MECHANICS");
        values.put(SUBJECT2_D, "STRENGTH OF MATERIALS");
        values.put(SUBJECT3_D, "MATERIALS ENGINEERING");
        values.put(SUBJECT4_D, "");
        values.put(SUBJECT5_D, "");
        values.put(SUBJECT6_D, "");
        values.put(SUBJECT7_D, "");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "MECHANICAL");
        values.put(YEAR_D, "IV");
        values.put(SECTION_D, "B");
        values.put(SUBJECT1_D, "MECHANISMS AND MECHANICAL DEVICES");
        values.put(SUBJECT2_D, "HYDRAULICS");
        values.put(SUBJECT3_D, "MECHATRONICS");
        values.put(SUBJECT4_D, "");
        values.put(SUBJECT5_D, "");
        values.put(SUBJECT6_D, "");
        values.put(SUBJECT7_D, "");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "CIVIL");
        values.put(YEAR_D, "I");
        values.put(SECTION_D, "A");
        values.put(SUBJECT1_D, "MATHS-I");
        values.put(SUBJECT2_D, "ENGLISH-I");
        values.put(SUBJECT3_D, "PHYSICS-I");
        values.put(SUBJECT4_D, "CHEMISTRY-I");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-I");
        values.put(SUBJECT6_D, "BIOLOGY");
        values.put(SUBJECT7_D, "ENGINEERING GRAPHICS");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "CIVIL");
        values.put(YEAR_D, "I");
        values.put(SECTION_D, "B");
        values.put(SUBJECT1_D, "MATHS-I");
        values.put(SUBJECT2_D, "ENGLISH-I");
        values.put(SUBJECT3_D, "PHYSICS-I");
        values.put(SUBJECT4_D, "CHEMISTRY-I");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-I");
        values.put(SUBJECT6_D, "BIOLOGY");
        values.put(SUBJECT7_D, "ENGINEERING GRAPHICS");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "CIVIL");
        values.put(YEAR_D, "II");
        values.put(SECTION_D, "A");
        values.put(SUBJECT1_D, "MATERIALS SCIENCE");
        values.put(SUBJECT2_D, "COASTAL ENGINEERING");
        values.put(SUBJECT3_D, "CONSTRUCTION ENGINEERING");
        values.put(SUBJECT4_D, "MATHS-II");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-II");
        values.put(SUBJECT6_D, "SURVEYING");
        values.put(SUBJECT7_D, "ENGINEERING GRAPHICS-II");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "CIVIL");
        values.put(YEAR_D, "II");
        values.put(SECTION_D, "B");
        values.put(SUBJECT1_D, "MATERIALS SCIENCE");
        values.put(SUBJECT2_D, "COASTAL ENGINEERING");
        values.put(SUBJECT3_D, "CONSTRUCTION ENGINEERING");
        values.put(SUBJECT4_D, "MATHS-II");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-II");
        values.put(SUBJECT6_D, "SURVEYING");
        values.put(SUBJECT7_D, "ENGINEERING GRAPHICS-II");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "CIVIL");
        values.put(YEAR_D, "III");
        values.put(SECTION_D, "A");
        values.put(SUBJECT1_D, "EARTHQUAKE ENGINEERING");
        values.put(SUBJECT2_D, "ENVIRONMENTAL ENGINEERING");
        values.put(SUBJECT3_D, "SURVEYING LAB");
        values.put(SUBJECT4_D, "MATHS-III");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-III");
        values.put(SUBJECT6_D, "");
        values.put(SUBJECT7_D, "");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "CIVIL");
        values.put(YEAR_D, "III");
        values.put(SECTION_D, "B");
        values.put(SUBJECT1_D, "EARTHQUAKE ENGINEERING");
        values.put(SUBJECT2_D, "ENVIRONMENTAL ENGINEERING");
        values.put(SUBJECT3_D, "SURVEYING LAB");
        values.put(SUBJECT4_D, "MATHS-III");
        values.put(SUBJECT5_D, "PERSONALITY DEVELOPMENT-III");
        values.put(SUBJECT6_D, "");
        values.put(SUBJECT7_D, "");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "CIVIL");
        values.put(YEAR_D, "IV");
        values.put(SECTION_D, "A");
        values.put(SUBJECT1_D, "STRUCTURAL ENGINEERING");
        values.put(SUBJECT2_D, "TRANSPORTATION ENGINEERING");
        values.put(SUBJECT3_D, "CONTROL ENGINEERING");
        values.put(SUBJECT4_D, "");
        values.put(SUBJECT5_D, "");
        values.put(SUBJECT6_D, "");
        values.put(SUBJECT7_D, "");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

        values.put(DEPARTMENT_D, "CIVIL");
        values.put(YEAR_D, "IV");
        values.put(SECTION_D, "B");
        values.put(SUBJECT1_D, "WATER RESOURCES ENGINEERING");
        values.put(SUBJECT2_D, "FORENSIC ENGINEERING");
        values.put(SUBJECT3_D, "CONTROL ENGINEERING");
        values.put(SUBJECT4_D, "");
        values.put(SUBJECT5_D, "");
        values.put(SUBJECT6_D, "");
        values.put(SUBJECT7_D, "");
        db.insert(TABLE_NAME_D, null, values);
        values.clear();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(LoginDB.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public String getUserType(String Email, String Password) {
        db = this.getReadableDatabase();

        String query = "SELECT USER_TYPE FROM USER_LOGIN WHERE EMAIL_ADDRESS = '" + Email + "' AND PASSWORD='" + Password + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
            if ((cursor.getCount()) == 1) {
                String user_type = cursor.getString(cursor.getColumnIndex(USER_TYPE));
                cursor.close();
                db.close();
                return user_type;
            } else {
                cursor.close();
                db.close();
                return " ";
            }
        } else {
            db.close();
            return " ";
        }
    }

    public boolean addUser(ContentValues loginValues) {
        db = this.getWritableDatabase();
        try {
            db.insert(TABLE_NAME, null, loginValues);
            db.close();
            return true;
        } catch (SQLiteException e) {
            db.close();
            return false;
        }
    }

    public boolean checkIfUserExists(String Email) {
        db = this.getReadableDatabase();
        String query = "SELECT * FROM USER_LOGIN WHERE EMAIL_ADDRESS = '" + Email + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() < 1) {
            Log.d(TAG, "Count 0");
            cursor.close();
            db.close();
            return false;
        } else {
            cursor.close();
            db.close();
            return true;
        }
    }

    public boolean checkIfUserExists(String Email, String MobileNumber) {
        db = this.getReadableDatabase();
        String query = "SELECT * FROM USER_LOGIN WHERE EMAIL_ADDRESS = '" + Email + "' AND " + MOBILE_NUMBER + " = '" + MobileNumber + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() < 1) {
            Log.d(TAG, "Count 0");
            cursor.close();
            db.close();
            return false;
        } else {
            cursor.close();
            db.close();
            return true;
        }
    }

    public String ifNewUser(String emailInput, String passwordInput) {
        db = this.getReadableDatabase();
        String query = "SELECT " + NEW_USER + " FROM " + TABLE_NAME + " WHERE " + EMAIL_ADDRESS + " = '" + emailInput + "'"
                + " AND " + PASSWORD + " = '" + passwordInput + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String new_user = cursor.getString(cursor.getColumnIndex(NEW_USER));
            cursor.close();
            db.close();
            return new_user;
        } else {
            cursor.close();
            db.close();
            return " ";
        }
    }

    public ContentValues getSubjects(ContentValues departmentDetails) {
        db = this.getReadableDatabase();

        String DEPARTMENT = departmentDetails.getAsString("DEPARTMENT").toUpperCase();
        Log.d("CMS: ", "DEPARTMENT" + DEPARTMENT);
        String CLASS_YEAR = departmentDetails.getAsString("CLASS_YEAR").toUpperCase();
        Log.d("CMS: ", "CLASS_YEAR" + CLASS_YEAR);
        String SECTION = departmentDetails.getAsString("SECTION").toUpperCase();
        Log.d("CMS: ", "SECTION" + SECTION);

        String query = "SELECT * FROM " + TABLE_NAME_D + " WHERE " + DEPARTMENT_D + " = '" + DEPARTMENT +
                "' AND " + YEAR_D + " = '" + CLASS_YEAR + "' AND " + SECTION_D + " = '" + SECTION + "'";

        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            values.put(SUBJECT1_D, cursor.getString(cursor.getColumnIndex(SUBJECT1_D)));
            values.put(SUBJECT2_D, cursor.getString(cursor.getColumnIndex(SUBJECT2_D)));
            values.put(SUBJECT3_D, cursor.getString(cursor.getColumnIndex(SUBJECT3_D)));
            values.put(SUBJECT4_D, cursor.getString(cursor.getColumnIndex(SUBJECT4_D)));
            values.put(SUBJECT5_D, cursor.getString(cursor.getColumnIndex(SUBJECT5_D)));
            values.put(SUBJECT6_D, cursor.getString(cursor.getColumnIndex(SUBJECT6_D)));
            values.put(SUBJECT7_D, cursor.getString(cursor.getColumnIndex(SUBJECT7_D)));
            cursor.close();
            db.close();
            return values;
        }
        return null;
    }

    public void updateUser(String email, String password, String New_User) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PASSWORD, password);
        values.put(NEW_USER, New_User);
        db.update(TABLE_NAME, values, EMAIL_ADDRESS + "=?", new String[]{email});
        Log.d(TAG, "User updated");
        db.close();
    }
}
