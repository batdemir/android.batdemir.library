package com.android.batdemir.library.models.todo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class TodoModel implements Serializable {

    @SerializedName("Id")
    private UUID id;

    @SerializedName("Name")
    private String name;

    @SerializedName("CreatedDate")
    private Date createdDate;

    @SerializedName("IsActive")
    private boolean isActive;

    public TodoModel() {
    }

    public TodoModel(UUID id, String name, Date createdDate, boolean isActive) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
