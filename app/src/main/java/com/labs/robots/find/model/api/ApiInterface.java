package com.labs.robots.find.model.api;

import com.labs.robots.find.model.ChatModel;
import com.labs.robots.find.model.FeedModel;
import com.labs.robots.find.model.SignupModel;
import com.labs.robots.find.model.TasksModel;
import com.labs.robots.find.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Ramil on 03.06.2016.
 */
public interface ApiInterface {
    @FormUrlEncoded
    @POST("Api.php?action=login")
    Call<UserModel> requestLogin(@Field("email") String user,
                                 @Field("password") String password
                                            );
    @FormUrlEncoded
    @POST("Api.php?action=signup")
    Call<SignupModel> requestSignup(@Field("username") String user,
                                    @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("spec") String spec

    );

    @FormUrlEncoded
    @POST("Api.php?action=feedback")
    Call<UserModel> requestfeedBack(@Field("user_id") String user_id,
                                    @Field("who_user") String who_user,
                                    @Field(" task_id") String  task_id

    );

    @FormUrlEncoded
    @POST("Api.php?action=settask")
    Call<TasksModel> requestTask(
                                @Field("user_id") int user_id,
                                 @Field("name_task") String name_task,
                                 @Field("city_task") String city_task,
                                 @Field("full_task") String full_task,
                                 @Field("short_task") String short_task,
                                 @Field("price_task")String price_task,
                                 @Field("number_task") String number_task
    );

    @FormUrlEncoded
    @POST("Api.php?action=gettask")
    Call<TasksModel> getTasks(
            @Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("Api.php?action=feed")
    Call<FeedModel> getFeed(
            @Field("user_id") int user_id, @Field("task_id") int task_id );

    @FormUrlEncoded
    @POST("Api.php?action=getuser")
    Call<UserModel> getUser(
            @Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("Api.php?action=setchat")
    Call<ChatModel> setChat(
            @Field("user_id") int user_id,
            @Field("who_id") int who_id,
            @Field("text") String text,
            @Field("task_id") int  task_id
    );

    @FormUrlEncoded
    @POST("Api.php?action=getchat")
    Call<ChatModel> getChat(
            @Field("user_id") int user_id,
            @Field("who_id") int who_id,
            @Field("text") String text,
            @Field("task_id") int  task_id
    );


}
