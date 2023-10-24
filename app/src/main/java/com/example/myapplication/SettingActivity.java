package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class SettingActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String USERNAME_TAG = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);



        findViewById(R.id.saveUserSettingsButton).setOnClickListener(view -> {
            SharedPreferences.Editor preferenceEditor = sharedPreferences.edit();
            EditText usernameEditText = findViewById(R.id.usernameEditText);
            String usernameString = usernameEditText.getText().toString();
            preferenceEditor.putString(USERNAME_TAG, usernameString);
            preferenceEditor.apply();


//            Toast.makeText(this, "UserName Saved", Toast.LENGTH_SHORT).show();


            Snackbar.make(findViewById(R.id.settingActivity), "UserName Saved", Snackbar.LENGTH_SHORT).show();
        });
    }
}