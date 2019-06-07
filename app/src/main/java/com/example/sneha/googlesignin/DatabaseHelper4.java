package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper4 extends SQLiteOpenHelper {

    SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");

    public static final String DATABASE_NAME=" Card3.db";
    public static final String TABLE_NAME="Card_table";
    public static final String COL_1="name";
    public static final String COL_2="Debit_Number";
    public static final String COL_3="Card_name";
    public static final String COL_4="expiry_date";
    public static final String COL_5="Security_code";
    public static final String COL_6="upi";
    public static final String COL_7="registered_mobile";
    public static final String COL_8="registered_id";
    public static final String COL_9="debitcredit";
    public static final String COL_10="type";
    public static final String COL_11="n1";
    public static final String COL_12="n2";
    public static final String COL_13="n3";
    public static final String COL_14="validfrom";
    public static final String COL_15="pin";

    public  DatabaseHelper4(Context context){
        super(context,DATABASE_NAME,null,3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (name TEXT ,Debit_Number TEXT,Card_name TEXT,expiry_date TEXT,Security_code TEXT,upi TEXT,registered_mobile TEXT,registered_id TEXT" +
                ",debitcredit TEXT,type TEXT,n1 TEXT,n2 TEXT,n3 TEXT,validfrom TEXT,pin TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  boolean insertData(String name,String Debit_Number,String Card_name,String expiry_date,String Security_code,String upi,String registered_mobile,
                               String registered_id,String debitcredit,String type,String n1,String n2,String n3,String validfrom,String pin)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,Debit_Number);
        contentValues.put(COL_3,Card_name);
        contentValues.put(COL_4,expiry_date);
        contentValues.put(COL_5,Security_code);
        contentValues.put(COL_6,upi);
        contentValues.put(COL_7,registered_mobile);
        contentValues.put(COL_8,registered_id);
        contentValues.put(COL_9,debitcredit);
        contentValues.put(COL_10,type);
        contentValues.put(COL_11,n1);
        contentValues.put(COL_12,n2);
        contentValues.put(COL_13,n3);
        contentValues.put(COL_14,validfrom);
        contentValues.put(COL_15,pin);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {return false;}
        else
            return  true;
    }
    public Cursor GetTwoData(String named)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where Debit_number=?", new String[]{named});
        return res;

    }
    public void QUERYs(String a)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        BankingCard1.a.clear();
        BankingCard1.b.clear();
        if(res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase())||
                        res.getString(2).toLowerCase().contains(a.toLowerCase())|| formatMonthYear(res.getString(3)).toLowerCase().contains(a.toLowerCase())||
                        res.getString(4).toLowerCase().contains(a.toLowerCase())|| res.getString(5).toLowerCase().contains(a.toLowerCase())
                        || res.getString(6).toLowerCase().contains(a.toLowerCase())|| res.getString(7).toLowerCase().contains(a.toLowerCase())
                        || res.getString(8).toLowerCase().contains(a.toLowerCase())|| res.getString(9).toLowerCase().contains(a.toLowerCase())
                        ||  formatMonthYear(res.getString(13)).toLowerCase().contains(a.toLowerCase())
                        ||  formatMonthYear(res.getString(14)).toLowerCase().contains(a.toLowerCase())) {

                    BankingCard1.a.add(res.getString(0));
                    BankingCard1.b.add(res.getString(1));


                }
            } while (res.moveToNext());
        }

    }

    public boolean update(String name,String Debit_Number,String Card_name,String expiry_date,
                          String Security_code,String upi,String registered_mobile,String registered_id,
                          String debitcredit,String type,String n1,String n2,String n3,String validfrom,String pin,String a){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,Debit_Number);
        contentValues.put(COL_3,Card_name);
        contentValues.put(COL_4,expiry_date);
        contentValues.put(COL_5,Security_code);
        contentValues.put(COL_6,upi);
        contentValues.put(COL_7,registered_mobile);
        contentValues.put(COL_8,registered_id);
        contentValues.put(COL_9,debitcredit);
        contentValues.put(COL_10,type);
        contentValues.put(COL_11,n1);
        contentValues.put(COL_12,n2);
        contentValues.put(COL_13,n3);
        contentValues.put(COL_14,validfrom);
        contentValues.put(COL_15,pin);

        db.update(TABLE_NAME,contentValues,"Debit_Number= ?",new String[] {a});
        return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    String formatMonthYear(String str) {
        Date date = null;
        try {
            date = input.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }



}
