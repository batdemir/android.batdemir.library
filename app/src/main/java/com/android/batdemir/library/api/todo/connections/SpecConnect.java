package com.android.batdemir.library.api.todo.connections;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.android.batdemir.mylibrary.connection.Connect;
import com.android.batdemir.mylibrary.tools.ToolConnection;

import retrofit2.Call;

public class SpecConnect extends Connect {

    @Override
    public void connect(Context context, Call<?> call, String operationType) {
        if (!ToolConnection.getInstance(context).isConnected()) {
            new MyAlertDialog
                    .Builder()
                    .setStyle(MyDialogStyle.WARNING)
                    .setMessage("Please check internet connection.")
                    .build()
                    .show(((FragmentActivity) context).getSupportFragmentManager(), Connect.class.getSimpleName());
            return;
        }
        new SpecConnectService(context, operationType).execute(call);
    }
}
