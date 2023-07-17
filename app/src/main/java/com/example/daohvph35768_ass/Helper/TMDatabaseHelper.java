package com.example.daohvph35768_ass.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TMDatabaseHelper extends SQLiteOpenHelper {

    private static final String nameDatabase = "infLogin";
    private static final Integer version = 1;
    public TMDatabaseHelper(Context context){super(context,nameDatabase,null,version);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table inflogin including  id, username, email. password, fullname.
    String sqlInfLogin = "CREATE TABLE tbl_infLogin (\n" +
            "    id       INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    name     TEXT    NOT NULL,\n" +
            "    fullname TEXT    NOT NULL,\n" +
            "    password TEXT    NOT NULL,\n" +
            "    email    TEXT    NOT NULL\n" +
            ");\n";
    db.execSQL(sqlInfLogin);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
