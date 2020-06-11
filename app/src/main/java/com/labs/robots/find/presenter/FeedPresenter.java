package com.labs.robots.find.presenter;

import com.labs.robots.find.model.FeedModel;
import com.labs.robots.find.model.UserModel;
import com.labs.robots.find.model.api.ApiFactory;
import com.labs.robots.find.model.api.ApiInterface;
import com.labs.robots.find.view.FeedView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedPresenter {
    ApiInterface apiInterface = ApiFactory.getRetrofitInstance("http://79003208229.myjino.ru/").create(ApiInterface.class);
    FeedView taskView;

    private List<UserModel> dataModels = new ArrayList<>();

    public FeedPresenter(FeedView taskView) {
        this.taskView = taskView;
    }

    public void getFeed(int user_id, int task_id) {

        Call<FeedModel> call = apiInterface.getFeed(user_id, task_id);

        call.enqueue(new Callback<FeedModel>() {
            @Override
            public void onResponse(Call<FeedModel> call, Response<FeedModel> response) {

                taskView.onFeedSuccess(response.body().getFeed());
            }

            @Override
            public void onFailure(Call<FeedModel> call, Throwable t) {
                taskView.onFeedFailed(t.getMessage().toString());
            }
        });

    }
}
