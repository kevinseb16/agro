package com.example.android.beach_agri;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UploadListAdapter extends RecyclerView.Adapter<Viewholder> {

    public List<String> filenamelist;
    public List<String> fileprice;
    public List<String> imageurl;
    public Context context;
    private LayoutInflater mInflater;


    public UploadListAdapter(Context context,List<String> filenamelist,List<String> imageurl,List<String> fileprice) {
        this.context=context;
        this.fileprice=fileprice;
        this.filenamelist = filenamelist;
        this.imageurl=imageurl;
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
        final String image=imageurl.get(position);
        String price=fileprice.get(position);
        Bitmap bm = null;
        try {
            bm = new Retrievebitmap().execute(image).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        holder.fileNameView.setText(fileName);

        holder.imageView.setImageBitmap(bm);
        holder.fileprice.setText(price+"Rs/Kg");

        holder.setItemClickListener(new RecycleViewClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent=new Intent(context.getApplicationContext(),showdetails.class);
                intent.putExtra("pos",pos);
                intent.putExtra("image",image);
                context.startActivity(intent);//
            }
        });

    }


    @Override
    public int getItemCount() {
        return filenamelist.size();
    }




}
