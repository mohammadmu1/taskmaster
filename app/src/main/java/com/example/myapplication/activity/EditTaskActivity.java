package com.example.myapplication.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskStatusEnum;
import com.amplifyframework.datastore.generated.model.Team;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EditTaskActivity extends AppCompatActivity {

    public static final String TAG= "editTaskActivity";
    private CompletableFuture<Task> taskCompletableFuture = null;
    private CompletableFuture<List<Team>> teamFuture = null;
    private Task taskToEdit= null;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Spinner taskCategorySpinner = null;
    private Spinner teamNameSpinner = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        taskCompletableFuture = new CompletableFuture<>();
        teamFuture = new CompletableFuture<>();

        setUpEditableUIElement();
        setUpSaveButton();
        setUpDeleteButton();
    }

    private void setUpEditableUIElement() {
        Intent callingIntent = getIntent();
        String taskId = null;

        if(callingIntent != null){
            taskId = callingIntent.getStringExtra(MainActivity.MAIN_ID_TAG);
        }

        String taskId2 = taskId;

        Amplify.API.query(
                ModelQuery.list(Task.class),
                success ->
                {
                    Log.i(TAG,"Read Tasks Successfully");

                    for (Task databaseTask: success.getData()){
                        if(databaseTask.getId().equals(taskId2)){
                            taskCompletableFuture.complete(databaseTask);
                        }
                    }

                    runOnUiThread(() ->
                    {
                        //Update UI element
                    });
                },
                failure -> Log.i(TAG, "Did not read Tasks successfully")
        );

        try {
            taskToEdit = taskCompletableFuture.get();
        }catch (InterruptedException ie){
            Log.e(TAG, "InterruptedException while getting task");
            Thread.currentThread().interrupt();
        }catch (ExecutionException ee){
            Log.e(TAG, "ExecutionException while getting task");
        }

        titleEditText = ((EditText) findViewById(R.id.editTextNewTitle));
        titleEditText.setText(taskToEdit.getTitle());
        descriptionEditText = ((EditText) findViewById(R.id.editTextNewDescription));
        descriptionEditText.setText(taskToEdit.getDescription());
        setUpSpinners();
    }

    private void setUpSpinners()
    {
        teamNameSpinner = (Spinner) findViewById(R.id.spinnerNewTeamName);

        Amplify.API.query(
                ModelQuery.list(Team.class),
                success ->
                {
                    Log.i(TAG, "Read Team Name successfully!");
                    ArrayList<String> TeamNames = new ArrayList<>();
                    ArrayList<Team> teams = new ArrayList<>();
                    for (Team team : success.getData())
                    {
                        teams.add(team);
                        TeamNames.add(team.getName());
                    }
                    teamFuture.complete(teams);

                    runOnUiThread(() ->
                    {
                        teamNameSpinner.setAdapter(new ArrayAdapter<>(
                                this,
                                android.R.layout.simple_spinner_item,
                                TeamNames));
                        teamNameSpinner.setSelection(getSpinnerIndex(teamNameSpinner, taskToEdit.getTeamName().getName()));
                    });
                },
                failure -> {
                    teamFuture.complete(null);
                    Log.i(TAG, "Did not read Team Name successfully!");
                }
        );

        taskCategorySpinner = (Spinner) findViewById(R.id.spinnerNewState);
        taskCategorySpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                TaskStatusEnum.values()));
        taskCategorySpinner.setSelection(getSpinnerIndex(taskCategorySpinner, taskToEdit.getTaskStatusEnum().toString()));
    }

    private int getSpinnerIndex(Spinner spinner, String stringValueToCheck){
        for (int i = 0;i < spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(stringValueToCheck)){
                return i;
            }
        }
        return 0;
    }


    private void setUpSaveButton()
    {
        Button saveButton = (Button)findViewById(R.id.editTaskButton);
        saveButton.setOnClickListener(v ->
        {
            List<Team> teams = null;
            String teamToSaveString = teamNameSpinner.getSelectedItem().toString();
            try
            {
                teams = teamFuture.get();
            }
            catch (InterruptedException ie)
            {
                Log.e(TAG, "InterruptedException while getting product");
                Thread.currentThread().interrupt();
            }
            catch (ExecutionException ee)
            {
                Log.e(TAG, "ExecutionException while getting product");
            }
            Team teamToSave = teams.stream().filter(c -> c.getName().equals(teamToSaveString)).findAny().orElseThrow(RuntimeException::new);
            Task taskToSave = Task.builder()
                    .title(titleEditText.getText().toString())
                    .id(taskToEdit.getId())
                    .description(descriptionEditText.getText().toString())
                    .teamName(teamToSave)
                    .taskStatusEnum(TaskCategoryFromString(taskCategorySpinner.getSelectedItem().toString()))
                    .build();

            Amplify.API.mutate(
                    ModelMutation.update(taskToSave),  // making a GraphQL request to the cloud
                    successResponse ->
                    {
                        Log.i(TAG, "EditTaskActivity.onCreate(): edited a Task successfully");
                        // TODO: Display a Snack bar
                        Snackbar.make(findViewById(R.id.editTaskActivity), "Task saved!", Snackbar.LENGTH_SHORT).show();
                    },  // success callback
                    failureResponse -> Log.i(TAG, "EditTaskActivity.onCreate(): failed with this response: " + failureResponse)  // failure callback
            );
        });
    }

    public static TaskStatusEnum TaskCategoryFromString(String inputTaskStateEnumText){
        for (TaskStatusEnum taskStatusEnum : TaskStatusEnum.values()){
            if(taskStatusEnum.toString().equals(inputTaskStateEnumText)){
                return taskStatusEnum;
            }
        }
        return null;
    }

    private void setUpDeleteButton(){
        Button deleteButton = (Button) findViewById(R.id.deleteTaskButton);
        deleteButton.setOnClickListener(v ->{
            Amplify.API.mutate(
                    ModelMutation.delete(taskToEdit),
                    successResponse ->
                    {
                        Log.i(TAG, "EditTaskActivity.onCreate(): deleted a Task successfully");
                        Intent goToMainActivity = new Intent(EditTaskActivity.this, MainActivity.class);
                        startActivity(goToMainActivity);
                    },
                    failureResponse -> Log.i(TAG,"EditTaskActivity.onCreate(): failed with this response: "+ failureResponse)
            );
        });
    }



}