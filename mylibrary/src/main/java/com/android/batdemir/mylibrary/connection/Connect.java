package com.android.batdemir.mylibrary.connection;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.android.batdemir.mylibrary.R;
import com.android.batdemir.mylibrary.tools.ToolConnection;

import retrofit2.Call;

public class Connect {
    public void connect(Context context, Call<?> call, String operationType) {
        if (!ToolConnection.getInstance(context).isConnected()) {
            new MyAlertDialog
                    .Builder()
                    .setStyle(MyDialogStyle.WARNING)
                    .setMessage(context.getString(R.string.message_please_check_internet_connection))
                    .build()
                    .show(((FragmentActivity) context).getSupportFragmentManager(), Connect.class.getSimpleName());
            return;
        }
        new ConnectService(context, operationType).execute(call);
    }
}

