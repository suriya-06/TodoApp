package com.bawp.todoister.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bawp.todoister.data.TaskRepo;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    public static TaskRepo repo;
    public LiveData<List<Task>> allTask;
    public TaskViewModel(@NonNull  Application application) {
        super(application);
        repo=new TaskRepo(application);
        allTask=repo.getAllTask();
    }
    public LiveData<List<Task>> getAllTask(){
        return allTask;
    }

    public LiveData<List<Task>> get(long id){
        return repo.get(id);
    }
    public static void insert(Task task){
        repo.insert(task);
    }

    public static void update(Task task){
        repo.update(task);
    }

    public static void deleteAll(){
        repo.deleteAll();
    }

    public static void delete(long id){
        repo.delete(id);
    }

}
