package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Passport.db";
    public static final String TABLE_NAME = "passport_table";
    public static final String COL_1 = "holder_name";
    public static final String COL_2 = "passport_num";
    public static final String COL_3 = "placeofissue";
    public static final String COL_4 = "dateofissue";
    public static final String COL_5 = "dateofexpiry";
    //public static final String COL_6 = "image";


    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (holder_name TEXT ,passport_num TEXT PRIMARY KEY, placeofissue TEXT, dateofissue TEXT, dateofexpiry TEXT)");
        db.execSQL("CREATE VIRTUAL TABLE fts_table USING fts3 ( "+COL_1 +","+ COL_2 +","+ COL_3 +","+ COL_4 +","+ COL_5 +","+ ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + "fts_table");
        onCreate(db);
    }


    public boolean insertData(String holdername, String passnum, String placeofissue, String dateofissue,String dateofexpiry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, holdername);
        contentValues.put(COL_2, passnum);
        contentValues.put(COL_3, placeofissue);
        contentValues.put(COL_4, dateofissue);
        contentValues.put(COL_5,dateofexpiry);
       // contentValues.put(COL_6,image);
        long result = db.insert(TABLE_NAME, null, contentValues);
        long resultvirtual=db.insert("fts_table",null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor GetOneData(String named)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where holder_name=?", new String[]{named});
        return res;

    }
    public void QUERYs(String a)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        Passport.a.clear();
        Passport.b.clear();
        if(res.moveToFirst()) {
            do {

                if (res.getString(0).toLowerCase().contains(a.toLowerCase()) || res.getString(1).toLowerCase().contains(a.toLowerCase())||
                        res.getString(2).toLowerCase().contains(a.toLowerCase())|| res.getString(3).toLowerCase().contains(a.toLowerCase())||
                        res.getString(4).toLowerCase().contains(a.toLowerCase())) {

                    Passport.a.add(res.getString(0));
                    Passport.b.add(res.getString(1));


                }
            } while (res.moveToNext());
        }

    }

    public Cursor GetTwoData(String named)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME+ " where passport_num=?", new String[]{named});
        return res;

    }

    public boolean updateData(String holdername, String passnum, String place, String issue,String expiry,String a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, holdername);
        contentValues.put(COL_2, passnum);
        contentValues.put(COL_3, place);
        contentValues.put(COL_4, issue);
        contentValues.put(COL_5, expiry);
       // contentValues.put(COL_6,image);
        db.update(TABLE_NAME, contentValues, "passport_num = ?", new String[]{a});
//        db.update("fts-table",contentValues,"passport_num = ?",new String[]{a});


        return true;
    }


}
