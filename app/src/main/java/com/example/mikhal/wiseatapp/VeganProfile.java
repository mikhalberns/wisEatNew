package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class VeganProfile extends AppCompatActivity {

    DatabaseHelper myDb;
    ListView listView;
    VFamilyItemAdapter itemAdapter;
    private FamilyData[] listData;
    String [] familyArr ={"Beef","Chicken","Pork","Fish","Insects","Eggs","Dairy","Honey","Shellfish"};
    int [] imArr ={R.drawable.beef,R.drawable.chicken,R.drawable.pork,R.drawable.fish,R.drawable.insects,R.drawable.eggs,R.drawable.dairy,R.drawable.honey,R.drawable.shellfish};
    static boolean [] familyClicked = {true,true,true,true,true,true,true,true,true};
    static boolean isVegan = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegan_profile);

        myDb = new DatabaseHelper(this);
        VeganProfile.isVegan=true;
        VegetarianProfile.isVegi=false;
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

                myDb.insertData(VFamilyItemAdapter.veganBeefVal,VFamilyItemAdapter.veganChickenVal,VFamilyItemAdapter.veganPorkVal,VFamilyItemAdapter.veganFishVal,VFamilyItemAdapter.veganInsectsVal,VFamilyItemAdapter.veganEggsVal,VFamilyItemAdapter.veganMilkVal,VFamilyItemAdapter.veganHoneyVal,0,0,0,0,VFamilyItemAdapter.veganShellfishVal,0,0,0,0,0,0,0);
                myDb.matchProfileToUser();
                Toast.makeText(VeganProfile.this,"Your Profile Has Been Set Up!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });

}

    private void generateData() {
        FamilyData data = null;
        listData = new FamilyData[9];
        for (int i = 0; i < 9; i++) { //please ignore this comment :>
            data = new FamilyData();
            data.familyTitle = familyArr[i];
            data.im = imArr[i];
            data.familyClicked = familyClicked[i];
            listData[i] = data;
        }
    }
}
