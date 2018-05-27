package com.example.mikhal.wiseatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/*****************************************************Login.java***********************************************************
This class is responsible for Login action. In this class we use Google Mobile Services API to authenticate the user with
 his Gmail account. We are using Firebase Api to handle the requests and authenticate them.
**************************************************************************************************************************/

public class Login extends AppCompatActivity {

    private DatabaseHelper myDb;
    private static final int RC_SIGN_IN = 1;
    public GoogleApiClient mGoogleApiClient; // user's sign-in request
    private FirebaseAuth mAuth; // active session
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb = new DatabaseHelper(this);
        myDb.insertING2DB();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        Button btnGoogle = (Button) findViewById(R.id.btnGoogle);

        //creating Google SignIn Client
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //load signIn() function when button clicked
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    //Sign In Functions
    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
    }

    //works after signIn function - handle the login by send a login message to Firebase object that handles the authentication and sessions
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            myDb.deactivateAll();

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess())
            {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
                myDb.checkIfNullUser();


                if (myDb.checkIfUIDExist(result.getSignInAccount().getId())==false || myDb.checkIfExistProfileIdForUser(result.getSignInAccount().getId())==false)//if first login - inset to db + move to set profile screen
                {
                    boolean isInserted = myDb.insertUIDToUsers(result.getSignInAccount().getId());

                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    startActivity(new Intent(getApplicationContext(), UserProfile.class));
               }
                else//move to the home page
                {
                    myDb.activateUser(result.getSignInAccount().getId());
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                }
            }
        }
    }

    //authenticate using firebase service (manage the sessions after the authentication)
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                        }
                    }
                });
    }
}
