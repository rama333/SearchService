package com.labs.robots.find.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel implements Serializable {
    @SerializedName("error")
    boolean error;
    @SerializedName("message")
    String message;
    @SerializedName("user")
    User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserModel(boolean error, String message, User user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }


}
