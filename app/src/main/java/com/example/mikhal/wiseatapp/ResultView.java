package com.example.mikhal.wiseatapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultView extends AppCompatActivity {

    TextView resView;
    TextView emailText;
    ImageView resImage;
    Button emailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);

        resView = (TextView) findViewById(R.id.resTextView);
        emailText = (TextView) findViewById(R.id.emailText);
        resImage = (ImageView) findViewById(R.id.resImageView);
        emailButton = (Button) findViewById(R.id.emailButton);

        if(checkIfNeverIng()==true)//there is never eat ingredient
        {
            resView.setText(getNeverIngString());

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.never);
            resImage.setImageBitmap(bm);
        }
        else if (checkIfOccIng()==true && SearchIngredients.cntUnknown==0) //check if there is occasionally ingredient
        {
            resView.setText(getOccIngString());

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.oc);
            resImage.setImageBitmap(bm);
        }
        else if(SearchIngredients.cntUnknown!=0)//unknown ingredient
        {
            resView.setText("We Didn't Find Some Of The Ingredients:\n" + SearchIngredients.buffer.toString() +"\n"+"Please Help Us Expand Our DataBase And Send Us" +
                    "The Data In This Format: (IngredientName,Family).\n"+"Don't Forget To Press The Send Button.");

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.unknown);
            resImage.setImageBitmap(bm);
        }
        else //allowed
        {
            resView.setText("This Product Is Perfect For You!");

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.perfect);
            resImage.setImageBitmap(bm);
        }

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   try {
                    GMailSender sender = new GMailSender("username@gmail.com", "password");
                    sender.sendMail("This is Subject",
                            "This is Body",
                            "user@gmail.com",
                            "user@yahoo.com");
                } catch (Exception e) {

                }*/


            }
        });

        for (int i = 0; i < 20; i++) SearchIngredients.neverFamily[i] = 0;
        SearchIngredients.occasionallyFamily = new int[20];
        for (int i = 0; i < 20; i++) SearchIngredients.occasionallyFamily[i] = 0;
        SearchIngredients.cntUnknown=0;
    }

    private boolean checkIfNeverIng()
    {
        for (int i = 0; i < 20; i++)
        {
            if(SearchIngredients.neverFamily[i] == 1)
                return true;
        }
        return false;
    }
    private boolean checkIfOccIng()
    {
        for (int i = 0; i < 20; i++)
        {
            if(SearchIngredients.occasionallyFamily[i] == 1)
                return true;
        }
        return false;
    }

    private String getNeverIngString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("You Are Not Allowed To Eat This Product.\n" + "This Product Contains:\n");

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
        return buffer.toString();
    }
    private String getOccIngString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("This Product Contains Ingredients That You've Marked As 'Occasionally':\n");

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
        return buffer.toString();
    }

}
