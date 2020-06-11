package com.labs.robots.find.presenter;

import com.labs.robots.find.model.TasksModel;
import com.labs.robots.find.model.UserModel;
import com.labs.robots.find.model.api.ApiFactory;
import com.labs.robots.find.model.api.ApiInterface;
import com.labs.robots.find.view.TaskAddView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskPresenter {

    ApiInterface apiInterface = ApiFactory.getRetrofitInstance("http://79003208229.myjino.ru/").create(ApiInterface.class);
    TaskAddView taskView;

    private List<UserModel> dataModels = new ArrayList<>();

    public TaskPresenter(TaskAddView taskView) {
        this.taskView = taskView;
    }

    public void addTask(int user_id,
                        String name_task,
                        String city_task,
                        String full_task,
                        String short_task,
                        String price_task,
                        String number_task) {

        Call<TasksModel> call = apiInterface.requestTask(user_id,name_task,
                city_task,
                full_task,
                short_task,
                price_task,
                number_task);

        call.enqueue(new Callback<TasksModel>() {
            @Override
            public void onResponse(Call<TasksModel> call, Response<TasksModel> response) {

                taskView.onTaskSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TasksModel> call, Throwable t) {
                taskView.onTaskFailed(t.getMessage().toString());
            }
        });

    }
}
