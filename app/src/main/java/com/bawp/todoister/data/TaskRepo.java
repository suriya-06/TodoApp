package com.bawp.todoister.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.TodoRoomDataBase;

import java.util.List;

public  class TaskRepo{
    private TaskDao taskDao;
    private LiveData<List<Task>> allTask;

    public TaskRepo(Application application){
        TodoRoomDataBase dataBase=TodoRoomDataBase.getDataBase(application.getApplicationContext());
        taskDao=dataBase.taskDao();
        allTask=taskDao.getAll();
    }

    public void  insert(Task task){
        TodoRoomDataBase.dataBaseService.execute(()->{
            taskDao.insert(task);
        });
    }

    public void update(Task task){
        TodoRoomDataBase.dataBaseService.execute(()->{
            taskDao.update(task);
        });
    }

    public LiveData<List<Task>> getAllTask(){
        return allTask;
    }

    public LiveData<List<Task>> get(long id){
        return taskDao.get(id);
    }

    public void deleteAll(){
        TodoRoomDataBase.dataBaseService.execute(()->{
            taskDao.deleteAll();
        });
    }

    public void delete(long id){
       TodoRoomDataBase.dataBaseService.execute(()->{
           taskDao.delete(id);
       });
    }
}
