package com.merchperch.merchperchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Stationery extends AppCompatActivity {
    Button b1,b2,b3,b4,b5;
    ImageButton ib1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stationery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        b1=findViewById(R.id.Monk);
        b2=findViewById(R.id.Geometry);
        b3=findViewById(R.id.Apsara);
        b4=findViewById(R.id.PlaceSt);
        b5=findViewById(R.id.ContinueSt);
        ib1=findViewById(R.id.Stattioneryback4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Stationery.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Stationery.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Stationery.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Stationery.this,PlaceOrder.class);
                startActivity(i);
                finish();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(Stationery.this,MainActivity.class);
                startActivity(j);
                finish();
            }
        });
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Stationery.this,MainActivity.class);
                startActivity(k);
                finish();
            }
        });
    }
}