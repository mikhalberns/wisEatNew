package com.example.mikhal.wiseatapp;

import android.content.ContentValues;
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
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (profileID INTEGER PRIMARY KEY AUTOINCREMENT,profileName TEXT,beef INTEGER,chicken INTEGER,pork INTEGER,fish INTEGER,Insects INTEGER,eggs INTEGER,milk INTEGER,honey INTEGER,gluten INTEGER,lupin INTEGER,sesame INTEGER,algae INTEGER,shellfish INTEGER,soy INTEGER,peanuts INTEGER,sulphite INTEGER,nuts INTEGER,mustard INTEGER,celery INTEGER,corn INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

   public boolean insertData(Integer beef,Integer chicken,Integer pork,Integer fish,Integer Insects, Integer eggs,Integer milk,Integer honey,Integer gluten,Integer lupin,Integer sesame, Integer algae, Integer shellfish, Integer soy, Integer peanuts,Integer sulphite,Integer nuts, Integer mustard,Integer celery, Integer corn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3,beef);
        contentValues.put(COL_4,chicken);
        contentValues.put(COL_5,pork);
       contentValues.put(COL_6,fish);
       contentValues.put(COL_7,Insects);
       contentValues.put(COL_8,eggs);
       contentValues.put(COL_9,milk);
       contentValues.put(COL_10,honey);
       contentValues.put(COL_11,gluten);
       contentValues.put(COL_12,lupin);
       contentValues.put(COL_13,sesame);
       contentValues.put(COL_14,algae);
       contentValues.put(COL_15,shellfish);
       contentValues.put(COL_16,soy);
       contentValues.put(COL_17,peanuts);
       contentValues.put(COL_18,sulphite);
       contentValues.put(COL_19,nuts);
       contentValues.put(COL_20,mustard);
       contentValues.put(COL_21,celery);
       contentValues.put(COL_22,corn);


        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
}
