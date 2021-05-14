package com.bawp.todoister.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
public class Task {
@ColumnInfo(name = "task_id")
@PrimaryKey(autoGenerate = true)
public int id;
public String task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getOnCreateDate() {
        return onCreateDate;
    }

    public void setOnCreateDate(Date onCreateDate) {
        this.onCreateDate = onCreateDate;
    }

    public Boolean getWorkDone() {
        return workDone;
    }

    public void setWorkDone(Boolean workDone) {
        this.workDone = workDone;
    }

    public Task(String task, Priority priority, Date dueDate, Date onCreateDate, Boolean workDone) {
        this.task = task;
        this.priority = priority;
        this.dueDate = dueDate;
        this.onCreateDate = onCreateDate;
        this.workDone = workDone;
    }

    public Priority priority;
@ColumnInfo(name = "due_Date")
public Date dueDate;
@ColumnInfo(name = "Created")
public Date onCreateDate;
@ColumnInfo(name = "Work_done")
public Boolean workDone;
}
