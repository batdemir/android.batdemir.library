package com.android.batdemir.mylibrary.Components.Spinner;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpinnerModel implements Serializable {

    @SerializedName("id")
    private Object id;

    @SerializedName("description")
    private String description;

    @SerializedName("model")
    private String model;

    public SpinnerModel(Object id, String description, String model) {
        this.id = id;
        this.description = description;
        this.model = model;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
