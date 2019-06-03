package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseElectricity extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = " elec.db";
    public static final String TABLE_NAME = "elec_table";
    public static final String COL_1 = " connectionno";
    public static final String COL_2 = " name";
    public static final String COL_3 = " address";
    public static final String COL_4 = " username";
    public static final String COL_5 = " password";
    public static final String COL_6 = " phone";
    public static final String COL_7 = " email";

    public DatabaseElectricity(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (connectionno TEXT ,name TEXT ,address TEXT ,username TEXT,password TEXT,phone TEXT,email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String connectionno, String name, String address, String username, String password, String phone,
                              String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, connectionno);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, address);
        contentValues.put(COL_4, username);
        contentValues.put(COL_5, password);
        contentValues.put(COL_6, phone);
        contentValues.put(COL_7, email);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public Cursor GetTwoData(String named) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where connectionno=?", new String[]{named});
        return res;

    }

    public void QUERYs(String a) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        Electricity1.a.clear();
        Electricity1.b.clear();
        if (res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase()) ||
                        res.getString(2).toLowerCase().contains(a.toLowerCase()) || res.getString(3).toLowerCase().contains(a.toLowerCase()) ||
                        res.getString(4).toLowerCase().contains(a.toLowerCase()) || res.getString(5).toLowerCase().contains(a.toLowerCase()) ||
                        res.getString(6).toLowerCase().contains(a.toLowerCase())) {

                    Electricity1.a.add(res.getString(1));
                    Electricity1.b.add(res.getString(0));


                }
            } while (res.moveToNext());
        }

    }

    public boolean updateData(String connectionno, String name, String address, String username, String password, String phone,
                              String email, String a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, connectionno);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, address);
        contentValues.put(COL_4, username);
        contentValues.put(COL_5, password);
        contentValues.put(COL_6, phone);
        contentValues.put(COL_7, email);

        db.update(TABLE_NAME, contentValues, "connectionno = ?", new String[]{a});
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
