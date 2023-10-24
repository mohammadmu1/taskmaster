package com.example.myapplication.Adabter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

//STEP4
public class TaskListRecyclerViewAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //STEP7
        View TaskFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        //STEP9
        return new TaskListViewHolder(TaskFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }
    //STEP78
    public static class TaskListViewHolder extends RecyclerView.ViewHolder{
        public TaskListViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
