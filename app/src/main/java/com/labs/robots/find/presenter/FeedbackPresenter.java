package com.labs.robots.find.presenter;



import com.labs.robots.find.model.UserModel;
import com.labs.robots.find.model.api.ApiFactory;
import com.labs.robots.find.model.api.ApiInterface;
import com.labs.robots.find.view.LoginView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackPresenter {
    ApiInterface apiInterface = ApiFactory.getRetrofitInstance("http://79003208229.myjino.ru/").create(ApiInterface.class);
    LoginView loginView;

    private List<UserModel> dataModels = new ArrayList<>();

    public FeedbackPresenter(LoginView coinView) {
        this.loginView = coinView;
    }

    public void feedBack(String user_id, String who_user,String task_id) {

        Call<UserModel> call = apiInterface.requestfeedBack(user_id, who_user, task_id);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                loginView.onLoginSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                loginView.onLoginFailed("error");
            }
        });

    }
}
