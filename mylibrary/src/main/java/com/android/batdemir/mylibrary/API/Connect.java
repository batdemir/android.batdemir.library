package com.android.batdemir.mylibrary.API;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.FragmentActivity;

import com.android.batdemir.mylibrary.Components.MyAlertDialog;
import com.android.batdemir.mylibrary.Tools.ToolConnection;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Response;

public class Connect<T> implements MyAlertDialog.AlertClickListener {

    public void connect(Context context, Call call, String operationType) {
        if (!ToolConnection.isConnected(context)) {
            MyAlertDialog
                    .newInstance("Lütfen, Internet Bağlantınızı Kontrol Ediniz.", false, false)
                    .show(((FragmentActivity) context).getSupportFragmentManager(), Connect.class.getSimpleName());
            return;
        }
        new AsyncConnectService(context, operationType).execute(call);
    }

    @SuppressLint("StaticFieldLeak")
    private class AsyncConnectService extends AsyncTask<Call, Void, Response> {
        private ProgressDialog progressDialog;
        private Context context;
        private String operationType;

        public AsyncConnectService(Context context, String operationType) {
            this.context = context;
            this.operationType = operationType;
        }

        @Override
        protected void onPreExecute() {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Lütfen Bekleyiniz...");
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
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Response response) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Activity activity = (Activity) context;
            ConnectServiceListener connectServiceListener = (ConnectServiceListener) activity;
            try {
                if (response.isSuccessful()) {
                    connectServiceListener.onSuccess(operationType, response);
                } else {
                    assert response.errorBody() != null;
                    MyAlertDialog
                            .newInstance(response.errorBody().string(), false, false)
                            .show(((FragmentActivity) context).getSupportFragmentManager(), Connect.class.getSimpleName());
                    connectServiceListener.onFailure(operationType, null);
                }
            } catch (Exception e) {
                if (e instanceof SocketTimeoutException) {
                    MyAlertDialog
                            .newInstance("Servis İle Bağlantı Sağlanamadı. Lütfen Tekrar Deneyiniz.", false, false)
                            .show(((FragmentActivity) context).getSupportFragmentManager(), Connect.class.getSimpleName());
                    connectServiceListener.onFailure(operationType, e);
                } else if (e instanceof NullPointerException) {
                    MyAlertDialog
                            .newInstance("Servis İle Bağlantı Sağlanamadı. Lütfen Tekrar Deneyiniz.", false, false)
                            .show(((FragmentActivity) context).getSupportFragmentManager(), Connect.class.getSimpleName());
                    connectServiceListener.onFailure(operationType, e);
                } else {
                    MyAlertDialog
                            .newInstance(e.getMessage(), false, false)
                            .show(((FragmentActivity) context).getSupportFragmentManager(), Connect.class.getSimpleName());
                    connectServiceListener.onFailure(operationType, e);
                }
            }
        }
    }

    public interface ConnectServiceListener<T> {
        void onSuccess(String operationType, Response<T> response);

        void onFailure(String operationType, Exception e);
    }

    @Override
    public void alertOkey() {

    }

    @Override
    public void alertCancel() {

    }
}

