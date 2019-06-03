package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOnline  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME=" Online.db";
    public static final String TABLE_NAME="online_table";
    public static final String COL_1=" account";
    public static final String COL_2=" mail";
    public static final String COL_3=" password";
    public static final String COL_4=" phone";
    public static final String COL_5=" holder";
    public DatabaseOnline(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (account TEXT ,mail TEXT ,password TEXT ,phone TEXT,holder TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  boolean insertData(String account,String mail,String password,String phone,String holder)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,account);
        contentValues.put(COL_2,mail);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,phone);
        contentValues.put(COL_5,holder);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;}
        else
            return  true;
    }
    public void QUERYs(String a)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        OnlineSites1.a.clear();
        OnlineSites1.b.clear();
        if(res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase())||
                        res.getString(2).toLowerCase().contains(a.toLowerCase())
                        || res.getString(3).toLowerCase().contains(a.toLowerCase())
                ||res.getString(4).toLowerCase().contains(a.toLowerCase())) {

                    OnlineSites1.a.add(res.getString(0));
                    OnlineSites1.b.add(res.getString(1));


                }
            } while (res.moveToNext());
        }

    }
    public boolean updateData(String account,String mail,String password,String phone,String holder,String a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,account);
        contentValues.put(COL_2,mail);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,phone);
        contentValues.put(COL_5,holder);
        db.update(TABLE_NAME, contentValues, "mail = ?", new String[]{a});
        return true;
    }
    public Cursor GetTwoData(String named)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where mail =?", new String[]{named});
        return res;

    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
