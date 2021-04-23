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


public class HardActivity1 extends AppCompatActivity {

    ListView listView;
    String[] tstory , dstory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard1);

        Toolbar toolbar = findViewById(R.id.hard_toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("Reading");
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tstory = getResources().getStringArray(R.array.title_story3);
        dstory = getResources().getStringArray(R.array.detailed_story3);


        listView = findViewById(R.id.listhard);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tstory);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String t = dstory[position];
                Intent intent = new Intent(HardActivity1.this, HardActivity2.class);
                intent.putExtra("story", t);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            HardActivity1.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}