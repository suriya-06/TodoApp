package com.bawp.todoister.util;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bawp.todoister.data.TaskDao;
import com.bawp.todoister.model.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class},version = 1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class TodoRoomDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "Todo_Database";
    public static  TodoRoomDataBase INSTANCE;

    public static final int THREADS=4;

    public static final ExecutorService dataBaseService= Executors.newFixedThreadPool(THREADS);

    public static RoomDatabase.Callback sRoomDataBase=new Callback() {
        @Override
        public void onCreate(@NonNull  SupportSQLiteDatabase db) {
            super.onCreate(db);
            dataBaseService.execute(()->{
                TaskDao taskDao=INSTANCE.taskDao();
                taskDao.deleteAll();
            });
        }
    };
    public static TodoRoomDataBase getDataBase(Context context){
        if(INSTANCE==null){
            synchronized (context.getApplicationContext()){
                if (INSTANCE == null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),TodoRoomDataBase.class,
                            DATABASE_NAME).addCallback(sRoomDataBase).build();
                }
            }
        }
        return INSTANCE;
    }
    public abstract TaskDao taskDao();
}
