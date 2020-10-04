package com.ucas.android2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> strings;

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    public ImagesAdapter(Context context,ArrayList<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.textView.setText(""+strings.get(position));
        Glide.with(context).load("https://png.pngtree.com/thumb_back/fw800/back_our/20190628/ourmid/pngtree-green-background-material-picture-image_263996.jpg").into(holder.imageView);
        Log.d("position", position + "");
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }
}
