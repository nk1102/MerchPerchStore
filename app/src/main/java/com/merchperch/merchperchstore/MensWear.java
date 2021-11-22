package com.merchperch.merchperchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MensWear extends AppCompatActivity {

    ImageButton ib1,ib2,ib3,ib4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mens_wear);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        ib1=findViewById(R.id.imageButton5);
        //Formals
        ib2=findViewById(R.id.imageButton6);
        //bottom wear;
        ib3=findViewById(R.id.imageButton7);
        //shoes
        ib4=findViewById(R.id.back3);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MensWear.this,Formals.class);
                startActivity(i);
                finish();
            }
        });
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(MensWear.this,BottomWear.class);
                startActivity(j);
                finish();
            }
        });
        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(MensWear.this,Shoes.class);
                startActivity(k);
                finish();
            }
        });
        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(MensWear.this,Clothing.class);
                startActivity(l);
                finish();
            }
        });
    }
}