package com.labs.robots.find.model;

import com.google.gson.annotations.SerializedName;

public class Chat {
    @SerializedName("user_id")
    int id;
    @SerializedName("who_id")
    int userid;
    @SerializedName("text")
    String text;

    public Chat(int id, int userid, String text) {
        this.id = id;
        this.userid = userid;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}


