package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSystem extends SQLiteOpenHelper {

    public static final String DATABASE_NAME=" System.db";
    public static final String TABLE_NAME="system_table";
    public static final String COL_1=" name";
    public static final String COL_2=" username";
    public static final String COL_3=" password";
    public static final String COL_4=" hint";
    public static final String COL_5=" os";

    public DatabaseSystem(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (name TEXT ,username TEXT ,password TEXT ,hint TEXT, OS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  boolean insertData(String name,String username,String password,String hint, String os)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,hint);
        contentValues.put(COL_5,os);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;}
        else
            return  true;
    }
    public Cursor GetTwoData(String named )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where name =?", new String[]{named});
        return res;

    }
    public void QUERYs(String a)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        SystemLogins1.a.clear();
        SystemLogins1.b.clear();
        if(res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase())||
                        res.getString(2).toLowerCase().contains(a.toLowerCase())|| res.getString(3).toLowerCase().contains(a.toLowerCase())) {

                    SystemLogins1.a.add(res.getString(0));
                    SystemLogins1.b.add(res.getString(1));


                }
            } while (res.moveToNext());
        }

    }

    public boolean updateData(String name,String username,String password,String hint, String os, String a,String b){
            SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,hint);
        contentValues.put(COL_5,os);

        db.update(TABLE_NAME,contentValues,"name = ? AND username =?",new String[] {a,b});
        return  true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
