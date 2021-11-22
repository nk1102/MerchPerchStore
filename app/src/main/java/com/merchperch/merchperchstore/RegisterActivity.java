package com.merchperch.merchperchstore;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static android.view.View.GONE;


public class RegisterActivity extends AppCompatActivity {

    /**
     * All the variables that are need to be declared are declared here
     */

    EditText email, password, Name;
    TextView privacyPolicy, Loginbtn;
    Button registerbtn;
    FirebaseAuth firebaseAuth;

    DatabaseReference firebaseDatabase;
    AlertDialog.Builder builder;
    AlertDialog progressDialog;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /**
         * All variables are initialised here
         */
        email = findViewById(R.id.editTextTextEmailAddressRegisterActivity);

        password = findViewById(R.id.editTextTextPasswordregisteractivity);
        Name = findViewById(R.id.editTextTextNameRegisterActivity);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        registerbtn = findViewById(R.id.registerbtnregisteractivity);
        Loginbtn = findViewById(R.id.LoginBtnregisteractivity);
        progressBar = findViewById(R.id.progressBar);


        /**
         * Action bar code to hide it
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        View parentLayout = findViewById(android.R.id.content);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        if (isNetworkAvailable() == false) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterActivity.this);
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


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String txt_email = email.getText().toString();


                String txt_password = password.getText().toString();


                String txt_name = Name.getText().toString();
                if (txt_name.isEmpty()) {
                    Name.setError("Name cannot be empty");
                }
                if (txt_email.isEmpty()) {

                    email.setError("Email cannot be empty");

                }

                if (txt_password.isEmpty()) {
                   progressBar.setVisibility(GONE);
                    password.setError("Password Cannot be empty");
                } else if (txt_password.length() <= 6) {
                   progressBar.setVisibility(GONE);
                    Snackbar.make(parentLayout, "Password cannot be of less than 6 digits", Snackbar.LENGTH_LONG)
                            .setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.white))
                            .show();

                } else {

                    firebaseAuth.createUserWithEmailAndPassword(txt_email, txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {


                                       progressBar.setVisibility(GONE);
                                        /**
                                         * Code for verification of email
                                         */

                                        firebaseAuth.getCurrentUser().sendEmailVerification();

                                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                        String userId = firebaseUser.getUid();


                                        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Users").child(userId);


                                        HashMap<String, String> hashMap = new HashMap<>();
                                        hashMap.put("id", userId);
                                        hashMap.put("username", txt_name);

                                        hashMap.put("Email", txt_email);

                                        firebaseDatabase.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {


                                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(RegisterActivity.this);
                                                    builder2.setTitle("Congrats");
                                                    builder2.setMessage("Your Account has been Created Successfully");
                                                    builder2.setCancelable(true);

                                                    builder2.setPositiveButton(
                                                            "Ok",
                                                            new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {
                                                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                                                    Toast.makeText(RegisterActivity.this, "Verification email sent .Please verify your email unless you cannot login.", Toast.LENGTH_LONG).show();
                                                                    finish();

                                                                }
                                                            });
                                                    AlertDialog dialog = builder2.create();
                                                    dialog.show();

                                                }
                                            }
                                        });

                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("LoginException", "FirebaseException: " + e.getMessage());
                            progressBar.setVisibility(GONE);
                            Snackbar.make(parentLayout, e.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                                    .show();

                        }
                    });
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