package com.example.mikhal.wiseatapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/***********************************************VFamilyItemAdapter.java***************************************************
 This class is responsible for users choices in the main choices screen in Vegan,Vegeterian and Custom Profiles.
 *************************************************************************************************************************/
public class VFamilyItemAdapter extends ArrayAdapter<FamilyData> {

    private Activity myContext;
    private FamilyData[] datas;
    //vegeterian
    static public int vegiBeef=1,vegiChicken=1,vegiPork=1,vegiFish=1,vegiInsects=1,vegiShellfish=1;

    //vegan
    static public int veganBeefVal=1,veganChickenVal=1,veganPorkVal=1,veganFishVal=1,veganInsectsVal=1,veganEggsVal=1,veganMilkVal=1,veganHoneyVal=1,veganShellfishVal=1;

    //custom
    static public int cBeef=0,cChicken=0,cPork=0,cFish=0,cInsects=0,cEggs=0,cMilk=0,cHoney=0,cGluten=0,cLupin=0,cSesame=0,cAlgae=0,cShellfish=0,cSoy=0,cPeanuts=0,cSulphite=0,cNuts=0,cMustrad=0,cCelery=0,cCorn=0;


    public VFamilyItemAdapter(Context context, int textViewResourceId, FamilyData[] objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        myContext = (Activity) context;
        datas = objects;
    }

    //loaded when the screen is loaded
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = myContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_v, null);
        ImageView thumbImageView = (ImageView) rowView
                .findViewById(R.id.postThumbV);

        final int p = position;

        TextView postTitleView = (TextView) rowView
                .findViewById(R.id.postTitleLabelV);
        postTitleView.setText(datas[position].familyTitle);

        final TextView postDateView = (TextView) rowView
                .findViewById(R.id.postDateLabelV);
        postDateView.setText(datas[position].desc);

        thumbImageView.setImageResource(datas[position].im);

        RadioGroup rg = (RadioGroup) rowView
                .findViewById(R.id.familyRGV);
        RadioButton r1= (RadioButton) rowView.findViewById(R.id.r1v);
        RadioButton r2= (RadioButton) rowView.findViewById(R.id.r2v);
        RadioButton r3= (RadioButton) rowView.findViewById(R.id.r3v);

       /* if(VeganProfile.isVegan==true ||VegetarianProfile.isVegi==true)
            r1.setChecked(true);
        else if(CustomProfile.isCustom==true)
            r3.setChecked(true);*/


        if(VeganProfile.isVegan==true) {

            if (p == 0)
            {
               if(veganBeefVal==1)//never
                   r1.setChecked(true);
               else if(veganBeefVal==2)//occ
                   r2.setChecked(true);
               else if(veganBeefVal==0)//always
                   r3.setChecked(true);
            }
            else if (p == 1)
            {
                if(veganChickenVal==1)//never
                    r1.setChecked(true);
                else if(veganChickenVal==2)//occ
                    r2.setChecked(true);
                else if(veganChickenVal==0)//always
                    r3.setChecked(true);
            }
            else if (p == 2)
            {
                if(veganPorkVal==1)//never
                    r1.setChecked(true);
                else if(veganPorkVal==2)//occ
                    r2.setChecked(true);
                else if(veganPorkVal==0)//always
                    r3.setChecked(true);
            }
            else if (p == 3)
            {
                if(veganFishVal==1)//never
                    r1.setChecked(true);
                else if(veganFishVal==2)//occ
                    r2.setChecked(true);
                else if(veganFishVal==0)//always
                    r3.setChecked(true);
            }
            else if (p == 4)
            {
                if(veganInsectsVal==1)//never
                    r1.setChecked(true);
                else if(veganInsectsVal==2)//occ
                    r2.setChecked(true);
                else if(veganInsectsVal==0)//always
                    r3.setChecked(true);
            }
            else if (p== 5)
            {
                if(veganEggsVal==1)//never
                    r1.setChecked(true);
                else if(veganEggsVal==2)//occ
                    r2.setChecked(true);
                else if(veganEggsVal==0)//always
                    r3.setChecked(true);
            }
            else if (p == 6)
            {
                if(veganMilkVal==1)//never
                    r1.setChecked(true);
                else if(veganMilkVal==2)//occ
                    r2.setChecked(true);
                else if(veganMilkVal==0)//always
                    r3.setChecked(true);
            }
            else if (p == 7)
            {
                if(veganHoneyVal==1)//never
                    r1.setChecked(true);
                else if(veganHoneyVal==2)//occ
                    r2.setChecked(true);
                else if(veganHoneyVal==0)//always
                    r3.setChecked(true);
            }
            else if (p == 8)
            {
                if(veganShellfishVal==1)//never
                    r1.setChecked(true);
                else if(veganShellfishVal==2)//occ
                    r2.setChecked(true);
                else if(veganShellfishVal==0)//always
                    r3.setChecked(true);
            }

        } else if(VegetarianProfile.isVegi==true)
        {
            if (p == 0)
            {
                if(vegiBeef==1)//never
                    r1.setChecked(true);
                else if(vegiBeef==2)//occ
                    r2.setChecked(true);
                else if(vegiBeef==0)//always
                    r3.setChecked(true);
            }
            else if (p == 1)
            {
                if(vegiChicken==1)//never
                    r1.setChecked(true);
                else if(vegiChicken==2)//occ
                    r2.setChecked(true);
                else if(vegiChicken==0)//always
                    r3.setChecked(true);
            }
            else if (p == 2)
            {
                if(vegiPork==1)//never
                    r1.setChecked(true);
                else if(vegiPork==2)//occ
                    r2.setChecked(true);
                else if(vegiPork==0)//always
                    r3.setChecked(true);
            }
            else if (p == 3)
            {
                if(vegiFish==1)//never
                    r1.setChecked(true);
                else if(vegiFish==2)//occ
                    r2.setChecked(true);
                else if(vegiFish==0)//always
                    r3.setChecked(true);
            }
            else if (p == 4)
            {
                if(vegiInsects==1)//never
                    r1.setChecked(true);
                else if(vegiInsects==2)//occ
                    r2.setChecked(true);
                else if(vegiInsects==0)//always
                    r3.setChecked(true);
            }
            else if (p== 5)
            {
                if(vegiShellfish==1)//never
                    r1.setChecked(true);
                else if(vegiShellfish==2)//occ
                    r2.setChecked(true);
                else if(vegiShellfish==0)//always
                    r3.setChecked(true);
            }
        }else if(CustomProfile.isCustom==true)
        {
            if (p == 0)
            {
                if(cBeef==1)//never
                    r1.setChecked(true);
                else if(cBeef==2)//occ
                    r2.setChecked(true);
                else if(cBeef==0)//always
                    r3.setChecked(true);
            }
            else if (p == 1)
            {
                if(cChicken==1)//never
                    r1.setChecked(true);
                else if(cChicken==2)//occ
                    r2.setChecked(true);
                else if(cChicken==0)//always
                    r3.setChecked(true);
            }
            else if (p == 2)
            {
                if(cPork==1)//never
                    r1.setChecked(true);
                else if(cPork==2)//occ
                    r2.setChecked(true);
                else if(cPork==0)//always
                    r3.setChecked(true);
            }
            else if (p == 3)
            {
                if(cFish==1)//never
                    r1.setChecked(true);
                else if(cFish==2)//occ
                    r2.setChecked(true);
                else if(cFish==0)//always
                    r3.setChecked(true);
            }
            else if (p == 4)
            {
                if(cInsects==1)//never
                    r1.setChecked(true);
                else if(cInsects==2)//occ
                    r2.setChecked(true);
                else if(cInsects==0)//always
                    r3.setChecked(true);
            }
            else if (p== 5)
            {
                if(cEggs==1)//never
                    r1.setChecked(true);
                else if(cEggs==2)//occ
                    r2.setChecked(true);
                else if(cEggs==0)//always
                    r3.setChecked(true);
            }
            else if (p == 6)
            {
                if(cMilk==1)//never
                    r1.setChecked(true);
                else if(cMilk==2)//occ
                    r2.setChecked(true);
                else if(cMilk==0)//always
                    r3.setChecked(true);
            }
            else if (p == 7)
            {
                if(cHoney==1)//never
                    r1.setChecked(true);
                else if(cHoney==2)//occ
                    r2.setChecked(true);
                else if(cHoney==0)//always
                    r3.setChecked(true);
            }
            else if (p == 8)
            {
                if(cGluten==1)//never
                    r1.setChecked(true);
                else if(cGluten==2)//occ
                    r2.setChecked(true);
                else if(cGluten==0)//always
                    r3.setChecked(true);
            }
            else if (p == 9)
            {
                if(  cLupin==1)//never
                    r1.setChecked(true);
                else if(  cLupin==2)//occ
                    r2.setChecked(true);
                else if(  cLupin==0)//always
                    r3.setChecked(true);
            }
            else if (p == 10)
            {
                if(cSesame==1)//never
                    r1.setChecked(true);
                else if(cSesame==2)//occ
                    r2.setChecked(true);
                else if(cSesame==0)//always
                    r3.setChecked(true);
            }
            else if (p == 11)
            {
                if(cAlgae==1)//never
                    r1.setChecked(true);
                else if(cAlgae==2)//occ
                    r2.setChecked(true);
                else if(cAlgae==0)//always
                    r3.setChecked(true);
            }
            else if (p == 12)
            {
                if(cShellfish==1)//never
                    r1.setChecked(true);
                else if(cShellfish==2)//occ
                    r2.setChecked(true);
                else if(cShellfish==0)//always
                    r3.setChecked(true);
            }
            else if (p == 13)
            {
                if(cSoy==1)//never
                    r1.setChecked(true);
                else if(cSoy==2)//occ
                    r2.setChecked(true);
                else if(cSoy==0)//always
                    r3.setChecked(true);
            }
            else if (p == 14)
            {
                if(cPeanuts==1)//never
                    r1.setChecked(true);
                else if(cPeanuts==2)//occ
                    r2.setChecked(true);
                else if(cPeanuts==0)//always
                    r3.setChecked(true);
            }
            else if (p == 15)
            {
                if(cSulphite==1)//never
                    r1.setChecked(true);
                else if(cSulphite==2)//occ
                    r2.setChecked(true);
                else if(cSulphite==0)//always
                    r3.setChecked(true);
            }
            else if (p== 16)
            {
                if(cNuts==1)//never
                    r1.setChecked(true);
                else if(cNuts==2)//occ
                    r2.setChecked(true);
                else if(cNuts==0)//always
                    r3.setChecked(true);
            }
            else if (p == 17)
            {
                if(cMustrad==1)//never
                    r1.setChecked(true);
                else if(cMustrad==2)//occ
                    r2.setChecked(true);
                else if(cMustrad==0)//always
                    r3.setChecked(true);
            }
            else if (p == 18)
            {
                if(cCelery==1)//never
                    r1.setChecked(true);
                else if(cCelery==2)//occ
                    r2.setChecked(true);
                else if(cCelery==0)//always
                    r3.setChecked(true);
            }
            else if (p == 19)
            {
                if(cCorn==1)//never
                    r1.setChecked(true);
                else if(cCorn==2)//occ
                    r2.setChecked(true);
                else if(cCorn==0)//always
                    r3.setChecked(true);
            }
        }

        rg.setVisibility(View.VISIBLE);
        r1.setVisibility(View.VISIBLE);
        r2.setVisibility(View.VISIBLE);
        r3.setVisibility(View.VISIBLE);
        postDateView.setVisibility(View.INVISIBLE);


            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    if(VeganProfile.isVegan==true)
                    {
                        if (checkedId == R.id.r1v)//never
                        {
                            if (p == 0)
                                veganBeefVal=1;
                            else if (p == 1)
                                veganChickenVal=1;
                            else if (p == 2)
                                veganPorkVal=1;
                            else if (p == 3)
                                veganFishVal=1;
                            else if (p == 4)
                                veganInsectsVal=1;
                            else if (p== 5)
                                veganEggsVal=1;
                            else if (p == 6)
                                veganMilkVal=1;
                            else if (p == 7)
                                veganHoneyVal=1;
                            else if (p == 8)
                                veganShellfishVal=1;
                        }
                        else if(checkedId == R.id.r2v) //occ
                        {
                            if (p == 0)
                                veganBeefVal=2;
                            else if (p == 1)
                                veganChickenVal=2;
                            else if (p == 2)
                                veganPorkVal=2;
                            else if (p == 3)
                                veganFishVal=2;
                            else if (p == 4)
                                veganInsectsVal=2;
                            else if (p== 5)
                                veganEggsVal=2;
                            else if (p == 6)
                                veganMilkVal=2;
                            else if (p == 7)
                                veganHoneyVal=2;
                            else if (p == 8)
                                veganShellfishVal=2;
                        }
                        else//off
                        {
                            if (p == 0)
                                veganBeefVal=0;
                            else if (p == 1)
                                veganChickenVal=0;
                            else if (p == 2)
                                veganPorkVal=0;
                            else if (p == 3)
                                veganFishVal=0;
                            else if (p == 4)
                                veganInsectsVal=0;
                            else if (p== 5)
                                veganEggsVal=0;
                            else if (p == 6)
                                veganMilkVal=0;
                            else if (p == 7)
                                veganHoneyVal=0;
                            else if (p == 8)
                                veganShellfishVal=0;
                        }
                    }
                    else if(VegetarianProfile.isVegi==true)
                    {
                        if (checkedId == R.id.r1v)//never
                        {
                            if (p == 0)
                                vegiBeef=1;
                            else if (p == 1)
                                vegiChicken=1;
                            else if (p == 2)
                                vegiPork=1;
                            else if (p == 3)
                                vegiFish=1;
                            else if (p == 4)
                                vegiInsects=1;
                            else if (p== 5)
                                vegiShellfish=1;
                        }
                        else if(checkedId == R.id.r2v) //occ
                        {
                            if (p == 0)
                                vegiBeef=2;
                            else if (p == 1)
                                vegiChicken=2;
                            else if (p == 2)
                                vegiPork=2;
                            else if (p == 3)
                                vegiFish=2;
                            else if (p == 4)
                                vegiInsects=2;
                            else if (p== 5)
                                vegiShellfish=2;
                        }
                        else//off
                        {
                            if (p == 0)
                                vegiBeef=0;
                            else if (p == 1)
                                vegiChicken=0;
                            else if (p == 2)
                                vegiPork=0;
                            else if (p == 3)
                                vegiFish=0;
                            else if (p == 4)
                                vegiInsects=0;
                            else if (p== 5)
                                vegiShellfish=0;
                        }
                    }
                    else if(CustomProfile.isCustom==true)
                    {
                        if (checkedId == R.id.r1v)//never
                        {
                            if (p == 0)
                                cBeef=1;
                            else if (p == 1)
                                cChicken=1;
                            else if (p == 2)
                                cPork=1;
                            else if (p == 3)
                                cFish=1;
                            else if (p == 4)
                                cInsects=1;
                            else if (p== 5)
                                cEggs=1;
                            else if (p == 6)
                                cMilk=1;
                            else if (p == 7)
                                cHoney=1;
                            else if (p == 8)
                                cGluten=1;
                            else if (p == 9)
                                cLupin=1;
                            else if (p == 10)
                                cSesame=1;
                            else if (p == 11)
                               cAlgae=1;
                            else if (p == 12)
                                cShellfish=1;
                            else if (p == 13)
                                cSoy=1;
                            else if (p == 14)
                                cPeanuts=1;
                            else if (p == 15)
                                cSulphite=1;
                            else if (p== 16)
                                cNuts=1;
                            else if (p == 17)
                                cMustrad=1;
                            else if (p == 18)
                                cCelery=1;
                            else if (p == 19)
                                cCorn=1;
                        }
                        else if(checkedId == R.id.r2v) //occ
                        {
                            if (p == 0)
                                cBeef=2;
                            else if (p == 1)
                                cChicken=2;
                            else if (p == 2)
                                cPork=2;
                            else if (p == 3)
                                cFish=2;
                            else if (p == 4)
                                cInsects=2;
                            else if (p== 5)
                                cEggs=2;
                            else if (p == 6)
                                cMilk=2;
                            else if (p == 7)
                                cHoney=2;
                            else if (p == 8)
                                cGluten=2;
                            else if (p == 9)
                                cLupin=2;
                            else if (p == 10)
                                cSesame=2;
                            else if (p == 11)
                                cAlgae=2;
                            else if (p == 12)
                                cShellfish=2;
                            else if (p == 13)
                                cSoy=2;
                            else if (p == 14)
                                cPeanuts=2;
                            else if (p == 15)
                                cSulphite=2;
                            else if (p== 16)
                                cNuts=2;
                            else if (p == 17)
                                cMustrad=2;
                            else if (p == 18)
                                cCelery=2;
                            else if (p == 19)
                                cCorn=2;
                        }
                        else//off
                        {
                            if (p == 0)
                                cBeef=0;
                            else if (p == 1)
                                cChicken=0;
                            else if (p == 2)
                                cPork=0;
                            else if (p == 3)
                                cFish=0;
                            else if (p == 4)
                                cInsects=0;
                            else if (p== 5)
                                cEggs=0;
                            else if (p == 6)
                                cMilk=0;
                            else if (p == 7)
                                cHoney=0;
                            else if (p == 8)
                                cGluten=0;
                            else if (p == 9)
                                cLupin=0;
                            else if (p == 10)
                                cSesame=0;
                            else if (p == 11)
                                cAlgae=0;
                            else if (p == 12)
                                cShellfish=0;
                            else if (p == 13)
                                cSoy=0;
                            else if (p == 14)
                                cPeanuts=0;
                            else if (p == 15)
                                cSulphite=0;
                            else if (p== 16)
                                cNuts=0;
                            else if (p == 17)
                                cMustrad=0;
                            else if (p == 18)
                                cCelery=0;
                            else if (p == 19)
                                cCorn=0;
                        }
                    }
                }
            });



        return rowView;
    }

}
