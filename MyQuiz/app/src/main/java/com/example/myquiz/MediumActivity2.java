package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media.MediaBrowserCompatUtils;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MediumActivity2 extends AppCompatActivity {

    TextView textView;
    MediaPlayer mysong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium2);
        textView = findViewById(R.id.txt);
        String dstory = getIntent().getStringExtra("story");

        textView.setText(dstory);
        mysong = MediaPlayer.create(MediumActivity2.this, R.raw.londonbridge);
    }

    public void playIT(View v) {
        mysong.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mysong.release();
    }
}