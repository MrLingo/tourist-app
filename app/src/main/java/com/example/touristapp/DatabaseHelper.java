package com.example.touristapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "touristApp.db";

    public static final String TABLE_1_NAME = "userInfo";
    public static final String TABLE_1_COL_1 = "ID";
    public static final String TABLE_1_COL_2 = "mail";
    public static final String TABLE_1_COL_3 = "password";
    public static final String TABLE_1_COL_4 = "nickName";
    public static final String TABLE_1_COL_5 = "visitedDestinationsId"; // FOREIGN KEY TO destinationInfo (ID)( 1-M )

    public static final String TABLE_2_NAME = "destinationInfo";
    public static final String TABLE_2_COL_1 = "ID";
    public static final String TABLE_2_COL_2 = "destinationName";
    public static final String TABLE_2_COL_3 = "destinationRegion";
    public static final String TABLE_2_COL_4 = "destinationImg";
    public static final String TABLE_2_COL_5 = "destinationDescription";

    // Separated table with no relationships
    public static final String TABLE_3_NAME = "visitedDestinations";
    public static final String TABLE_3_COL_1 = "nickName";
    public static final String TABLE_3_COL_2 = "destinationName";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE IF EXISTS userInfo");

        // userInfo
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_1_NAME + "( " + TABLE_1_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_1_COL_2 +" VARCHAR(35) NOT NULL," + TABLE_1_COL_3 +
                   " VARCHAR(50) NOT NULL, " + TABLE_1_COL_4 + " VARCHAR(35) NOT NULL, " + TABLE_1_COL_5 + " INTEGER, FOREIGN KEY(" + TABLE_1_COL_5 + ") REFERENCES destinationInfo(ID))");

        // DestinationInfo
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_2_NAME + "( " + TABLE_2_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_2_COL_2 +" VARCHAR(35) NOT NULL," + TABLE_2_COL_3 +
                " VARCHAR(40) NOT NULL, " + TABLE_2_COL_4 + " TEXT(250) NOT NULL, " + TABLE_2_COL_5 + " VARCHAR(150))");

        // visitedDestinations
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_3_NAME + "( " + TABLE_3_COL_1 + " VARCHAR(35) NOT NULL, " + TABLE_3_COL_2 +" VARCHAR(35))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_1_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_2_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_3_NAME);
        onCreate(db);
    }

    public boolean addUser(String mail, String password, String nickName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_1_COL_2, mail);
        contentValues.put(TABLE_1_COL_3, password);
        contentValues.put(TABLE_1_COL_4, nickName);
        long result = db.insert(TABLE_1_NAME, null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }


    public String[] pullUserInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor res = db.rawQuery("select mail, password, nickName from " + TABLE_1_NAME , null);
            int id = res.getColumnIndex(TABLE_1_COL_1);
            int mail = res.getColumnIndex(TABLE_1_COL_2);
            int password = res.getColumnIndex(TABLE_1_COL_3);
            int nickName = res.getColumnIndex(TABLE_1_COL_4);
            int visitedDestId = res.getColumnIndex(TABLE_1_COL_5);
            String[] result = new String[5];
            for( res.moveToFirst(); !res.isAfterLast(); res.moveToNext()){
                result[0] = res.getString(mail);
                result[1] = res.getString(password);
                result[2] = res.getString(nickName);
            }
            return result;

        }catch (Exception e){
             String[] falseStr = new String[1];
             falseStr[0] = "No such user!";
             return falseStr;
        }
    }

    public boolean updateUserInfo(String id, String mail, String password, String nickName, int visitedDestinations){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_1_COL_1, id);
        contentValues.put(TABLE_1_COL_2, mail);
        contentValues.put(TABLE_1_COL_3, password);
        contentValues.put(TABLE_1_COL_4, nickName);
        contentValues.put(TABLE_1_COL_5, visitedDestinations);
        db.update(TABLE_1_NAME, contentValues, "ID = ?", new String[] {id});
        return true;
    }


    public void reCreateTable1(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_1_NAME);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_1_NAME + "( " + TABLE_1_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_1_COL_2 +" VARCHAR(35) NOT NULL, " + TABLE_1_COL_3 +
                " VARCHAR(50) NOT NULL, " + TABLE_1_COL_4 + " VARCHAR(35) NOT NULL, " + TABLE_1_COL_5 + " INTEGER, FOREIGN KEY(" + TABLE_1_COL_5 + ") REFERENCES destinationInfo(ID))");
    }
    /*
    public boolean addDestination(){
        SQLiteDatabase db = this.getWritableDatabase();

    }

    public boolean mapUserToDestination(){
        SQLiteDatabase db = this.getWritableDatabase();

    }
    */
}
