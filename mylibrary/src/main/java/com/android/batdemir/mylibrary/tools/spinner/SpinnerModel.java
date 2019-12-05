package com.android.batdemir.mylibrary.tools.spinner;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpinnerModel implements Serializable, Parcelable {

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

    //PARCELABLE

    private SpinnerModel(Parcel in) {
        id = in.readValue(SpinnerModel.class.getClassLoader());
        description = in.readString();
        model = in.readString();
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

    public static final Parcelable.Creator<SpinnerModel> CREATOR = new Parcelable.Creator<SpinnerModel>() {
        public SpinnerModel createFromParcel(Parcel in) {
            return new SpinnerModel(in);
        }

        public SpinnerModel[] newArray(int size) {
            return new SpinnerModel[size];
        }
    };
}
