package com.android.batdemir.mylibrary.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ToolSharedPreferences {

    private String TAG = ToolSharedPreferences.class.getSimpleName();
    private SharedPreferences sharedPreferences;

    public ToolSharedPreferences(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void set_sharedPreferencesInteger(String key, Integer value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public Integer get_sharedPreferencesInteger(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public void set_sharedPreferencesString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String get_sharedPreferencesString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void set_sharedPreferencesBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public Boolean get_sharedPreferencesBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void set_sharedPreferencesLong(String key, Long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public Long get_sharedPreferencesLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public void set_sharedPreferencesFloat(String key, Float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public Float get_sharedPreferencesFloat(String key) {
        return sharedPreferences.getFloat(key, 0);
    }
}
