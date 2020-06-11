package com.labs.robots.find.presenter;

import com.labs.robots.find.model.SignupModel;
import com.labs.robots.find.model.UserModel;
import com.labs.robots.find.model.api.ApiFactory;
import com.labs.robots.find.model.api.ApiInterface;
import com.labs.robots.find.view.SignupView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupPresenter {

    ApiInterface apiInterface = ApiFactory.getRetrofitInstance("http://79003208229.myjino.ru/").create(ApiInterface.class);
    SignupView signupView;

    private List<UserModel> dataModels = new ArrayList<>();

    public SignupPresenter(SignupView signupView) {
        this.signupView = signupView;
    }

    public void signup(String username, String email, String password, String spec) {

        Call<SignupModel> call = apiInterface.requestSignup(username,email, password, spec);

        call.enqueue(new Callback<SignupModel>() {
            @Override
            public void onResponse(Call<SignupModel> call, Response<SignupModel> response) {

                signupView.onSignupSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SignupModel> call, Throwable t) {
                signupView.onSignupFailed("error");
            }
        });

    }
}
