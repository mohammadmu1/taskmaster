package com.example.myapplication.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.Model.Task;

import java.util.List;

@Dao
public interface TaskDAO {
    @Insert
    public void insertATask(Task task);

    @Query("select * from task")
    public List<Task> findAll();

    @Query("select * from Task ORDER BY title ASC")
    public List<Task> findAllSortedByName();

    @Query("select * from Task where id = :id")//(:id) : bind parameter take value from the function below (Long id)
    Task findByAnId(long id);
}
