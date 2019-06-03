package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseInsurance  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME=" Insurance.db";
    public static final String TABLE_NAME="Insurance_table";
    public static final String COL_1=" name";
    public static final String COL_2=" plantype";
    public static final String COL_3=" policyname";
    public static final String COL_4=" number";
    public static final String COL_5=" duration";
    public static final String COL_6=" premium";
    public static final String COL_7=" frequency";
    public static final String COL_8=" duedate";
    public static final String COL_9=" n";
    public static final String COL_10=" holder";
    public  DatabaseInsurance(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (name TEXT ,plantype TEXT ,policyname TEXT,number TEXT,duration TEXT,premium TEXT,frequency TEXT,duedate TEXT,n TEXT,holder TEXT )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  boolean insertData(String name,String plantype,String policyname,String number,String duration,String premium,String frequency,String duedate,String n,String holder)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,plantype);
        contentValues.put(COL_3,policyname);
        contentValues.put(COL_4,number);
        contentValues.put(COL_5,duration);
        contentValues.put(COL_6,premium);
        contentValues.put(COL_7,frequency);
        contentValues.put(COL_8,duedate);
        contentValues.put(COL_9,n);
        contentValues.put(COL_10,holder);

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
        Insurance1.a.clear();
        Insurance1.b.clear();
        if(res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase())||
                        res.getString(2).toLowerCase().contains(a.toLowerCase())|| res.getString(3).toLowerCase().contains(a.toLowerCase())
                        ||res.getString(4).toLowerCase().contains(a.toLowerCase())||res.getString(5).toLowerCase().contains(a.toLowerCase())
                        ||res.getString(6).toLowerCase().contains(a.toLowerCase())||res.getString(7).toLowerCase().contains(a.toLowerCase())
                        ||res.getString(9).toLowerCase().contains(a.toLowerCase())) {

                  Insurance1.a.add(res.getString(0));
                    Insurance1.b.add(res.getString(3));


                }
            } while (res.moveToNext());
        }

    }
    public Cursor GetTwoData(String named)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where name=?", new String[]{named});
        return res;

    }
    public Cursor GetTwoData(String a,String b)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where name=? AND number=?", new String[]{a,b});
        return res;

    }

    public boolean updateData(String name,String plantype,String policyname,String number,String duration,String premium,String frequency,String duedate,String n,String holder,String a,String b){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,plantype);
        contentValues.put(COL_3,policyname);
        contentValues.put(COL_4,number);
        contentValues.put(COL_5,duration);
        contentValues.put(COL_6,premium);
        contentValues.put(COL_7,frequency);
        contentValues.put(COL_8,duedate);
        contentValues.put(COL_9,n);
        contentValues.put(COL_10,holder);
        db.update(TABLE_NAME,contentValues,"name = ? and number = ?",new String[] {a,b});
        return  true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

}
