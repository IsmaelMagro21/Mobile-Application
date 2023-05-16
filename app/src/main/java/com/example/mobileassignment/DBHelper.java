package com.example.mobileassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db"; //name of DB

    //The constructor initializes the DBHelper class by calling the super class's constructor with the database name Login.db and version 1.
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    //This method creates a table named users with two columns: "username" (TEXT) and "password" (TEXT).
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");

    }

    //This method is called when the database needs to be upgraded, i.e., when the version number is increased.
    @Override
    public void onUpgrade(SQLiteDatabase MyDB   , int i, int x) {
        MyDB.execSQL("drop Table if exists users");

    }

    //This method inserts a new row into the users table with the provided username and password values.
    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result==1) return false;
        else
            return true;


    }

    //This method checks if a username exists in the "users" table.
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where Username = ?", new String[] {username});
        if (cursor.getCount()>0)
                return true;
        else
            return false;

    }
    //This method checks if a username and password combination exists in the users table
    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
