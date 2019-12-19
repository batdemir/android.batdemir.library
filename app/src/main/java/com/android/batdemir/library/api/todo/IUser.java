package com.android.batdemir.library.api.todo;

import com.android.batdemir.library.models.todo.UserModel;
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

public interface IUser {

    @GET("/User")
    Call<ResponseModel<List<UserModel>>> get();

    @GET("/User/{id}")
    Call<ResponseModel<UserModel>> get(@Path("id") UUID id);

    @POST("/User")
    Call<ResponseModel<UserModel>> insert(@Body UserModel model);

    @PUT("/User/{id}")
    Call<ResponseModel<UserModel>> update(@Path("id") UUID id, @Body UserModel model);

    @DELETE("/User/{id}")
    Call<ResponseModel<UserModel>> delete(@Path("id") UUID id);
}
