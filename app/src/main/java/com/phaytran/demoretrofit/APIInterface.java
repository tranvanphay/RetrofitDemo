package com.phaytran.demoretrofit;


import com.phaytran.demoretrofit.model.AddTodo;
import com.phaytran.demoretrofit.model.AddTodoRes;
import com.phaytran.demoretrofit.model.Todo;
import com.phaytran.demoretrofit.pojo.MultipleResource;
import com.phaytran.demoretrofit.pojo.User;
import com.phaytran.demoretrofit.pojo.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


interface APIInterface {

    @POST("/todo/create")
    Call<AddTodoRes> createTodo(@Body AddTodo addTodo);

    @GET("/todo/update")
    Call<User> createUser(@Body User user);

    @GET("/todo/delete")
    Call<UserList> doGetUserList(@Query("page") String page);

    @GET("/todo/getAll")
    Call<List<Todo>> getAllTodo();
}
