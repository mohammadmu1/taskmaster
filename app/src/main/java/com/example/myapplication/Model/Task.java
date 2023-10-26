package com.example.myapplication.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.myapplication.Enum.State;
//TODO STEP 2-1 CREATE CLASS FOR DATA
@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String title;
    private String body;
    private State state;

    public Task(String title, String body, State state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}