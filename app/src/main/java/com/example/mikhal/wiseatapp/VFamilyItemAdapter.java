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

        return rowView;
    }

}
