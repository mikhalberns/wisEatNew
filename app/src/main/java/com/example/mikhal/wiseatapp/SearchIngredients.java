package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

/************************************************SearchIngredients.java*****************************************************
 This class is responsible for ingredients search (all ingredients - both from OCR and from the manual search.
 **************************************************************************************************************************/
public class SearchIngredients extends AppCompatActivity {

    private String ingredients;
    private Button btnSearch;
    private Button btnBack;
    private DatabaseHelper myDb;
    public static boolean isOCR = false;
    public static String ocrString;
    public String tmp;
    public static int[] neverFamily;
    public static int[] occasionallyFamily;
    private String[] resOcr;
    public static String buffer = "";
    public static int cntUnknown;
    private SearchView s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDb = new DatabaseHelper(this);
        cntUnknown=0;

        buffer="";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ingredients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        s = (SearchView) findViewById(R.id.search);
        s.setIconifiedByDefault(false);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnBack = (Button) findViewById(R.id.backB);
        btnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                    }
                });
        neverFamily = new int[20];
        for (int i = 0; i < 20; i++) neverFamily[i] = 0;
        occasionallyFamily = new int[20];
        for (int i = 0; i < 20; i++) occasionallyFamily[i] = 0;

        //OCR Case
        if (isOCR == true) {
            isOCR = false;
            tmp = ocrString;
            resOcr = deleteSpacesAndSplit(tmp);

            searchOcr(resOcr);
        }

        //Manual Case
        search();
    }

    //converts the String (from OCR & Manual Search) to the same format (also delete unnecessary spaces)
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

    //Case Of Manual search - after press the search button. search each one of the ingredients in the DB
    public void search() {
        btnSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buffer ="";
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

    //Case Of OCR search - after press check button in the ocr view. search each one of the ingredients in the DB
    public void searchOcr(String[] ocr) {

        buffer ="";

        for (String s : ocr) {

            searchInDb(s);
        }

        resOcr = null;
    }

    //Search each ingredient in the DB and classifies it acorrdinly to the user's profile
    public void searchInDb(String ingredient) {

        if(myDb.checkIfExistInRecoveryTable()==false)
        {
            myDb.insertRowsForNewUserInRecovery();
        }

        Cursor DBIngredient = myDb.getIngredientFromDb(ingredient);
        if (DBIngredient.getCount() == 0) {
            cntUnknown++;
            if(buffer.equals(""))
                buffer = buffer + ingredient;
            else
                buffer = buffer +", "+ ingredient;
        }

        String family = null;
        while (DBIngredient.moveToNext()) {
            family = DBIngredient.getString(1);

            if(family.equals("null")==false)
            {
                if(family.equals("beef")==true || family.equals("chicken")==true || family.equals("pork")==true|| family.equals("fish")==true|| family.equals("insects")==true
                        || family.equals("eggs")==true|| family.equals("milk")==true || family.equals("honey")==true|| family.equals("gluten")==true
                        || family.equals("lupin")==true|| family.equals("sesame")==true|| family.equals("algae")==true|| family.equals("shellfish")==true
                        || family.equals("soy")==true|| family.equals("peanuts")==true|| family.equals("sulphite")==true|| family.equals("nuts")==true
                        || family.equals("mustrad")==true|| family.equals("celery")==true|| family.equals("corn")==true)
                {
                    updateProfileArrays(family);
                }

            }
        }

        startActivity(new Intent(getApplicationContext(), ResultView.class));

    }

    //classifies each ingredient in the right category (never/ocasionally eat) and update choices data in the DB
    private void updateProfileArrays(String family) {
        int rate = myDb.getRateFromProfile(family);

        switch (family) {
            case "beef":
                if(rate == 2)
                {
                    occasionallyFamily[0]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[0]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "chiken":
                if(rate == 2)
                {
                    occasionallyFamily[1]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[1]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "pork":
                if(rate == 2)
                {
                    occasionallyFamily[2]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[2]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "fish":
                if(rate == 2)
                {
                    occasionallyFamily[3]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[3]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "insects":
                if(rate == 2)
                {
                    occasionallyFamily[4]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[4]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "eggs":
                if(rate == 2)
                {
                    occasionallyFamily[5]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[5]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "milk":
                if(rate == 2)
                {
                    occasionallyFamily[6]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[6]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "honey":
                if(rate == 2)
                {
                    occasionallyFamily[7]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[7]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "gluten":
                if(rate == 2)
                {
                    occasionallyFamily[8]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[8]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "lupin":
                if(rate == 2)
                {
                    occasionallyFamily[9]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[9]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "sesame":
                if(rate == 2)
                {
                    occasionallyFamily[10]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[10]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "algae":
                if(rate == 2)
                {
                    occasionallyFamily[11]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[11]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "shellfish":
                if(rate == 2)
                {
                    occasionallyFamily[12]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[12]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "soy":
                if(rate == 2)
                {
                    occasionallyFamily[13]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[13]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "peanuts":
                if(rate == 2)
                {
                    occasionallyFamily[14]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[14]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "sulphite":
                if(rate == 2)
                {
                    occasionallyFamily[15]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[15]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "nuts":
                if(rate == 2)
                {
                    occasionallyFamily[16]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[16]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "mustard":
                if(rate == 2)
                {
                    occasionallyFamily[17]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[17]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "celery":
                if(rate == 2)
                {
                    occasionallyFamily[18]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[18]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            case "corn":
                if(rate == 2)
                {
                    occasionallyFamily[19]=1;
                    myDb.updateRelevantFamily(family,0);
                }
                else if(rate == 1)
                {
                    neverFamily[19]=1;
                    myDb.updateRelevantFamily(family,1);
                }
                break;
            default:
                return;
        }
        return;
    }

    @Override
    public void onBackPressed() {
        // startActivity(new Intent(getApplicationContext(), HomePage.class));
    }
}
