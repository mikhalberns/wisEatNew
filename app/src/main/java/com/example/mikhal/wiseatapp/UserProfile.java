package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import  android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class UserProfile extends AppCompatActivity {

    ListView listView;
    FamilyItemAdapter itemAdapter;
    private FamilyData[] listData;
    String [] familyArr ={"Dairy Free","Gluten Free","Peanuts Free","Eggs Free","Vegetarian","Vegan","Custom"};
    String [] descArr ={"Choose profile to avoid dairy products","Choose profile to avoid Gluten",
            "Choose profile to avoid Peanuts","Choose profile to avoid Eggs","Choose this profile if your diet is vegetarian",
            "Choose this profile if your diet is vegan","Create your own profile"};
    int [] imArr ={R.drawable.dairy,R.drawable.gluten,R.drawable.peanuts,R.drawable.eggs,R.drawable.vegaterian,R.drawable.vegan,R.drawable.custom};
    static boolean [] familyClicked = {false,false,false,false};
    static public boolean isVegetarian = false;
    static public boolean isVegan = false;
    static public boolean isCuston = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        isVegetarian=isVegan=isCuston=false;
        this.generateData();
        listView = (ListView) this.findViewById(R.id.familyListView);
        itemAdapter = new FamilyItemAdapter(this,
                R.layout.item, listData);
        listView.setAdapter(itemAdapter);

        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() { //do when click done
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    clickOnFamily(l);
            }
        });
    }

   /* //  @Override
    protected void onStart() {
        super.onStart();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"on start",Toast.LENGTH_SHORT).show();
                clickOnFamily(l);
            }
        });

    }*/

    public void clickOnFamily(long position)
    {
        if(position==0)
        {
            for(int i=0;i<4;i++)this.familyClicked[i]=false;
            this.familyClicked[0]=true;
            generateData();
            itemAdapter = new FamilyItemAdapter(this, R.layout.item, listData);
            listView.setAdapter(itemAdapter);

        }
        else if(position==1){
            for(int i=0;i<4;i++)this.familyClicked[i]=false;
            this.familyClicked[1]=true;
            generateData();
            itemAdapter = new FamilyItemAdapter(this,
                    R.layout.item, listData);
            listView.setAdapter(itemAdapter);
        }
        else if(position==2){
            for(int i=0;i<4;i++)this.familyClicked[i]=false;
            this.familyClicked[2]=true;
            generateData();
            itemAdapter = new FamilyItemAdapter(this,
                    R.layout.item, listData);
            listView.setAdapter(itemAdapter);

        }
        else if(position==3){
            for(int i=0;i<4;i++)this.familyClicked[i]=false;
            this.familyClicked[3]=true;
            generateData();
            itemAdapter = new FamilyItemAdapter(this,
                    R.layout.item, listData);
            listView.setAdapter(itemAdapter);

        }
        else if(position==4){
            isVegan=isCuston=false;
            isVegetarian=true;
            startActivity(new Intent(getApplicationContext(), VegetarianProfile.class));
        }
        else if(position==5){
            isVegetarian=isCuston=false;
            isVegan=true;
            startActivity(new Intent(getApplicationContext(), VeganProfile.class));

        }
        else if(position==6){
            isVegetarian=isVegan=false;
            isCuston=true;
            startActivity(new Intent(getApplicationContext(), CustomProfile.class));

        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    private void generateData() {
        FamilyData data = null;
        listData = new FamilyData[7];
        for (int i = 0; i < 7; i++) { //please ignore this comment :>
            data = new FamilyData();
            data.desc = descArr[i];
            data.familyTitle = familyArr[i];
            data.im = imArr[i];
            if(i<4)
                data.familyClicked = familyClicked[i];
            listData[i] = data;
        }
    }
}


