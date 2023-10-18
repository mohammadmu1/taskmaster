package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;

    public static final String TASK_NAME_TAG ="taskName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        setUpTaskTwoButton();
        setUpTaskOneButton();

        //step1: get a UI element By id
        Button addTaskButton = findViewById(R.id.addTaskbtn);
        Button allTasksButton = findViewById(R.id.allTasksbtn);
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

        ((TextView)findViewById(R.id.usernameTxt)).setText(getString(R.string.username_with_input, username));
    }

    private void setUpTaskOneButton() {
        Button taskOneButton = findViewById(R.id.btnTaskOne);
        taskOneButton.setOnClickListener(view -> {
            String taskOneName = taskOneButton.getText().toString();
            Intent goToDetailIntent = new Intent(MainActivity.this, TaskDetailActivity.class);
            goToDetailIntent.putExtra(MainActivity.TASK_NAME_TAG, taskOneName);
            startActivity(goToDetailIntent);
        });
    }

    private void setUpTaskTwoButton() {
        Button taskTwoButton = findViewById(R.id.btnTaskTow);
        taskTwoButton.setOnClickListener(view -> {
            String taskTwoName = taskTwoButton.getText().toString();
            Intent goToDetailIntent = new Intent(MainActivity.this, TaskDetailActivity.class);
            goToDetailIntent.putExtra(MainActivity.TASK_NAME_TAG, taskTwoName);
            startActivity(goToDetailIntent);
        });
    }

}