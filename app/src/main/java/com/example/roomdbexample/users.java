package com.example.roomdbexample;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class users {
    @PrimaryKey(autoGenerate = true)
    public int taskid;

    @ColumnInfo(name = "taskTitle")
    public String TaskTitle;

    @ColumnInfo(name="taskDiscription")
    public String TaskDiscription;

    @ColumnInfo(name="date")
    public String date;

    public users() {
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public String getTaskTitle() {
        return TaskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        TaskTitle = taskTitle;
    }

    public String getTaskDiscription() {
        return TaskDiscription;
    }

    public void setTaskDiscription(String taskDiscription) {
        TaskDiscription = taskDiscription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
