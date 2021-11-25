package com.merchperch.merchperchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.merchperch.merchperchstore.R;

/**
 * Contributed by pranay
 */

public class Tops extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6;
    ImageButton ib1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tops);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        b1=findViewById(R.id.WhiteContrast);
        b2=findViewById(R.id.PinkShade);
        b3=findViewById(R.id.YellowKurta);
        b4=findViewById(R.id.BrownishShade);
        b5=findViewById(R.id.PlaceT);
        b6=findViewById(R.id.ContinueT);
        ib1=findViewById(R.id.topsback4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tops.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tops.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tops.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tops.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Tops.this,PlaceOrder.class);
                startActivity(i);
                finish();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(Tops.this,MainActivity.class);
                startActivity(j);
                finish();
            }
        });

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Tops.this,MainActivity.class);
                startActivity(k);
                finish();
            }
        });
    }
}