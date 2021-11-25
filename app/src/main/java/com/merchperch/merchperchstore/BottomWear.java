package com.merchperch.merchperchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.merchperch.merchperchstore.R;

public class BottomWear extends AppCompatActivity {
    Button b1,b2,b3,b4,b5;
    ImageButton ib1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_wear);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        b1=findViewById(R.id.BlackTrouser);
        b2=findViewById(R.id.SkinTrouser);
        b3=findViewById(R.id.CreamWhiteTrouser);
        b4=findViewById(R.id.PlaceBw);
        b5=findViewById(R.id.ContinueBw);
        ib1=findViewById(R.id.Bottomwearback4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomWear.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomWear.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomWear.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(BottomWear.this,PlaceOrder.class);
                startActivity(j);
                finish();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(BottomWear.this,MainActivity.class);
                startActivity(j);
                finish();
            }
        });
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(BottomWear.this,MainActivity.class);
                startActivity(j);
                finish();
            }
        });
    }
}