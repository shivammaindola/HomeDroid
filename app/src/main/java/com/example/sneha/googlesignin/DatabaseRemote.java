package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseRemote extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = " remote.db";
    public static final String TABLE_NAME = "remote_table";
    public static final String COL_1 = " appname";
    public static final String COL_2 = " login_id";
    public static final String COL_3 = " password";

    public DatabaseRemote(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (appname TEXT ,login_id TEXT ,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String appname, String login_id, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, appname);
        contentValues.put(COL_2, login_id);
        contentValues.put(COL_3, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public Cursor GetTwoData(String named) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where appname =?", new String[]{named});
        return res;

    }

    public void QUERYs(String a) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        RemoteLogin1.a.clear();
        RemoteLogin1.b.clear();
        if (res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase()) ||
                        res.getString(2).toLowerCase().contains(a.toLowerCase()) ) {

                    RemoteLogin1.a.add(res.getString(0));
                    RemoteLogin1.b.add(res.getString(1));


                }
            } while (res.moveToNext());
        }

    }

    public boolean updateData(String appname, String login_id, String password, String b) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, appname);
        contentValues.put(COL_2, login_id);
        contentValues.put(COL_3, password);

        db.update(TABLE_NAME, contentValues, "appname = ? ", new String[]{b});
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
