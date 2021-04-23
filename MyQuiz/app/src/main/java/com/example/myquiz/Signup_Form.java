package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Signup_Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        Toolbar toolbar = findViewById(R.id.set_toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Signup Form");
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void btn_mainactivity(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}