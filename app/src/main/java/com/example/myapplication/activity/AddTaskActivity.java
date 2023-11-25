package com.example.myapplication.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskStatusEnum;
import com.amplifyframework.datastore.generated.model.Team;
import com.example.myapplication.activity.Enum.State;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddTaskActivity extends AppCompatActivity {

    public static final String TAG = "AddTaskActivity";

    Spinner teamSpinner= null;
    Spinner taskCategorySpinner= null;
    CompletableFuture<List<Team>> teamFuture = new CompletableFuture<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        teamFuture = new CompletableFuture<>();
        setUpSpinners();
        setUpSaveButton();

        Spinner taskCategorySpinner = (Spinner) findViewById(R.id.addTaskCategorySpinner);
        taskCategorySpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                TaskStatusEnum.values()));



        Button back = (Button) findViewById(R.id.backbtn2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMainIntent = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(goToMainIntent);
            }
        });
    }

    private void setUpSpinners(){

        teamSpinner = (Spinner) findViewById(R.id.addTaskTeamNameSpinner);


        Amplify.API.query(
                ModelQuery.list(Team.class),
                success ->
                {
                    Log.i(TAG, "Read TeamName Successfully");
                    ArrayList<String> teamNames = new ArrayList<>();
                    ArrayList<Team> teams = new ArrayList<>();
                    for(Team team: success.getData()){
                        teams.add(team);
                        teamNames.add(team.getName());
                    }
                    teamFuture.complete(teams);

                    runOnUiThread(() ->
                    {
                        teamSpinner.setAdapter(new ArrayAdapter<>(
                                this,
                                (android.R.layout.simple_spinner_item),
                                teamNames
                        ));
                    });
                },
                failure-> {
                    teamFuture.complete(null);
                    Log.i(TAG, "Did not read TeamName successfully");
                }
        );
        taskCategorySpinner = (Spinner) findViewById(R.id.addTaskCategorySpinner);
        taskCategorySpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                State.values()
        ));

    }


    private void setUpSaveButton(){

        Button addTasksButton = (Button) findViewById(R.id.addTaskToTotalbtn);
        addTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = ((EditText)findViewById(R.id.taskTitleEdtTxt)).getText().toString();
                String body = ((EditText)findViewById(R.id.taskBodyEdtTxt)).getText().toString();
                String currentDateString = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
                String selectedTeamNameString = teamSpinner.getSelectedItem().toString();

                List<Team> teams=null;
                try {
                    teams=teamFuture.get();
                }catch (InterruptedException ie){
                    Log.e(TAG, " InterruptedException while getting TeamName");
                }catch (ExecutionException ee){
                    Log.e(TAG," ExecutionException while getting TeamName");
                }

                Team selectedTeam = teams.stream().filter(c -> c.getName().equals(selectedTeamNameString)).findAny().orElseThrow(RuntimeException::new);



                Task newTask = Task.builder()
                        .title(title)
                        .description(body)
                        .taskStatusEnum((TaskStatusEnum) taskCategorySpinner.getSelectedItem())
                        .teamName(selectedTeam)
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(newTask),
                        successResponse -> Log.i(TAG, "AddTaskActivity.onCreate(): made a Task successfully"),//success response
                        failureResponse -> Log.e(TAG, "AddTaskActivity.onCreate(): failed with this response" + failureResponse)// in case we have a failed response
                );

                Snackbar.make(findViewById(R.id.addTaskActivity), "Task saved!", Snackbar.LENGTH_SHORT).show();

            }
        });
    }




}