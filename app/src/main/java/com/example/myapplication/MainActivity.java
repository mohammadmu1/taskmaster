package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Adabter.TaskListRecyclerViewAdapter;
import com.example.myapplication.DataBase.TasksDataBase;
import com.example.myapplication.Enum.State;
import com.example.myapplication.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    List<Task> tasks =null;
    public static final String TASK_NAME_TAG ="taskName";
    public static final String TASK_BODY_TAG ="taskBody";
    public static final String TASK_STATE_TAG ="taskState";
    public static  final String DATABASE_NAME = "task";

    TaskListRecyclerViewAdapter adapter;
    TasksDataBase tasksDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasksDataBase= Room.databaseBuilder(
                        getApplicationContext(),
                        TasksDataBase.class,
                        DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();


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

        tasks= tasksDataBase.taskDao().findAll();
//

        //TODO : step 1-5 Create RV Adapter
        //TODO : step 2-3 Hand in data items
        //TODO : step 3-2 Hand in Activity Context
         adapter = new TaskListRecyclerViewAdapter(tasks , this);
        TaskListRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        String username = preferences.getString(SettingActivity.USERNAME_TAG, "No Username");

        ((TextView) findViewById(R.id.usernameTxt)).setText(getString(R.string.username_with_input, username));

        tasks.clear();
        tasks.addAll(tasksDataBase.taskDao().findAll());
        adapter.notifyDataSetChanged();
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