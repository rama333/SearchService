package com.labs.robots.find.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

    public class Task implements Serializable {


        @SerializedName("id")
        String id;
        @SerializedName("user_id")
        String user_id;
        @SerializedName("name_task")
        String name_task;
        @SerializedName("city_task")
        String city_task;
        @SerializedName("full_task")
        String full_task;
        @SerializedName("short_task")
        String short_task;
        @SerializedName("price_task")
        String price_task;
        @SerializedName("number_task")
        String number_task;

        public Task(String id, String user_id, String name_task, String city_task, String full_task, String short_task, String price_task, String number_task) {
            this.id = id;
            this.user_id = user_id;
            this.name_task = name_task;
            this.city_task = city_task;
            this.full_task = full_task;
            this.short_task = short_task;
            this.price_task = price_task;
            this.number_task = number_task;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getName_task() {
            return name_task;
        }

        public void setName_task(String name_task) {
            this.name_task = name_task;
        }

        public String getCity_task() {
            return city_task;
        }

        public void setCity_task(String city_task) {
            this.city_task = city_task;
        }

        public String getFull_task() {
            return full_task;
        }

        public void setFull_task(String full_task) {
            this.full_task = full_task;
        }

        public String getShort_task() {
            return short_task;
        }

        public void setShort_task(String short_task) {
            this.short_task = short_task;
        }

        public String getPrice_task() {
            return price_task;
        }

        public void setPrice_task(String price_task) {
            this.price_task = price_task;
        }

        public String getNumber_task() {
            return number_task;
        }

        public void setNumber_task(String number_task) {
            this.number_task = number_task;
        }
    }

