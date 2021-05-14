package com.bawp.todoister.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bawp.todoister.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getAll();

    @Query("SELECT * FROM task_table WHERE task_table.task_id == :id")
    LiveData<List<Task>> get(long id);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Query("DELETE from task_table WHERE task_id == :id")
    void delete(long id);
}
