package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;



public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent callingIntent = getIntent();
        String taskNameString = null;

        if(callingIntent != null){
            taskNameString = callingIntent.getStringExtra(MainActivity.TASK_NAME_TAG);

            TextView taskTextView = findViewById(R.id.taskDetailTxt);

            if (taskNameString != null){
                taskTextView.setText(taskNameString);
            }else {
                taskTextView.setText("Task Unknown");
            }

        }
    }
}