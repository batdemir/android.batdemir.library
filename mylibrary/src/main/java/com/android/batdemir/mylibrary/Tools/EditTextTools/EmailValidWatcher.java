package com.android.batdemir.mylibrary.Tools.EditTextTools;

import android.text.Editable;
import android.text.TextWatcher;

import com.android.batdemir.mylibrary.MyEditText;

public class EmailValidWatcher implements TextWatcher {

    private final MyEditText myEditText;

    public EmailValidWatcher(MyEditText myEditText) {
        this.myEditText = myEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (myEditText.get_editText().getText().toString().isEmpty()) {
            myEditText.getGradientDrawable().setStroke(myEditText.getBorderWidth(), myEditText.getNonConfirmativeBorderColor());
        } else if (new HelperEditText().isEmailValid(myEditText.get_editText().getText().toString())) {
            myEditText.getGradientDrawable().setStroke(myEditText.getBorderWidth(), myEditText.getConfirmativeBorderColor());
            myEditText.get_editText().setError(null);
        } else {
            myEditText.getGradientDrawable().setStroke(myEditText.getBorderWidth(), myEditText.getNonConfirmativeBorderColor());
            myEditText.get_editText().setError("Input could not be match e-mail format.");
        }
    }
}
