package com.android.batdemir.mylibrary.components.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpinnerModel implements Serializable, Parcelable {

    public static final Parcelable.Creator<SpinnerModel> CREATOR = new Parcelable.Creator<SpinnerModel>() {
        public SpinnerModel createFromParcel(Parcel in) {
            return new SpinnerModel(in);
        }

        public SpinnerModel[] newArray(int size) {
            return new SpinnerModel[size];
        }
    };
    @SuppressWarnings("java:S1948")
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

    private SpinnerModel(Parcel in) {
        id = in.readValue(SpinnerModel.class.getClassLoader());
        description = in.readString();
        model = in.readString();
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

    //EQUALS

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    //PARCELABLE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpinnerModel)) return false;

        SpinnerModel that = (SpinnerModel) o;

        if (!getId().equals(that.getId())) return false;
        return getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeString(description);
        dest.writeString(model);
    }
}
