package com.ucas.android2.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucas.android2.R;
import com.ucas.android2.interfaces.StudentInterface;
import com.ucas.android2.modules.Student;

import java.util.ArrayList;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.MyViewHolder> {

    ArrayList<Student> studentsList;
    StudentInterface studentInterface;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, age;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
        }
    }

    public StudentsAdapter(ArrayList<Student> studentsList,StudentInterface studentInterface) {
        this.studentsList = studentsList;
        this.studentInterface = studentInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sutdent_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(studentsList.get(position).getName());
        holder.age.setText(studentsList.get(position).getAge());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentInterface.sendSomeText(""+studentsList.get(position).getName());
            }
        });
        Log.d("position", position + "");


    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }
}
