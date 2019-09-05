package com.android.batdemir.mylibrary.Tools.EditTextTools;

import android.text.Editable;
import android.text.TextWatcher;

import com.android.batdemir.mylibrary.MyEditText;

public class CharCountValidWatcher implements TextWatcher {

    private final int count;
    private final MyEditText myEditText;

    public CharCountValidWatcher(MyEditText myEditText) {
        this.count = myEditText.getConfirmativeCharCount();
        this.myEditText = myEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (myEditText.get_editText().getText().toString().isEmpty()) {
            myEditText.getGradientDrawable().setStroke(myEditText.getBorderWidth(), myEditText.getNonConfirmativeBorderColor());
        } else if (myEditText.get_editText().getText().toString().length() >= count) {
            myEditText.getGradientDrawable().setStroke(myEditText.getBorderWidth(), myEditText.getConfirmativeBorderColor());
            myEditText.get_editText().setError(null);
        } else {
            myEditText.getGradientDrawable().setStroke(myEditText.getBorderWidth(), myEditText.getNonConfirmativeBorderColor());
            myEditText.get_editText().setError("Input could not be lower than " + String.valueOf(count) + " character.");
        }
    }
}
