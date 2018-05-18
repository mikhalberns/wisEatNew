package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class CustomProfile extends AppCompatActivity {

    DatabaseHelper myDb;
    ListView listView;
    VFamilyItemAdapter itemAdapter;
    private FamilyData[] listData;
    String [] familyArr ={"Beef","Chicken","Pork","Fish","Insects","Eggs","Milk","Honey","Gluten","Lupin","Sesame","Algae","Shellfish","Soy","Peanuts","Sulphite"
            ,"Nuts","Mustard","Celery","Corn"};
    int [] imArr ={R.drawable.dairy,R.drawable.gluten,R.drawable.peanuts,R.drawable.eggs,R.drawable.vegaterian,R.drawable.vegan,R.drawable.custom,R.drawable.dairy,R.drawable.dairy
            ,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten,R.drawable.gluten
            ,R.drawable.gluten,R.drawable.gluten};
    static boolean [] familyClicked = {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
    static boolean isCustom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_profile);
        VeganProfile.isVegan=false;
        VegetarianProfile.isVegi=false;
        CustomProfile.isCustom=true;

        myDb = new DatabaseHelper(this);

        this.generateData();
        listView = (ListView) this.findViewById(R.id.CfamilyListView);
        itemAdapter = new VFamilyItemAdapter(this,
                R.layout.item_v, listData);
        listView.setAdapter(itemAdapter);

        Button done = (Button) findViewById(R.id.Cdone);
        done.setOnClickListener(new View.OnClickListener() { //do when click done
            @Override
            public void onClick(View v) {

                myDb.insertData(VFamilyItemAdapter.cBeef,VFamilyItemAdapter.cChicken,VFamilyItemAdapter.cPork,VFamilyItemAdapter.cFish,VFamilyItemAdapter.cInsects,VFamilyItemAdapter.cEggs,VFamilyItemAdapter.cMilk,VFamilyItemAdapter.cHoney,VFamilyItemAdapter.cGluten,VFamilyItemAdapter.cLupin,VFamilyItemAdapter.cSesame,VFamilyItemAdapter.cAlgae,VFamilyItemAdapter.vegiShellfish,0,0,0,0,0,0,0);
                myDb.matchProfileToUser();
                startActivity(new Intent(getApplicationContext(), HomePage.class));
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
            data.familyClicked = familyClicked[i];
            listData[i] = data;
        }
    }
}
