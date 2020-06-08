package com.android.batdemir.mydialog.ui;

import android.content.Context;
import android.graphics.Color;

import com.android.batdemir.mydialog.R;

@SuppressWarnings({"java:S115"})
public class MyDialogDefault {
    static final int informationTitleColor = Color.parseColor("#4E55EB");
    static final int warningTitleColor = Color.parseColor("#FF9D00");
    static final int successTitleColor = Color.parseColor("#03C9B8");
    static final int failedTitleColor = Color.parseColor("#FF0A00");
    private String informationTitle;
    private String warningTitle;
    private String successTitle;
    private String failedTitle;
    private String cancelButtonText;
    private String okButtonText;
    private String inputEmptyMessage;

    public MyDialogDefault(Context context) {
        informationTitle = context.getString(R.string.informationTitle);
        warningTitle = context.getString(R.string.warningTitle);
        successTitle = context.getString(R.string.successTitle);
        failedTitle = context.getString(R.string.failedTitle);
        cancelButtonText = context.getString(R.string.cancelButtonText);
        okButtonText = context.getString(R.string.okButtonText);
        inputEmptyMessage = context.getString(R.string.inputEmptyMessage);
    }

    public String getInformationTitle() {
        return informationTitle;
    }

    public void setInformationTitle(String informationTitle) {
        this.informationTitle = informationTitle;
    }

    public String getWarningTitle() {
        return warningTitle;
    }

    public void setWarningTitle(String warningTitle) {
        this.warningTitle = warningTitle;
    }

    public String getSuccessTitle() {
        return successTitle;
    }

    public void setSuccessTitle(String successTitle) {
        this.successTitle = successTitle;
    }

    public String getFailedTitle() {
        return failedTitle;
    }

    public void setFailedTitle(String failedTitle) {
        this.failedTitle = failedTitle;
    }

    public String getCancelButtonText() {
        return cancelButtonText;
    }

    public void setCancelButtonText(String cancelButtonText) {
        this.cancelButtonText = cancelButtonText;
    }

    public String getOkButtonText() {
        return okButtonText;
    }

    public void setOkButtonText(String okButtonText) {
        this.okButtonText = okButtonText;
    }

    public String getInputEmptyMessage() {
        return inputEmptyMessage;
    }

    public void setInputEmptyMessage(String inputEmptyMessage) {
        this.inputEmptyMessage = inputEmptyMessage;
    }
}
