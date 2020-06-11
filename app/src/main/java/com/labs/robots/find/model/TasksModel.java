package com.labs.robots.find.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TasksModel implements Serializable {
    @SerializedName("error")
    boolean error;
    @SerializedName("message")
    String message;
    @SerializedName("tasks")
    List<Task> tasks;

    public TasksModel(boolean error, String message, List<Task> tasks) {
        this.error = error;
        this.message = message;
        this.tasks = tasks;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}