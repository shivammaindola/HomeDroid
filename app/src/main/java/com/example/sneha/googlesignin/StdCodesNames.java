package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class StdCodesNames extends SQLiteOpenHelper {

    public static final String DATABASE_NAME=" Std1.db";
    public static final String TABLE_NAME="Std_table";
    public static final String COL_1=" Std_place";
    public static final String COL_2=" Std_codes";



    public StdCodesNames(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME+" (Std_place TEXT ,Std_codes TEXT )");

    }

    public boolean InsertTheValues()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        try {
            String insertion = "INSERT or replace INTO Std_table (Std_place,Std_codes) VALUES('Andaman and Nicobar Island','11')," +
                    "('Assam','212'),('Bihar','221'),('Himachal Pradesh','116'),('Jammu & Kashmir','28')," +
                    "('Jharkhand','17'),('Karnataka','1087'),('Kerala','234'),('Madhya Pradesh','502')," +
                    "('Maharastra','774'),('Manipur','2'),('Mizoram','1'),('North East','89'),('Orissa','325')," +
                    "('Punjab','532'),('Rajasthan','553'),('Sikkim','10'),('Uttar Pradesh','7'),('Uttar Pradesh East','178')," +
                    "('Uttar Pradesh West','92'),('West Bengal','475')";

            Log.i("Status","Success");
            db.execSQL(insertion);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.i("Status","Failure");
            return false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  boolean insertData(String std_place, String std_code)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,std_place);
        contentValues.put(COL_2,std_code);


        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;}
        else
            return  true;
    }
   /* public boolean update(String num,String name,String place,String issue,String expiry){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,num);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,place);
        contentValues.put(COL_4,issue);
        contentValues.put(COL_5,expiry);
        db.update(TABLE_NAME,contentValues,"passport_number = ?",new String[] {num});
        return  true;
    }*/
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        //SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public   Integer deleteData(String name){
        SQLiteDatabase db=this.getWritableDatabase();
       return  db.delete(TABLE_NAME,"Std_place=?",new String[]{name});
       //db.delete(StdCodesNames.COL_1,null,null);
      // db.delete(StdCodesNames.COL_2,null,null);


    }
}
