package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MediumActivity1 extends AppCompatActivity {

    ListView listView;
    String[] tstory , dstory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium1);

        Toolbar toolbar = findViewById(R.id.medium_toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("Reading");
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tstory = getResources().getStringArray(R.array.title_story2);
        dstory = getResources().getStringArray(R.array.detailed_story2);


        listView = findViewById(R.id.listmedium);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tstory);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String t = dstory[position];
                Intent intent = new Intent(MediumActivity1.this, MediumActivity2.class);
                intent.putExtra("story", t);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            MediumActivity1.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}