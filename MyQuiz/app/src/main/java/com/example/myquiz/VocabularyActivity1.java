package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class VocabularyActivity1 extends AppCompatActivity {
    private Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary1);

        Toolbar toolbar = findViewById(R.id.set_toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CATEGORY");
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        b1 = findViewById(R.id.SpellingB);
        b2 = findViewById(R.id.VerbB);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);
        b1.setTypeface(typeface);
        b2.setTypeface(typeface);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VocabularyActivity1.this, VocabularyActivity2.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VocabularyActivity1.this, VocabularyActivity3.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            VocabularyActivity1.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}