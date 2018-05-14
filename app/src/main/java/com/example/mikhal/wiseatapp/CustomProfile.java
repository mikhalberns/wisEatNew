package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class CustomProfile extends AppCompatActivity {

    private FamilyData[] listData;
    String [] familyArr ={"Beef","Chicken","Pork","Fish","Insects","Eggs","Milk","Honey","Gluten","Lupin","Sesame","Algae","Shellfish","Soy","Peanuts","Sulphite"
            ,"Nuts","Mustard","Celery","Corn"};
    int [] imArr ={R.drawable.dairy,R.drawable.gluten,R.drawable.peanuts,R.drawable.eggs,R.drawable.vegaterian,R.drawable.vegan,R.drawable.custom,R.drawable.dairy,R.drawable.dairy
            ,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten
            ,R.drawable.gluten,R.drawable.gluten};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_profile);

        this.generateData();
        final ListView listView = (ListView) this.findViewById(R.id.CfamilyListView);
        FamilyItemAdapter itemAdapter = new FamilyItemAdapter(this,
                R.layout.item, listData);
        listView.setAdapter(itemAdapter);

        Button done = (Button) findViewById(R.id.Cdone);
        done.setOnClickListener(new View.OnClickListener() { //do when click done
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // clickOnFamily(l);
            }
        });
    }

    private void generateData() {
        FamilyData data = null;
        listData = new FamilyData[20];
        for (int i = 0; i < 20; i++) { //please ignore this comment :>
            data = new FamilyData();
            data.familyTitle = familyArr[i];
            data.im = imArr[i];
            listData[i] = data;
        }
    }
}
