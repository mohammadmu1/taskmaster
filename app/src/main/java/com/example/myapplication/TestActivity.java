package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Adabter.TaskListRecyclerViewAdapter;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.img).setOnClickListener(V->


                {
                    Intent goToSitting= new Intent(TestActivity.this,SettingActivity.class);
                    startActivity(goToSitting);

                }

        );

      setUpRecycleViewList();
    }

        //STEP1
        private void setUpRecycleViewList(){
            //STEP2
            RecyclerView taskListRecyclerView = findViewById(R.id.taskList);
            //STEP3
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
            taskListRecyclerView.setLayoutManager(layoutManager);
            //STEP5
            TaskListRecyclerViewAdapter adapter=new TaskListRecyclerViewAdapter();
            taskListRecyclerView.setAdapter(adapter);

        }


}