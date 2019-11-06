package com.android.batdemir.mylibrary;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

public class MyEditText extends RelativeLayout {

    private TextInputLayout inputLayout;
    private EditText editText;
    private TextView txtEditCharCount;
    private TextView txtEditMaxCharCount;
    private TextView txtSlash;

    // Constructor

    public MyEditText(Context context) {
        super(context);
        init(context, null);
    }

    public MyEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    // Initialize

    private void init(Context context, AttributeSet attributeSet) {
        inflate(context, R.layout.component_edit_text, this);

        editText = findViewById(R.id.editText);
        inputLayout = findViewById(R.id.inputLayout);
        txtEditCharCount = findViewById(R.id.txtEditCharCount);
        txtEditMaxCharCount = findViewById(R.id.txtEditMaxCharCount);
        txtSlash = findViewById(R.id.txtSlash);

        editText.addTextChangedListener(new TextWatcherListener() {
            @Override
            public void textChanged(String str) {
                super.textChanged(str);
                Log.d(MyEditText.class.getSimpleName() + " textChanged", str);
            }
        });

        if (attributeSet == null)
            return;

        applyAttribute(context, attributeSet);
    }

    private void applyAttribute(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ComponentEditText, 0, 0);

        try {
            int editTextHint = R.styleable.ComponentEditText_editTextHint;
            int editTextHintTextAppearance = R.styleable.ComponentEditText_editTextHintTextAppearance;
            int editTextValue = R.styleable.ComponentEditText_editTextValue;
            int editTextValueTextAppearance = R.styleable.ComponentEditText_editTextValueTextAppearance;
            int editTextSubValueTextAppearance = R.styleable.ComponentEditText_editTextSubValueTextAppearance;
            int maxCharCount = R.styleable.ComponentEditText_maxCharCount;
            int inputType = R.styleable.ComponentEditText_android_inputType;

            if (typedArray.hasValue(editTextHint)) {
                setHint(typedArray.getString(editTextHint));
            }

            if (typedArray.hasValue(editTextHintTextAppearance)) {
                setHintTextAppearance(typedArray.getResourceId(editTextHintTextAppearance, R.style.TitleTextAppearance));
            }

            if (typedArray.hasValue(editTextValue)) {
                setEditTextValue(typedArray.getString(editTextValue));
            }

            if (typedArray.hasValue(editTextValueTextAppearance)) {
                setEditTextValueTextAppearance(typedArray.getResourceId(editTextValueTextAppearance, R.style.ValueTextAppearance));
            }

            if (typedArray.hasValue(editTextSubValueTextAppearance)) {
                setEditTextSubValueTextAppearance(typedArray.getResourceId(editTextSubValueTextAppearance, R.style.SubValueTextAppearance));
            }

            if (typedArray.hasValue(maxCharCount)) {
                setMaxCharCount(typedArray.getInt(maxCharCount, 10));
            }

            if (typedArray.hasValue(inputType)) {
                setInputType(typedArray.getInt(inputType, InputType.TYPE_CLASS_TEXT));
            }

        } catch (Exception e) {
            Log.e(MyEditText.class.getSimpleName(), e.getMessage());
        } finally {
            typedArray.recycle();
        }
    }

    // Initialize Methods

    private void setHint(String hintName) {
        inputLayout.setHint(hintName);
    }

    private void setHintTextAppearance(Integer hintTextAppearance) {
        inputLayout.setHintTextAppearance(hintTextAppearance);
    }

    private void setEditTextValue(String editTextValue) {
        editText.setText(editTextValue);
    }

    private void setEditTextValueTextAppearance(Integer editTextValueTextAppearance) {
        editText.setTextAppearance(editTextValueTextAppearance);
    }

    private void setEditTextSubValueTextAppearance(Integer editTextSubValueTextAppearance) {
        txtEditMaxCharCount.setTextAppearance(editTextSubValueTextAppearance);
        txtEditCharCount.setTextAppearance(editTextSubValueTextAppearance);
        txtSlash.setTextAppearance(editTextSubValueTextAppearance);
    }

    private void setMaxCharCount(Integer maxCharCount) {
        txtEditMaxCharCount.setText(String.valueOf(maxCharCount));
    }

    private void setInputType(int inputType) {
        editText.setInputType(inputType);
    }

    // Get Property

    public TextInputLayout getTextInputLayout() {
        return inputLayout;
    }

    public EditText getEditText() {
        return editText;
    }

    public TextView getTxtEditCharCount() {
        return txtEditCharCount;
    }

    public TextView getTxtEditMaxCharCount() {
        return txtEditMaxCharCount;
    }

    // Validate Methods

    public boolean isValid(boolean controlIsEmpty) {
        if (controlIsEmpty)
            return !editText.getText().toString().isEmpty() && editText.getError() == null;
        else
            return editText.getError() == null;
    }

    private abstract class TextWatcherListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            textChanged(String.valueOf(s));
        }

        public void textChanged(String str) {
            txtEditCharCount.setText(String.valueOf(str.length()));

            if (str.length() > Integer.parseInt(txtEditMaxCharCount.getText().toString())) {
                editText.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(R.color.edit_text_warning_color)));
                editText.setError("Girilen Değer " + txtEditMaxCharCount.getText().toString() + " Karakterden Daha Fazla Olamaz.");
                txtEditCharCount.setTextColor(getContext().getColor(R.color.edit_text_warning_color));
                txtEditMaxCharCount.setTextColor(getContext().getColor(R.color.edit_text_warning_color));
                txtSlash.setTextColor(getContext().getColor(R.color.edit_text_warning_color));
            } else {
                editText.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(R.color.edit_text_value_color)));
                editText.setError(null);
                txtEditCharCount.setTextColor(getContext().getColor(R.color.edit_text_value_color));
                txtEditMaxCharCount.setTextColor(getContext().getColor(R.color.edit_text_value_color));
                txtSlash.setTextColor(getContext().getColor(R.color.edit_text_value_color));
            }
        }
    }
}
