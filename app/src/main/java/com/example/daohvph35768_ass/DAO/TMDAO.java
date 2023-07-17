package com.example.daohvph35768_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.daohvph35768_ass.DTO.TMDTO;
import com.example.daohvph35768_ass.Helper.TMDatabaseHelper;

public class TMDAO {
    //Create Database
    TMDatabaseHelper tmDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;

    public TMDAO(Context context) {
        tmDatabaseHelper = new TMDatabaseHelper(context);
        sqLiteDatabase = tmDatabaseHelper.getWritableDatabase();
    }

    public long insert(TMDTO tmdto) {
        long result = -1;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", tmdto.getUsername());
            contentValues.put("fullname", tmdto.getFullname());
            contentValues.put("password", tmdto.getPassword());
            contentValues.put("email", tmdto.getEmail());
            result = sqLiteDatabase.insert("tbl_infLogin", null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //Check user
    public boolean checkUser(String username) {
        boolean result = false;
        String[] condition = new String[]{username};
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_infLogin WHERE username = ?", condition);
            if (cursor.moveToFirst()){
                result = true;
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //Check email
    public boolean checkEmail(String email) {
        boolean result = false;
        String[] condition = new String[]{email};
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_infLogin WHERE email = ?", condition);
            if (cursor.moveToFirst()){
                result = true;
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //check account
    public boolean checkAccount(String email, String password){
        boolean result = false;
        String[] condition = new String[]{
                email, password
        };
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_ìnLogin WHERE email = ? AND password = ?", condition);
            if (cursor.moveToFirst()) {
                result = true;
            }
            cursor.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }



}
