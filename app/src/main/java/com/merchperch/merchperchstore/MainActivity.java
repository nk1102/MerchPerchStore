package com.merchperch.merchperchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ImageButton ib1,ib2,ib3;
    Button b1;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Code by Rishabh
         */

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        ib1=findViewById(R.id.imageButton18);
        //Clothing
        ib2=findViewById(R.id.imageButton19);
        //electronics
        ib3=findViewById(R.id.imageButton20);
        //menu
        b1=findViewById(R.id.button);
        //logout
        firebaseAuth = FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent l=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(l);
                finish();
            }
        });
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Clothing.class);
                startActivity(i);
                finish();
            }
        });
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(MainActivity.this, Electronics.class);
                startActivity(j);
                finish();
            }
        });
        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(MainActivity.this,Menu.class);
                startActivity(k);
                finish();
            }
        });
    }
}