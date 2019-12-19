package com.android.batdemir.library.models.todo.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseModel<T> implements Serializable {

    @SerializedName("model")
    private T model;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private int status;

    public ResponseModel() {
    }

    public ResponseModel(T model, String message, int status) {
        this.model = model;
        this.message = message;
        this.status = status;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
