package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.mukeshsolanki.sociallogin.google.GoogleHelper;
import com.mukeshsolanki.sociallogin.google.GoogleListener;


public class Login extends AppCompatActivity implements GoogleListener{

    GoogleHelper mGoogle;
    DatabaseHelper myDb;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        myDb.deactivateAll();

        if (myDb.checkIfUIDExist(result.getSignInAccount().getId())==false)//if first login - inset to db + move to set profile screen
        {
            boolean isInserted = myDb.insertUIDToUsers(result.getSignInAccount().getId());

            if(isInserted == true)
                Toast.makeText(Login.this,"Data Inserted",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(Login.this,"Data not Inserted",Toast.LENGTH_LONG).show();

            startActivity(new Intent(getApplicationContext(), setProfile.class));
        }
        else//move to the home page
        {
            myDb.activateUser(result.getSignInAccount().getId());
            startActivity(new Intent(getApplicationContext(), HomePage.class));
        }

        //only show connection token on screen
        // mGoogle.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb= new DatabaseHelper(this);

        //Init Google
        mGoogle= new GoogleHelper(this,this,null);

        Button btnGoogle = (Button)findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogle.performSignIn(Login.this);
            }
        });
    }


    @Override
    public void onGoogleAuthSignIn(String authToken, String userId) {
        Toast.makeText(this, ""+userId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleAuthSignInFailed(String errorMessage) {
        Toast.makeText(this, ""+errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleAuthSignOut() {
        Toast.makeText(this, "Signout !!", Toast.LENGTH_SHORT).show();
    }
}
