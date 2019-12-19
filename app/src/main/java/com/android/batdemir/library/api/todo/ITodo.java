package com.android.batdemir.library.api.todo;

import com.android.batdemir.library.models.todo.TodoModel;
import com.android.batdemir.library.models.todo.response.ResponseModel;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ITodo {

    @GET("/Todo")
    Call<ResponseModel<List<TodoModel>>> get();

    @GET("/Todo/{id}")
    Call<ResponseModel<TodoModel>> get(@Path("id") UUID id);

    @POST("/Todo")
    Call<ResponseModel<TodoModel>> insert(@Body TodoModel model);

    @PUT("/Todo/{id}")
    Call<ResponseModel<TodoModel>> update(@Path("id") UUID id, @Body TodoModel model);

    @DELETE("/Todo/{id}")
    Call<ResponseModel<TodoModel>> delete(@Path("id") UUID id);
}
