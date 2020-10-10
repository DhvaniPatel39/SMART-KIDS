package com.example.myquiz;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquiz.Interface.ImageOnClick;


public class ImageViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    private ImageOnClick imageOnClick;

    public void setImageOnClick(ImageOnClick imageOnClick) {
        this.imageOnClick = imageOnClick;
    }

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image_outliner);

        itemView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                imageOnClick.onClick ( getAdapterPosition () );
            }
        } );
    }
}
