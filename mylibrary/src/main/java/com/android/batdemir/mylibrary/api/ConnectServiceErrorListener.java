package com.android.batdemir.mylibrary.api;

public interface ConnectServiceErrorListener {
    void onException(String operationType, String errorMessage);
}
