package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button allTaskButton = findViewById(R.id.allTaskButton);
        Button addTaskButton = findViewById(R.id.addTaskButton);
//        Toast toast =Toast.makeText(this,"Hello ya 7amar",Toast.LENGTH_SHORT);
        allTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAllTaskActivity = new Intent(MainActivity.this , AllTaskActivity.class);
                startActivity(goToAllTaskActivity);

            }
        });

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToAddTaskActivity = new Intent(MainActivity.this , AddTaskActivity.class);
                startActivity(goToAddTaskActivity);
            }
        });
    }


}