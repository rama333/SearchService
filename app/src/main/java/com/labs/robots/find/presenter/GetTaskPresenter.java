package com.labs.robots.find.presenter;

import com.labs.robots.find.model.TasksModel;
import com.labs.robots.find.model.UserModel;
import com.labs.robots.find.model.api.ApiFactory;
import com.labs.robots.find.model.api.ApiInterface;
import com.labs.robots.find.view.GetTasksView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTaskPresenter {
    ApiInterface apiInterface = ApiFactory.getRetrofitInstance("http://79003208229.myjino.ru/").create(ApiInterface.class);
    GetTasksView taskView;

    private List<UserModel> dataModels = new ArrayList<>();

    public GetTaskPresenter(GetTasksView taskView) {
        this.taskView = taskView;
    }

    public void getTask(int user_id) {

        Call<TasksModel> call = apiInterface.getTasks(user_id);

        call.enqueue(new Callback<TasksModel>() {
            @Override
            public void onResponse(Call<TasksModel> call, Response<TasksModel> response) {

                taskView.onTaskSuccess(response.body().getTasks());
            }

            @Override
            public void onFailure(Call<TasksModel> call, Throwable t) {
                taskView.onTaskFailed(t.getMessage().toString());
            }
        });

    }
}
