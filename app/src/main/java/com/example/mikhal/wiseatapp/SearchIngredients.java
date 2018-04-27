package com.example.mikhal.wiseatapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
    //Ingredients csv

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                        // here we can send the string- ingredients to the function that will transform the string to individual ingredients
                   }
               }
       );
   }

}
