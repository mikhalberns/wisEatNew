package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


/***********************************************VegeterianProfile.java**************************************************
 This class is responsible for the vegeterian profile choices
 ***********************************************************************************************************************/
public class VegetarianProfile extends AppCompatActivity {

    private DatabaseHelper myDb;
    private ListView listView;
    private VFamilyItemAdapter itemAdapter;
    private FamilyData[] listData;
    private String [] familyArr ={"Beef","Chicken","Pork","Fish","Insects","Shellfish"};
    private int [] imArr ={R.drawable.beef,R.drawable.chicken,R.drawable.pork,R.drawable.fish,R.drawable.insects,R.drawable.shellfish};
    public static boolean [] familyClicked = {true,true,true,true,true,true};
    public static boolean isVegi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetarian_profile);
        myDb = new DatabaseHelper(this);
        VeganProfile.isVegan=false;
        VegetarianProfile.isVegi=true;
        CustomProfile.isCustom=false;

        this.generateData();
        listView = (ListView) this.findViewById(R.id.VegeterianListView);
        itemAdapter = new VFamilyItemAdapter(this,
                R.layout.item_v, listData);
        listView.setAdapter(itemAdapter);

        Button done = (Button) findViewById(R.id.VegeterianDone);

        //insert the data that chosen to the db
        done.setOnClickListener(new View.OnClickListener() { //do when click done
            @Override
            public void onClick(View v) {
                myDb.insertData(VFamilyItemAdapter.vegiBeef,VFamilyItemAdapter.vegiChicken,VFamilyItemAdapter.vegiPork,VFamilyItemAdapter.vegiFish,VFamilyItemAdapter.vegiInsects,0,0,0,0,0,0,0,VFamilyItemAdapter.vegiShellfish,0,0,0,0,0,0,0);
                myDb.matchProfileToUser();
                Toast.makeText(VegetarianProfile.this,"Your Profile Has Been Set Up!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });

    }

    //generate pictures and titles into the list
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
