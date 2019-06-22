package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper11 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME=" other.db";
    public static final String TABLE_NAME="other_table";
    public static final String COL_1="question";
    public static final String COL_2="answer";
    public static final String COL_3="formName";
    public  DatabaseHelper11(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (question TEXT ,answer TEXT, formName TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String question,String answer, String formName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,question);
        contentValues.put(COL_2,answer);
        contentValues.put(COL_3,formName);



        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;}
        else
            return  true;
    }
    public boolean update(String question,String answer, String named){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,question);
        contentValues.put(COL_2,answer);

        db.update(TABLE_NAME,contentValues,"question = ?",new String[] {named});
        return true;
    }

    public Cursor getTwoData(String passnum){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where question=?", new String[]{passnum});
        return res;
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
