package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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

        );}


}