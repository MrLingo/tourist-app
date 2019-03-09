package com.example.touristapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "touristApp.db";

    // users
    public static final String TABLE_1_NAME = "user";
    public static final String TABLE_1_COL_1 = "id";
    public static final String TABLE_1_COL_2 = "mail";
    public static final String TABLE_1_COL_3 = "password";
    public static final String TABLE_1_COL_4 = "nickName";

    // destinations
    public static final String TABLE_2_NAME = "destinations";
    public static final String TABLE_2_COL_1 = "id";
    public static final String TABLE_2_COL_2 = "destinationName";
    public static final String TABLE_2_COL_3 = "destinationImg";
    public static final String TABLE_2_COL_4 = "destinationDescription";
    public static final String TABLE_2_COL_5 = "latitude";
    public static final String TABLE_2_COL_6 = "longitude";
    public static final String TABLE_2_COL_7 = "destinationGroupId";


    // visits
    public static final String TABLE_3_NAME = "visits";
    public static final String TABLE_3_COL_1 = "id";
    public static final String TABLE_3_COL_2 = "userId";
    public static final String TABLE_3_COL_3 = "destinationId";
    public static final String TABLE_3_COL_4 = "visitDate";

    // destinations group
    public static final String TABLE_4_NAME = "destinationGroup";
    public static final String TABLE_4_COL_1 = "id";
    public static final String TABLE_4_COL_2 = "name";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE IF EXISTS user");

        //destinationGroup
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_4_NAME + "( " + TABLE_4_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  " + TABLE_4_COL_2 +" VARCHAR(20) NOT NULL)");

        // DestinationInfo
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_2_NAME + "( " + TABLE_2_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_2_COL_2 +" VARCHAR(35) NOT NULL," + TABLE_2_COL_3 +
                " TEXT(40) NOT NULL, " + TABLE_2_COL_4 + " VARCHAR(250) NOT NULL, " + TABLE_2_COL_5 + " REAL(15) NOT NULL, "+ TABLE_2_COL_6 + " REAL(15) NOT NULL, " + TABLE_2_COL_7 + " INTEGER NOT NULL," +
                " FOREIGN KEY(destinationGroupId) REFERENCES destinationGroup(id) )");

        // visits
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_3_NAME + "( " + TABLE_3_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  " + TABLE_3_COL_2 +" INTEGER NOT NULL," + TABLE_3_COL_3 + "INTEGER NOT NULL," + TABLE_3_COL_4 + " VARCHAR(25) NOT NULL, " +
                "FOREIGN KEY(userId) REFERENCES user(id),  FOREIGN KEY(destinationId) REFERENCES destinations(id)) " );

        // user
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_1_NAME + "( " + TABLE_1_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_1_COL_2 +" VARCHAR(35) NOT NULL," + TABLE_1_COL_3 +
                   " VARCHAR(50) NOT NULL, " + TABLE_1_COL_4 + " VARCHAR(35) NOT NULL )");


        // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // Create needed instances:
       // Resources resources = new Resources();

        // Insert destinations:
       // destinationGroupInstance.setDestinationGroup("Театър");
      //  destinationGroupInstance.setDestinationGroup("Myзей");
      //  destinationGroupInstance.setDestinationGroup("Пещера");

        // Insert destinations in table 1
      //  destinationsInstance.addDestination("Античен театър", resources.antichenTeaturDesc, "",42.147777, 24.751123, 1 );

    } // onCreate


    // ---------------------------------------------------------------------------------------------------------------------------------


    public String[] pullUserInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor res = db.rawQuery("select mail, password, nickName from " + TABLE_1_NAME , null);
            int id = res.getColumnIndex(TABLE_1_COL_1);
            int mail = res.getColumnIndex(TABLE_1_COL_2);
            int password = res.getColumnIndex(TABLE_1_COL_3);
            int nickName = res.getColumnIndex(TABLE_1_COL_4);

            String[] result = new String[4];
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



    public String[] getDesinations(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor res = db.rawQuery("select destinationName, destinationImg, destinationDescription, latitude, longitude, destinationGroupId from " + TABLE_2_NAME , null);
            int id = res.getColumnIndex(TABLE_2_COL_1);
            int destinationName = res.getColumnIndex(TABLE_2_COL_2);
            int destinationImg = res.getColumnIndex(TABLE_2_COL_3);
            int destinationDescription = res.getColumnIndex(TABLE_2_COL_4);
            int latitude = res.getColumnIndex(TABLE_2_COL_5);
            int longitude = res.getColumnIndex(TABLE_2_COL_6);
            int destinationGroupId = res.getColumnIndex(TABLE_2_COL_7);

            String[] result = new String[7];
            for( res.moveToFirst(); !res.isAfterLast(); res.moveToNext()){
                result[0] = res.getString(destinationName);
                result[1] = res.getString(destinationImg);
                result[2] = res.getString(destinationDescription);
                result[3] = res.getString(latitude);
                result[4] = res.getString(longitude);
                result[5] = res.getString(destinationGroupId);
            }
            return result;

        }catch (Exception e){
            String[] falseStr = new String[1];
            falseStr[0] = "Error!";
            return falseStr;
        }

    }

    public boolean addDestination(String destinationName, String destinationDescription, String destinationImg, double latitude, double longitude, int destinationGroupId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_2_COL_2, destinationName);
        contentValues.put(TABLE_2_COL_3, destinationImg);
        contentValues.put(TABLE_2_COL_4, destinationDescription);
        contentValues.put(TABLE_2_COL_5, latitude);
        contentValues.put(TABLE_2_COL_6, longitude);
        contentValues.put(TABLE_2_COL_7, destinationGroupId);
        long result = db.insert(TABLE_2_NAME, null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }


    public String[] getVisits(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor res = db.rawQuery("select userId, destinationId, visitDate from " + TABLE_3_NAME , null);
            int id = res.getColumnIndex(TABLE_3_COL_1);
            int userId = res.getColumnIndex(TABLE_3_COL_2);
            int destinationId = res.getColumnIndex(TABLE_3_COL_3);
            int visitDate = res.getColumnIndex(TABLE_3_COL_4);

            String[] result = new String[4];
            for( res.moveToFirst(); !res.isAfterLast(); res.moveToNext()){
                result[0] = res.getString(userId);
                result[1] = res.getString(destinationId);
                result[2] = res.getString(visitDate);
            }
            return result;

        }catch (Exception e){
            String[] falseStr = new String[1];
            falseStr[0] = "Error!";
            return falseStr;
        }
    }


    public boolean setVisit(int userId, int destinationId, String visitDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_3_COL_2, userId);
        contentValues.put(TABLE_3_COL_3, destinationId);
        contentValues.put(TABLE_3_COL_4, visitDate);

        long result = db.insert(TABLE_3_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public String[] getDestinationGroup(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor res = db.rawQuery("select name from " + TABLE_4_NAME , null);
            int id = res.getColumnIndex(TABLE_4_COL_1);
            int name = res.getColumnIndex(TABLE_4_COL_2);
            String[] result = new String[2];
            for( res.moveToFirst(); !res.isAfterLast(); res.moveToNext()){
                result[0] = res.getString(name);
            }
            return result;

        }catch (Exception e){
            String[] falseStr = new String[1];
            falseStr[0] = "Error!";
            return falseStr;
        }
    }

    public boolean setDestinationGroup(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_4_COL_2, name);
        long result = db.insert(TABLE_4_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    // When changes are made to the database this method is called:
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_1_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_2_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_3_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_4_NAME);

        // Create tables again
        onCreate(db);
    }

    public boolean updateUserInfo(String id, String mail, String password, String nickName, int visitedDestinations){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_1_COL_1, id);
        contentValues.put(TABLE_1_COL_2, mail);
        contentValues.put(TABLE_1_COL_3, password);
        contentValues.put(TABLE_1_COL_4, nickName);
        db.update(TABLE_1_NAME, contentValues, "ID = ?", new String[] {id});
        return true;
    }


    public void reCreateTable1(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_1_NAME);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_1_NAME + "( " + TABLE_1_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_1_COL_2 +" VARCHAR(35) NOT NULL," + TABLE_1_COL_3 +
                " VARCHAR(50) NOT NULL, " + TABLE_1_COL_4 + " VARCHAR(35) NOT NULL )");
    }

}