package com.android.batdemir.mylibrary.API;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.android.batdemir.mylibrary.Components.MyAlertDialog;

import retrofit2.Call;
import retrofit2.Response;

@SuppressLint("StaticFieldLeak")
public class ConnectService extends AsyncTask<Call, Void, Response> {
    private ProgressDialog progressDialog;
    private Context context;
    private String operationType;

    private String progressBarMessage = "Lütfen Bekleyiniz...";
    private String connectionFailMessage = "Servis İle Bağlantı Sağlanamadı. Lütfen Tekrar Deneyiniz.";

    ConnectService(Context context, String operationType) {
        this.context = context;
        this.operationType = operationType;
    }

    public void setProgressBarMessage(String progressBarMessage) {
        this.progressBarMessage = progressBarMessage;
    }

    public void setConnectionFailMessage(String connectionFailMessage) {
        this.connectionFailMessage = connectionFailMessage;
    }

    @Override
    protected void onPreExecute() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(progressBarMessage);
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
        }
    }

    @Override
    protected Response doInBackground(Call... calls) {
        try {
            return calls[0].execute();
        } catch (Exception e) {
            Log.e(ConnectService.class.getSimpleName(), e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response response) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        ConnectServiceListener connectServiceListener = (ConnectServiceListener) context;
        try {
            if (response.isSuccessful()) {
                connectServiceListener.onSuccess(operationType, response);
            } else {
                connectServiceListener.onFailure(operationType, response);
            }
        } catch (NullPointerException e) {
            MyAlertDialog myAlertDialog = MyAlertDialog.getInstance(connectionFailMessage);
            myAlertDialog.setShowCancelButton(false);
            myAlertDialog.setShowEditText(false);
            myAlertDialog.setIsCancelable(false);
            myAlertDialog.setAutoDismiss(true);
            myAlertDialog.show(((FragmentActivity) context).getSupportFragmentManager(), Connect.class.getSimpleName());
            connectServiceListener.onException(operationType, e);
        } catch (Exception e) {
            MyAlertDialog myAlertDialog = MyAlertDialog.getInstance(e.getMessage());
            myAlertDialog.setShowCancelButton(false);
            myAlertDialog.setShowEditText(false);
            myAlertDialog.setIsCancelable(false);
            myAlertDialog.setAutoDismiss(true);
            myAlertDialog.show(((FragmentActivity) context).getSupportFragmentManager(), Connect.class.getSimpleName());
            connectServiceListener.onException(operationType, e);
        }
    }
}
