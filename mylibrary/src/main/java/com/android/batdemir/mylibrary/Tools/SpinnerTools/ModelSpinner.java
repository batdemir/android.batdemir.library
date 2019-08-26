package com.android.batdemir.mylibrary.Tools.SpinnerTools;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelSpinner implements Serializable {

    @SerializedName("Id")
    private Integer Id;

    @SerializedName("Description")
    private String Description;

    public ModelSpinner() {
    }

    public ModelSpinner(Integer id, String description) {
        Id = id;
        Description = description;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelSpinner)) return false;

        ModelSpinner that = (ModelSpinner) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
