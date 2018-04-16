package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
    CheckBox veggsFreeCheck;
    CheckBox vdiaryFreeCheck;
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
    CheckBox customCheck;
    Boolean iscustomCheck = false;
    Button btnAddData;
    Integer beef, chicken , pork, fish , Insects, eggsFree, diaryFree,
            honey , glutenFree , lupin, sesame, algae, shellfish,soy,
            peanuts,sulphite, nuts,mustard,celery,corn;

    RadioGroup eggsFreeRG,diaryFreeRG,veggsFreeRG,vdiaryFreeRG,glutenFreeRG,beefRG, chickenRG , porkRG,
            fishRG , InsectsRG, honeyRG , lupinRG, sesameRG, algaeRG, shellfishRG,soyRG,
            peanutsRG ,sulphiteRG, nutsRG,mustardRG,celeryRG,cornRG;

    RadioButton eggs1,eggs2,diary1,diary2,veggs1,veggs2,vdiary1,vdiary2,gluten1,gluten2,
            beef1,beef2, chicken1,chicken2 , pork1,pork2, fish1,fish2 , Insects1,Insects2,
            honey1,honey2 ,lupin1,lupin2, sesame1,sesame2, algae1,algae2, shellfish1,shellfish2 ,soy1,soy2,
            peanuts1,peanuts2,sulphite1,sulphite2, nuts1,nuts2,mustard1,mustard2,celery1,celery2,corn1,corn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb= new DatabaseHelper(this);
        veganCheck= (CheckBox) findViewById(R.id.vegan);
        vegetarianCheck = (CheckBox) findViewById(R.id.vegetarian);

        beef= chicken = pork=fish=Insects=eggsFree=diaryFree=
                honey=glutenFree=lupin=sesame=algae=shellfish=soy=
                        peanuts=sulphite= nuts=mustard=celery=corn=0;

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


        btnAddData = (Button)findViewById(R.id.done);
        customCheck= (CheckBox) findViewById(R.id.custom);

        customCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {
                    iscustomCheck = true;
                    AlertDialog.Builder pBuilder = new AlertDialog.Builder(setProfile.this);
                    View pView = getLayoutInflater().inflate(R.layout.dialog_custom,null);

                    beefCheck= (CheckBox) pView.findViewById(R.id.beef);
                    beefRG= (RadioGroup) pView.findViewById(R.id.beefRG);
                    addListenerOnChk(beefCheck, beefRG);
                    beef1=(RadioButton) pView.findViewById(R.id.beef1);
                    beef2=(RadioButton) pView.findViewById(R.id.beef2);

                    chickenCheck= (CheckBox) pView.findViewById(R.id.chicken);
                    chickenRG= (RadioGroup) pView.findViewById(R.id.chickenRG);
                    addListenerOnChk(chickenCheck, chickenRG);
                    chicken1=(RadioButton) pView.findViewById(R.id.chicken1);
                    chicken2=(RadioButton) pView.findViewById(R.id.chicken2);

                    porkCheck= (CheckBox) pView.findViewById(R.id.pork);
                    porkRG= (RadioGroup) pView.findViewById(R.id.porkRG);
                    addListenerOnChk(porkCheck, porkRG);
                    pork1=(RadioButton) pView.findViewById(R.id.pork1);
                    pork2=(RadioButton) pView.findViewById(R.id.pork2);

                    fishCheck= (CheckBox) pView.findViewById(R.id.fish);
                    fishRG= (RadioGroup) pView.findViewById(R.id.fishRG);
                    addListenerOnChk(fishCheck, fishRG);
                    fish1=(RadioButton) pView.findViewById(R.id.fish1);
                    fish2=(RadioButton) pView.findViewById(R.id.fish2);

                    InsectsCheck= (CheckBox) pView.findViewById(R.id.Insects);
                    InsectsRG= (RadioGroup) pView.findViewById(R.id.InsectsRG);
                    addListenerOnChk(InsectsCheck, InsectsRG);
                    Insects1=(RadioButton) pView.findViewById(R.id.Insects1);
                    Insects2=(RadioButton) pView.findViewById(R.id.Insects2);

                    honeyCheck= (CheckBox) pView.findViewById(R.id.honey);
                    honeyRG= (RadioGroup) pView.findViewById(R.id.honeyRG);
                    addListenerOnChk(honeyCheck, honeyRG);
                    honey1=(RadioButton) pView.findViewById(R.id.honey1);
                    honey2=(RadioButton) pView.findViewById(R.id.honey2);

                    lupinCheck= (CheckBox) pView.findViewById(R.id.lupin);
                    lupinRG= (RadioGroup) pView.findViewById(R.id.lupinRG);
                    addListenerOnChk(lupinCheck, lupinRG);
                    lupin1=(RadioButton) pView.findViewById(R.id.lupin1);
                    lupin2=(RadioButton) pView.findViewById(R.id.lupin2);

                    sesameCheck= (CheckBox) pView.findViewById(R.id.sesame);
                    sesameRG= (RadioGroup) pView.findViewById(R.id.sesameRG);
                    addListenerOnChk(sesameCheck, sesameRG);
                    sesame1=(RadioButton) pView.findViewById(R.id.sesame1);
                    sesame2=(RadioButton) pView.findViewById(R.id.sesame2);

                    algaeCheck= (CheckBox) pView.findViewById(R.id.algae);
                    algaeRG= (RadioGroup) pView.findViewById(R.id.algaeRG);
                    addListenerOnChk(algaeCheck, algaeRG);
                    algae1=(RadioButton) pView.findViewById(R.id.algae1);
                    algae2=(RadioButton) pView.findViewById(R.id.algae2);

                    shellfishCheck= (CheckBox) pView.findViewById(R.id.shellfish);
                    shellfishRG= (RadioGroup) pView.findViewById(R.id.shellfishRG);
                    addListenerOnChk(shellfishCheck, shellfishRG);
                    shellfish1=(RadioButton) pView.findViewById(R.id.shellfish1);
                    shellfish2=(RadioButton) pView.findViewById(R.id.shellfish2);

                    soyCheck= (CheckBox) pView.findViewById(R.id.soy);
                    soyRG= (RadioGroup) pView.findViewById(R.id.soyRG);
                    addListenerOnChk(soyCheck, soyRG);
                    soy1=(RadioButton) pView.findViewById(R.id.soy1);
                    soy2=(RadioButton) pView.findViewById(R.id.soy2);

                    peanutsCheck= (CheckBox) pView.findViewById(R.id.peanuts);
                    peanutsRG= (RadioGroup) pView.findViewById(R.id.peanutsRG);
                    addListenerOnChk(peanutsCheck, peanutsRG);
                    peanuts1=(RadioButton) pView.findViewById(R.id.peanuts1);
                    peanuts2=(RadioButton) pView.findViewById(R.id.peanuts2);

                    sulphiteCheck= (CheckBox) pView.findViewById(R.id.sulphite);
                    sulphiteRG= (RadioGroup) pView.findViewById(R.id.sulphiteRG);
                    addListenerOnChk(sulphiteCheck, sulphiteRG);
                    sulphite1=(RadioButton) pView.findViewById(R.id.sulphite1);
                    sulphite2=(RadioButton) pView.findViewById(R.id.sulphite2);

                    nutsCheck= (CheckBox) pView.findViewById(R.id.nuts);
                    nutsRG= (RadioGroup) pView.findViewById(R.id.nutsRG);
                    addListenerOnChk(nutsCheck, nutsRG);
                    nuts1=(RadioButton) pView.findViewById(R.id.nuts1);
                    nuts2=(RadioButton) pView.findViewById(R.id.nuts2);

                    mustardCheck= (CheckBox) pView.findViewById(R.id.mustard);
                    mustardRG= (RadioGroup) pView.findViewById(R.id.mustardRG);
                    addListenerOnChk(mustardCheck, mustardRG);
                    mustard1=(RadioButton) pView.findViewById(R.id.mustard1);
                    mustard2=(RadioButton) pView.findViewById(R.id.mustard2);

                    celeryCheck= (CheckBox) pView.findViewById(R.id.celery);
                    celeryRG= (RadioGroup) pView.findViewById(R.id.celeryRG);
                    addListenerOnChk(celeryCheck, celeryRG);
                    celery1=(RadioButton) pView.findViewById(R.id.celery1);
                    celery2=(RadioButton) pView.findViewById(R.id.celery2);

                    cornCheck= (CheckBox) pView.findViewById(R.id.corn);
                    cornRG= (RadioGroup) pView.findViewById(R.id.cornRG);
                    addListenerOnChk(cornCheck, cornRG);
                    corn1=(RadioButton) pView.findViewById(R.id.corn1);
                    corn2=(RadioButton) pView.findViewById(R.id.corn2);
                 pBuilder.setView(pView);
                 AlertDialog dialog= pBuilder.create();
                 dialog.show();
                }

            }

        });

        veganCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {
                    AlertDialog.Builder pBuilder = new AlertDialog.Builder(setProfile.this);
                    View pView = getLayoutInflater().inflate(R.layout.dialog_vegan,null);

                    veggsFreeCheck= (CheckBox) pView.findViewById(R.id.veggsFree);
                    veggsFreeRG= (RadioGroup) pView.findViewById(R.id.veggsFreeRG);
                    addListenerOnChk(veggsFreeCheck,veggsFreeRG);
                    veggs1=(RadioButton) pView.findViewById(R.id.veggs1);
                    veggs2=(RadioButton) pView.findViewById(R.id.veggs2);

                    vdiaryFreeCheck= (CheckBox) pView.findViewById(R.id.vdiaryFree);
                    vdiaryFreeRG=(RadioGroup) pView.findViewById(R.id.vdiaryFreeRG);
                    addListenerOnChk(vdiaryFreeCheck, vdiaryFreeRG);
                    vdiary1=(RadioButton) pView.findViewById(R.id.vdiary1);
                    vdiary2=(RadioButton) pView.findViewById(R.id.vdiary2);

                    beefCheck= (CheckBox) pView.findViewById(R.id.beef);
                    beefRG= (RadioGroup) pView.findViewById(R.id.beefRG);
                    addListenerOnChk(beefCheck, beefRG);
                    beef1=(RadioButton) pView.findViewById(R.id.beef1);
                    beef2=(RadioButton) pView.findViewById(R.id.beef2);

                    chickenCheck= (CheckBox) pView.findViewById(R.id.chicken);
                    chickenRG= (RadioGroup) pView.findViewById(R.id.chickenRG);
                    addListenerOnChk(chickenCheck, chickenRG);
                    chicken1=(RadioButton) pView.findViewById(R.id.chicken1);
                    chicken2=(RadioButton) pView.findViewById(R.id.chicken2);

                    porkCheck= (CheckBox) pView.findViewById(R.id.pork);
                    porkRG= (RadioGroup) pView.findViewById(R.id.porkRG);
                    addListenerOnChk(porkCheck, porkRG);
                    pork1=(RadioButton) pView.findViewById(R.id.pork1);
                    pork2=(RadioButton) pView.findViewById(R.id.pork2);

                    fishCheck= (CheckBox) pView.findViewById(R.id.fish);
                    fishRG= (RadioGroup) pView.findViewById(R.id.fishRG);
                    addListenerOnChk(fishCheck, fishRG);
                    fish1=(RadioButton) pView.findViewById(R.id.fish1);
                    fish2=(RadioButton) pView.findViewById(R.id.fish2);

                    InsectsCheck= (CheckBox) pView.findViewById(R.id.Insects);
                    InsectsRG= (RadioGroup) pView.findViewById(R.id.InsectsRG);
                    addListenerOnChk(InsectsCheck, InsectsRG);
                    Insects1=(RadioButton) pView.findViewById(R.id.Insects1);
                    Insects2=(RadioButton) pView.findViewById(R.id.Insects2);

                    honeyCheck= (CheckBox) pView.findViewById(R.id.honey);
                    honeyRG= (RadioGroup) pView.findViewById(R.id.honeyRG);
                    addListenerOnChk(honeyCheck, honeyRG);
                    honey1=(RadioButton) pView.findViewById(R.id.honey1);
                    honey2=(RadioButton) pView.findViewById(R.id.honey2);

                    shellfishCheck= (CheckBox) pView.findViewById(R.id.shellfish);
                    shellfishRG= (RadioGroup) pView.findViewById(R.id.shellfishRG);
                    addListenerOnChk(shellfishCheck, shellfishRG);
                    shellfish1=(RadioButton) pView.findViewById(R.id.shellfish1);
                    shellfish2=(RadioButton) pView.findViewById(R.id.shellfish2);

                    pBuilder.setView(pView);
                    AlertDialog dialog= pBuilder.create();
                    dialog.show();
                }

            }

        });

        vegetarianCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {
                    AlertDialog.Builder pBuilder = new AlertDialog.Builder(setProfile.this);
                    View pView = getLayoutInflater().inflate(R.layout.dialog_vegetarian,null);

                    beefCheck= (CheckBox) pView.findViewById(R.id.beef);
                    beefRG= (RadioGroup) pView.findViewById(R.id.beefRG);
                    addListenerOnChk(beefCheck, beefRG);
                    beef1=(RadioButton) pView.findViewById(R.id.beef1);
                    beef2=(RadioButton) pView.findViewById(R.id.beef2);

                    chickenCheck= (CheckBox) pView.findViewById(R.id.chicken);
                    chickenRG= (RadioGroup) pView.findViewById(R.id.chickenRG);
                    addListenerOnChk(chickenCheck, chickenRG);
                    chicken1=(RadioButton) pView.findViewById(R.id.chicken1);
                    chicken2=(RadioButton) pView.findViewById(R.id.chicken2);

                    porkCheck= (CheckBox) pView.findViewById(R.id.pork);
                    porkRG= (RadioGroup) pView.findViewById(R.id.porkRG);
                    addListenerOnChk(porkCheck, porkRG);
                    pork1=(RadioButton) pView.findViewById(R.id.pork1);
                    pork2=(RadioButton) pView.findViewById(R.id.pork2);

                    fishCheck= (CheckBox) pView.findViewById(R.id.fish);
                    fishRG= (RadioGroup) pView.findViewById(R.id.fishRG);
                    addListenerOnChk(fishCheck, fishRG);
                    fish1=(RadioButton) pView.findViewById(R.id.fish1);
                    fish2=(RadioButton) pView.findViewById(R.id.fish2);

                    InsectsCheck= (CheckBox) pView.findViewById(R.id.Insects);
                    InsectsRG= (RadioGroup) pView.findViewById(R.id.InsectsRG);
                    addListenerOnChk(InsectsCheck, InsectsRG);
                    Insects1=(RadioButton) pView.findViewById(R.id.Insects1);
                    Insects2=(RadioButton) pView.findViewById(R.id.Insects2);

                    shellfishCheck= (CheckBox) pView.findViewById(R.id.shellfish);
                    shellfishRG= (RadioGroup) pView.findViewById(R.id.shellfishRG);
                    addListenerOnChk(shellfishCheck, shellfishRG);
                    shellfish1=(RadioButton) pView.findViewById(R.id.shellfish1);
                    shellfish2=(RadioButton) pView.findViewById(R.id.shellfish2);

                    pBuilder.setView(pView);
                    AlertDialog dialog= pBuilder.create();
                    dialog.show();
                }

            }

        });

        AddData();
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (veganCheck.isChecked()) {
                            if (veggsFreeCheck.isChecked())
                            {
                                if(veggs1.isChecked()){
                                    eggsFree  =1;
                                }
                                else{
                                    eggsFree  =2;
                                }
                            }

                            if (vdiaryFreeCheck.isChecked())
                            {
                                if(vdiary1.isChecked()){
                                    diaryFree  =1;
                                }
                                else{
                                    diaryFree  =2;
                                }
                            }
                            if (beefCheck.isChecked()) {
                                if (beef1.isChecked()) {
                                    beef = 1;
                                } else {
                                    beef = 2;
                                }
                            }
                            if (chickenCheck.isChecked()) {
                                if (chicken1.isChecked()) {
                                    chicken = 1;
                                } else {
                                    chicken = 2;
                                }
                            }
                            if (porkCheck.isChecked()) {
                                if (pork1.isChecked()) {
                                    pork = 1;
                                } else {
                                    pork = 2;
                                }
                            }
                            if (fishCheck.isChecked()) {
                                if (fish1.isChecked()) {
                                    fish = 1;
                                } else {
                                    fish = 2;
                                }
                            }
                            if (InsectsCheck.isChecked()) {
                                if (Insects1.isChecked()) {
                                    Insects = 1;
                                } else {
                                    Insects = 2;
                                }
                            }
                            if (honeyCheck.isChecked()) {
                                if (honey1.isChecked()) {
                                    honey = 1;
                                } else {
                                    honey = 2;
                                }
                            }
                            if (shellfishCheck.isChecked()) {
                                if (shellfish1.isChecked()) {
                                    shellfish = 1;
                                } else {
                                    shellfish = 2;
                                }
                            }
                        }
                        if (vegetarianCheck.isChecked()) {
                            if (beefCheck.isChecked()) {
                                if (beef1.isChecked()) {
                                    beef = 1;
                                } else {
                                    beef = 2;
                                }
                            }
                            if (chickenCheck.isChecked()) {
                                if (chicken1.isChecked()) {
                                    chicken = 1;
                                } else {
                                    chicken = 2;
                                }
                            }
                            if (porkCheck.isChecked()) {
                                if (pork1.isChecked()) {
                                    pork = 1;
                                } else {
                                    pork = 2;
                                }
                            }
                            if (fishCheck.isChecked()) {
                                if (fish1.isChecked()) {
                                    fish = 1;
                                } else {
                                    fish = 2;
                                }
                            }
                            if (InsectsCheck.isChecked()) {
                                if (Insects1.isChecked()) {
                                    Insects = 1;
                                } else {
                                    Insects = 2;
                                }
                            }
                            if (shellfishCheck.isChecked()) {
                                if (shellfish1.isChecked()) {
                                    shellfish = 1;
                                } else {
                                    shellfish = 2;
                                }
                            }

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

                        if (iscustomCheck) {
                            if (beefCheck.isChecked()) {
                                if (beef1.isChecked()) {
                                    beef = 1;
                                } else {
                                    beef = 2;
                                }
                            }
                            if (chickenCheck.isChecked()) {
                                if (chicken1.isChecked()) {
                                    chicken = 1;
                                } else {
                                    chicken = 2;
                                }
                            }
                            if (porkCheck.isChecked()) {
                                if (pork1.isChecked()) {
                                    pork = 1;
                                } else {
                                    pork = 2;
                                }
                            }
                            if (fishCheck.isChecked()) {
                                if (fish1.isChecked()) {
                                    fish = 1;
                                } else {
                                    fish = 2;
                                }
                            }
                            if (InsectsCheck.isChecked()) {
                                if (Insects1.isChecked()) {
                                    Insects = 1;
                                } else {
                                    Insects = 2;
                                }
                            }
                            if (honeyCheck.isChecked()) {
                                if (honey1.isChecked()) {
                                    honey = 1;
                                } else {
                                    honey = 2;
                                }
                            }
                            if (lupinCheck.isChecked()) {
                                if (lupin1.isChecked()) {
                                    lupin = 1;
                                } else {
                                    lupin = 2;
                                }
                            }
                            if (sesameCheck.isChecked()) {
                                if (sesame1.isChecked()) {
                                    sesame = 1;
                                } else {
                                    sesame = 2;
                                }
                            }
                            if (algaeCheck.isChecked()) {
                                if (algae1.isChecked()) {
                                    algae = 1;
                                } else {
                                    algae = 2;
                                }
                            }
                            if (shellfishCheck.isChecked()) {
                                if (shellfish1.isChecked()) {
                                    shellfish = 1;
                                } else {
                                    shellfish = 2;
                                }
                            }
                            if (soyCheck.isChecked()) {
                                if (soy1.isChecked()) {
                                    soy = 1;
                                } else {
                                    soy = 2;
                                }
                            }
                            if (peanutsCheck.isChecked()) {
                                if (peanuts1.isChecked()) {
                                    peanuts = 1;
                                } else {
                                    peanuts = 2;
                                }
                            }
                            if (sulphiteCheck.isChecked()) {
                                if (sulphite1.isChecked()) {
                                    sulphite = 1;
                                } else {
                                    sulphite = 2;
                                }
                            }
                            if (nutsCheck.isChecked()) {
                                if (nuts1.isChecked()) {
                                    nuts = 1;
                                } else {
                                    nuts = 2;
                                }
                            }
                            if (mustardCheck.isChecked()) {
                                if (mustard1.isChecked()) {
                                    mustard = 1;
                                } else {
                                    mustard = 2;
                                }
                            }
                            if (celeryCheck.isChecked()) {
                                if (celery1.isChecked()) {
                                    celery = 1;
                                } else {
                                    celery = 2;
                                }
                            }
                            if (cornCheck.isChecked()) {
                                if (corn1.isChecked()) {
                                    corn = 1;
                                } else {
                                    corn = 2;
                                }
                            }
                        }
                        boolean isInserted = myDb.insertData( beef,chicken ,pork,fish , Insects, eggsFree, diaryFree, honey , glutenFree , lupin, sesame, algae, shellfish,soy, peanuts,sulphite, nuts,mustard,celery,corn);
                        if(isInserted == true)
                            Toast.makeText(setProfile.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(setProfile.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                        startActivity(new Intent(getApplicationContext(), HomePage.class));
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
