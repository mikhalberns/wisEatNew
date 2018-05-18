package com.example.mikhal.wiseatapp;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

        rg.setVisibility(View.VISIBLE);
        r1.setVisibility(View.VISIBLE);
        r2.setVisibility(View.VISIBLE);
        r3.setVisibility(View.VISIBLE);
        postDateView.setVisibility(View.INVISIBLE);

        if(VeganProfile.isVegan==true ||VegetarianProfile.isVegi==true)
            r1.setChecked(true);
        else if(CustomProfile.isCustom==true)
            r3.setChecked(true);


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
