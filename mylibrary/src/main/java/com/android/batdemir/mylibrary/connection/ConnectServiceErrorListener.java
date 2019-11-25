package com.android.batdemir.mylibrary.connection;

public interface ConnectServiceErrorListener {
    void onException(String operationType, String errorMessage);
}
