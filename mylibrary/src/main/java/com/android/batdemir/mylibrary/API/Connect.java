package com.android.batdemir.mylibrary.API;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.android.batdemir.mylibrary.Components.MyAlertDialog;
import com.android.batdemir.mylibrary.Tools.ToolConnection;

import retrofit2.Call;
import retrofit2.Response;

public class Connect<T> {

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
        new AsyncConnectService(context, operationType).execute(call);
    }

    public interface ConnectServiceListener<T> {
        void onSuccess(String operationType, Response<T> response);

        void onFailure(String operationType, Response<T> response);

        void onException(String operationType, Exception e);
    }

    @SuppressLint("StaticFieldLeak")
    private class AsyncConnectService extends AsyncTask<Call, Void, Response> {
        private ProgressDialog progressDialog;
        private Context context;
        private String operationType;

        AsyncConnectService(Context context, String operationType) {
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
                Log.e(Connect.class.getSimpleName(), e.getMessage());
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
                    connectServiceListener.onFailure(operationType, response);
                }
            } catch (NullPointerException e) {
                MyAlertDialog myAlertDialog = MyAlertDialog.getInstance("Servis İle Bağlantı Sağlanamadı. Lütfen Tekrar Deneyiniz.");
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

}

