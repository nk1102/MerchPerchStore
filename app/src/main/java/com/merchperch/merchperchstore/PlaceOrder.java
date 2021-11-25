package com.merchperch.merchperchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class PlaceOrder extends AppCompatActivity {
    EditText e1,e2;
    Button b1;
    ImageButton ib1;
    String s1,s2;

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
                            Intent intent = new Intent(PlaceOrder.this,ConfrimOrder.class);
                            startActivity(intent);
                            finish();
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