package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;


public class SettingActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String USERNAME_TAG="username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Button saveButton= findViewById(R.id.saveUserSettingsButton);
        saveButton.setOnClickListener(view -> {
            SharedPreferences.Editor preferneceEditor= sharedPreferences.edit();
            EditText usernameEditText = findViewById(R.id.usernameEditText);
            String usernameString = usernameEditText.getText().toString();
            preferneceEditor.putString(USERNAME_TAG, usernameString);
            preferneceEditor.apply();
            Snackbar.make(findViewById(R.id.settingActivity), "Settings Saved", Snackbar.LENGTH_SHORT).show();
        });

    }
}