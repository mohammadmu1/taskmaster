package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Adabter.TaskListRecyclerViewAdapter;
import com.example.myapplication.Enum.State;
import com.example.myapplication.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;

    public static final String TASK_NAME_TAG ="taskName";
    public static final String TASK_BODY_TAG ="taskBody";
    public static final String TASK_STATE_TAG ="taskState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        setUpTaskButtons();

        setuptaskbtn(findViewById(R.id.addTaskbtn), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddTaskIntent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(goToAddTaskIntent);
            }
        });

        setuptaskbtn(findViewById(R.id.allTasksbtn), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAllTaskIntent = new Intent(MainActivity.this, AllTasksActivity.class);
                startActivity(goToAllTaskIntent);
            }
        });

        setuptaskbtn(findViewById(R.id.btnSetting), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingIntent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(goToSettingIntent);
            }
        });
        setUpTaskListRecyclerView();
    }

    private void setUpTaskListRecyclerView(){
        //TODO : step 1-1 Add a RV to Activity
        //TODO : step 1-2 grab the  RV
        RecyclerView TaskListRecyclerView = findViewById(R.id.taskList);
        //TODO : step 1-3 set Layout manager of RV TO LinearLayoutManager
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);

        TaskListRecyclerView.setLayoutManager(layoutManager);




        //TODO : step 2-2 make some data items
        List<Task> tasks = new ArrayList<>();

        tasks.add(new Task("Task 1", "Description of Task 1", State.NEW));
        tasks.add(new Task("Task 2", "Description of Task 2", State.ASSIGNED));
        tasks.add(new Task("Task 3", "Description of Task 3", State.IN_PROGRESS));
        tasks.add(new Task("Task 4", "Description of Task 4", State.COMPLETE));
        tasks.add(new Task("Task 5", "Description of Task 5", State.IN_PROGRESS));


        //TODO : step 1-5 Create RV Adapter
        //TODO : step 2-3 Hand in data items
        //TODO : step 3-2 Hand in Activity Context
        TaskListRecyclerViewAdapter adapter = new TaskListRecyclerViewAdapter(tasks , this);
        TaskListRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        String username = preferences.getString(SettingActivity.USERNAME_TAG, "No Username");

        ((TextView) findViewById(R.id.usernameTxt)).setText(getString(R.string.username_with_input, username));
    }

    private void setUpTaskButtons() {
//        setUpTaskButton(findViewById(R.id.btnTaskOne), "Task One Name");
//        setUpTaskButton(findViewById(R.id.btnTaskTow), "Task Two Name");
    }

    private void setuptaskbtn(Button button, View.OnClickListener clickListener) {
        button.setOnClickListener(clickListener);
    }

//    private void setUpTaskButton(Button button, String taskName) {
//        button.setOnClickListener(view -> {
//            Intent goToDetailIntent = new Intent(MainActivity.this, TaskDetailActivity.class);
//            goToDetailIntent.putExtra(MainActivity.TASK_NAME_TAG, taskName);
//            startActivity(goToDetailIntent);
//        });
//    }

    
}
