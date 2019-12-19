package com.android.batdemir.library.api.todo.connections;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.android.batdemir.library.models.todo.response.ResponseModel;
import com.android.batdemir.library.models.todo.response.ResponseStatus;
import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mylibrary.connection.ConnectService;

import java.io.IOException;

import retrofit2.Response;

public class SpecConnectService extends ConnectService {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private ProgressDialog progressDialog;
    private String operationType;

    private void showProgressBarSpec() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
        }
    }

    private void hideProgressBarSpec() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public SpecConnectService(Context context, String operationType) {
        super(context, operationType);
        this.context = context;
        this.operationType = operationType;
    }

    @Override
    protected void onPreExecute() {
        showProgressBarSpec();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onPostExecute(Response response) {
        hideProgressBarSpec();
        ConnectionServiceListener connectServiceListener = (ConnectionServiceListener) context;
        if (response.isSuccessful()) {
            ResponseModel<Object> model = (ResponseModel<Object>) response.body();
            MyAlertDialog.DialogStyle style;
            if (model == null)
                return;
            ResponseStatus status = getStatus(model.getStatus());
            switch (status) {
                case SUCCESS:
                    connectServiceListener.onSuccess(operationType, model.getModel());
                    style = MyAlertDialog.DialogStyle.SUCCESS;
                    break;
                case FAIL:
                    connectServiceListener.onFailure(operationType);
                    style = MyAlertDialog.DialogStyle.FAILED;
                    break;
                case NOT_FOUND:
                    connectServiceListener.onFailure(operationType);
                    style = MyAlertDialog.DialogStyle.WARNING;
                    break;
                case DUPLICATE:
                    connectServiceListener.onFailure(operationType);
                    style = MyAlertDialog.DialogStyle.INFO;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + model.getStatus());
            }
            if (model.getMessage() != null && !model.getMessage().isEmpty())
                MyAlertDialog.getInstance(model.getMessage(), style).show(((FragmentActivity) context).getSupportFragmentManager(), status.name());
        } else {
            try {
                connectServiceListener.onFailure(operationType);
                MyAlertDialog.getInstance(response.errorBody() == null ? "Failed" : response.errorBody().string(), MyAlertDialog.DialogStyle.FAILED).show(((FragmentActivity) context).getSupportFragmentManager(), "failure");
            } catch (IOException e) {
                Log.e(SpecConnectService.class.getSimpleName(), e.getMessage());
            }
        }
    }

    @Override
    protected void onCancelled() {
        hideProgressBarSpec();
    }

    public interface ConnectionServiceListener {
        void onSuccess(String operationType, Object model);

        void onFailure(String operationType);
    }

    private ResponseStatus getStatus(int statusCode) {
        if (statusCode == 0)
            return ResponseStatus.FAIL;
        if (statusCode == 1)
            return ResponseStatus.SUCCESS;
        if (statusCode == 2)
            return ResponseStatus.DUPLICATE;
        if (statusCode == 3)
            return ResponseStatus.NOT_FOUND;
        return ResponseStatus.FAIL;
    }
}
