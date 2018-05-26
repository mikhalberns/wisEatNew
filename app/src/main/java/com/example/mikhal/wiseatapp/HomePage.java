package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/****************************************************HomePage.java**********************************************************
This class is responsible for navigating the user to the right activity. The user can choose between the following options:
1.Taking A Picture
2.Type Text Manually
3.Edit Profile (update it after the first time)
4.Logout from the active account
 In addition, in this activity you will get recommendations by your style of choices.
**************************************************************************************************************************/

public class HomePage extends AppCompatActivity {

    private ImageButton btnSetProfile;
    private Button btnLogout;
    private Button btnEnterIngredients;
    private Button btnAddPic;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);

        //get the active session in order to be able to log out the account
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        //Go back to SetProfile Page
        btnSetProfile= (ImageButton)findViewById(R.id.ButtonSetProfile);
        btnSetProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
            }
        });

        //Logout user account
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

        //Go to Search Ingredients Manually Screen
        btnEnterIngredients= (Button)findViewById(R.id.EnterIngredients);
        btnEnterIngredients.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchIngredients.class));

            }
        });

        //Go to Take A Picture Screen
        btnAddPic= (Button)findViewById(R.id.addPic);
        btnAddPic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OcrView.class));

            }
        });

    }

    /**********************************************************************************
     * This function starts each time the user get to the homepage screen.
     * If the user has been searched 10 or more ingredients that the user's profile says
     * the user never or occationally eat - A Messege will popup in this screen with
     * a recommendation.
     */
    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);

        if(myDb.checkIfExistInRecoveryTable()==true)
        {
            String neverStr = myDb.checkIfGotTo10NeverIng();
            String occStr = myDb.checkIfGotTo10OccIng();

            if(neverStr!="")
            {
                showMessage("Reccomendation", "We've noticed that you choose ingredients from food families that you have marked as 'Never' over and over again, consider changing your profile.\n"+"The families are:\n"+neverStr);
            }
            if(occStr!="")
            {
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
