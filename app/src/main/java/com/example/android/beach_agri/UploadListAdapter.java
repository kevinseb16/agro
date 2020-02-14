package com.example.android.beach_agri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UploadListAdapter extends RecyclerView.Adapter<UploadListAdapter.ViewHolder> {

    public List<String> filenamelist;


    public UploadListAdapter(List<String> filenamelist) {
        this.filenamelist = filenamelist;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String fileName = filenamelist.get(position);
        holder.fileNameView.setText(fileName);

    }

    @Override
    public int getItemCount() {
        return filenamelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView fileNameView;

        View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            fileNameView = (TextView) mView.findViewById(R.id.nameview);


        }
    }
}
