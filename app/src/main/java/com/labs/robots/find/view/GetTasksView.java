package com.labs.robots.find.view;

import com.labs.robots.find.model.Task;

import java.util.List;

public interface GetTasksView {

    void onTaskSuccess(List<Task> userModel);
    void onTaskFailed(String error);
}
