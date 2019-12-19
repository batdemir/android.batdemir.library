package com.android.batdemir.library.models.todo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

public class UserModel implements Serializable {

    @SerializedName("Id")
    private UUID id;

    @SerializedName("Name")
    private String name;

    @SerializedName("IsActive")
    private boolean isActive;

    public UserModel() {
    }

    public UserModel(UUID id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
