package com.android.batdemir.library.App;

import android.app.Application;
import android.util.Log;

import com.android.batdemir.library.UI.SpecificDialogImp;
import com.android.batdemir.mylibrary.Components.MyAlertDialog;

public class MyApplication extends Application {

    private static final String TAG = "Application Warning:\t";

    @Override
    public void onCreate() {
        super.onCreate();
        MyAlertDialog.setMyAlertDialogCreator(new SpecificDialogImp());
        MyAlertDialog.getInstance("", MyAlertDialog.DialogStyle.INFO);
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
