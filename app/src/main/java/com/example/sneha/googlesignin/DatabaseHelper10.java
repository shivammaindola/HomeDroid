package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper10 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME=" todo.db";
    public static final String TABLE_NAME="todo_table";
    public static final String COL_1=" list1";
    public static final String COL_2=" list2";
    public static final String COL_3=" list3";
    public static final String COL_4=" list4";
    public static final String COL_5=" list5";
    public static final String COL_6=" list6";
    public static final String COL_7=" list7";
    public static final String COL_8=" list8";
    public static final String COL_9=" list9";
    public DatabaseHelper10(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (list1 TEXT ,list2 TEXT,list3 TEXT,list4 TEXT,list5 TEXT,list6 TEXT,list7 TEXT,list8 TEXT,list9)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  boolean insertData(String list1,String list2,String list3,String list4,String list5,String list6,String list7,String list8,String list9)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,list1);
        contentValues.put(COL_2,list2);
        contentValues.put(COL_3,list3);
        contentValues.put(COL_4,list4);
        contentValues.put(COL_5,list5);
        contentValues.put(COL_6,list6);
        contentValues.put(COL_7,list7);
        contentValues.put(COL_8,list8);
        contentValues.put(COL_9,list9);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;}
        else
            return  true;
    }
    public boolean update(String list1,String list2,String list3,String list4,String list5,String list6,String list7,String list8,String list9){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,list1);
        contentValues.put(COL_2,list2);
        contentValues.put(COL_3,list3);
        contentValues.put(COL_4,list4);
        contentValues.put(COL_5,list5);
        contentValues.put(COL_6,list6);
        contentValues.put(COL_7,list7);
        contentValues.put(COL_8,list8);
        contentValues.put(COL_9,list9);


        db.update(TABLE_NAME,contentValues,"list = ?",new String[] {list1});
        return  true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
