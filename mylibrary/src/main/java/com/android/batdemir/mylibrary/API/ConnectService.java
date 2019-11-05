package com.android.batdemir.mylibrary.API;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.android.batdemir.mylibrary.Components.Dialog.MyAlertDialog;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Response;

@SuppressLint("StaticFieldLeak")
public class ConnectService extends AsyncTask<Call, Void, Response> {

    private ProgressDialog progressDialog;
    private Context context;
    private String operationType;
    private ConnectServiceListener connectServiceListener;
    private ConnectServiceErrorListener connectServiceErrorListener;

    private String progressBarMessage = "Lütfen Bekleyiniz...";
    private String connectionFailMessage = "Servis İle Bağlantı Sağlanamadı. Lütfen Tekrar Deneyiniz.";
    private String connectionTimeOutMessage = "Servis İle Bağlantı Zaman Aşımına Uğradı. Lütfen Tekrar Deneyiniz.";

    public ConnectService(Context context, String operationType) {
        this.context = context;
        this.operationType = operationType;
    }

    public void setProgressBarMessage(String progressBarMessage) {
        this.progressBarMessage = progressBarMessage;
    }

    public void setConnectionFailMessage(String connectionFailMessage) {
        this.connectionFailMessage = connectionFailMessage;
    }

    public void setConnectionTimeOutMessage(String connectionTimeOutMessage) {
        this.connectionTimeOutMessage = connectionTimeOutMessage;
    }

    private void showProgressBar() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(progressBarMessage);
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
        }
    }

    private void hideProgressBar() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onPreExecute() {
        showProgressBar();
        connectServiceListener = (ConnectServiceListener) context;
        try {
            connectServiceErrorListener = (ConnectServiceErrorListener) context;
        } catch (ClassCastException e) {
            Log.e("ConnectionServiceErrorListener", "If you want to use this listener, main context must be implements to this listener");
        }
    }

    @Override
    protected Response doInBackground(Call... calls) {
        try {
            return calls[0].execute();
        } catch (Exception e) {
            cancel(true);
            if (e.getClass().equals(ConnectException.class)) {
                MyAlertDialog.getInstance(connectionFailMessage + "\n" + e.getMessage(), MyAlertDialog.DialogStyle.INFO).show(((FragmentActivity) context).getSupportFragmentManager(), operationType);
            } else if (e.getClass().equals(SocketTimeoutException.class) || e.getClass().equals(IOException.class)) {
                MyAlertDialog.getInstance(connectionTimeOutMessage + "\n" + e.getMessage(), MyAlertDialog.DialogStyle.INFO).show(((FragmentActivity) context).getSupportFragmentManager(), operationType);
            } else {
                MyAlertDialog.getInstance(e.getMessage(), MyAlertDialog.DialogStyle.INFO).show(((FragmentActivity) context).getSupportFragmentManager(), operationType);
            }
            if (connectServiceErrorListener != null)
                connectServiceErrorListener.onException(operationType, e.getMessage());
            Log.e(operationType, e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(Response response) {
        hideProgressBar();
        if (response.isSuccessful())
            connectServiceListener.onSuccess(operationType, response);
        else
            connectServiceListener.onFailure(operationType, response);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        hideProgressBar();
    }
}
