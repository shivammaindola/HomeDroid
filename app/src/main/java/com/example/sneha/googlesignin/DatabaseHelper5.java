package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper5 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME=" accountcredit1.db";
    public static final String TABLE_NAME="account_table";
    public static final String COL_1=" name";
    public static final String COL_2=" number";
    public static final String COL_3=" holder";
    public static final String COL_4=" ifsc";
    public static final String COL_5=" nominee";
    public static final String COL_6=" relation";
    public static final String COL_7=" profile";
    public static final String COL_8=" n";
    public  DatabaseHelper5(Context context){
        super(context,DATABASE_NAME,null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (name TEXT ,number TEXT ,holder TEXT ,ifsc TEXT,nominee TEXT,relation TEXT,profile TEXT,n,TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  boolean insertData(String name,String number,String holder,String ifsc,String nominee,String relation, String profile,String n)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,number);
        contentValues.put(COL_3,holder);
        contentValues.put(COL_4,ifsc);
        contentValues.put(COL_5,nominee);
        contentValues.put(COL_6,relation);
        contentValues.put(COL_7,profile);
        contentValues.put(COL_8,n);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;}
        else
            return  true;
    }
    public boolean update(String name,String number,String holder,String ifsc,String nominee,String relation, String profile,String n,String a){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,number);
        contentValues.put(COL_3,holder);
        contentValues.put(COL_4,ifsc);
        contentValues.put(COL_5,nominee);
        contentValues.put(COL_6,relation);
        contentValues.put(COL_7,profile);
        contentValues.put(COL_8,n);
        db.update(TABLE_NAME,contentValues,"number = ?",new String[] {a});
        return  true;
    }
    public void QUERYs(String a)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Cursor res = db.rawQuery("select * from " + "fts_table"+ " where fts_table MATCH ?", new String[]{a});
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        Account1.a.clear();
        Account1.b.clear();
        if(res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase())||
                        res.getString(2).toLowerCase().contains(a.toLowerCase())|| res.getString(3).toLowerCase().contains(a.toLowerCase())
                ||res.getString(4).toLowerCase().contains(a.toLowerCase())||res.getString(5).toLowerCase().contains(a.toLowerCase())
                ||res.getString(6).toLowerCase().contains(a.toLowerCase())||res.getString(7).toLowerCase().contains(a.toLowerCase())) {

                    Account1.a.add(res.getString(0));
                    Account1.b.add(res.getString(1));


                }
            } while (res.moveToNext());
        }

    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Cursor GetTwoData(String named)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where number=?", new String[]{named});
        return res;

    }



}
