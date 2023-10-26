package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.DataBase.TasksDataBase;
import com.example.myapplication.Enum.State;
import com.example.myapplication.Model.Task;
import com.google.android.material.snackbar.Snackbar;


public class AddTaskActivity extends AppCompatActivity {

    int count = 1;
    TasksDataBase tasksDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_task);

        tasksDataBase = Room.databaseBuilder(
                        getApplicationContext(),
                        TasksDataBase.class,
                        "task")
                .allowMainThreadQueries()
                .build();

        Spinner taskCategorySpinner = (Spinner) findViewById(R.id.taskCategorySpinner);
        taskCategorySpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                State.values()));

        Button addTasksButton = (Button) findViewById(R.id.addTaskbtn);
        Button back = (Button) findViewById(R.id.backbtn2);

        TextView totalText = (TextView) findViewById(R.id.countertxt);
        addTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalText.setText(String.valueOf(count++));


                Task newTasks = new Task(
                        ((EditText) findViewById(R.id.taskTitle)).getText().toString(),
                        ((EditText) findViewById(R.id.taskDescription)).getText().toString(),
                        State.fromString(taskCategorySpinner.getSelectedItem().toString())
                );
                tasksDataBase.taskDao().insertATask(newTasks);

                Snackbar.make(view, "Task added", Snackbar.LENGTH_SHORT)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMainIntent = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(goToMainIntent);
            }
        });
    }





    }
