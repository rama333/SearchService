package com.labs.robots.find.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("username")
    String username;
    @SerializedName("id")
    int id;
    @SerializedName("email")
    String email;
    @SerializedName("spec")
    String spec;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public User(String username, int id, String email, String spec) {
        this.username = username;
        this.id = id;
        this.email = email;
        this.spec = spec;
    }
}