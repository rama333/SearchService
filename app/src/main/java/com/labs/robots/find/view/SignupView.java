package com.labs.robots.find.view;

import com.labs.robots.find.model.SignupModel;

public interface SignupView {
    void onSignupSuccess(SignupModel signupModel);
    void onSignupFailed(String error);
}
