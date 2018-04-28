package com.example.mikhal.wiseatapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SearchIngredients extends AppCompatActivity {
    String ingredients;
    Button btnSearch;
    String[] notClearIngredientsArr=  new String[50];
    String[] notClearProfilesArr=  new String[50];
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDb = new DatabaseHelper(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ingredients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSearch = (Button)findViewById(R.id.btnSearch);

        search();
    }

   public void search(){
       btnSearch.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v){

                       SearchView searchWord = (SearchView) findViewById(R.id.search);
                       CharSequence query = searchWord.getQuery();
                       ingredients=  query.toString();


                       for (String retval: ingredients.split(",")) {
                           searchInDb(retval);
                       }
                   }
               }
       );
   }
    public void searchInDb(String ingredient) {

        Cursor DBIngredient = myDb.getIngredientFromDb(ingredient);
        if (DBIngredient.getCount() == 0) {
            showMessage("Error", "Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();


        String family = null;
        while (DBIngredient.moveToNext()) {
            family = DBIngredient.getString(1);
            buffer.append("ingredient :" + DBIngredient.getString(0) + "\n");
            buffer.append("family :" + DBIngredient.getString(1) + "\n");
        }


        Integer ingredientRate = myDb.getRateFromPRofile(family);
        buffer.append("avoidence rate :" + ingredientRate + "\n");
        showMessage("Data", buffer.toString());


        return;

/*
        if(IngredientRate == 0){
            notClearIngredientsArr[i]= ingredient;
            //notClearProfilesArr[i]=profile;
        }
        else if(IngredientRate == 1){
            notClearIngredientsArr[i]= ingredient;
            //notClearProfilesArr[i]=profile;

        }
        else if(IngredientRate == 2){
            notClearIngredientsArr[i]= ingredient;
            //notClearProfilesArr[i]=profile;
        }

        //IngredientRate == null
        else{

        }*/
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    }
