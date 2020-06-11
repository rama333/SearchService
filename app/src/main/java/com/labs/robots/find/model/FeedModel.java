package com.labs.robots.find.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FeedModel implements Serializable {
    @SerializedName("error")
    boolean error;
    @SerializedName("message")
    String message;
    @SerializedName("feed")
    List<Feed> feed;

    public FeedModel(boolean error, String message, List<Feed> feed) {
        this.error = error;
        this.message = message;
        this.feed = feed;
    }

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

    public List<Feed> getFeed() {
        return feed;
    }

    public void setFeed(List<Feed> feed) {
        this.feed = feed;
    }
}
