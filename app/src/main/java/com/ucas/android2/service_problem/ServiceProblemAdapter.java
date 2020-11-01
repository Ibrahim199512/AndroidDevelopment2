package com.ucas.android2.service_problem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucas.android2.R;

import java.util.ArrayList;

class ServiceProblemAdapter extends RecyclerView.Adapter<ServiceProblemAdapter.MyViewHolder> {

    ArrayList<String> strings;
    int selectedPosition = 0;
    Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

    public ServiceProblemAdapter(Context context, ArrayList<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText("" + strings.get(position));
        if (selectedPosition == position)
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        else
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

        Log.d("position", position + "");
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }
}
