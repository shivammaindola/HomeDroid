package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseH extends SQLiteOpenHelper {
    public static final String DATABASE_NAME=" Social.db";
    public static final String TABLE_NAME="Social_table";
    public static final String COL_1=" name";
    public static final String COL_2=" username";
    public static final String COL_3=" pass";
    public static final String COL_4=" question";
    public static final String COL_5=" answer";
    public static final String COL_6=" recovery";
    public static final String COL_7="n1";
    public static final String COL_8="holder";

    public DatabaseH(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (name TEXT ,username TEXT ,pass TEXT ,question TEXT ,answer TEXT ,recovery TEXT,n1 TEXT,holder TEXT)");
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  boolean insertData(String name,String username,String pass,String question,String answer,String recovery,String n,String holder)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,pass);
        contentValues.put(COL_4,question);
        contentValues.put(COL_5,answer);
        contentValues.put(COL_6,recovery);
        contentValues.put(COL_7,n);
        contentValues.put(COL_8,holder);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;}
        else
            return  true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Cursor GetTwoData(String named)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where name=?", new String[]{named});
        return res;

    }
    public void QUERYs(String a)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        SocialSites1.a.clear();
        SocialSites1.b.clear();
        if(res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase())||
                        res.getString(2).toLowerCase().contains(a.toLowerCase())|| res.getString(3).toLowerCase().contains(a.toLowerCase())
                        || res.getString(4).toLowerCase().contains(a.toLowerCase())||res.getString(7).toLowerCase().contains(a.toLowerCase())
                        || res.getString(5).toLowerCase().contains(a.toLowerCase())) {

                    SocialSites1.a.add(res.getString(0));
                    SocialSites1.b.add(res.getString(1));


                }
            } while (res.moveToNext());
        }

    }
    public Cursor GetTwoData(String a,String b)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where name=? AND username=?", new String[]{a,b});
        return res;

    }
    public boolean update(String name,String username,String pass,String question,String answer,String recovery,String n,String holder,String a,String b){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,pass);
        contentValues.put(COL_4,question);
        contentValues.put(COL_5,answer);
        contentValues.put(COL_6,recovery);
        contentValues.put(COL_7,n);
        contentValues.put(COL_8,holder);

        db.update(TABLE_NAME,contentValues,"name= ? and username= ?",new String[] {a,b});
        return  true;
    }
}
