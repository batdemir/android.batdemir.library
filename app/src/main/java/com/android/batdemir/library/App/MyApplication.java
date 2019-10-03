package com.android.batdemir.library.App;

import android.app.Application;
import android.text.InputType;
import android.util.Log;

import com.android.batdemir.mylibrary.Components.MyAlertDialog;

public class MyApplication extends Application {

    private static final String TAG = "Application Warning:\t";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"Created");
        MyAlertDialog myAlertDialog = MyAlertDialog.getInstance("");
        myAlertDialog.setComponentProperty(new MyAlertDialog.ComponentProperty(
                true,
                true,
                false,
                true,
                InputType.TYPE_CLASS_NUMBER
        ));
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
