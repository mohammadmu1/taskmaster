package com.example.myapplication.Adabter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Task;
import com.example.myapplication.R;
import com.example.myapplication.TaskDetailActivity;

import java.util.List;

//TODO : step 1-4 Make a Class to Manage RV
//TODO : step 3-1 clean up the RV.Adapter to reference to actually use TaskListRV
public class TaskListRecyclerViewAdapter extends RecyclerView.Adapter<TaskListRecyclerViewAdapter.TaskListViewHolder> {

    //TODO : step 2-3 Hand in data items
    List<Task> tasks;
    //TODO : step 3-2 Hand in Activity Context
    Context callingActivity;
    public TaskListRecyclerViewAdapter(List<Task> tasks , Context callingActivity) {
        this.callingActivity=callingActivity;
        this.tasks = tasks;
    }

    //TODO : step 1-7 inflate fragment
    @NonNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View TaskFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task_list,parent,false);
        //TODO : step 1-9 Attach fragment to ViewHolder
        return new TaskListViewHolder(TaskFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position) {

        //TODO : step 2-4 Bind data item to frgment inside view holder
        TextView taskFragmentTextView = holder.itemView.findViewById(R.id.taskFragmentTextView);
        String taskName = tasks.get(position).getTitle();
        String taskBody = tasks.get(position).getBody();
        String taskState = String.valueOf(tasks.get(position).getState());

        taskFragmentTextView.setText((position+1) + " " + taskName);

        //TODO : step 3-3 create a onClickListener make intent to call it to go to another activity
        View listViewHolder = holder.itemView;
        listViewHolder.setOnClickListener(view -> {
            Intent  goToTaskIntent = new Intent(callingActivity , TaskDetailActivity.class);
            goToTaskIntent.putExtra(MainActivity.TASK_NAME_TAG,  taskName);
            goToTaskIntent.putExtra(MainActivity.TASK_BODY_TAG,  taskBody);
            goToTaskIntent.putExtra(MainActivity.TASK_STATE_TAG, taskState);
            callingActivity.startActivity(goToTaskIntent);
        });
    }

    @Override
    public int getItemCount() {
        //TODO : step 2-4 Make size of the list dynamic
        return tasks.size();
    }
    //TODO : step 1-8 make  A view Holder Class hold the fragment
    public static class TaskListViewHolder extends RecyclerView.ViewHolder{
        public TaskListViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
