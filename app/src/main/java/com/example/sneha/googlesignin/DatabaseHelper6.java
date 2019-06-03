package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper6 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME=" membership.db";
    public static final String TABLE_NAME="membership_table";
    public static final String COL_1=" name";
    public static final String COL_2=" number";
    public static final String COL_3=" expiry";
    public static final String COL_4=" holder";

    public  DatabaseHelper6(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (name TEXT ,number TEXT ,expiry TEXT,holder,TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  boolean insertData(String name,String number,String expiry,String holder)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,number);
        contentValues.put(COL_3,expiry);
        contentValues.put(COL_4,holder);


        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;}
        else
            return  true;
    }
    public void QUERYs(String a)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Cursor res = db.rawQuery("select * from " + "fts_table"+ " where fts_table MATCH ?", new String[]{a});
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        membership1.a.clear();
        membership1.b.clear();
        if(res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase())||
                        res.getString(2).toLowerCase().contains(a.toLowerCase())||res.getString(3).toLowerCase().contains(a.toLowerCase())) {

                    membership1.a.add(res.getString(0));
                    membership1.b.add(res.getString(1));


                }
            } while (res.moveToNext());
        }

    }
    public boolean update(String name,String number,String expiry,String holder,String a){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,number);
        contentValues.put(COL_3,expiry);
        contentValues.put(COL_4,holder);

        db.update(TABLE_NAME,contentValues,"number = ?",new String[] {a});
        return  true;
    }
    public Cursor GetTwoData(String named)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where number=?", new String[]{named});
        return res;

    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
