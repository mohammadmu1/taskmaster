package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String USERNAME_TAG="username";
    public static final String USER_TEAM_TAG="userTeam";
    public static final String TAG="setting";
    Spinner teamSpinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        setUpTeamSpinner();

        Button saveButton= findViewById(R.id.saveUserSettingsButton);
        saveButton.setOnClickListener(view -> {
            SharedPreferences.Editor preferneceEditor= sharedPreferences.edit();
            EditText usernameEditText = findViewById(R.id.usernameEditText);
            String usernameString = usernameEditText.getText().toString();

            String userTeamNameString = teamSpinner.getSelectedItem().toString();
            preferneceEditor.putString(USER_TEAM_TAG, userTeamNameString);


            preferneceEditor.putString(USERNAME_TAG, usernameString);
            preferneceEditor.apply();
            Snackbar.make(findViewById(R.id.settingActivity), "Settings Saved", Snackbar.LENGTH_SHORT).show();
        });

    }


    private void setUpTeamSpinner() {

        teamSpinner = (Spinner) findViewById(R.id.teamNameSpinner);

        Amplify.API.query(
                ModelQuery.list(Team.class),
                success -> {
                    Log.i(TAG, "Read Teams Successfully!");
                    ArrayList<String> teamNames = new ArrayList<>();
                    ArrayList<Team> teams = new ArrayList<>();
                    for(Team team : success.getData()){
                        teamNames.add(team.getName());
                        teams.add(team);
                    }
                    runOnUiThread(() -> {
                        teamSpinner.setAdapter(new ArrayAdapter<>(
                                this,
                                android.R.layout.preference_category,
                                teamNames
                        ));
                    });
                },
                failure -> {
                    Log.i(TAG, "Failed to add team names!");
                }
        );

    }



}