package com.labs.robots.find.view;

import com.labs.robots.find.model.UserModel;

public interface LoginView {
    void onLoginSuccess(UserModel userModel);
    void onLoginFailed(String error);
}
