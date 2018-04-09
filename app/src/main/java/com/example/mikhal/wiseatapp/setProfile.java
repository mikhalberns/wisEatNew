package com.example.mikhal.wiseatapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class setProfile extends AppCompatActivity {
    DatabaseHelper myDb;
    CheckBox veganCheck;
    CheckBox vegetarianCheck;
    CheckBox beefCheck;
    CheckBox chickenCheck;
    CheckBox porkCheck;
    CheckBox fishCheck;
    CheckBox InsectsCheck;
    CheckBox eggsFreeCheck;
    CheckBox diaryFreeCheck;
    CheckBox honeyCheck;
    CheckBox glutenFreeCheck;
    CheckBox lupinCheck;
    CheckBox sesameCheck;
    CheckBox algaeCheck;
    CheckBox shellfishCheck;
    CheckBox soyCheck;
    CheckBox peanutsCheck;
    CheckBox sulphiteCheck;
    CheckBox nutsCheck;
    CheckBox mustardCheck;
    CheckBox celeryCheck;
    CheckBox cornCheck;
    CheckBox customCheck= (CheckBox) findViewById(R.id.custom);
    
    Button btnAddData;
    Integer beef, chicken , pork, fish , Insects, eggsFree, diaryFree,
            honey , glutenFree , lupin, sesame, algae, shellfish,soy,
            peanuts,sulphite, nuts,mustard,celery,corn;
    RadioGroup eggsFreeRG,diaryFreeRG,glutenFreeRG;
    RadioButton eggs1,eggs2,diary1,diary2,gluten1,gluten2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb= new DatabaseHelper(this);
        veganCheck= (CheckBox) findViewById(R.id.vegan);
        vegetarianCheck = (CheckBox) findViewById(R.id.vegetarian);
        beefCheck= (CheckBox) findViewById(R.id.beef);
        chickenCheck= (CheckBox) findViewById(R.id.chicken);
        porkCheck= (CheckBox) findViewById(R.id.pork);
        fishCheck= (CheckBox) findViewById(R.id.fish);
        InsectsCheck= (CheckBox) findViewById(R.id.Insects);

        eggsFreeCheck= (CheckBox) findViewById(R.id.eggsFree);
        eggsFreeRG= (RadioGroup) findViewById(R.id.eggsFreeRG);
        addListenerOnChk(eggsFreeCheck, eggsFreeRG);
        eggs1=(RadioButton) findViewById(R.id.eggs1);
        eggs2=(RadioButton) findViewById(R.id.eggs2);

        diaryFreeCheck= (CheckBox) findViewById(R.id.diaryFree);
        diaryFreeRG=(RadioGroup) findViewById(R.id.diaryFreeRG);
        addListenerOnChk(diaryFreeCheck, diaryFreeRG);
        diary1=(RadioButton) findViewById(R.id.diary1);
        diary2=(RadioButton) findViewById(R.id.diary2);

        glutenFreeCheck= (CheckBox) findViewById(R.id.glutenFree);
        glutenFreeRG= (RadioGroup) findViewById(R.id.glutenFreeRG);
        addListenerOnChk(glutenFreeCheck, glutenFreeRG);
        gluten1=(RadioButton) findViewById(R.id.gluten1);
        gluten2=(RadioButton) findViewById(R.id.gluten2);

        honeyCheck= (CheckBox) findViewById(R.id.honey);
        lupinCheck= (CheckBox) findViewById(R.id.lupin);
        sesameCheck= (CheckBox) findViewById(R.id.sesame);
        algaeCheck= (CheckBox) findViewById(R.id.algae);
        shellfishCheck= (CheckBox) findViewById(R.id.shellfish);
        soyCheck= (CheckBox) findViewById(R.id.soy);
        peanutsCheck= (CheckBox) findViewById(R.id.peanuts);
        sulphiteCheck= (CheckBox) findViewById(R.id.sulphite);
        nutsCheck= (CheckBox) findViewById(R.id.nuts);
        mustardCheck= (CheckBox) findViewById(R.id.mustard);
        celeryCheck= (CheckBox) findViewById(R.id.celery);
        cornCheck= (CheckBox) findViewById(R.id.corn);
        btnAddData = (Button)findViewById(R.id.done);
       /* btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(setProfile.this,profilePop.class));
            }
        });*/

        AddData();
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (veganCheck.isChecked()) {
                            beef  =1 ;
                            pork  =1 ;
                            fish  =1 ;
                            Insects  =1 ;
                            eggsFree  =1 ;
                            diaryFree  =1 ;
                            honey  =1 ;
                            shellfish  =1 ;
                        }
                        if (vegetarianCheck.isChecked()) {
                            beef  =1 ;
                            chicken  =1 ;
                            pork  =1 ;
                            fish  =1 ;
                            Insects  =1 ;
                            shellfish  =1 ;
                        }

                        if (eggsFreeCheck.isChecked())
                            {
                                if(eggs1.isChecked()){
                                    eggsFree  =1;
                                }
                                else{
                                    eggsFree  =2;
                                }
                            }

                        if (diaryFreeCheck.isChecked())
                            {
                                if(diary1.isChecked()){
                                    diaryFree  =1;
                                }
                                else{
                                    diaryFree  =2;
                                }
                            }


                        if (glutenFreeCheck.isChecked())
                            {
                                if(gluten1.isChecked()){
                                    glutenFree  =1;
                                }
                                else{
                                    glutenFree  =2;
                                }
                            }


                        if (beefCheck.isChecked()) {beef  =1 ; }
                        if (chickenCheck.isChecked()) {chicken  =1 ; }
                        if (porkCheck.isChecked()) {pork  =1 ; }
                        if (fishCheck.isChecked()) {fish  =1 ; }
                        if (InsectsCheck.isChecked()) {Insects  =1 ; }
                        if (honeyCheck.isChecked()) {honey  =1 ; }
                        if (lupinCheck.isChecked()) {lupin  =1 ; }
                        if (sesameCheck.isChecked()) {sesame  =1 ; }
                        if (algaeCheck.isChecked()) {algae  =1 ; }
                        if (shellfishCheck.isChecked()) {shellfish  =1 ; }
                        if (soyCheck.isChecked()) {soy  =1 ; }
                        if (peanutsCheck.isChecked()) {peanuts  =1 ; }
                        if (sulphiteCheck.isChecked()) {sulphite  =1 ; }
                        if (nutsCheck.isChecked()) {nuts  =1 ; }
                        if (mustardCheck.isChecked()) {mustard  =1 ; }
                        if (celeryCheck.isChecked()) {celery  =1 ; }
                        if (cornCheck.isChecked()) {corn  =1 ; }

                        boolean isInserted = myDb.insertData(beef , chicken ,pork, fish , Insects, eggsFree, diaryFree, honey , glutenFree , lupin, sesame, algae, shellfish,soy, peanuts,sulphite, nuts,mustard,celery,corn);
                        if(isInserted == true)
                            Toast.makeText(setProfile.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(setProfile.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void addListenerOnChk(CheckBox checkBox, final RadioGroup rg) // showing consumption degree
    {

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {
                    rg.setVisibility(View.VISIBLE);
                }
                else {
                    rg.setVisibility(View.GONE);
                }
            }

        });
    }

    }
