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

public class FamilyItemAdapter extends ArrayAdapter<FamilyData> {
    private Activity myContext;
    private FamilyData[] datas;
    static public int dairyVal=0,glutenVal=0,peanutsVal=0,eggsVal=0;


    public FamilyItemAdapter(Context context, int textViewResourceId,
                           FamilyData[] objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        myContext = (Activity) context;
        datas = objects;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = myContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item, null);
        ImageView thumbImageView = (ImageView) rowView
                .findViewById(R.id.postThumb);

        TextView postTitleView = (TextView) rowView
                .findViewById(R.id.postTitleLabel);
        postTitleView.setText(datas[position].familyTitle);

        final TextView postDateView = (TextView) rowView
                .findViewById(R.id.postDateLabel);
        postDateView.setText(datas[position].desc);

        thumbImageView.setImageResource(datas[position].im);

        RadioGroup rg = (RadioGroup) rowView
                .findViewById(R.id.familyRG);
        RadioButton r1 = (RadioButton) rowView.findViewById(R.id.r1);
        RadioButton r2 = (RadioButton) rowView.findViewById(R.id.r2);


        if (datas[position].familyClicked == true) {

            rg.setVisibility(View.VISIBLE);
            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.VISIBLE);
            postDateView.setVisibility(View.INVISIBLE);
            r1.setChecked(true);

            switch (position) {
                case 0://dairy
                    glutenVal = 0;//unchecked
                    peanutsVal = 0;//unchecked
                    eggsVal = 0;//unchecked
                    dairyVal = 1;
                    break;
                case 1://gluten
                    dairyVal = 0;//unchecked
                    peanutsVal = 0;//unchecked
                    eggsVal = 0;//unchecked
                    glutenVal = 1;
                    break;
                case 2://peanuts
                    dairyVal = 0;//unchecked
                    glutenVal = 0;//unchecked
                    eggsVal = 0;//unchecked
                    peanutsVal = 1;
                    break;
                case 3://eggs
                    dairyVal = 0;//unchecked
                    glutenVal = 0;//unchecked
                    peanutsVal = 0;//unchecked
                    eggsVal = 1;
                    break;
                default:
                    break;
            }
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    if (checkedId == R.id.r1)//never
                    {
                        if (position == 0)
                            dairyVal = 1;
                        else if (position == 1)
                            glutenVal = 1;
                        else if (position == 2)
                            peanutsVal = 1;
                        else if (position == 3)
                            eggsVal = 1;

                    } else {
                        if (position == 0)
                            dairyVal = 2;
                        else if (position == 1)
                            glutenVal = 2;
                        else if (position == 2)
                            peanutsVal = 2;
                        else if (position == 3)
                            eggsVal = 2;
                    }
                }
            });
        }

            return rowView;
        }
    }
