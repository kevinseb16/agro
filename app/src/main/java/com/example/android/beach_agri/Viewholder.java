package com.example.android.beach_agri;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView fileNameView;
    public ImageView imageView;
    public TextView fileprice;
    RecycleViewClickListener itemClickListener;
    View mView;
    Context context;

    public Viewholder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        this.context = itemView.getContext();
        fileprice=(TextView)mView.findViewById(R.id.priceview);
        fileNameView = (TextView) mView.findViewById(R.id.nameview);
        imageView=(ImageView)mView.findViewById(R.id.image2);

        mView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());


    }

    public void setItemClickListener(RecycleViewClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
