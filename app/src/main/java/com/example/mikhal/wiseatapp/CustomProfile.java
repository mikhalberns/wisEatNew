package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class CustomProfile extends AppCompatActivity {

    DatabaseHelper myDb;
    ListView listView;
    VFamilyItemAdapter itemAdapter;
    private FamilyData[] listData;
    String [] familyArr ={"Beef","Chicken","Pork","Fish","Insects","Eggs","Dairy","Honey","Gluten","Lupin","Sesame","Algae","Shellfish","Soy","Peanuts","Sulphite"
            ,"Nuts","Mustard","Celery","Corn"};
    int [] imArr ={R.drawable.beef,R.drawable.chicken,R.drawable.pork,R.drawable.fish,R.drawable.insects,R.drawable.eggs,R.drawable.dairy,R.drawable.honey
            ,R.drawable.gluten,R.drawable.lupin,R.drawable.sesame,R.drawable.algae,R.drawable.shellfish,R.drawable.soya,R.drawable.peanuts,R.drawable.sulphite,R.drawable.nuts
            ,R.drawable.mustard,R.drawable.celery,R.drawable.corn};
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

                myDb.insertData(VFamilyItemAdapter.cBeef,VFamilyItemAdapter.cChicken,VFamilyItemAdapter.cPork,VFamilyItemAdapter.cFish,VFamilyItemAdapter.cInsects,VFamilyItemAdapter.cEggs,VFamilyItemAdapter.cMilk,VFamilyItemAdapter.cHoney,VFamilyItemAdapter.cGluten,VFamilyItemAdapter.cLupin,VFamilyItemAdapter.cSesame,VFamilyItemAdapter.cAlgae,VFamilyItemAdapter.cShellfish,VFamilyItemAdapter.cSoy,VFamilyItemAdapter.cPeanuts,VFamilyItemAdapter.cSulphite,VFamilyItemAdapter.cNuts,VFamilyItemAdapter.cMustrad,VFamilyItemAdapter.cCelery,VFamilyItemAdapter.cCorn);
                myDb.matchProfileToUser();
                Toast.makeText(CustomProfile.this,"Your Profile Has Been Set Up!",Toast.LENGTH_SHORT).show();
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
