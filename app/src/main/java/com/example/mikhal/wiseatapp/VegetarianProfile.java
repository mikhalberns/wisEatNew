package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import static com.example.mikhal.wiseatapp.R.id.VegeterianDone;

public class VegetarianProfile extends AppCompatActivity {

    ListView listView;
    VFamilyItemAdapter itemAdapter;
    private FamilyData[] listData;
    String [] familyArr ={"Beef","Chicken","Pork","Fish","Insects","Shellfish"};
    int [] imArr ={R.drawable.dairy,R.drawable.peanuts,R.drawable.eggs,R.drawable.vegaterian,R.drawable.vegan,R.drawable.custom};
    static boolean [] familyClicked = {true,true,true,true,true,true};
    static boolean isVegi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegan_profile);

        VeganProfile.isVegan=false;
        VegetarianProfile.isVegi=true;
        CustomProfile.isCustom=false;

        this.generateData();
        listView = (ListView) this.findViewById(R.id.VeganListView);
        itemAdapter = new VFamilyItemAdapter(this,
                R.layout.item_v, listData);
        listView.setAdapter(itemAdapter);

        Button done = (Button) findViewById(R.id.VeganDone);
        done.setOnClickListener(new View.OnClickListener() { //do when click done
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });

    }

    private void generateData() {
        FamilyData data = null;
        listData = new FamilyData[6];
        for (int i = 0; i < 6; i++) { //please ignore this comment :>
            data = new FamilyData();
            data.familyTitle = familyArr[i];
            data.im = imArr[i];
            data.familyClicked = familyClicked[i];
            listData[i] = data;
        }
    }
}