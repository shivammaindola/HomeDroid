package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper3 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME=" Aadhar.db";
    public static final String TABLE_NAME="Aadhar_table";
    public static final String COL_1=" name";
    public static final String COL_2=" dob";
    public static final String COL_3=" address";
    public static final String COL_4=" number";
    public static final String COL_5=" phoneNumber";

    public  DatabaseHelper3(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (name TEXT ,dob TEXT , address TEXT ,number TEXT, phoneNumber TEXT)");
        // DOB is number,
        // number is dob,
        // phoneNumber is address,
        // address is phoneNumber...
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public  boolean insertData(String name,String dob,String address,String number, String phoneNumber)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,dob);
        contentValues.put(COL_3,address);
        contentValues.put(COL_4,number);
        contentValues.put(COL_5,phoneNumber);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
            return  true;
    }

    public Cursor GetTwoData(String named)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where dob=?", new String[]{named});
        return res;

    }

    public void QUERYs(String a)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Cursor res = db.rawQuery("select * from " + "fts_table"+ " where fts_table MATCH ?", new String[]{a});
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        aadhar1.a.clear();
        aadhar1.b.clear();
        if(res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase())||
                        res.getString(2).toLowerCase().contains(a.toLowerCase())|| res.getString(3).toLowerCase().contains(a.toLowerCase())) {

                    aadhar1.a.add(res.getString(0));
                    aadhar1.b.add(res.getString(1));


                }
            } while (res.moveToNext());
        }

    }
    public boolean updateData(String name,String dob,String address,String number, String phoneNumber, String passnum){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,dob);
        contentValues.put(COL_3,address);
        contentValues.put(COL_4,number);
        contentValues.put(COL_5,phoneNumber);
        db.update(TABLE_NAME,contentValues,"dob = ?",new String[] {passnum});
        return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

}
