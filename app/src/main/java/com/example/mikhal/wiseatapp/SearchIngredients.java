package com.example.mikhal.wiseatapp;

import android.content.Intent;
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
    DatabaseHelper myDb;
    public static boolean isOCR = false;
    public static String ocrString;
    String tmp;
    static int[] neverFamily;
    static int[] occasionallyFamily;
    String[] resOcr;
    static StringBuffer buffer = new StringBuffer();
    static int cntUnknown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDb = new DatabaseHelper(this);
        cntUnknown=0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ingredients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        neverFamily = new int[20];
        for (int i = 0; i < 20; i++) neverFamily[i] = 0;
        occasionallyFamily = new int[20];
        for (int i = 0; i < 20; i++) occasionallyFamily[i] = 0;

        if (isOCR == true) {
            isOCR = false;
            tmp = ocrString;
            resOcr = deleteSpacesAndSplit(tmp);

            searchOcr(resOcr);
        }

        search();
    }

    private String[] deleteSpacesAndSplit(String str) {
        if (str.contains(",")) {
            String[] ingStrings = new String[str.split(",").length];
            int cnt = 0;


            for (String s : str.split(",")) { //foreach word

                s = s.replace("\n", " ");
                String tmp = s.replaceAll(" {2,}", " ");
                tmp = tmp.toLowerCase();

                if (tmp.charAt(tmp.length() - 1) == ' ')
                    tmp = tmp.substring(0, tmp.length() - 1);
                if (tmp.charAt(0) == ' ')
                    tmp = tmp.substring(1, tmp.length());

                ingStrings[cnt] = tmp;
                cnt++;
            }
            return ingStrings;
        } else {
            String[] ingStrings = new String[1];

            String s = str.replace("\n", " ");
            String tmp = s.replaceAll(" {2,}", " ");
            tmp = tmp.toLowerCase();

            if (tmp.charAt(tmp.length() - 1) == ' ')
                tmp = tmp.substring(0, tmp.length() - 1);
            if (tmp.charAt(0) == ' ')
                tmp = tmp.replace(" ", "");

            ingStrings[0] = tmp;

            return ingStrings;
        }
    }


    public void search() {
        btnSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] str;
                        SearchView searchWord = (SearchView) findViewById(R.id.search);
                        CharSequence query = searchWord.getQuery();
                        ingredients = query.toString();

                        if (ingredients != null && ingredients.equals("") == false) {
                            str = deleteSpacesAndSplit(ingredients);

                            for (String s : str) {
                                searchInDb(s);
                            }
                        } else {
                            Toast.makeText(getApplication(), "Please enter ingredients", Toast.LENGTH_SHORT).show();
                        }

                        str = null;
                    }
                }
        );
    }

    public void searchOcr(String[] ocr) {

        for (String s : ocr) {
            searchInDb(s);
        }

        resOcr = null;
    }

    public void searchInDb(String ingredient) {

        buffer = new StringBuffer();

        Cursor DBIngredient = myDb.getIngredientFromDb(ingredient);
        if (DBIngredient.getCount() == 0) {
            cntUnknown++;
            buffer.append(ingredient+"\n");
        }

        String family = null;
        while (DBIngredient.moveToNext()) {
            family = DBIngredient.getString(1);

            updateProfileArrays(family);
        }

        startActivity(new Intent(getApplicationContext(), ResultView.class));

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private void updateProfileArrays(String family) {
        int rate = myDb.getRateFromProfile(family);

        switch (family) {
            case "beef":
                if(rate == 2)
                    occasionallyFamily[0]=1;
                else if(rate == 1)
                    neverFamily[0]=1;
                break;
            case "chiken":
                if(rate == 2)
                    occasionallyFamily[1]=1;
                else if(rate == 1)
                    neverFamily[1]=1;
                break;
            case "pork":
                if(rate == 2)
                    occasionallyFamily[2]=1;
                else if(rate == 1)
                    neverFamily[2]=1;
                break;
            case "fish":
                if(rate == 2)
                    occasionallyFamily[3]=1;
                else if(rate == 1)
                    neverFamily[3]=1;
                break;
            case "insects":
                if(rate == 2)
                    occasionallyFamily[4]=1;
                else if(rate == 1)
                    neverFamily[4]=1;
                break;
            case "eggs":
                if(rate == 2)
                    occasionallyFamily[5]=1;
                else if(rate == 1)
                    neverFamily[5]=1;
                break;
            case "milk":
                if(rate == 2)
                    occasionallyFamily[6]=1;
                else if(rate == 1)
                    neverFamily[6]=1;
                break;
            case "honey":
                if(rate == 2)
                    occasionallyFamily[7]=1;
                else if(rate == 1)
                    neverFamily[7]=1;
                break;
            case "gluten":
                if(rate == 2)
                    occasionallyFamily[8]=1;
                else if(rate == 1)
                    neverFamily[8]=1;
                break;
            case "lupin":
                if(rate == 2)
                    occasionallyFamily[9]=1;
                else if(rate == 1)
                    neverFamily[9]=1;
                break;
            case "sesame":
                if(rate == 2)
                    occasionallyFamily[10]=1;
                else if(rate == 1)
                    neverFamily[10]=1;
                break;
            case "algae":
                if(rate == 2)
                    occasionallyFamily[11]=1;
                else if(rate == 1)
                    neverFamily[11]=1;
                break;
            case "shellfish":
                if(rate == 2)
                    occasionallyFamily[12]=1;
                else if(rate == 1)
                    neverFamily[12]=1;
                break;
            case "soy":
                if(rate == 2)
                    occasionallyFamily[13]=1;
                else if(rate == 1)
                    neverFamily[13]=1;
                break;
            case "peanuts":
                if(rate == 2)
                    occasionallyFamily[14]=1;
                else if(rate == 1)
                    neverFamily[14]=1;
                break;
            case "sulphite":
                if(rate == 2)
                    occasionallyFamily[15]=1;
                else if(rate == 1)
                    neverFamily[15]=1;
                break;
            case "nuts":
                if(rate == 2)
                    occasionallyFamily[16]=1;
                else if(rate == 1)
                    neverFamily[16]=1;
                break;
            case "mustard":
                if(rate == 2)
                    occasionallyFamily[17]=1;
                else if(rate == 1)
                    neverFamily[17]=1;
                break;
            case "celery":
                if(rate == 2)
                    occasionallyFamily[18]=1;
                else if(rate == 1)
                    neverFamily[18]=1;
                break;
            case "corn":
                if(rate == 2)
                    occasionallyFamily[19]=1;
                else if(rate == 1)
                    neverFamily[19]=1;
                break;
            default:
                return;
        }
        return;
    }
}
