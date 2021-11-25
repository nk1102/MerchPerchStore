package com.merchperch.merchperchstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PlaceOrder extends AppCompatActivity {
    EditText e1,e2, Name;
    Button b1;
    ImageButton ib1;
    String s1,s2;
    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        e1=(EditText)findViewById(R.id.editTextTextPostalAddress2);
        e2=(EditText)findViewById(R.id.editTextPhone);
        b1=findViewById(R.id.confirmorder);
        ib1=findViewById(R.id.ConfirmOrder);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=e1.getText().toString();
                s2=e2.getText().toString();
                if(s1.isEmpty()|| s2.isEmpty())
                {
                    e1.setError("FILL ADDRESS and phone number");
                    return;
                }
                else
                {
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            String userId = firebaseUser.getUid();
                            String userName = firebaseAuth.getCurrentUser().getDisplayName();
                            String email = firebaseAuth.getCurrentUser().getEmail();

                            firebaseDatabase = FirebaseDatabase.getInstance().getReference("Users").child(userId);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userId);
                            hashMap.put("Name", userName);
                            hashMap.put("Email", email);
                            hashMap.put("Address",s1);
                            hashMap.put("ContactNumber",s2);

                            firebaseDatabase.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull  Task<Void> task) {
                                    if (task.isSuccessful()){

                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(PlaceOrder.this);
                                        builder2.setTitle("Congrats");
                                        builder2.setMessage("Your Order has been placed successfully");
                                        builder2.setCancelable(true);

                                        builder2.setPositiveButton(
                                                "Ok",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        startActivity(new Intent(PlaceOrder.this, ConfrimOrder.class));
                                                        finish();

                                                    }
                                                });
                                        AlertDialog dialog = builder2.create();
                                        dialog.show();

                                    }
                                }
                            });


//                            Intent intent = new Intent(PlaceOrder.this,ConfrimOrder.class);
//                            startActivity(intent);
//                            finish();
                        }
                    });
                }
            }
        });
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlaceOrder.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}