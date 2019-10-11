package com.android.batdemir.mylibrary.API;

import retrofit2.Response;

public interface ConnectServiceListener {
    void onSuccess(String operationType, Response response);

    void onFailure(String operationType, Response response);

    void onException(String operationType, Exception e);
}
