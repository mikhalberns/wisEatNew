package com.example.mikhal.wiseatapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "wiseat.db";
    public static final String TABLE_NAME = "profile_table";
    public static final String COL_1 = "profileID";
    public static final String COL_2 = "profileName";
    public static final String COL_3 = "beef";
    public static final String COL_4 = "chicken";
    public static final String COL_5 = "pork";
    public static final String COL_6 = "fish";
    public static final String COL_7 = "Insects";
    public static final String COL_8 = "eggs";
    public static final String COL_9 = "milk";
    public static final String COL_10 = "honey";
    public static final String COL_11 = "gluten";
    public static final String COL_12 = "lupin";
    public static final String COL_13 = "sesame";
    public static final String COL_14 = "algae";
    public static final String COL_15 = "shellfish";
    public static final String COL_16 = "soy";
    public static final String COL_17 = "peanuts";
    public static final String COL_18 = "sulphite";
    public static final String COL_19 = "nuts";
    public static final String COL_20 = "mustard";
    public static final String COL_21 = "celery";
    public static final String COL_22 = "corn";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (profileID INTEGER PRIMARY KEY AUTOINCREMENT,profileName TEXT,beef BOOLEAN,chicken BOOLEAN,pork BOOLEAN,fish BOOLEAN,Insects BOOLEAN,eggs BOOLEAN,milk BOOLEAN,honey BOOLEAN,gluten BOOLEAN,lupin BOOLEAN,sesame BOOLEAN,algae BOOLEAN,shellfish BOOLEAN,soy BOOLEAN,peanuts BOOLEAN,sulphite BOOLEAN,nuts BOOLEAN,mustard BOOLEAN,celery BOOLEAN,corn BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
