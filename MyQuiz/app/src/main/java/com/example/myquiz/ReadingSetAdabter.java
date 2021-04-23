package com.example.myquiz;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ReadingSetAdabter extends BaseAdapter {
    private  int numOfSets;

    public ReadingSetAdabter(int numOfSets) {
        this.numOfSets = numOfSets;
    }

    @Override
    public int getCount() {
        return numOfSets;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item_layout,parent,false);
        }
        else{
            view = convertView;
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(position){
                    case 0:
                        Intent intent = new Intent(parent.getContext(), ReadingActivity.class);
                        intent.putExtra("Reading", "Set 1");
                        parent.getContext().startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(parent.getContext(), MediumActivity1.class);
                        intent.putExtra("Reading", "Set 2");
                        parent.getContext().startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(parent.getContext(), HardActivity1.class);
                        intent.putExtra("Reading", "Set 3");
                        parent.getContext().startActivity(intent);
                        break;
                }
              //  Intent intent = new Intent(parent.getContext(), ReadingActivity.class);
              //  parent.getContext().startActivity(intent);
            }
        });

        ((TextView)view.findViewById(R.id.setNo_TV)).setText(String.valueOf(position+1));

        return view;
    }
}
