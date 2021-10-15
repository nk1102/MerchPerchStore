package com.merchperch.merchperchstore;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;


import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;


public class LoginActivity extends AppCompatActivity {

    private BroadcastReceiver MyReceiver = null;
    private static  int RC_SIGN_IN = 123;
    /**
     * Variables are declared here.
     */

    EditText email, password;
    TextView registerBtn, forgotPwd;
    Button loginBtn;
    ImageView googleBtn;


    FirebaseAuth firebaseAuth;

    DatabaseReference reference;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() != null){
            Intent intent =new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**
         * Code to hide the action bar on top of the screen
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();


        /**
         * Variables are Initialised here
         */

        email = findViewById(R.id.editTextTextEmailAddressLoginActivity);
        password = findViewById(R.id.editTextTextPasswordLoginActivity);
        registerBtn = findViewById(R.id.registerbtnloginactivity);
        forgotPwd = findViewById(R.id.ForgotPsswd);
        loginBtn = findViewById(R.id.loginbtnloginactivity);
        googleBtn = findViewById(R.id.googlebtnloginactivty);
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        View parentLayout = findViewById(android.R.id.content);

        if (isNetworkAvailable() == false) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
            builder1.setTitle("Oops!");
            builder1.setMessage("Your are offline.Check your connection and try again");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            finish();

                        }
                    });
            AlertDialog dialog = builder1.create();
            dialog.show();
        }

        /**
         * Login button code below
         */
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = email.getText().toString().trim();
                String userPassword = password.getText().toString().trim();
                if (emailId.isEmpty()){
                    email.setError("Email cannot be empty ");
                }
                else if(userPassword.isEmpty()){
                    password.setError("Password cannot be empty");
                }
                else{
                //    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(emailId,userPassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull  Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                     //   progressBar.setVisibility(View.GONE);
                                        if(firebaseAuth.getCurrentUser().isEmailVerified()) {
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            Log.e("EmailVerification", "Email verified successfully ");
                                            Log.e("LoginSuccessful", "User signed in successfully ");
                                            startActivity(intent);
                                            finish();
                                        }
                                        else{

                                            Snackbar.make(parentLayout, "Verification of email required.", Snackbar.LENGTH_LONG)
                                                    .setAction("Ok", new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                        }
                                                    })
                                                    .setActionTextColor(getResources().getColor(R.color.white))
                                                    .show();
                                        }
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull  Exception e) {
                            //progressBar.setVisibility(View.GONE);
                            Log.e("LoginException", "FirebaseException: Login Failure " + e.getMessage());
                            View parentLayout = findViewById(android.R.id.content);
                            Snackbar.make(parentLayout,e.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

                finish();

            }
        });
        forgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   progressBar.setVisibility(View.VISIBLE);
               // startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class));

            }

        });

        googleBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                signIn();
                mGoogleSignInClient.signOut();

            }
        });


        createRequest();
    }



    private void createRequest() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("548998130778-g5ho0a4qurftj36hh2r24lfrf1d3kkvl.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
               // progressBar.setVisibility(View.VISIBLE);
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.e(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
              //  progressBar.setVisibility(View.GONE);
                Log.e(TAG, "Google sign in failed", e.getCause());
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("logging in please wait ..");

        pd.show();


        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            Log.d(TAG, "signInWithCredential:success");
                            pd.dismiss();

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            /**
                             * Google authenticated user details have been stored in the firebase Database
                             */

                            /* helpful line to store same location as users in register code */
                            /**
                             * Important code below
                             */
                            reference =FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
                            HashMap<String , String> hashMap = new HashMap<>();
                            hashMap.put("id",user.getUid());
                            hashMap.put("username",user.getDisplayName());

                            hashMap.put("imageURL","default");
                            hashMap.put("status","offline");
                            hashMap.put("search",user.getDisplayName().toLowerCase());
                            hashMap.put("Email",user.getEmail());


                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });


                        } else {
                          //  progressBar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });

    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}