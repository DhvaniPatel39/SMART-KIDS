package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        Toolbar toolbar = findViewById(R.id.set_toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login Form");
    }

    public void btn_SignupForm(View view) {
        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }
}