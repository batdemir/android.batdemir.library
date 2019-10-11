package com.android.batdemir.mylibrary.API;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.android.batdemir.mylibrary.Components.MyAlertDialog;
import com.android.batdemir.mylibrary.Tools.ToolConnection;

import retrofit2.Call;

public class Connect {

    public void connect(Context context, Call call, String operationType) {
        if (!ToolConnection.isConnected(context)) {
            MyAlertDialog myAlertDialog = MyAlertDialog.getInstance("Lütfen, Internet Bağlantınızı Kontrol Ediniz.");
            myAlertDialog.setIsCancelable(false);
            myAlertDialog.setShowCancelButton(false);
            myAlertDialog.setShowEditText(false);
            myAlertDialog.setAutoDismiss(true);
            myAlertDialog.show(((FragmentActivity) context).getSupportFragmentManager(), Connect.class.getSimpleName());
            return;
        }
        new ConnectService(context, operationType).execute(call);
    }
}

