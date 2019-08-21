package com.android.batdemir.mylibrary.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ToolSharedPreferences {

    private String TAG=ToolSharedPreferences.class.getSimpleName();
    private Context context;

    public ToolSharedPreferences(Context context) {
        this.context = context;
    }

    public void set_sharedPreferencesInteger(Enum key, Integer value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(String.valueOf(key),value);
        editor.commit();
    }

    public Integer get_sharedPreferencesInteger(Enum key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Integer status = preferences.getInt(String.valueOf(key), 0);
        return status;
    }

    public void set_sharedPreferencesString(Enum key, String value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(String.valueOf(key),value);
        editor.commit();
    }

    public String get_sharedPreferencesString(Enum key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String status = preferences.getString(String.valueOf(key),"");
        return status;
    }

    public void set_sharedPreferencesBoolean(Enum key, Boolean value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(String.valueOf(key),value);
        editor.commit();
    }

    public Boolean get_sharedPreferencesBoolean(Enum key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean status = preferences.getBoolean(String.valueOf(key), false);
        return status;
    }

    public void set_sharedPreferencesLong(Enum key, Long value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(String.valueOf(key),value);
        editor.commit();
    }

    public Long get_sharedPreferencesLong(Enum key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Long status = preferences.getLong(String.valueOf(key), 0);
        return status;
    }

    public void set_sharedPreferencesFloat(Enum key, Float value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(String.valueOf(key),value);
        editor.commit();
    }

    public Float get_sharedPreferencesFloat(Enum key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Float status = preferences.getFloat(String.valueOf(key), 0);
        return status;
    }
}
