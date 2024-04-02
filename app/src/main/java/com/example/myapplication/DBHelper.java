package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class   DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "SocietyDB4.db";

    public DBHelper(@Nullable Context context) {
        super(context, "SocietyDB4.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users (mobnum TEXT primary key, password TEXT, fullname TEXT, email VARCHAR,hsnum VARCHAR);");
        MyDB.execSQL("Create Table request2 (id INTEGER PRIMARY KEY AUTOINCREMENT, mobnum VARCHAR, requesttype VARCHAR, fullname VARCHAR, description VARCHAR,housenum VARCHAR, status VARCHAR, requesttime TEXT, completedtime TEXT);");

    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop table if exists users;");
        MyDB.execSQL("drop table if exists request2;");

        onCreate(MyDB);
    }



    public boolean insertData(String mobnum, String password, String fullname, String email, String hsnum) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mobnum", mobnum);
        contentValues.put("password", password);
        contentValues.put("fullname", fullname);
        contentValues.put("email", email);
        contentValues.put("hsnum", hsnum);

        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public long insertRequest(String mobnum, String RequestType, String fullname, String description, String HouseNum, String Status, String requesttime) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("status", Status);
        contentValues.put("description", description);
        contentValues.put("fullname", fullname);
        contentValues.put("mobnum", mobnum);
        contentValues.put("housenum", HouseNum);
        contentValues.put("requesttype", RequestType);
        contentValues.put("requesttime", requesttime);





        long result = MyDB.insertOrThrow("request2", null, contentValues);
        return result;
//        if (result == -1) return false;
//        else
//            return true;

    }

    public Cursor viewData(String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from request2 where status = 'Active' and mobnum = '"+ userId +"'";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor viewStat(String dateFrom, String dateTo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select requesttype, status, count(*)\n" +
                "From request2\n" +
                "where requesttime >= Datetime('"+dateFrom+"') "+" and requesttime <= Datetime('"+dateTo+"')"+
                "Group by 1, 2\n" +
                "Order by 1, 2;";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor viewCompletedRequest(String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from request2 where status = 'Completed' and mobnum = '"+ userId +"'";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor viewPlumbingRequest() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from request2 where requesttype = 'Plumbing' ";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor viewElectricalRequest() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from request2 where requesttype = 'Electricity' ";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor viewCarpentryRequests() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from request2 where requesttype = 'Carpentry' ";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor viewGardeningRequest() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from request2 where requesttype = 'Gardening' ";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }



    public Boolean setRequestComplete(String id, String completetime){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("status", "Completed");
        contentValues.put("completedtime", completetime);

        long result = MyDB.update("request2", contentValues, "id=?", new String[]{id});
        if (result == -1) return false;
        else
            return true;
    }





    public Boolean checkmobnum(String mobnum) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where mobnum = ?", new String[]{mobnum});

        if (cursor.getCount() > 0) return true;
        else
            return false;
    }

    public Boolean checkmobnumpassword(String mobnum, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where mobnum = ? and password = ?", new String[] {mobnum,password});
//        Log.w("DBhelper", "cursor valu"+cursor);
        if(cursor.getCount()>0) return true;
        else
            return false;
    }



}


