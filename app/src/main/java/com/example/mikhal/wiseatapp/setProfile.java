package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.content.SharedPreferences;
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
    Button animalFriendlyCheck;
    Button beefCheck;
    Button chickenCheck;
    Button porkCheck;
    Button fishCheck;
    Button InsectsCheck;
    Button eggsFreeCheck;
    Button dairyFreeCheck;
    Button veggsFreeCheck;
    Button vdairyFreeCheck;
    Button honeyCheck;
    Button glutenFreeCheck;
    Button lupinCheck;
    Button sesameCheck;
    Button algaeCheck;
    Button shellfishCheck;
    Button soyCheck;
    Button peanutsCheck;
    Button sulphiteCheck;
    Button nutsCheck;
    Button mustardCheck;
    Button celeryCheck;
    Button cornCheck;
    Button customCheck;
    Boolean iscustomCheck = false;
    Button btnAddData;
    Integer beef, chicken , pork, fish , Insects, eggsFree, dairyFree,
            honey , glutenFree , lupin, sesame, algae, shellfish,soy,
            peanuts,sulphite, nuts,mustard,celery,corn;

    RadioGroup animalFriendlyRG, eggsFreeRG,dairyFreeRG,veggsFreeRG,vdairyFreeRG,glutenFreeRG,beefRG, chickenRG , porkRG,
            fishRG , InsectsRG, honeyRG , lupinRG, sesameRG, algaeRG, shellfishRG,soyRG,
            peanutsRG ,sulphiteRG, nutsRG,mustardRG,celeryRG,cornRG;



    RadioButton vegetarian,vegan,eggs1,eggs2,dairy1,dairy2,veggs1,veggs2,vdairy1,vdairy2,gluten1,gluten2,
            beef1,beef2, chicken1,chicken2 , pork1,pork2, fish1,fish2 , Insects1,Insects2,
            honey1,honey2 ,lupin1,lupin2, sesame1,sesame2, algae1,algae2, shellfish1,shellfish2 ,soy1,soy2,
            peanuts1,peanuts2,sulphite1,sulphite2, nuts1,nuts2,mustard1,mustard2,celery1,celery2,corn1,corn2;


    //////////////////////custom///////////////////////////////////////////
    //array of foodGroup with the same index as the boolean array
    String[] customfoodGroup=  {"beef", "chicken" , "pork", "fish" , "Insects",
            "honey" ,  "lupin", "sesame", "algae", "shellfish","soy",
            "peanuts","sulphite", "nuts","mustard","celery","corn"};

    Boolean[] isFoodGroupChecked={false, false , false, false , false, false, false,
            false , false , false, false, false, false,false, false,false, false};

    Button[] customButtons= {beefCheck,chickenCheck,porkCheck,fishCheck,InsectsCheck,
            honeyCheck,lupinCheck,sesameCheck,algaeCheck, shellfishCheck,
            soyCheck,peanutsCheck, sulphiteCheck,nutsCheck, mustardCheck,celeryCheck,cornCheck};

    RadioGroup[] customRG={beefRG, chickenRG , porkRG,
            fishRG , InsectsRG, honeyRG , lupinRG, sesameRG, algaeRG, shellfishRG,soyRG,
            peanutsRG ,sulphiteRG, nutsRG,mustardRG,celeryRG,cornRG};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb= new DatabaseHelper(this);

        beef= chicken = pork=fish=Insects=eggsFree=dairyFree=
                honey=glutenFree=lupin=sesame=algae=shellfish=soy=
                        peanuts=sulphite= nuts=mustard=celery=corn=0;

        animalFriendlyCheck = (Button) findViewById(R.id.animalFriendly);
        animalFriendlyRG= (RadioGroup) findViewById(R.id.animalFriendlyRG);
        addListenerOnChk(animalFriendlyCheck, animalFriendlyRG);
        vegan= (RadioButton) findViewById(R.id.vegan);
        vegetarian = (RadioButton) findViewById(R.id.vegetarian);

        eggsFreeCheck= (Button) findViewById(R.id.eggsFree);
        eggsFreeRG= (RadioGroup) findViewById(R.id.eggsFreeRG);
        addListenerOnChk(eggsFreeCheck, eggsFreeRG);
        eggs1=(RadioButton) findViewById(R.id.eggs1);
        eggs2=(RadioButton) findViewById(R.id.eggs2);

        dairyFreeCheck= (Button) findViewById(R.id.dairyFree);
        dairyFreeRG=(RadioGroup) findViewById(R.id.dairyFreeRG);
        addListenerOnChk(dairyFreeCheck, dairyFreeRG);
        dairy1=(RadioButton) findViewById(R.id.dairy1);
        dairy2=(RadioButton) findViewById(R.id.dairy2);

        glutenFreeCheck= (Button) findViewById(R.id.glutenFree);
        glutenFreeRG= (RadioGroup) findViewById(R.id.glutenFreeRG);
        addListenerOnChk(glutenFreeCheck, glutenFreeRG);
        gluten1=(RadioButton) findViewById(R.id.gluten1);
        gluten2=(RadioButton) findViewById(R.id.gluten2);


        btnAddData = (Button)findViewById(R.id.done);
        customCheck= (Button) findViewById(R.id.custom);

        customCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    iscustomCheck = true;
                    AlertDialog.Builder pBuilder = new AlertDialog.Builder(setProfile.this);
                    View pView = getLayoutInflater().inflate(R.layout.dialog_custom,null);

                    beefCheck= (Button) pView.findViewById(R.id.beef);
                    beefRG= (RadioGroup) pView.findViewById(R.id.beefRG);
                    addListenerOnChk(beefCheck, beefRG);
                    beef1=(RadioButton) pView.findViewById(R.id.beef1);
                    beef2=(RadioButton) pView.findViewById(R.id.beef2);

                    chickenCheck= (Button) pView.findViewById(R.id.chicken);
                    chickenRG= (RadioGroup) pView.findViewById(R.id.chickenRG);
                    addListenerOnChk(chickenCheck, chickenRG);
                    chicken1=(RadioButton) pView.findViewById(R.id.chicken1);
                    chicken2=(RadioButton) pView.findViewById(R.id.chicken2);

                    porkCheck= (Button) pView.findViewById(R.id.pork);
                    porkRG= (RadioGroup) pView.findViewById(R.id.porkRG);
                    addListenerOnChk(porkCheck, porkRG);
                    pork1=(RadioButton) pView.findViewById(R.id.pork1);
                    pork2=(RadioButton) pView.findViewById(R.id.pork2);

                    fishCheck= (Button) pView.findViewById(R.id.fish);
                    fishRG= (RadioGroup) pView.findViewById(R.id.fishRG);
                    addListenerOnChk(fishCheck, fishRG);
                    fish1=(RadioButton) pView.findViewById(R.id.fish1);
                    fish2=(RadioButton) pView.findViewById(R.id.fish2);

                    InsectsCheck= (Button) pView.findViewById(R.id.Insects);
                    InsectsRG= (RadioGroup) pView.findViewById(R.id.InsectsRG);
                    addListenerOnChk(InsectsCheck, InsectsRG);
                    Insects1=(RadioButton) pView.findViewById(R.id.Insects1);
                    Insects2=(RadioButton) pView.findViewById(R.id.Insects2);

                    honeyCheck= (Button) pView.findViewById(R.id.honey);
                    honeyRG= (RadioGroup) pView.findViewById(R.id.honeyRG);
                    addListenerOnChk(honeyCheck, honeyRG);
                    honey1=(RadioButton) pView.findViewById(R.id.honey1);
                    honey2=(RadioButton) pView.findViewById(R.id.honey2);

                    lupinCheck= (Button) pView.findViewById(R.id.lupin);
                    lupinRG= (RadioGroup) pView.findViewById(R.id.lupinRG);
                    addListenerOnChk(lupinCheck, lupinRG);
                    lupin1=(RadioButton) pView.findViewById(R.id.lupin1);
                    lupin2=(RadioButton) pView.findViewById(R.id.lupin2);

                    sesameCheck= (Button) pView.findViewById(R.id.sesame);
                    sesameRG= (RadioGroup) pView.findViewById(R.id.sesameRG);
                    addListenerOnChk(sesameCheck, sesameRG);
                    sesame1=(RadioButton) pView.findViewById(R.id.sesame1);
                    sesame2=(RadioButton) pView.findViewById(R.id.sesame2);

                    algaeCheck= (Button) pView.findViewById(R.id.algae);
                    algaeRG= (RadioGroup) pView.findViewById(R.id.algaeRG);
                    addListenerOnChk(algaeCheck, algaeRG);
                    algae1=(RadioButton) pView.findViewById(R.id.algae1);
                    algae2=(RadioButton) pView.findViewById(R.id.algae2);

                    shellfishCheck= (Button) pView.findViewById(R.id.shellfish);
                    shellfishRG= (RadioGroup) pView.findViewById(R.id.shellfishRG);
                    addListenerOnChk(shellfishCheck, shellfishRG);
                    shellfish1=(RadioButton) pView.findViewById(R.id.shellfish1);
                    shellfish2=(RadioButton) pView.findViewById(R.id.shellfish2);

                    soyCheck= (Button) pView.findViewById(R.id.soy);
                    soyRG= (RadioGroup) pView.findViewById(R.id.soyRG);
                    addListenerOnChk(soyCheck, soyRG);
                    soy1=(RadioButton) pView.findViewById(R.id.soy1);
                    soy2=(RadioButton) pView.findViewById(R.id.soy2);

                    peanutsCheck= (Button) pView.findViewById(R.id.peanuts);
                    peanutsRG= (RadioGroup) pView.findViewById(R.id.peanutsRG);
                    addListenerOnChk(peanutsCheck, peanutsRG);
                    peanuts1=(RadioButton) pView.findViewById(R.id.peanuts1);
                    peanuts2=(RadioButton) pView.findViewById(R.id.peanuts2);

                    sulphiteCheck= (Button) pView.findViewById(R.id.sulphite);
                    sulphiteRG= (RadioGroup) pView.findViewById(R.id.sulphiteRG);
                    addListenerOnChk(sulphiteCheck, sulphiteRG);
                    sulphite1=(RadioButton) pView.findViewById(R.id.sulphite1);
                    sulphite2=(RadioButton) pView.findViewById(R.id.sulphite2);

                    nutsCheck= (Button) pView.findViewById(R.id.nuts);
                    nutsRG= (RadioGroup) pView.findViewById(R.id.nutsRG);
                    addListenerOnChk(nutsCheck, nutsRG);
                    nuts1=(RadioButton) pView.findViewById(R.id.nuts1);
                    nuts2=(RadioButton) pView.findViewById(R.id.nuts2);

                    mustardCheck= (Button) pView.findViewById(R.id.mustard);
                    mustardRG= (RadioGroup) pView.findViewById(R.id.mustardRG);
                    addListenerOnChk(mustardCheck, mustardRG);
                    mustard1=(RadioButton) pView.findViewById(R.id.mustard1);
                    mustard2=(RadioButton) pView.findViewById(R.id.mustard2);

                    celeryCheck= (Button) pView.findViewById(R.id.celery);
                    celeryRG= (RadioGroup) pView.findViewById(R.id.celeryRG);
                    addListenerOnChk(celeryCheck, celeryRG);
                    celery1=(RadioButton) pView.findViewById(R.id.celery1);
                    celery2=(RadioButton) pView.findViewById(R.id.celery2);

                    cornCheck= (Button) pView.findViewById(R.id.corn);
                    cornRG= (RadioGroup) pView.findViewById(R.id.cornRG);
                    addListenerOnChk(cornCheck, cornRG);
                    corn1=(RadioButton) pView.findViewById(R.id.corn1);
                    corn2=(RadioButton) pView.findViewById(R.id.corn2);
                    pBuilder.setView(pView);
                    AlertDialog dialog= pBuilder.create();
                    dialog.show();

                    beefCheck.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isFGChecked(v);
                            addListenerOnChk(beefCheck, beefRG);
                        }
                    }) ;

                chickenCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(chickenCheck, chickenRG);
                    }
                }) ;

                porkCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(porkCheck, porkRG);
                    }
                }) ;

                fishCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(fishCheck, fishRG);
                    }
                }) ;

                InsectsCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(InsectsCheck, InsectsRG);
                    }
                }) ;

                honeyCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(honeyCheck, honeyRG);
                    }
                }) ;

                lupinCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(lupinCheck, lupinRG);
                    }
                }) ;

                sesameCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(sesameCheck, sesameRG);
                    }
                }) ;

                algaeCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(algaeCheck, algaeRG);
                    }
                }) ;

                shellfishCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(shellfishCheck, shellfishRG);
                    }
                }) ;

                soyCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(soyCheck, soyRG);
                    }
                }) ;

                peanutsCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(peanutsCheck, peanutsRG);
                    }
                }) ;

                sulphiteCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(sulphiteCheck, sulphiteRG);
                    }
                }) ;

                nutsCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(nutsCheck, nutsRG);
                    }
                }) ;

                mustardCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(mustardCheck, mustardRG);
                    }
                }) ;

                celeryCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(celeryCheck, celeryRG);
                    }
                }) ;

                cornCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFGChecked(v);
                        addListenerOnChk(cornCheck, cornRG);
                    }
                }) ;


            }

        });

        vegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((RadioButton) v).isChecked()) {
                    AlertDialog.Builder pBuilder = new AlertDialog.Builder(setProfile.this);
                    View pView = getLayoutInflater().inflate(R.layout.dialog_vegan,null);

                    veggsFreeCheck= (Button) pView.findViewById(R.id.veggsFree);
                    veggsFreeRG= (RadioGroup) pView.findViewById(R.id.veggsFreeRG);
                    addListenerOnChk(veggsFreeCheck,veggsFreeRG);
                    veggs1=(RadioButton) pView.findViewById(R.id.veggs1);
                    veggs2=(RadioButton) pView.findViewById(R.id.veggs2);

                    vdairyFreeCheck= (Button) pView.findViewById(R.id.vdairyFree);
                    vdairyFreeRG=(RadioGroup) pView.findViewById(R.id.vdairyFreeRG);
                    addListenerOnChk(vdairyFreeCheck, vdairyFreeRG);
                    vdairy1=(RadioButton) pView.findViewById(R.id.vdairy1);
                    vdairy2=(RadioButton) pView.findViewById(R.id.vdairy2);

                    beefCheck= (Button) pView.findViewById(R.id.beef);
                    beefRG= (RadioGroup) pView.findViewById(R.id.beefRG);
                    addListenerOnChk(beefCheck, beefRG);
                    beef1=(RadioButton) pView.findViewById(R.id.beef1);
                    beef2=(RadioButton) pView.findViewById(R.id.beef2);

                    chickenCheck= (Button) pView.findViewById(R.id.chicken);
                    chickenRG= (RadioGroup) pView.findViewById(R.id.chickenRG);
                    addListenerOnChk(chickenCheck, chickenRG);
                    chicken1=(RadioButton) pView.findViewById(R.id.chicken1);
                    chicken2=(RadioButton) pView.findViewById(R.id.chicken2);

                    porkCheck= (Button) pView.findViewById(R.id.pork);
                    porkRG= (RadioGroup) pView.findViewById(R.id.porkRG);
                    addListenerOnChk(porkCheck, porkRG);
                    pork1=(RadioButton) pView.findViewById(R.id.pork1);
                    pork2=(RadioButton) pView.findViewById(R.id.pork2);

                    fishCheck= (Button) pView.findViewById(R.id.fish);
                    fishRG= (RadioGroup) pView.findViewById(R.id.fishRG);
                    addListenerOnChk(fishCheck, fishRG);
                    fish1=(RadioButton) pView.findViewById(R.id.fish1);
                    fish2=(RadioButton) pView.findViewById(R.id.fish2);

                    InsectsCheck= (Button) pView.findViewById(R.id.Insects);
                    InsectsRG= (RadioGroup) pView.findViewById(R.id.InsectsRG);
                    addListenerOnChk(InsectsCheck, InsectsRG);
                    Insects1=(RadioButton) pView.findViewById(R.id.Insects1);
                    Insects2=(RadioButton) pView.findViewById(R.id.Insects2);

                    honeyCheck= (Button) pView.findViewById(R.id.honey);
                    honeyRG= (RadioGroup) pView.findViewById(R.id.honeyRG);
                    addListenerOnChk(honeyCheck, honeyRG);
                    honey1=(RadioButton) pView.findViewById(R.id.honey1);
                    honey2=(RadioButton) pView.findViewById(R.id.honey2);

                    shellfishCheck= (Button) pView.findViewById(R.id.shellfish);
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

        vegetarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((RadioButton) v).isChecked()) {
                    AlertDialog.Builder pBuilder = new AlertDialog.Builder(setProfile.this);
                    View pView = getLayoutInflater().inflate(R.layout.dialog_vegetarian,null);

                    beefCheck= (Button) pView.findViewById(R.id.beef);
                    beefRG= (RadioGroup) pView.findViewById(R.id.beefRG);
                    addListenerOnChk(beefCheck, beefRG);
                    beef1=(RadioButton) pView.findViewById(R.id.beef1);
                    beef2=(RadioButton) pView.findViewById(R.id.beef2);

                    chickenCheck= (Button) pView.findViewById(R.id.chicken);
                    chickenRG= (RadioGroup) pView.findViewById(R.id.chickenRG);
                    addListenerOnChk(chickenCheck, chickenRG);
                    chicken1=(RadioButton) pView.findViewById(R.id.chicken1);
                    chicken2=(RadioButton) pView.findViewById(R.id.chicken2);

                    porkCheck= (Button) pView.findViewById(R.id.pork);
                    porkRG= (RadioGroup) pView.findViewById(R.id.porkRG);
                    addListenerOnChk(porkCheck, porkRG);
                    pork1=(RadioButton) pView.findViewById(R.id.pork1);
                    pork2=(RadioButton) pView.findViewById(R.id.pork2);

                    fishCheck= (Button) pView.findViewById(R.id.fish);
                    fishRG= (RadioGroup) pView.findViewById(R.id.fishRG);
                    addListenerOnChk(fishCheck, fishRG);
                    fish1=(RadioButton) pView.findViewById(R.id.fish1);
                    fish2=(RadioButton) pView.findViewById(R.id.fish2);

                    InsectsCheck= (Button) pView.findViewById(R.id.Insects);
                    InsectsRG= (RadioGroup) pView.findViewById(R.id.InsectsRG);
                    addListenerOnChk(InsectsCheck, InsectsRG);
                    Insects1=(RadioButton) pView.findViewById(R.id.Insects1);
                    Insects2=(RadioButton) pView.findViewById(R.id.Insects2);

                    shellfishCheck= (Button) pView.findViewById(R.id.shellfish);
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
                        ////////VEGAN///////////
                        if (vegan.isChecked()) {
                            if (veggsFreeRG.isShown())
                            {
                                if(veggs1.isChecked()){
                                    eggsFree  =1;
                                }
                                else{
                                    eggsFree  =2;
                                }
                            }

                            if (vdairyFreeRG.isShown())
                            {
                                if(dairy1.isChecked()){
                                    dairyFree  =1;
                                }
                                else{
                                    dairyFree  =2;
                                }
                            }

                            if (beefRG.isShown()) {
                                if (beef1.isChecked()) {
                                    beef = 1;
                                } else {
                                    beef = 2;
                                }
                            }
                            if (chickenRG.isShown()) {
                                if (chicken1.isChecked()) {
                                    chicken = 1;
                                } else {
                                    chicken = 2;
                                }
                            }
                            if (porkRG.isShown()) {
                                if (pork1.isChecked()) {
                                    pork = 1;
                                } else {
                                    pork = 2;
                                }
                            }
                            if (fishRG.isShown()) {
                                if (fish1.isChecked()) {
                                    fish = 1;
                                } else {
                                    fish = 2;
                                }
                            }
                            if (InsectsRG.isShown()) {
                                if (Insects1.isChecked()) {
                                    Insects = 1;
                                } else {
                                    Insects = 2;
                                }
                            }
                            if (honeyRG.isShown()) {
                                if (honey1.isChecked()) {
                                    honey = 1;
                                } else {
                                    honey = 2;
                                }
                            }
                            if (shellfishRG.isShown()) {
                                if (shellfish1.isChecked()) {
                                    shellfish = 1;
                                } else {
                                    shellfish = 2;
                                }
                            }
                        }

                        ////////vegetarian///////////

                        if (vegetarian.isChecked()) {
                            if (beefRG.isShown()) {
                                if (beef1.isChecked()) {
                                    beef = 1;
                                } else {
                                    beef = 2;
                                }
                            }
                            if (chickenRG.isShown()) {
                                if (chicken1.isChecked()) {
                                    chicken = 1;
                                } else {
                                    chicken = 2;
                                }
                            }
                            if (porkRG.isShown()) {
                                if (pork1.isChecked()) {
                                    pork = 1;
                                } else {
                                    pork = 2;
                                }
                            }
                            if (fishRG.isShown()) {
                                if (fish1.isChecked()) {
                                    fish = 1;
                                } else {
                                    fish = 2;
                                }
                            }
                            if (InsectsRG.isShown()) {
                                if (Insects1.isChecked()) {
                                    Insects = 1;
                                } else {
                                    Insects = 2;
                                }
                            }
                            if (shellfishRG.isShown()) {
                                if (shellfish1.isChecked()) {
                                    shellfish = 1;
                                } else {
                                    shellfish = 2;
                                }
                            }

                        }

                        ////////Eggs///////////

                        if (eggsFreeRG.isShown())
                        {
                            if(eggs1.isChecked()){
                                eggsFree  =1;
                            }
                            else{
                                eggsFree  =2;
                            }
                        }

                        ////////dairy///////////

                        if (dairyFreeRG.isShown())
                        {
                            if(dairy1.isChecked()){
                                dairyFree  =1;
                            }
                            else{
                                dairyFree  =2;
                            }
                        }

                        ////////gluten///////////

                        if (glutenFreeRG.isShown())
                        {
                            if(gluten1.isChecked()){
                                glutenFree  =1;
                            }
                            else{
                                glutenFree  =2;
                            }
                        }

                        ////////custom///////////

                        if (iscustomCheck) {

                            if(isFoodGroupChecked[0]){
                                if (beef1.isChecked()) {
                                    beef = 1;
                                }
                                else if (beef2.isChecked()) {
                                    beef = 2;
                                }
                            }

                            if (isFoodGroupChecked[1]) {
                                if (chicken1.isChecked()) {
                                    chicken = 1;
                                } else {
                                    chicken = 2;
                                }
                            }
                            if (isFoodGroupChecked[2]) {
                                if (pork1.isChecked()) {
                                    pork = 1;
                                } else {
                                    pork = 2;
                                }
                            }
                            if (isFoodGroupChecked[3]) {
                                if (fish1.isChecked()) {
                                    fish = 1;
                                } else {
                                    fish = 2;
                                }
                            }
                            if (isFoodGroupChecked[4]) {
                                if (Insects1.isChecked()) {
                                    Insects = 1;
                                } else {
                                    Insects = 2;
                                }
                            }
                            if (isFoodGroupChecked[5]) {
                                if (honey1.isChecked()) {
                                    honey = 1;
                                } else {
                                    honey = 2;
                                }
                            }
                            if (isFoodGroupChecked[6]) {
                                if (lupin1.isChecked()) {
                                    lupin = 1;
                                } else {
                                    lupin = 2;
                                }
                            }
                            if (isFoodGroupChecked[7]) {
                                if (sesame1.isChecked()) {
                                    sesame = 1;
                                } else {
                                    sesame = 2;
                                }
                            }
                            if (isFoodGroupChecked[8]) {
                                if (algae1.isChecked()) {
                                    algae = 1;
                                } else {
                                    algae = 2;
                                }
                            }
                            if (isFoodGroupChecked[9]) {
                                if (shellfish1.isChecked()) {
                                    shellfish = 1;
                                } else {
                                    shellfish = 2;
                                }
                            }
                            if (isFoodGroupChecked[10]) {
                                if (soy1.isChecked()) {
                                    soy = 1;
                                } else {
                                    soy = 2;
                                }
                            }
                            if (isFoodGroupChecked[11]) {
                                if (peanuts1.isChecked()) {
                                    peanuts = 1;
                                } else {
                                    peanuts = 2;
                                }
                            }
                            if (isFoodGroupChecked[12]) {
                                if (sulphite1.isChecked()) {
                                    sulphite = 1;
                                } else {
                                    sulphite = 2;
                                }
                            }
                            if (isFoodGroupChecked[13]) {
                                if (nuts1.isChecked()) {
                                    nuts = 1;
                                } else {
                                    nuts = 2;
                                }
                            }
                            if (isFoodGroupChecked[14]) {
                                if (mustard1.isChecked()) {
                                    mustard = 1;
                                } else {
                                    mustard = 2;
                                }
                            }
                            if (isFoodGroupChecked[15]) {
                                if (celery1.isChecked()) {
                                    celery = 1;
                                } else {
                                    celery = 2;
                                }
                            }
                            if (isFoodGroupChecked[16]) {
                                if (corn1.isChecked()) {
                                    corn = 1;
                                } else {
                                    corn = 2;
                                }
                            }
                        }
                        boolean isInserted = myDb.insertData( beef,chicken ,pork,fish , Insects, eggsFree, dairyFree, honey , glutenFree , lupin, sesame, algae, shellfish,soy, peanuts,sulphite, nuts,mustard,celery,corn);
                        if(isInserted == true)
                        {
                            Toast.makeText(setProfile.this,"Data Inserted",Toast.LENGTH_LONG).show();
                            myDb.matchProfileToUser();
                        }
                        else {
                            //   Toast.makeText(setProfile.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                        }

                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                    }
                }

        );
    }


    public void addListenerOnChk(Button button, final RadioGroup rg ) // showing consumption degree
    {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(rg.isShown()))
                {
                    rg.setVisibility(View.VISIBLE);
                }

                else {
                    rg.setVisibility(View.GONE);

                }

            }
        });

    }
    public void isFGChecked(View v) {
        String FGName = v.getTag().toString();

        for(int i=0;i<customfoodGroup.length;i++) {
            if(FGName.equals(customfoodGroup[i])){
                isFoodGroupChecked[i]=true;
                return;
            }
        }
        return;
    }
}
