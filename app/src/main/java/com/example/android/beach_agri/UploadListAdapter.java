package com.example.android.beach_agri;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UploadListAdapter extends RecyclerView.Adapter<Viewholder> {

    public List<String> filenamelist;
    public Context context;
    private LayoutInflater mInflater;


    public UploadListAdapter(Context context,List<String> filenamelist) {
        this.context=context;
        this.filenamelist = filenamelist;
        this.mInflater = LayoutInflater.from(context);

    }

    // inflates the row layout from xml when needed
    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview, parent, false);
        return new Viewholder(view);
    }





    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String fileName = filenamelist.get(position);
        holder.fileNameView.setText(fileName);
        holder.setItemClickListener(new RecycleViewClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(context,showdetails.class);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filenamelist.size();
    }




}
