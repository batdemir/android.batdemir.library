package com.android.batdemir.mylibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.batdemir.mylibrary.Tools.EditTextTools.CharCountValidWatcher;
import com.android.batdemir.mylibrary.Tools.EditTextTools.EmailValidWatcher;
import com.android.batdemir.mylibrary.Tools.EditTextTools.HelperEditText;
import com.android.batdemir.mylibrary.Tools.EditTextTools.PhoneValidWatcher;

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
    private boolean emailValid=false;
    private boolean phoneValid=false;

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

    public GradientDrawable getGradientDrawable() {
        return gradientDrawable;
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
        _editText.addTextChangedListener(new CharCountValidWatcher(this));
    }

    public boolean isEmailValid() {
        return emailValid;
    }

    public void setEmailValid(boolean emailValid) {
        this.emailValid = emailValid;
        _editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        _editText.addTextChangedListener(new EmailValidWatcher(this));
    }

    public boolean isPhoneValid() {
        return phoneValid;
    }

    public void setPhoneValid(boolean phoneValid) {
        this.phoneValid = phoneValid;
        _editText.setInputType(InputType.TYPE_CLASS_PHONE);
        _editText.addTextChangedListener(new PhoneValidWatcher(this));
    }

    //---functions---//

    private void init() {
        try {
            inflate(getContext(), R.layout.view_my_edittext, this);
            _editText = findViewById(R.id.viewMyEditText);
            gradientDrawable = (GradientDrawable) _editText.getBackground();
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
}
