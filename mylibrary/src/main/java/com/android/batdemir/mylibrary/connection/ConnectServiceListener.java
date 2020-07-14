package com.android.batdemir.mylibrary.connection;

import retrofit2.Response;

public interface ConnectServiceListener {
    void onSuccess(String operationType, Response<?> response);

    void onFailure(String operationType, Response<?> response);
}
