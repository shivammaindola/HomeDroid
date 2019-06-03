package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper1 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = " Voterid.db";
    public static final String TABLE_NAME = "Voterid_table";
    public static final String COL_1 = "voterid_number";
    public static final String COL_2 = "name";
    public static final String COL_3 = "fathers_name";
    public static final String COL_4 = "dob";
    public static final String COL_5 = "address";
    public static final String COL_6 = "issue_date";

    public DatabaseHelper1(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (voterid_number TEXT ,name TEXT ,fathers_name TEXT ,dob TEXT ,address TEXT ,issue_date TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String num, String name, String father, String dob, String address, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, num);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, father);
        contentValues.put(COL_4, dob);
        contentValues.put(COL_5, address);
        contentValues.put(COL_6, date);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean updateData(String num, String name, String father, String dob, String address, String date, String a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, num);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, father);
        contentValues.put(COL_4, dob);
        contentValues.put(COL_5, address);
        contentValues.put(COL_6, date);
        db.update(TABLE_NAME, contentValues, "voterid_number = ?", new String[]{a});
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public void QUERYs(String a) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Cursor res = db.rawQuery("select * from " + "fts_table"+ " where fts_table MATCH ?", new String[]{a});
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        voterid1.a.clear();
        voterid1.b.clear();
        if (res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase()) ||
                        res.getString(2).toLowerCase().contains(a.toLowerCase()) || res.getString(3).toLowerCase().contains(a.toLowerCase()) ||
                        res.getString(4).toLowerCase().contains(a.toLowerCase()) || res.getString(5).toLowerCase().contains(a.toLowerCase())) {

                    voterid1.b.add(res.getString(1));
                    voterid1.a.add(res.getString(0));


                }
            } while (res.moveToNext());
        }

    }

    public Cursor GetOneData(String named) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where name=?", new String[]{named});
        return res;

    }

    public Cursor GetTwoData(String named) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where voterid_number=?", new String[]{named});
        return res;

    }
}
