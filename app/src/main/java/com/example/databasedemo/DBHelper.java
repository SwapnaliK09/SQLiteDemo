package com.example.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "info_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "mycourses";

    private static final String ID_COL = "id";
    private static final String API_COL = "api";
    private static final String DESCRIPTION_COL = "description";
    private static final String AUTH_COL = "auth";




    public DBHelper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + API_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + AUTH_COL + " TEXT)";

        sqLiteDatabase.execSQL(query);


    }

    public void insertNewInfo(String api,String description, String auth) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        UserModel userModel = new UserModel();
        values.put(API_COL, api);
        values.put(DESCRIPTION_COL,description);
        values.put(AUTH_COL,auth);
        db.insert(TABLE_NAME, null, values);
        db.close();
//        return db;
    }



    public ArrayList<UserModel> getAllInfo() {
        return MainActivity.userModelArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
