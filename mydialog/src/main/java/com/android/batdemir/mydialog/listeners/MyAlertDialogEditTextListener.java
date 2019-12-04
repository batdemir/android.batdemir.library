package com.android.batdemir.mydialog.listeners;

import android.widget.EditText;

import com.android.batdemir.mydialog.ui.MyAlertDialog;

public interface MyAlertDialogEditTextListener {
    void dialogOkEditText(MyAlertDialog myAlertDialog, EditText editText);

    void dialogCancelEditText(MyAlertDialog myAlertDialog, EditText editText);
}
