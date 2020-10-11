package com.ucas.android2.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucas.android2.R;

import java.util.ArrayList;

public class MultipleItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> stringsList;
    int TYPE_TEST = 1;
    int TYPE_STUDENT = 2;

    class MyViewHolder1 extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView name, age;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
        }
    }

    public MultipleItemsAdapter(ArrayList<String> stringsList) {
        this.stringsList = stringsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_TEST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
            MyViewHolder1 myViewHolder = new MyViewHolder1(view);
            return myViewHolder;
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sutdent_item, parent, false);
            MyViewHolder2 myViewHolder = new MyViewHolder2(view);
            return myViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_TEST) {
            ((MyViewHolder1) holder).textView.setText("" + stringsList.get(position));
        } else {
            ((MyViewHolder2) holder).name.setText("" + stringsList.get(position));
        }
        Log.d("position", position + "");
    }

    @Override
    public int getItemCount() {
        return stringsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (stringsList.get(position).isEmpty()) {
            return TYPE_TEST;
        } else {
            return TYPE_STUDENT;
        }
    }
}
