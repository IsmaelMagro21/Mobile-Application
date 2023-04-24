package com.example.mobileassignment.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(Username TEXT primary key, Password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB   , int i, int x) {
        MyDB.execSQL("drop Table if exists users");

    }

    public Boolean insertData(String Username, String Password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", Username);
        contentValues.put("Password", Password);
        long result = MyDB.insert("users", null, contentValues);
        if (result==1) return false;
        else
            return true;


    }

    public Boolean checkusername(String Username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where Username = ?", new String[] {Username});
        if (cursor.getCount()>0)
                return true;
        else
            return false;

    }
    public Boolean checkusernamepassword(String Username, String Password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where Username = ? and Password = ?", new String[] {Username,Password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
