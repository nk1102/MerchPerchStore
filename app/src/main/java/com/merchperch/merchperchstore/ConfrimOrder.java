package com.merchperch.merchperchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ConfrimOrder extends AppCompatActivity {
    ImageButton ib1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrim_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        ib1=findViewById(R.id.thankyou);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ConfrimOrder.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}