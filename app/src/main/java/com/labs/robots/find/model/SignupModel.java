package com.labs.robots.find.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignupModel implements Serializable {
    @SerializedName("error")
    boolean error;
    @SerializedName("message")
    String message;
    @SerializedName("username")
    String username;
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignupModel(boolean error, String message, String username, String email, String password) {
        this.error = error;
        this.message = message;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
