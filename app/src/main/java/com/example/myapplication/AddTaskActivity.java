package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class AddTaskActivity extends AppCompatActivity {

    int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button addTasksButton = (Button) findViewById(R.id.addTaskToTotalbtn);
        Button back = (Button) findViewById(R.id.backbtn2);
        Toast toast = Toast.makeText(this, "Done !!!", Toast.LENGTH_SHORT);

        TextView totalText = (TextView) findViewById(R.id.countertxt);


        addTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalText.setText(String.valueOf(count++));
                toast.show();
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