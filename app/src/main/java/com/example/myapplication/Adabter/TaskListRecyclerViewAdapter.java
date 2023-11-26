package com.example.myapplication.Adabter;import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.TaskDetailActivity;

import java.util.List;

//TODO: step 1-4: Make a class whose purpose is to manage the RecyclerView
//TODO: step 3-1: clean up the RecyclerView.Adapter reference to actually use ProductListRecyclerViewAdapter

public class TaskListRecyclerViewAdapter extends RecyclerView.Adapter<TaskListRecyclerViewAdapter.TaskListViewHolder> {

    //TODO: step: 2-3: Hand in data items
    List<Task> tasks;

    //TODO: step 3-2: Hand in the Activity context
    Context callingActivity;

    //TODO: step: 2-3: Hand in data items
    public TaskListRecyclerViewAdapter(List<Task> tasks,Context callingActivity) {
        this.tasks = tasks;
        this.callingActivity=callingActivity;
    }

    //TODO: step 1-7: start Inflate fragment
    @NonNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task_list, parent,false);

        //TODO: step 1-9: Attach fragment to viewHolder
        return new TaskListViewHolder(productFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position) {

        //TODO: step 2-4: Bind data items to Fragment inside of ViewHolder
        TextView taskFragmentTextView = (TextView) holder.itemView.findViewById(R.id.taskFragmentTextView);
        String taskName = tasks.get(position).getTitle();
        String taskBody = tasks.get(position).getDescription();
        String taskState = String.valueOf(tasks.get(position).getTaskStatusEnum());
        taskFragmentTextView.setText(position +" - "+ taskName);

        //TODO: step 3-3: create a onClickListener, make an intent inside it and call this intent with extra to go to another activity
        View productViewHolder = holder.itemView;
        productViewHolder.setOnClickListener(view -> {
            Intent goToOrderFormIntent = new Intent(callingActivity, TaskDetailActivity.class);
            goToOrderFormIntent.putExtra(MainActivity.TASK_NAME_TAG,  taskName);
            goToOrderFormIntent.putExtra(MainActivity.TASK_BODY_TAG,  taskBody);
            goToOrderFormIntent.putExtra(MainActivity.TASK_STATE_TAG, taskState);
            callingActivity.startActivity(goToOrderFormIntent);
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    // TODO: step 1-8: make a ViewHolder class to hold a fragment
    public static class TaskListViewHolder extends RecyclerView.ViewHolder{
        public TaskListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}