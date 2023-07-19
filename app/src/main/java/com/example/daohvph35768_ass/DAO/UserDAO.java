package com.example.daohvph35768_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.daohvph35768_ass.DTO.UserDTO;
import com.example.daohvph35768_ass.Helper.TMDatabaseHelper;

public class UserDAO {
    //Create Database
    TMDatabaseHelper tmDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;

    public UserDAO(Context context) {
        tmDatabaseHelper = new TMDatabaseHelper(context);
        sqLiteDatabase = tmDatabaseHelper.getWritableDatabase();
    }

    public long insert(UserDTO userDTO) {
        long result = -1;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", userDTO.getUsername());
            contentValues.put("fullname", userDTO.getFullname());
            contentValues.put("password", userDTO.getPassword());
            contentValues.put("email", userDTO.getEmail());
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
            if (cursor.moveToFirst()) {
                result = true;
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //Check email
    public boolean checkUsername(String username) {
        boolean result = false;
        String[] condition = new String[]{username};
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_infLogin WHERE name = ?", condition);
            if (cursor.moveToFirst()) {
                result = true;
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //check account
    public boolean checkAccount(String username, String password) {
        boolean result = false;
        String[] condition = new String[]{
                username, password
        };
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_infLogin WHERE name = ? AND password = ?", condition);
            if (cursor.moveToFirst()) {
                result = true;
            }
            cursor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //check username and email match return boolean
    public boolean checkUsernameAndEmail(String username, String email) {

        //create result variable
        boolean result = false;

        //create condition
        String[] condition = new String[]{
                username, email
        };

        //try catch
        try {

            //create cursor
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_infLogin WHERE name = ? AND email = ?", condition);

            //check cursor if move to first return true
            if (cursor.moveToFirst()) {
                result = true;
            }

            //close cursor because we don't need it anymore
            cursor.close();

        } catch (SQLException e) {

            //print error
            e.printStackTrace();

        }

        //return result
        return result;

    }

    public UserDTO checkUsernameandEmailAfter(String username, String email) {
        UserDTO userDTO = null;

        String[] condition = new String[]{username, email};

        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_infLogin WHERE name = ? AND email = ?", condition);

            if (cursor.moveToFirst()) {
                userDTO = new UserDTO();

                userDTO.setId(cursor.getInt(0));
                userDTO.setUsername(cursor.getString(1));
                userDTO.setFullname(cursor.getString(2));
                userDTO.setEmail(cursor.getString(4));
                userDTO.setPassword(cursor.getString(3));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDTO;
    }
    //Get id by username and email return int
    public int getIdByUsernameAndEmail(String username, String email) {

        //create result variable
        int result = -1;

        //create condition
        String[] condition = new String[]{username, email};

        //try catch
        try {

            //create cursor
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_infLogin WHERE name = ? AND email = ?", condition);

            //check cursor if move to first return true
            if (cursor.moveToFirst()) {

                //set result is id
                result = cursor.getInt(0);
            }

            //close cursor because we don't need it anymore
            cursor.close();

        } catch (SQLException e) {

            //print error
            e.printStackTrace();

        }

        //return result
        return result;

    }
    //check if password match return boolean
    public boolean checkPassword(String password) {

        //create result variable
        boolean result = false;

        //create condition
        String[] condition = new String[]{
                password
        };

        //try catch
        try {

            //create cursor
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM infLogin WHERE password = ?", condition);

            //check cursor if move to first return true
            if (cursor.moveToFirst()) {

                //set result to true if cursor move to first because password match
                result = true;

            }

            cursor.close();

        } catch (SQLException e) {

            //print error
            e.printStackTrace();

        }

        //return result
        return result;
    }
    //Update user return int
    public int updateUser(UserDTO userDTO) {

        //create result variable
        int result = -1;

        //create content values
        ContentValues contentValues = new ContentValues();

        //put data to content values
        contentValues.put("name", userDTO.getUsername());
        contentValues.put("fullname", userDTO.getFullname());
        contentValues.put("email", userDTO.getEmail());
        contentValues.put("password", userDTO.getPassword());

        //create condition
        String[] condition = new String[]{String.valueOf(userDTO.getId())};

        //try catch
        try {
            //update user
            result = sqLiteDatabase.update("tbl_infLogin", contentValues, "id = ?", condition);

        } catch (SQLException e) {

            //print error
            e.printStackTrace();

        }

        //return result
        return result;

    }
}
