package com.android.batdemir.library.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityLoginBinding;
import com.android.batdemir.mylibrary.components.dialog.MyAlertDialog;
import com.android.batdemir.mylibrary.components.dialog.MyAlertDialogListener;
import com.android.batdemir.mylibrary.tools.Tool;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements
        MyAlertDialogListener {

    static final String TEST = "TEST";
    static final String TEST_2 = "TEST_2";
    static final String TEST_3 = "TEST_3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context;
        ActivityLoginBinding binding;
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_login);
        binding.btnLogin.setOnClickListener(v -> MyAlertDialog.getInstance(TEST, MyAlertDialog.DialogStyle.ACTION).show(getSupportFragmentManager(), TEST));
        binding.btnSignUp.setOnClickListener(v -> Tool.getInstance(context).move(SignUpActivity.class, true, false, null));
    }

    @Override
    public void dialogOk(MyAlertDialog myAlertDialog) {
        Log.v("dialogOk","ok");
        if (Objects.equals(myAlertDialog.getTag(), TEST))
            MyAlertDialog.getInstance(TEST_2, MyAlertDialog.DialogStyle.INPUT).show(getSupportFragmentManager(), TEST_2);
        if (Objects.equals(myAlertDialog.getTag(), TEST_2))
            MyAlertDialog.getInstance(TEST_3, MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), TEST_3);
    }

    @Override
    public void dialogCancel(MyAlertDialog myAlertDialog) {
        Log.v("dialogCancel","cancel");
        if (Objects.equals(myAlertDialog.getTag(), TEST))
            MyAlertDialog.getInstance(TEST_2, MyAlertDialog.DialogStyle.INPUT).show(getSupportFragmentManager(), TEST_2);
        if (Objects.equals(myAlertDialog.getTag(), TEST_2))
            MyAlertDialog.getInstance(TEST_3, MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), TEST_3);
    }
}
