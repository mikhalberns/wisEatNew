package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/***************************************************ResultView.java*********************************************************
 This class is responsible for the Results of the ingredients search.
 **************************************************************************************************************************/

public class ResultView extends AppCompatActivity {

    private TextView resView;
    private TextView email;
    private ImageView resImage;
    private ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);

        resView = (TextView) findViewById(R.id.resTextView);
        resImage = (ImageView) findViewById(R.id.resImageView);
        email = (TextView) findViewById(R.id.emailAddress);
        email.setVisibility(View.GONE);
        home = (ImageButton) findViewById(R.id.homeButton);

        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomePage.class));
            }
        });

        if(checkIfNeverIng()==true)//there is never eat ingredient
        {
            resView.setText(getNeverIngString());

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.bad);
            resImage.setImageBitmap(bm);
        }
        else if (checkIfOccIng()==true && SearchIngredients.cntUnknown==0) //check if there is occasionally ingredient
        {
            resView.setText(getOccIngString());

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.suspicious);
            resImage.setImageBitmap(bm);
        }
        else if(SearchIngredients.cntUnknown!=0)//unknown ingredient
        {

            resView.setText("Oh no!\nUnfortunately, our app couldn't find a food family classification for the following ingredients:\n" + SearchIngredients.buffer +"\n"+"Think you know the right classification?\n" +
                    "Feel free to send us an Email with your suggestion:");

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.please);
            resImage.setImageBitmap(bm);

            email.setVisibility(View.VISIBLE);
        }
        else //allowed
        {
            resView.setText("This Product Is Perfect For You!\nBon Appetit!");

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.perfect);
            resImage.setImageBitmap(bm);
        }

        for (int i = 0; i < 20; i++) SearchIngredients.neverFamily[i] = 0;
        SearchIngredients.occasionallyFamily = new int[20];
        for (int i = 0; i < 20; i++) SearchIngredients.occasionallyFamily[i] = 0;
        SearchIngredients.cntUnknown=0;
    }

    //Checks if the user searched ingredients that the profile says the user never eat
    private boolean checkIfNeverIng()
    {
        for (int i = 0; i < 20; i++)
        {
            if(SearchIngredients.neverFamily[i] == 1)
                return true;
        }
        return false;
    }

    //Checks if the user searched ingredients that the profile says the user occasionally eat
    private boolean checkIfOccIng()
    {
        for (int i = 0; i < 20; i++)
        {
            if(SearchIngredients.occasionallyFamily[i] == 1)
                return true;
        }
        return false;
    }

    //Get the list of families that user never eat and found in the search
    private String getNeverIngString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("You Are Not Allowed To Eat This Product.\n" + " This product contains ingredients from the following food families that you've marked as 'Never':\n");

        for (int i = 0; i < 20; i++)
        {
            if(SearchIngredients.neverFamily[i] == 1)
            {
                switch (i)
                {
                    case 0:
                        buffer.append("Beef\n");
                        break;
                    case 1:
                        buffer.append("Chicken\n");
                        break;
                    case 2:
                        buffer.append("Pork\n");
                        break;
                    case 3:
                        buffer.append("Fish\n");
                        break;
                    case 4:
                        buffer.append("Insects\n");
                        break;
                    case 5:
                        buffer.append("Eggs\n");
                        break;
                    case 6:
                        buffer.append("Milk\n");
                        break;
                    case 7:
                        buffer.append("Honey\n");
                        break;
                    case 8:
                        buffer.append("Gluten\n");
                        break;
                    case 9:
                        buffer.append("Lupin\n");
                        break;
                    case 10:
                        buffer.append("Sesame\n");
                        break;
                    case 11:
                        buffer.append("Algae\n");
                        break;
                    case 12:
                        buffer.append("Shellfish\n");
                        break;
                    case 13:
                        buffer.append("Soy\n");
                        break;
                    case 14:
                        buffer.append("Peanuts\n");
                        break;
                    case 15:
                        buffer.append("Sulphite\n");
                        break;
                    case 16:
                        buffer.append("Nuts\n");
                        break;
                    case 17:
                        buffer.append("Mustard\n");
                        break;
                    case 18:
                        buffer.append("Celery\n");
                        break;
                    case 19:
                        buffer.append("Corn\n");
                        break;
                    default:
                        return " ";
                }
            }
        }
        buffer.append("We're really sorry for you. Try another product!");
        return buffer.toString();
    }

    //Get the list of families that user occationally eat and found in the search
    private String getOccIngString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("This Product Contains Ingredients From Food Families That You've Marked As 'Occasionally':\n");

        for (int i = 0; i < 20; i++)
        {
            if(SearchIngredients.occasionallyFamily[i] == 1)
            {
                switch (i)
                {
                    case 0:
                        buffer.append("Beef\n");
                        break;
                    case 1:
                        buffer.append("Chicken\n");
                        break;
                    case 2:
                        buffer.append("Pork\n");
                        break;
                    case 3:
                        buffer.append("Fish\n");
                        break;
                    case 4:
                        buffer.append("Insects\n");
                        break;
                    case 5:
                        buffer.append("Eggs\n");
                        break;
                    case 6:
                        buffer.append("Milk\n");
                        break;
                    case 7:
                        buffer.append("Honey\n");
                        break;
                    case 8:
                        buffer.append("Gluten\n");
                        break;
                    case 9:
                        buffer.append("Lupin\n");
                        break;
                    case 10:
                        buffer.append("Sesame\n");
                        break;
                    case 11:
                        buffer.append("Algae\n");
                        break;
                    case 12:
                        buffer.append("Shellfish\n");
                        break;
                    case 13:
                        buffer.append("Soy\n");
                        break;
                    case 14:
                        buffer.append("Peanuts\n");
                        break;
                    case 15:
                        buffer.append("Sulphite\n");
                        break;
                    case 16:
                        buffer.append("Nuts\n");
                        break;
                    case 17:
                        buffer.append("Mustard\n");
                        break;
                    case 18:
                        buffer.append("Celery\n");
                        break;
                    case 19:
                        buffer.append("Corn\n");
                        break;
                    default:
                        return " ";
                }
            }
        }
        buffer.append("You should consider whether to consume this product or not.");
        return buffer.toString();
    }

    @Override
    public void onBackPressed() {
        // startActivity(new Intent(getApplicationContext(), HomePage.class));
    }

}