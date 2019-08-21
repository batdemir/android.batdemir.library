package com.android.batdemir.mylibrary;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MyEditTextView extends RelativeLayout {

    private String TAG = "MyEditTextView";
    private EditText _editText;
    private Boolean enableBorder;
    private Integer confirmativeCharCount;

    public MyEditTextView(Context context) {
        super(context);
        init();
    }

    public MyEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyEditTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    //---properties---//

    public EditText get_editText() {
        return _editText;
    }

    public Boolean getEnableBorder() {
        return enableBorder==null?true:enableBorder;
    }

    public void setEnableBorder(Boolean enableBorder) {
        this.enableBorder = enableBorder;
        if (getEnableBorder()) {
            _editText.setBackground(getContext().getDrawable(R.drawable.gradient_unselected_box));
        } else {
            _editText.setBackground(getContext().getDrawable(R.drawable.gradient_non_border_box));
        }
        _editText.addTextChangedListener(textWatcher(getEnableBorder()));
    }

    public Integer getConfirmativeCharCount() {
        return confirmativeCharCount==null?1:confirmativeCharCount;
    }

    public void setConfirmativeCharCount(Integer confirmativeCharCount) {
        this.confirmativeCharCount = confirmativeCharCount;
    }

    //---functions---//

    private void init() {
        try {
            inflate(getContext(), R.layout.view_my_edittext, this);
            _editText = findViewById(R.id.viewMyEditText);
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

    private TextWatcher textWatcher(Boolean enableBorder){
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(enableBorder){
                    if(_editText.getText().toString().isEmpty()){
                        _editText.setBackground(getContext().getDrawable(R.drawable.gradient_unselected_box));
                    }else if(_editText.getText().toString().length()>=getConfirmativeCharCount()){
                        _editText.setBackground(getContext().getDrawable(R.drawable.gradient_selected_box));
                    }else {
                        _editText.setBackground(getContext().getDrawable(R.drawable.gradient_unselected_box));
                    }
                }else {
                    _editText.setBackground(getContext().getDrawable(R.drawable.gradient_non_border_box));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(enableBorder){
                    if(_editText.getText().toString().length()>=getConfirmativeCharCount()){
                        _editText.setError(null);
                    }else {
                        _editText.setError("Input can't be lower than "+String.valueOf(getConfirmativeCharCount())+" character.");
                    }
                }else {
                    _editText.setError(null);
                }

            }
        };
        return textWatcher;
    };

}
