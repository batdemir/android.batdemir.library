package com.android.batdemir.mylibrary.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ToolSharedPreferences {

    private String TAG = ToolSharedPreferences.class.getSimpleName();
    private Context context;

    public ToolSharedPreferences(Context context) {
        this.context = context;
    }

    public void set_sharedPreferencesInteger(String key, Integer value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public Integer get_sharedPreferencesInteger(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Integer status = preferences.getInt(key, 0);
        return status;
    }

    public void set_sharedPreferencesString(String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String get_sharedPreferencesString(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String status = preferences.getString(key, "");
        return status;
    }

    public void set_sharedPreferencesBoolean(String key, Boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean get_sharedPreferencesBoolean(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean status = preferences.getBoolean(key, false);
        return status;
    }

    public void set_sharedPreferencesLong(String key, Long value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public Long get_sharedPreferencesLong(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Long status = preferences.getLong(key, 0);
        return status;
    }

    public void set_sharedPreferencesFloat(String key, Float value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public Float get_sharedPreferencesFloat(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Float status = preferences.getFloat(key, 0);
        return status;
    }
}
