package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {

    ImageButton btnSetProfile;
    Button btnLogout;
    Button btnEnterIngredients;
    Button btnAddPic;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDb = new DatabaseHelper(this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null) // if we are logged out
                {
                   // Toast.makeText(HomePage.this,"user logged out",Toast.LENGTH_LONG).show();
                   //startActivity(new Intent(HomePage.this, Login.class));
                }
            }
        };

        btnSetProfile= (ImageButton)findViewById(R.id.ButtonSetProfile);
        btnSetProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), setProfile.class));

            }
        });

        //log out button
       btnLogout= (Button)findViewById(R.id.ButtonLogout);
        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.delete();
                mAuth.signOut();
                startActivity(new Intent(HomePage.this, Login.class));
            }
        });


        btnEnterIngredients= (Button)findViewById(R.id.EnterIngredients);
        btnEnterIngredients.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchIngredients.class));

            }
        });

        btnAddPic= (Button)findViewById(R.id.addPic);
        btnAddPic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OcrView.class));

            }
        });

    }

  //  @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);

        if(myDb.checkIfExistInRecoveryTable()==true)
        {
            String neverStr = myDb.checkIfGotTo10NeverIng();
            String occStr = myDb.checkIfGotTo10OccIng();

            //Toast.makeText(HomePage.this,neverStr,Toast.LENGTH_LONG).show();
            if(neverStr!="")
            {

                showMessage("Reccomendation", "We've noticed that you choose ingredients from food families that you have marked as 'Never' over and over again, consider changing your profile.\n"+"The families are:\n"+neverStr);
            }
            if(occStr!="")
            {
                //Toast.makeText(HomePage.this,occStr,Toast.LENGTH_LONG).show();
                showMessage("Reccomendation", "We've noticed that you choose ingredients from food families that you have marked as 'Occasionally' over and over again, consider changing your profile.\n"+"The families are:\n"+occStr);
            }
        }

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public void onBackPressed() {
      //  startActivity(new Intent(getApplicationContext(), HomePage.class));
    }
}
