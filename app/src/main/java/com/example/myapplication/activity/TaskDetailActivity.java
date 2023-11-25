package com.example.myapplication.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;


public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent callingIntent = getIntent();
        String taskNameString = null;
        String taskBodyString = null;
        String taskStateEnum = null;

        if(callingIntent != null){
            taskNameString = callingIntent.getStringExtra(MainActivity.TASK_NAME_TAG);
            taskBodyString = callingIntent.getStringExtra(MainActivity.TASK_BODY_TAG);
            taskStateEnum = callingIntent.getStringExtra(MainActivity.TASK_STATE_TAG);

            TextView taskNameTextView = (TextView) findViewById(R.id.taskNameTxt);
            TextView taskBodyTextView = (TextView) findViewById(R.id.taskBodyTxt);
            TextView taskStateTextView = (TextView) findViewById(R.id.taskStateTxt);

            if (taskNameString != null){
                taskNameTextView.setText(taskNameString);
            }else {
                taskNameTextView.setText("Task Unknown");
            }

            if (taskBodyString != null){
                taskBodyTextView.setText(taskBodyString);
            }else {
                taskBodyTextView.setText("Body Unknown");
            }

            if (taskStateEnum != null){
//
                taskStateTextView.setText(taskStateEnum.toString());

            }else {
                taskStateTextView.setText("State Unknown");
            }

        }
    }
}