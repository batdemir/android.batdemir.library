package com.android.batdemir.library.models.todo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

public class CustomTodoList implements Serializable {

    @SerializedName("id")
    private UUID id;

    @SerializedName("userId")
    private UUID userId;

    @SerializedName("todoId")
    private UUID todoId;

    @SerializedName("userName")
    private String userName;

    @SerializedName("todoName")
    private String todoName;

    public CustomTodoList() {
    }

    public CustomTodoList(UUID id, UUID userId, UUID todoId, String userName, String todoName) {
        this.id = id;
        this.userId = userId;
        this.todoId = todoId;
        this.userName = userName;
        this.todoName = todoName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getTodoId() {
        return todoId;
    }

    public void setTodoId(UUID todoId) {
        this.todoId = todoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }
}
