package com.merchperch.merchperchstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.FirebaseDatabase;

public class ForgotPasswordActivity extends AppCompatActivity {




    EditText email;
    Button resetBtn;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
//    ProgressBar progressBar;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextTextEmailAddressforgotpsswdactivity);

        resetBtn = findViewById(R.id.resetBtn);
       // Enhance.showInterstitialAd();
        /**
         * Action bar code to hide it
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
//        progressBar = findViewById(R.id.progressBarForgotpasswordActivity);
        /**
         * Code to check the internet connection!
         */
        if (isNetworkAvailable() == false) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(ForgotPasswordActivity.this);
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
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString();

                /* To check the given email is empty or not */
                if (txt_email.isEmpty()) {
                    email.setError("Email is required");

                } else if (checkEmail(txt_email)) {

                }
                else {
                    /*  Password reset mail sent through below code */
                   // progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.sendPasswordResetEmail(txt_email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                       // progressBar.setVisibility(View.GONE);
                                        //Toast.makeText(ResetPasswordActivity.this, "Email Sent ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            //progressBar.setVisibility(View.GONE);
                            Log.e("ResetPasswordActivity", "RuntimeException: " + e.getMessage());
                        }
                    });
                }
            }
        });

    }
    /* To check if user's email is already present or not  */


    public boolean checkEmail(String txtEmail) {
        firebaseAuth.fetchSignInMethodsForEmail(txtEmail)
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.getResult().getSignInMethods().isEmpty()) {
                            View parentLayout = findViewById(android.R.id.content);
                            Snackbar.make(parentLayout, "Email doesn't Exists.", Snackbar.LENGTH_LONG)
                                    .show();
                        } else {
                            View parentLayout = findViewById(android.R.id.content);
                            Snackbar.make(parentLayout, "Password reset email has been sent on your email address.", Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
        return false;
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}