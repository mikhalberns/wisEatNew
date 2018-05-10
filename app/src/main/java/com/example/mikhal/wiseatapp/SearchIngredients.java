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
    public static boolean isOCR=false;
    public static String ocrString;
    String tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDb = new DatabaseHelper(this);
        String [] resOcr;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ingredients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSearch = (Button)findViewById(R.id.btnSearch);

       if(isOCR==true)
        {
            isOCR=false;
            tmp = ocrString;
            resOcr = deleteSpacesAndSplit(tmp);

            searchOcr(resOcr);

            resOcr = null;
        }

        search();
    }

    private String [] deleteSpacesAndSplit(String str)
    {
        if(str.contains(","))
        {
            String [] ingStrings = new String[str.split(",").length];
            int cnt = 0;


            for (String s: str.split(",")) { //foreach word

                s = s.replace("\n", " ");
                String tmp = s.replaceAll(" {2,}", " ");
                tmp = tmp.toLowerCase();

                if(tmp.charAt(tmp.length()-1)==' ')
                    tmp = tmp.substring(0,tmp.length()-1);
                if(tmp.charAt(0)==' ')
                    tmp = tmp.substring(1,tmp.length());

                ingStrings[cnt]=tmp;
                cnt++;
            }
            return ingStrings;
        }
        else
        {
            String [] ingStrings = new String[1];

            String s = str.replace("\n", " ");
            String tmp = s.replaceAll(" {2,}", " ");
            tmp = tmp.toLowerCase();

            if(tmp.charAt(tmp.length()-1)==' ')
                tmp = tmp.substring(0,tmp.length()-1);
            if(tmp.charAt(0)==' ')
                tmp = tmp.replace(" ","");

            ingStrings[0]=tmp;

            return ingStrings;
        }
    }


    public void search(){
        btnSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        String [] str;
                        SearchView searchWord = (SearchView) findViewById(R.id.search);
                        CharSequence query = searchWord.getQuery();
                        ingredients=  query.toString();

                        if(ingredients!=null && ingredients.equals("")==false)
                        {
                            str = deleteSpacesAndSplit(ingredients);

                            for (String s: str) {
                                searchInDb(s);
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplication(), "Please enter ingredients", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public void searchOcr(String [] ocr){

        for (String s: ocr) {
            searchInDb(s);
        }
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


        /*Integer ingredientRate = myDb.getRateFromPRofile(family);
        buffer.append("avoidence rate :" + ingredientRate + "\n");*/
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
