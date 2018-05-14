package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class VeganProfile extends AppCompatActivity {

    private FamilyData[] listData;
    String [] familyArr ={"Beef","Chicken","Pork","Fish","Insects","Eggs","Milk","Honey","Shellfish"};
    int [] imArr ={R.drawable.dairy,R.drawable.gluten,R.drawable.peanuts,R.drawable.eggs,R.drawable.vegaterian,R.drawable.vegan,R.drawable.custom,R.drawable.dairy,R.drawable.dairy};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegan_profile);

        this.generateData();
        final ListView listView = (ListView) this.findViewById(R.id.familyListView);
        FamilyItemAdapter itemAdapter = new FamilyItemAdapter(this,
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
                // clickOnFamily(l);
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
            listData[i] = data;
        }
    }
}
