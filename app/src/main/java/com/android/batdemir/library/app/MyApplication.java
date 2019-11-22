package com.android.batdemir.library.app;

import android.app.Application;
import android.util.Log;

import com.android.batdemir.library.ui.SpecificDialogImp;
import com.android.batdemir.mylibrary.components.dialog.MyAlertDialog;

public class MyApplication extends Application {

    private static final String TAG = "Application Warning:\t";

    @Override
    public void onCreate() {
        super.onCreate();
        MyAlertDialog.setMyAlertDialogCreator(new SpecificDialogImp());
        MyAlertDialog.getInstance("", MyAlertDialog.DialogStyle.INFO);
        MyAlertDialog.setBuilder(new MyAlertDialog.Builder()
                .setInformationTitle("testInformation")
                .setInformationTitleColor(getColor(android.R.color.holo_green_dark))
                .setWarningTitle("testWarning")
                .setWarningTitleColor(getColor(android.R.color.holo_red_dark))
                .setInputEmptyMessage("youCannotBePassEmpty")
        );
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
