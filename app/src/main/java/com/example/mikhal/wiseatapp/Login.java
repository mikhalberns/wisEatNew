package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.FacebookActivity;
import com.mukeshsolanki.sociallogin.facebook.FacebookHelper;
import com.mukeshsolanki.sociallogin.facebook.FacebookListener;
import com.mukeshsolanki.sociallogin.google.GoogleHelper;
import com.mukeshsolanki.sociallogin.google.GoogleListener;


public class Login extends AppCompatActivity implements FacebookListener, GoogleListener{

    FacebookHelper mFacebook;
    GoogleHelper mGoogle;
    DatabaseHelper myDb;
    boolean isGoogle = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (isGoogle)//google
        {
            final GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

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

            }
        }
        else //facebook
        {

        }

        //only show connection token on screen
        //mFacebook.onActivityResult(requestCode, resultCode, data);
        // mGoogle.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb= new DatabaseHelper(this);

        // init Facebook
        FacebookSdk.setApplicationId(getResources().getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(this);
        mFacebook= new FacebookHelper(this);

        //Init Google
        mGoogle= new GoogleHelper(this,this,null);

        Button btnFacebook = (Button)findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFacebook.performSignIn(Login.this);
            }
        });

        Button btnGoogle = (Button)findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isGoogle=true;
                mGoogle.performSignIn(Login.this);
            }
        });
    }

    @Override
    public void onFbSignInFail(String errorMessage) {
        Toast.makeText(this, ""+errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFbSignInSuccess(String authToken, String userId) {
        Toast.makeText(this, ""+userId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFBSignOut() {
        Toast.makeText(this, "Signout !!!", Toast.LENGTH_SHORT).show();
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
