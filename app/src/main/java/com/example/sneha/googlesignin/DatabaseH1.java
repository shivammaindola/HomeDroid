package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseH1 extends  SQLiteOpenHelper {
    public static final String DATABASE_NAME=" Mail.db";
    public static final String TABLE_NAME="mail_table";
    public static final String COL_1=" username";
    public static final String COL_2=" pass";
    public static final String COL_3=" phone";
    public static final String COL_4=" recovery";
    //public static final String COL_5=" user";
    public static final String COL_6=" owner";
    public DatabaseH1(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (username TEXT ,pass TEXT ,phone TEXT ,recovery TEXT,owner TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  boolean insertData(String username,String pass,String phone,String recovery,String owner)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,username);
        contentValues.put(COL_2,pass);
        contentValues.put(COL_3,phone);
        contentValues.put(COL_4,recovery);
        //contentValues.put(COL_5,user);
        contentValues.put(COL_6,owner);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;}
        else
            return  true;
    }
    public boolean updateData(String username,String pass,String phone,String recovery,String owner,String passnum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,username);
        contentValues.put(COL_2,pass);
        contentValues.put(COL_3,phone);
        contentValues.put(COL_4,recovery);
        //contentValues.put(COL_5,user);
        contentValues.put(COL_6,owner);

        db.update(TABLE_NAME, contentValues, "username = ?", new String[]{passnum});
        return true;
    }
    public void QUERYs(String a)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        Mail1.a.clear();
        if(res.moveToFirst()) {
            do {
// Have to remove here.....
                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase())||
                        res.getString(2).toLowerCase().contains(a.toLowerCase())|| res.getString(3).toLowerCase().contains(a.toLowerCase())
                ||res.getString(4).toLowerCase().contains(a.toLowerCase())|| res.getString(5).toLowerCase().contains(a.toLowerCase())) {

                    Mail1.a.add(res.getString(0));


                }
            } while (res.moveToNext());
        }

    }
    public Cursor GetTwoData(String named)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME+ " where username =?", new String[]{named});

    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
