package com.android.batdemir.library.api.todo;

import com.android.batdemir.library.models.todo.CustomTodoList;
import com.android.batdemir.library.models.todo.response.ResponseModel;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ITodoList {

    @GET("/TodoList")
    Call<ResponseModel<List<CustomTodoList>>> get();

    @GET("/TodoList/todo/{id}")
    Call<ResponseModel<List<CustomTodoList>>> getByTodo(@Path("id") UUID id);

    @GET("/TodoList/user/{id}")
    Call<ResponseModel<List<CustomTodoList>>> getByUser(@Path("id") UUID id);
}
