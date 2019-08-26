package com.android.batdemir.mylibrary.Tools.EditTextTools;

import android.text.Editable;
import android.text.TextWatcher;

import com.android.batdemir.mylibrary.MyEditText;

import java.util.Locale;

public class PhoneValidWatcher implements TextWatcher {

    private final MyEditText myEditText;

    public PhoneValidWatcher(MyEditText myEditText) {
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
        if(myEditText.get_editText().getText().toString().isEmpty()){
            myEditText.getGradientDrawable().setStroke(myEditText.getBorderWidth(),myEditText.getNonConfirmativeBorderColor());
        }else if(new HelperEditText().isPhoneNumberValid(myEditText.get_editText().getText().toString(), Locale.getDefault().getCountry())){
            myEditText.getGradientDrawable().setStroke(myEditText.getBorderWidth(),myEditText.getConfirmativeBorderColor());
            myEditText.get_editText().setError(null);
        }else {
            myEditText.getGradientDrawable().setStroke(myEditText.getBorderWidth(),myEditText.getNonConfirmativeBorderColor());
            myEditText.get_editText().setError("Input could not be match phone format.");
        }
    }
}
