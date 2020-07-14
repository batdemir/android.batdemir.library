package com.android.batdemir.library.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Event implements Serializable {

    @SerializedName("eventName")
    private String eventName;

    @SerializedName("eventIcon")
    private int eventIcon;

    @SerializedName("to")
    private Class<?> to;

    public Event(String eventName, int eventIcon, Class<?> to) {
        this.eventName = eventName;
        this.eventIcon = eventIcon;
        this.to = to;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventIcon() {
        return eventIcon;
    }

    public void setEventIcon(int eventIcon) {
        this.eventIcon = eventIcon;
    }

    public Class<?> getTo() {
        return to;
    }

    public void setTo(Class<?> to) {
        this.to = to;
    }
}
