package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper7 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME=" wifi.db";
    public static final String TABLE_NAME="wifi_table";
    public static final String COL_1=" name";
    public static final String COL_2=" pass";
    public static final String COL_3 = "wifiId";


    public  DatabaseHelper7(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (name TEXT ,pass TEXT, wifiId TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  boolean insertData(String name, String pass, String wifiId)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,pass);
        contentValues.put(COL_3, wifiId);


        long result=db.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return  true;
    }
    public boolean update(String name, String pass, String wifiId, String a){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,pass);
        contentValues.put(COL_3, wifiId);



        db.update(TABLE_NAME,contentValues,"name = ?",new String[] {a});
        return  true;

    }

    public void QUERYs(String a)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Cursor res = db.rawQuery("select * from " + "fts_table"+ " where fts_table MATCH ?", new String[]{a});
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        Wifipass1.a.clear();

        if(res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(2).toLowerCase().contains(a.toLowerCase())){
                    Wifipass1.a.add(res.getString(0));

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
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where name =?", new String[]{named});
        return res;

    }

}


