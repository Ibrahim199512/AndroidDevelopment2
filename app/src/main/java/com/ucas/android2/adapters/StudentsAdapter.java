package com.ucas.android2.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucas.android2.R;
import com.ucas.android2.databinding.SutdentItemBinding;
import com.ucas.android2.interfaces.StudentInterface;
import com.ucas.android2.modules.Student;

import java.util.ArrayList;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.MyViewHolder> {

    ArrayList<Student> studentsList;
    StudentInterface studentInterface;

    class MyViewHolder extends RecyclerView.ViewHolder {
        SutdentItemBinding binding;

        public MyViewHolder(SutdentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public StudentsAdapter(ArrayList<Student> studentsList, StudentInterface studentInterface) {
        this.studentsList = studentsList;
        this.studentInterface = studentInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SutdentItemBinding binding = SutdentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.binding.name.setText(studentsList.get(position).getName());
        holder.binding.age.setText(studentsList.get(position).getAge());
        holder.binding.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentInterface.sendSomeText("" + studentsList.get(position).getName());
            }
        });
        Log.d("position", position + "");


    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }
}
