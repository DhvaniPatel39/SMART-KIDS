package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Vocabulary_Score extends AppCompatActivity {

    private TextView score;
    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary__score);

        score = findViewById(R.id.score);
        done = findViewById(R.id.done);

        String score_str = getIntent().getStringExtra("SCORE");
        Log.d("Tag", score_str);
        score.setText(score_str);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Vocabulary_Score.this,VocabularyActivity1.class);
                Vocabulary_Score.this.startActivity(intent);
                Vocabulary_Score.this.finish();
            }
        });

    }
}