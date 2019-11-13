package com.android.batdemir.mylibrary.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ToolSharedPreferences {

    private static ToolSharedPreferences ourInstance = null;
    private SharedPreferences sharedPreferences;

    private ToolSharedPreferences(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static ToolSharedPreferences getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new ToolSharedPreferences(context);
        }
        return ourInstance;
    }

    public void setInteger(String key, Integer value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public Integer getInteger(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void setBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void setLong(String key, Long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public Long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public void setFloat(String key, Float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public Float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0);
    }
}
