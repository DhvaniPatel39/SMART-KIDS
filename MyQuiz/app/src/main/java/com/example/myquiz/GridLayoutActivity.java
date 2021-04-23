package com.example.myquiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

public class GridLayoutActivity extends AppCompatActivity {
    private TextView textgrid;
    GridLayout mainGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);

        textgrid = findViewById(R.id.textGrid);

        Typeface typeface = ResourcesCompat.getFont(this,R.font.blacklist);
        textgrid.setTypeface(typeface);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);
//        setToggleEvent(mainGrid);
    }

    public void btn_spinwheel(View view) {
        startActivity(new Intent(getApplicationContext(),SpinnerActivity.class));
    }

    /*private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final int x=i;
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(GridLayoutActivity.this, "State : True"+x, Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(GridLayoutActivity.this, "State : False"+x, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }*/





    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch(finalI){
                        case 0:
                            Intent intent = new Intent(GridLayoutActivity.this,ReadingSets.class);
                            intent.putExtra("CATEGORY","Reading");
                            GridLayoutActivity.this.startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(GridLayoutActivity.this,ColoringGame.class);
                            intent.putExtra("CATEGORY","Coloring");
                            GridLayoutActivity.this.startActivity(intent);
                            break;
                        case 2:
                            //Toast.makeText(view.getContext(), "Building in progress", Toast.LENGTH_SHORT).show();
                            intent = new Intent(GridLayoutActivity.this,Maths.class);
                            intent.putExtra("CATEGORY","Maths");
                            GridLayoutActivity.this.startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(GridLayoutActivity.this,VocabularyActivity1.class);
                            intent.putExtra("CATEGORY","Vocabulary");
                            GridLayoutActivity.this.startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent(GridLayoutActivity.this, SetsActivity.class);
                            intent.putExtra("CATEGORY","G.K.");
                            GridLayoutActivity.this.startActivity(intent);
                            break;
                        case 5:
                            intent = new Intent(GridLayoutActivity.this, Memory_game.class);
                            intent.putExtra("CATEGORY","Memory Game");
                            GridLayoutActivity.this.startActivity(intent);
                            break;
                    }
                }
            });
        }
    }
}