package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.example.myapplication.Adabter.TaskListRecyclerViewAdapter;


import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    List<Task> tasks = null;
    TaskListRecyclerViewAdapter adapter;
    public static final String TASK_NAME_TAG = "taskName";
    public static final String TASK_BODY_TAG = "taskBody";
    public static final String TASK_STATE_TAG = "taskState";
    public static final String DATABASE_NAME = "tasks";
    public static final String TAG = "TaskActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        tasks = new ArrayList<>();

        Amplify.API.query(
                ModelQuery.list(Task.class),
                success -> {
                    Log.i(TAG, "Updated Tasks Successfully!");
                    tasks.clear();
                    for(Task databaseTask : success.getData()){
                        tasks.add(databaseTask);
                    }
                    runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
                    });
                },


                failure -> Log.i(TAG, "failed with this response: ")
        );


        setUpTaskListRecyclerView();

        //step1: get a UI element By id
        Button addTaskButton = findViewById(R.id.addTaskBtn);
        Button allTasksButton = findViewById(R.id.allTasksBtn);
        Button settingButton = findViewById(R.id.btnSetting);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            //step 3: Attach a callback function with onClick() method
            @Override
            public void onClick(View view) {
                // step 4: Do stuff in the callback
                Intent goToAddTaskIntent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(goToAddTaskIntent);
            }
        });


        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAllTaskIntent = new Intent(MainActivity.this, AllTasksActivity.class);
                startActivity(goToAllTaskIntent);
            }
        });


        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingIntent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(goToSettingIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        String username = preferences.getString(SettingActivity.USERNAME_TAG, "No Username");

        ((TextView) findViewById(R.id.usernameTxt)).setText(getString(R.string.username_with_input, username));

    }


    private void setUpTaskListRecyclerView() {
        //TODO: step 1-1: Add a RecyclerView to the Activity in the WSYIWYG editor
        //TODO: step 1-2: grab the RecyclerView
        RecyclerView taskListRecyclerView = (RecyclerView) findViewById(R.id.taskListRecyclerView);

        //TODO: step 1-3: set the layout manager of the RecyclerView to a LinerLayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        taskListRecyclerView.setLayoutManager(layoutManager);


        //TODO: step 1-5: create and attach the RecyclerView.Adapter
        adapter = new TaskListRecyclerViewAdapter(tasks, this);
        taskListRecyclerView.setAdapter(adapter);

    }
}