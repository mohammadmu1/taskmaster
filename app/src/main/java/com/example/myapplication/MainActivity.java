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
    public static final String TASK_NAME_TAG = "taskName";

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
                Intent goToSettingIntent = new Intent(MainActivity.this, TestActivity.class);
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

    private void setUpTaskButtons() {
        setUpTaskButton(findViewById(R.id.btnTaskOne), "Task One Name");
        setUpTaskButton(findViewById(R.id.btnTaskTow), "Task Two Name");
    }

    private void setuptaskbtn(Button button, View.OnClickListener clickListener) {
        button.setOnClickListener(clickListener);
    }

    private void setUpTaskButton(Button button, String taskName) {
        button.setOnClickListener(view -> {
            Intent goToDetailIntent = new Intent(MainActivity.this, TaskDetailActivity.class);
            goToDetailIntent.putExtra(MainActivity.TASK_NAME_TAG, taskName);
            startActivity(goToDetailIntent);
        });
    }

    
}
