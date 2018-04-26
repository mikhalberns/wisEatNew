package com.example.mikhal.wiseatapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // profiles table
    public static final String WISEATAPP_DATABASE = "wiseatApp.db";
    public static final String PROFILES_TABLE = "profiles_table";
    public static final String PROFILEID = "profileID";
    public static final String BEEF = "beef";
    public static final String CHICKEN = "chicken";
    public static final String PORK = "pork";
    public static final String FISH = "fish";
    public static final String INSECTS = "Insects";
    public static final String EGGS = "eggs";
    public static final String MILK = "milk";
    public static final String HONEY = "honey";
    public static final String GLUTEN = "gluten";
    public static final String LUPIN = "lupin";
    public static final String SESAME = "sesame";
    public static final String ALGAE = "algae";
    public static final String SHELLFISH = "shellfish";
    public static final String SOY = "soy";
    public static final String PEANUTS = "peanuts";
    public static final String SULPHITE = "sulphite";
    public static final String NUTS = "nuts";
    public static final String MUSTARD = "mustard";
    public static final String CELERY = "celery";
    public static final String CORN = "corn";

    // users table
    public static final String USERS_TABLE = "usersTable";
    public static final String USER_ID = "userId";
    public static final String IS_ACTIVE = "isActive";

    //ingredients table
    public static  final String ING_TABLE = "ingredientsTable";
    public static  final  String INGREDIENT = "ingredient";
    public static final String FAMILY = "family";


    public DatabaseHelper(Context context) {
        super(context, WISEATAPP_DATABASE, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USERS_TABLE + " (userId TEXT PRIMARY KEY, profileID INTEGER, isActive INTEGER)");
        db.execSQL("create table " + ING_TABLE + " (ingredient TEXT, family TEXT)");
        db.execSQL("create table " + PROFILES_TABLE +" (profileID INTEGER PRIMARY KEY autoincrement,beef INTEGER,chicken INTEGER,pork INTEGER,fish INTEGER,Insects INTEGER,eggs INTEGER,milk INTEGER,honey INTEGER,gluten INTEGER,lupin INTEGER,sesame INTEGER,algae INTEGER,shellfish INTEGER,soy INTEGER,peanuts INTEGER,sulphite INTEGER,nuts INTEGER,mustard INTEGER,celery INTEGER,corn INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+PROFILES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+USERS_TABLE);
        onCreate(db);
    }

    public boolean insertData(Integer beef,Integer chicken,Integer pork,Integer fish,Integer Insects, Integer eggs,Integer milk,Integer honey,Integer gluten,Integer lupin,Integer sesame, Integer algae, Integer shellfish, Integer soy, Integer peanuts,Integer sulphite,Integer nuts, Integer mustard,Integer celery, Integer corn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BEEF,beef);
        contentValues.put(CHICKEN,chicken);
        contentValues.put(PORK,pork);
        contentValues.put(FISH,fish);
        contentValues.put(INSECTS,Insects);
        contentValues.put(EGGS,eggs);
        contentValues.put(MILK,milk);
        contentValues.put(HONEY,honey);
        contentValues.put(GLUTEN,gluten);
        contentValues.put(LUPIN,lupin);
        contentValues.put(SESAME,sesame);
        contentValues.put(ALGAE,algae);
        contentValues.put(SHELLFISH,shellfish);
        contentValues.put(SOY,soy);
        contentValues.put(PEANUTS,peanuts);
        contentValues.put(SULPHITE,sulphite);
        contentValues.put(NUTS,nuts);
        contentValues.put(MUSTARD,mustard);
        contentValues.put(CELERY,celery);
        contentValues.put(CORN,corn);

        long result = db.insert(PROFILES_TABLE,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertUIDToUsers(String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID,userId);
        contentValues.put(IS_ACTIVE,1);

        long result = db.insert(USERS_TABLE,null ,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public void deactivateAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IS_ACTIVE,0);
        db.update("usersTable",contentValues,null,null);
    }

    public void matchProfileToUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select MAX(profileID) from profiles_table", null);
        if(res.getCount()!=0) {
            res.moveToFirst();
            int data = res.getInt(0);

            ContentValues contentValues = new ContentValues();
            contentValues.put(PROFILEID,data);

            db.update("usersTable",contentValues,"isActive=1",null);
        }
    }


    public void activateUser (String userId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IS_ACTIVE,1);

        db.update("usersTable",contentValues,"userId='"+userId+"'",null);
}

    public boolean checkIfUIDExist(String userId) {
        //query that checks if the userId exist
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from usersTable where userId='"+userId+"'", null);

        if(res.getCount()==0)
            return false;
        else
            return true;
    }

    public void truncateTables()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + PROFILES_TABLE);
        db.execSQL("delete from " + USERS_TABLE);
    }
}
