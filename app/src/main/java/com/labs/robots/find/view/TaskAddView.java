package com.labs.robots.find.view;

import com.labs.robots.find.model.TasksModel;

public interface TaskAddView {
    void onTaskSuccess(TasksModel taskModel);
    void onTaskFailed(String error);
}
