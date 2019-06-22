package com.example.sneha.googlesignin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper13 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ComponyProfile.db";
    public static final String TABLE_NAME = "Compony_Table";
    public static final String COL_1 = "NameVenture";
    public static final String COL_2 = "PAN";
    public static final String COL_3 = "TAN";
    public static final String COL_4 = "NumberDirector";
    public static final String COL_5 = "BankName";
    public static final String COL_6 = "AccountNumber";
    public static final String COL_7 = "IFSCCode";
    public static final String COL_8 = "ComponyID";
    public static final String COL_9 = "EStatementPass1";
    public static final String COL_10 = "EStatementPass2";
    public static final String COL_11 = "NetBankingID";
    public static final String COL_12 = "NetBankingPass";
    public static final String COL_13 = "MPIN";
    public static final String COL_14 = "VPA";
    public static final String COL_15 = "GSTINNumber";
    public static final String COL_16 = "GSTPortalID";
    public static final String COL_17 = "GSTPortalPassword";
    public static final String COL_18 = "MailID";
    public static final String COL_19 = "MailPass";
    public static final String COL_20 = "DomainID";
    public static final String COL_21 = "DomainPass";
    public static final String COL_22 = "RailwayID";
    public static final String COL_23 = "RailwayPass";
    public static final String COL_24 = "AccountHolderName";
    public static final String COL_25 = "DOB";
    public static final String COL_26 = "EmailID";
    public static final String COL_27 = "PhoneNumber";
    public static final String COL_28 = "FacebookID";
    public static final String COL_29 = "FacebookPass";
    public static final String COL_30 = "AmazonID";
    public static final String COL_31 = "AmazonPass";
    public static final String COL_32 = "AWSID";
    public static final String COL_33 = "AWSPass";
    public static final String COL_34 = "AWSIDParse";
    public static final String COL_35 = "AWSPassParse";
    public static final String COL_36 = "CIN";
    public static final String COL_37 = "DOI";
    public static final String COL_38 = "HO_Address";


    public DatabaseHelper13(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (NameVenture TEXT, PAN TEXT, TAN TEXT, NumberDirector TEXT, " +
                "BankName TEXT, AccountNumber TEXT, IFSCCode TEXT, ComponyID TEXT, EStatementPass1 TEXT, EStatementPass2 TEXT, NetBankingID TEXT, NetBankingPass TEXT, MPIN TEXT, VPA TEXT, " +
                "GSTINNumber TEXT, GSTPortalID TEXT, GSTPortalPassword TEXT, " +
                "MailID TEXT, MailPass TEXT, " +
                "DomainID TEXT, DomainPass TEXT, " +
                "RailwayID TEXT, RailwayPass TEXT, AccountHolderName TEXT, DOB TEXT, EmailID TEXT, PhoneNumber TEXT, " +
                "FacebookID TEXT, FacebookPass TEXT, " +
                "AmazonID TEXT, AmazonPass TEXT, " +
                "AWSID TEXT, AWSPass TEXT, AWSIDParse TEXT, AWSPassParse TEXT, " +
                "CIN TEXT, DOI TEXT, HO_ADDRESS TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13,
                              String a14, String a15, String a16, String a17, String a18, String a19, String a20, String a21, String a22, String a23, String a24, String a25, String a26,
                              String a27, String a28, String a29, String a30, String a31, String a32, String a33, String a34, String a35, String a36, String a37, String a38){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, a1);
        contentValues.put(COL_2, a2);
        contentValues.put(COL_3, a3);
        contentValues.put(COL_4, a4);
        contentValues.put(COL_5, a5);
        contentValues.put(COL_6, a6);
        contentValues.put(COL_7, a7);
        contentValues.put(COL_8, a8);
        contentValues.put(COL_9, a9);
        contentValues.put(COL_10, a10);
        contentValues.put(COL_11, a11);
        contentValues.put(COL_12, a12);
        contentValues.put(COL_13, a13);
        contentValues.put(COL_14, a14);
        contentValues.put(COL_15, a15);
        contentValues.put(COL_16, a16);
        contentValues.put(COL_17, a17);
        contentValues.put(COL_18, a18);
        contentValues.put(COL_19, a19);
        contentValues.put(COL_20, a20);
        contentValues.put(COL_21, a21);
        contentValues.put(COL_22, a22);
        contentValues.put(COL_23, a23);
        contentValues.put(COL_24, a24);
        contentValues.put(COL_25, a25);
        contentValues.put(COL_26, a26);
        contentValues.put(COL_27, a27);
        contentValues.put(COL_28, a28);
        contentValues.put(COL_29, a29);
        contentValues.put(COL_30, a30);
        contentValues.put(COL_31, a31);
        contentValues.put(COL_32, a32);
        contentValues.put(COL_33, a33);
        contentValues.put(COL_34, a34);
        contentValues.put(COL_35, a35);
        contentValues.put(COL_36, a36);
        contentValues.put(COL_37, a37);
        contentValues.put(COL_38, a38);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else
            return true;
    }

    // Update data....
    public boolean updateData(String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13,
                              String a14, String a15, String a16, String a17, String a18, String a19, String a20, String a21, String a22, String a23, String a24, String a25, String a26,
                              String a27, String a28, String a29, String a30, String a31, String a32, String a33, String a34, String a35,
                              String a36,String a37,String a38,String passnum) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, a1);
        contentValues.put(COL_2, a2);
        contentValues.put(COL_3, a3);
        contentValues.put(COL_4, a4);
        contentValues.put(COL_5, a5);
        contentValues.put(COL_6, a6);
        contentValues.put(COL_7, a7);
        contentValues.put(COL_8, a8);
        contentValues.put(COL_9, a9);
        contentValues.put(COL_10, a10);
        contentValues.put(COL_11, a11);
        contentValues.put(COL_12, a12);
        contentValues.put(COL_13, a13);
        contentValues.put(COL_14, a14);
        contentValues.put(COL_15, a15);
        contentValues.put(COL_16, a16);
        contentValues.put(COL_17, a17);
        contentValues.put(COL_18, a18);
        contentValues.put(COL_19, a19);
        contentValues.put(COL_20, a20);
        contentValues.put(COL_21, a21);
        contentValues.put(COL_22, a22);
        contentValues.put(COL_23, a23);
        contentValues.put(COL_24, a24);
        contentValues.put(COL_25, a25);
        contentValues.put(COL_26, a26);
        contentValues.put(COL_27, a27);
        contentValues.put(COL_28, a28);
        contentValues.put(COL_29, a29);
        contentValues.put(COL_30, a30);
        contentValues.put(COL_31, a31);
        contentValues.put(COL_32, a32);
        contentValues.put(COL_33, a33);
        contentValues.put(COL_34, a34);
        contentValues.put(COL_35, a35);
        contentValues.put(COL_36, a36);
        contentValues.put(COL_37, a37);
        contentValues.put(COL_38, a38);

        db.update(TABLE_NAME, contentValues, "NameVenture = ?", new String[]{passnum});
        return true;
    }

    //Return data According to the value...
    public Cursor getData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where NameVenture=?", new String[]{name});
        return res;
    }

    // Returning all the data in database...
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
