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
public class Heals extends AppCompatActivity {
    Button b1,b2,b3,b4,b5;
    ImageButton ib1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heals);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        b1=findViewById(R.id.BlackHeels);
        b2=findViewById(R.id.CreamHeels);
        b3=findViewById(R.id.darkredHeels);
        b4=findViewById(R.id.PlaceH);
        b5=findViewById(R.id.ContinueH);
        ib1=findViewById(R.id.healsback4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Heals.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Heals.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Heals.this, "ITEM ADDED TO CART", Toast.LENGTH_SHORT).show();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Heals.this,PlaceOrder.class);
                startActivity(i);
                finish();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(Heals.this,MainActivity.class);
                startActivity(j);
                finish();
            }
        });
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Heals.this,MainActivity.class);
                startActivity(k);
                finish();
            }
        });
    }
}