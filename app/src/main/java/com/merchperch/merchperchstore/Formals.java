package com.merchperch.merchperchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.merchperch.merchperchstore.R;

public class Formals extends AppCompatActivity {
    Button b1,b2,b3,b4,b5;
    ImageButton ib1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formals);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        b1=findViewById(R.id.white1);
        b2=findViewById(R.id.Purple);
        b3=findViewById(R.id.Black);
        b4=findViewById(R.id.PlaceF);
        b5=findViewById(R.id.ContinueF);
        ib1=findViewById(R.id.Formalsback4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Formals.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Formals.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Formals.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Formals.this,PlaceOrder.class);
                startActivity(i);
                finish();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(Formals.this,MainActivity.class);
                startActivity(j);
                finish();
            }
        });
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Formals.this,MainActivity.class);
                startActivity(k);
                finish();
            }
        });
    }
}