package com.android.batdemir.library.app;

import android.app.Application;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

public class MyApplication extends Application {

    private static final String TAG = "Application Warning:\t";

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        Log.d(TAG, "Created");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "Low Memory");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "Terminated");
    }
}
