package com.example.myapplication.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.DAO.TaskDAO;
import com.example.myapplication.Model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TasksDataBase extends RoomDatabase {
    public abstract TaskDAO taskDao();

}