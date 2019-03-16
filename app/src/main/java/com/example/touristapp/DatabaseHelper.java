package com.example.touristapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


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
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_3_NAME + "( " + TABLE_3_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  " + TABLE_3_COL_2 +" INTEGER NOT NULL, " + TABLE_3_COL_3 + " INTEGER NOT NULL," + TABLE_3_COL_4 + " VARCHAR(25) NOT NULL, " +
                "FOREIGN KEY(userId) REFERENCES user(id),  FOREIGN KEY(" + TABLE_3_COL_3 + ") REFERENCES destinations(id)) " );

        // user
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_1_NAME + "( " + TABLE_1_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_1_COL_2 +" VARCHAR(35) NOT NULL, " + TABLE_1_COL_3 +
                   " VARCHAR(50) NOT NULL, " + TABLE_1_COL_4 + " VARCHAR(35) NOT NULL )");


        // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // Create needed instances:
        Resources res = new Resources();

        // Insert destinations:

        setDestinationGroup("Театър", db);     // 1
        setDestinationGroup("Myзей", db);      // 2
        setDestinationGroup("Пещера", db);     // 3
        setDestinationGroup("Паметник", db);   // 4
        setDestinationGroup("Връх", db);       // 5
        setDestinationGroup("Резерват", db);   // 6
        setDestinationGroup("Манастир", db);   // 7
        setDestinationGroup("Друго", db);      // 8


        // Insert destinations in table 1
        addDestination("Александър Невски", res.aleksandurNevskiDesc, res.aleksandurNevskiImg,42.696000, 23.332879, 4 , db );
        addDestination("Античен театър", res.antichenTeaturDesc, res.antichenTeaturImg,42.147777, 24.751123, 1 , db );
        addDestination("Археологически Резерват „Калиакра”", res.rezervatKaliakraDesc, res.rezervatKaliakraImg,43.361190, 28.465788, 6 , db );
        addDestination("Асенова Крепост", res.asenovaKrepostDesc, res.asenovaKrepostImg,41.987020, 24.873552, 8 , db );
        addDestination("Бачковски Манастир", res.bachkovskiManastirDesc, res.bachkovskiManastirImg,41.942380, 24.849340, 7 , db );
        addDestination("Белоградчишки Скали", res.belogradchishkiSkaliDesc, res.belogradchishkiSkaliImg,43.623361, 22.677964, 8 , db );
        addDestination("Вр. Мусала (2925 М.) - Рила", res.mysalaDesc, res.mysalaImg,42.180021, 23.585167, 8 , db );
        addDestination("Връх Снежанка", res.vruhSnejankaDesc, res.vruhSnejankaImg,41.638506, 24.675594, 5 , db );
        addDestination("Връх Шипка", res.shipkaDesc, res.shipkaImg,42.748281, 25.321387, 5 , db );
        addDestination("Мадарски Конник", res.madarskiKonnikDesc, res.madarskiKonnikImg,43.277708, 27.118949, 8 , db );
        addDestination("Национален Музей Параход Радецки", res.radeckiDesc, res.radeckiImg,43.799125, 23.676921, 2 , db );
        addDestination("Паметник На Христо Ботев И Неговата Чета", res.hristoBotevDesc, res.hristoBotevImg,43.798045, 23.677926, 4 , db );
        addDestination("Перперикон Хиперперакион", res.perperikonDesc, res.perperikonImg,41.718126, 25.468954, 8 , db );
        addDestination("Пещера – Пещера „Снежанка“ (Дължина 145 М)", res.peshteraSnejankaDesc, res.peshteraSnejankaImg,42.004459, 24.278645, 3 , db );
        addDestination("Пещера „Леденика“", res.ledenikaDesc, res.ledenikaImg,43.204703, 23.493687, 3 , db );
        addDestination("Резерват „Сребърна“", res.sreburnaDesc, res.sreburnaImg,44.115654, 27.071807, 6 , db );
        addDestination("Седемте рилски езера", res.rilskiEzeraDesc, res.rilskiEzeraImg,42.203413, 23.319871, 8 , db );
        addDestination("Царевец", res.carevecDesc, res.carevecImg,43.084030, 25.652586, 8 , db );
        addDestination("Чудните мостове", res.chydniMostoveDesc, res.chydniMostoveImg,41.819929, 24.581748, 8 , db );
        addDestination("Ягодинска пещера", res.qgodinskaPeshteraDesc, res.qgodinskaPeshteraImg,41.628984, 24.329589, 3 , db );
        //addDestination("Ягодинска пещера", res.qgodinskaPeshteraDesc, res.qgodinskaPeshteraImg,41.628984, 24.329589, 3 , db );

    } // onCreate


      // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    public List<String> pullUserInfo() throws RuntimeException {
        SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from " + TABLE_1_NAME , null);
            int id = res.getColumnIndex(TABLE_1_COL_1);
            int mail = res.getColumnIndex(TABLE_1_COL_2);
            int password = res.getColumnIndex(TABLE_1_COL_3);
            int nickName = res.getColumnIndex(TABLE_1_COL_4);

            ArrayList<String> list= new ArrayList<String>();

            while(res.moveToNext()){
                String col1 = res.getString(mail);
                String col2 = res.getString(password);
                String col3 = res.getString(nickName);
                list.add(col1);
                list.add(col2);
                list.add(col3);
            }

            res.close();
            db.close();
            return list;
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



    public List<String> getDestinations()  throws RuntimeException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_2_NAME, null);

        int id = res.getColumnIndex(TABLE_2_COL_1);
        int destinationName = res.getColumnIndex(TABLE_2_COL_2);
        int destinationDescription = res.getColumnIndex(TABLE_2_COL_3);
        int destinationImg = res.getColumnIndex(TABLE_2_COL_4);
        int latitude = res.getColumnIndex(TABLE_2_COL_5);
        int longitude = res.getColumnIndex(TABLE_2_COL_6);
        int destinationGroupId = res.getColumnIndex(TABLE_2_COL_7);

        List<String> destList = new ArrayList<String>();

        if (res.moveToFirst()) {

            //iterate over rows
            for (int i = 0; i < res.getCount(); i++) {

                //iterate over the columns
                for(int j = 0; j < res.getColumnNames().length; j++){

                    //append the column value to the string builder
                    destList.add(res.getString(j));
                }
                //add a new line carriage return
                destList.add("\n");

                //move to the next row
                res.moveToNext();
            }
        }

            res.close();
            db.close();
            return destList;
    }



    public boolean addDestination(String destinationName, String destinationDescription, String destinationImg, double latitude, double longitude, int destinationGroupId, SQLiteDatabase db){
        //db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_2_COL_2, destinationName);
        contentValues.put(TABLE_2_COL_3, destinationDescription);
        contentValues.put(TABLE_2_COL_4, destinationImg);
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
        SQLiteDatabase db = this.getReadableDatabase();
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


    public boolean setVisit(int userId, int destinationId, String visitDate, SQLiteDatabase db){
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
        SQLiteDatabase db = this.getReadableDatabase();
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

    public boolean setDestinationGroup(String name, SQLiteDatabase db){
        //db = this.getWritableDatabase();
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
        db.update(TABLE_1_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }


    public void emptyTables(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_1_NAME, null, null);
        db.delete(TABLE_2_NAME, null, null);
        db.delete(TABLE_3_NAME, null, null);
        db.delete(TABLE_4_NAME, null, null);

    }

    public void  deleteUser(String mail, String nickName){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TABLE_1_NAME, TABLE_1_COL_2 + "=" + mail + " and " + TABLE_1_COL_4 + "=" + nickName, null);
        db.execSQL("DELETE FROM " + TABLE_1_NAME + " WHERE " + TABLE_1_COL_2 + " = '" + mail + "' AND " + TABLE_1_COL_4 + " = '" + nickName + "' ");
    }

    public List<String> getDestTitleAndDesc(int position){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select destinationName, destinationDescription from " + TABLE_2_NAME + " where id = " + position, null);

        int id = res.getColumnIndex(TABLE_2_COL_1);
        int destinationName = res.getColumnIndex(TABLE_2_COL_2);
        int destinationDescription = res.getColumnIndex(TABLE_2_COL_3);

        List<String> destList = new ArrayList<String>();

        if (res.moveToFirst()) {

            //iterate over rows
            for (int i = 0; i < res.getCount(); i++) {

                //iterate over the columns
                for(int j = 0; j < res.getColumnNames().length; j++){

                    //append the column value to the string builder
                    destList.add(res.getString(j));
                }
                //add a new line carriage return
                destList.add("\n");

                //move to the next row
                res.moveToNext();
            }
        }

        res.close();
        db.close();
        return destList;
    }

}