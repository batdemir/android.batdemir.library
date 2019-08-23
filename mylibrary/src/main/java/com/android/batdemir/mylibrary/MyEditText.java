package com.android.batdemir.mylibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MyEditText extends RelativeLayout{

    private String TAG = MyEditText.class.getSimpleName();
    private EditText _editText;

    private GradientDrawable gradientDrawable;
    private boolean enableBorder = false;
    private int borderWidth = 1;
    private int borderColor = Color.BLACK;
    private int confirmativeBorderColor = Color.GREEN;
    private int nonConfirmativeBorderColor = Color.RED;
    private float borderRadius = 0F;
    private int solidColor = Color.TRANSPARENT;

    private int confirmativeCharCount=10;

    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    //---properties---//

    public EditText get_editText() {
        return _editText;
    }

    public boolean isEnableBorder() {
        return enableBorder;
    }

    public void setEnableBorder(boolean enableBorder) {
        this.enableBorder = enableBorder;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        gradientDrawable.setStroke(getBorderWidth(),getBorderColor());
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        gradientDrawable.setStroke(getBorderWidth(),getBorderColor());
    }

    public int getConfirmativeBorderColor() {
        return confirmativeBorderColor;
    }

    public void setConfirmativeBorderColor(int confirmativeBorderColor) {
        this.confirmativeBorderColor = confirmativeBorderColor;
    }

    public int getNonConfirmativeBorderColor() {
        return nonConfirmativeBorderColor;
    }

    public void setNonConfirmativeBorderColor(int nonConfirmativeBorderColor) {
        this.nonConfirmativeBorderColor = nonConfirmativeBorderColor;
    }

    public float getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(float borderRadius) {
        this.borderRadius = borderRadius;
        gradientDrawable.setCornerRadius(getBorderRadius());
    }

    public int getSolidColor() {
        return solidColor;
    }

    public void setSolidColor(int solidColor) {
        this.solidColor = solidColor;
        gradientDrawable.setColor(getSolidColor());
    }

    public int getConfirmativeCharCount() {
        return confirmativeCharCount;
    }

    public void setConfirmativeCharCount(int confirmativeCharCount) {
        this.confirmativeCharCount = confirmativeCharCount;
    }

    //---functions---//

    private void init() {
        try {
            inflate(getContext(), R.layout.view_my_edittext, this);
            _editText = findViewById(R.id.viewMyEditText);
            _editText.addTextChangedListener(textWatcher());
            gradientDrawable = (GradientDrawable) _editText.getBackground();
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

    private TextWatcher textWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(isEnableBorder()){
                    if(_editText.getText().toString().isEmpty()){
                        gradientDrawable.setStroke(getBorderWidth(),getNonConfirmativeBorderColor());
                    }else if(_editText.getText().toString().length()>=getConfirmativeCharCount()){
                        gradientDrawable.setStroke(getBorderWidth(),getConfirmativeBorderColor());
                    }else {
                        gradientDrawable.setStroke(getBorderWidth(),getNonConfirmativeBorderColor());
                    }
                }else {
                    gradientDrawable.setStroke(getBorderWidth(),getBorderColor());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isEnableBorder()){
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
    };
}
