package com.labs.robots.find.model;

import com.google.gson.annotations.SerializedName;

public class Feed {

    @SerializedName("id")
    String id;
    @SerializedName("userid")
    String userid;
    @SerializedName("whyid")
    String whyid;
    @SerializedName("state")
    String state;
    @SerializedName("taskid")
    String taskid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getWhyid() {
        return whyid;
    }

    public void setWhyid(String whyid) {
        this.whyid = whyid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public Feed(String id, String userid, String whyid, String state, String taskid) {
        this.id = id;
        this.userid = userid;
        this.whyid = whyid;
        this.state = state;
        this.taskid = taskid;
    }
}
