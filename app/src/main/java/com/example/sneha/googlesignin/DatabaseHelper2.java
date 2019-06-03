package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME=" PanCard.db";
    public static final String TABLE_NAME="Pan_table";
    public static final String COL_1=" name";
    public static final String COL_2=" fathers_name";
    public static final String COL_3=" dob";
    public static final String COL_4=" number";

    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (name TEXT ,fathers_name TEXT ,dob TEXT ,number TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  boolean insertData(String name,String father,String dob,String number)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,father);
        contentValues.put(COL_3,dob);
        contentValues.put(COL_4,number);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;}
        else
            return  true;
    }
    public Cursor GetTwoData(String named)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where number=?", new String[]{named});
        return res;

    }
    public void QUERYs(String a)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Cursor res = db.rawQuery("select * from " + "fts_table"+ " where fts_table MATCH ?", new String[]{a});
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
     pancard1.a.clear();
        pancard1.b.clear();
        if(res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase())||
                        res.getString(2).toLowerCase().contains(a.toLowerCase())|| res.getString(3).toLowerCase().contains(a.toLowerCase())) {

                    pancard1.a.add(res.getString(0));
                    pancard1.b.add(res.getString(3));


                }
            } while (res.moveToNext());
        }

    }

    public boolean updateData(String name,String father,String dob,String number,String a){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,father);
        contentValues.put(COL_3,dob);
        contentValues.put(COL_4,number);

        db.update(TABLE_NAME,contentValues,"number = ?",new String[] {a});
        return  true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
