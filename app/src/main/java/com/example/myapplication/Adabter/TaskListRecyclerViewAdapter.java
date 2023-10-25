package com.example.myapplication.Adabter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

//TODO : step 1-4 Make a Class to Manage RV
public class TaskListRecyclerViewAdapter extends RecyclerView.Adapter {
    //TODO : step 1-7 inflate fragment
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View TaskFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task_list,parent,false);
        //TODO : step 1-9 Attach fragment to ViewHolder
        return new TaskListViewHolder(TaskFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }
    //TODO : step 1-8 make  A view Holder Class hold the fragment
    public static class TaskListViewHolder extends RecyclerView.ViewHolder{
        public TaskListViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
