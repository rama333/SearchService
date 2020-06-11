package com.labs.robots.find.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatModel {
    @SerializedName("error")
    boolean error;
    @SerializedName("message")
    String message;
    @SerializedName("chat")
    List<Chat> chat;

    public ChatModel(boolean error, String message, List<Chat> chat) {
        this.error = error;
        this.message = message;
        this.chat = chat;
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

    public List<Chat> getChat() {
        return chat;
    }

    public void setChat(List<Chat> chat) {
        this.chat = chat;
    }
}
