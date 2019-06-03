package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper12 extends SQLiteOpenHelper {

    public static final String DATABSE_NAME = "DrivingLicense.db";
    public static final String TABLE_NAME = "Driving_table";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "license_number";
    public static final String COL_3 = "doi";
    public static final String COL_4 = "validity";
    public static final String COL_5 = "dob";
    public static final String COL_6 = "father_name";
    public static final String COL_7 = "address";


    public DatabaseHelper12(@Nullable Context context) {

        super(context, DATABSE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (Name TEXT, License_Number TEXT, Doi TEXT, Validity TEXT, Dob TEXT, Father_Name TEXT, Address TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String number, String doi, String validity, String dob, String father_name, String address) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, name);
        contentValues.put(COL_2, number);
        contentValues.put(COL_3, doi);
        contentValues.put(COL_4, validity);
        contentValues.put(COL_5, dob);
        contentValues.put(COL_6, father_name);
        contentValues.put(COL_7, address);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else
            return true;
    }

    // Returning all the data in database...
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    //Return data According to the value...
    public Cursor getData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where license_number=?", new String[]{name});
        return res;
    }

    // Update data....
    public boolean updateData(String name, String number, String doi, String validity, String dob, String father_name, String address, String passnum) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, number);
        contentValues.put(COL_3, doi);
        contentValues.put(COL_4, validity);
        contentValues.put(COL_5, dob);
        contentValues.put(COL_6, father_name);
        contentValues.put(COL_7, address);

        db.update(TABLE_NAME, contentValues, "license_number = ?", new String[]{passnum});
        return true;
    }

}
