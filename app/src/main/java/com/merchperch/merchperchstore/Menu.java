package com.merchperch.merchperchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {
    ImageButton ib1,ib2,ib3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        ib1 = findViewById(R.id.imageButton2);
        ib3 = findViewById(R.id.imageButtonOI);
        //back
        ib2 = findViewById(R.id.imageButton3);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Menu.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(Menu.this,Stationery.class);
                startActivity(j);
                finish();
            }
        });

        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(Menu.this,OtherItems.class);
              startActivity(intent);
              finish();
            }
        });
    }
}