package com.ucas.android2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.MyViewHolder> {

    ArrayList<Student> studentsList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, age;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
        }
    }

    public StudentsAdapter(ArrayList<Student> studentsList) {
        this.studentsList = studentsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sutdent_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(studentsList.get(position).getName());
        holder.age.setText(studentsList.get(position).getAge());
        Log.d("position", position + "");


    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }
}
