package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME=" Passport.db";
    public static final String TABLE_NAME="Passport_table";
    public static final String COL_1=" passport_number";
    public static final String COL_2=" name";
    public static final String COL_3=" place_of_issue";
    public static final String COL_4=" date_of_issue";
    public static final String COL_5=" expiry_date";
   // public static final String COL_6=" image";


    public  DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME+" (passport_number TEXT ,name TEXT ,place_of_issue TEXT ,date_of_issue TEXT ,expiry_date TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
     db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
     onCreate(db);
    }
    public  boolean insertData(String num, String name, String place, String issue, String expiry)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,num);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,place);
        contentValues.put(COL_4,issue);
        contentValues.put(COL_5,expiry);
      //  contentValues.put(COL_6, String.valueOf(image));
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;}
        else
            return  true;
    }
    public boolean update(String num,String name,String place,String issue,String expiry){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,num);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,place);
        contentValues.put(COL_4,issue);
        contentValues.put(COL_5,expiry);
        db.update(TABLE_NAME,contentValues,"passport_number = ?",new String[] {num});
         return  true;
    }
   public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
   }
}
