package com.android.batdemir.library.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityLoginBinding;
import com.android.batdemir.mylibrary.Components.Dialog.MyAlertDialog;
import com.android.batdemir.mylibrary.Components.Dialog.MyAlertDialogListener;
import com.android.batdemir.mylibrary.Tools.Tool;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements
        MyAlertDialogListener {

    private Context context;
    private ActivityLoginBinding binding;

    final String DENEME = "DENEME";
    final String DENEME_2 = "DENEME_2";
    final String DENEME_3 = "DENEME_3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_login);
        binding.btnLogin.setOnClickListener(v -> MyAlertDialog.getInstance(DENEME, MyAlertDialog.DialogStyle.ACTION).show(getSupportFragmentManager(), DENEME));
        binding.btnSignUp.setOnClickListener(v -> Tool.getInstance(context).move(SignUpActivity.class, true, false, null));
    }

    @Override
    public void dialogOk(MyAlertDialog myAlertDialog) {
        if (Objects.equals(myAlertDialog.getTag(), DENEME))
            MyAlertDialog.getInstance(DENEME_2, MyAlertDialog.DialogStyle.INPUT).show(getSupportFragmentManager(), DENEME_2);
        if (Objects.equals(myAlertDialog.getTag(), DENEME_2))
            MyAlertDialog.getInstance(DENEME_3, MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), DENEME_3);
    }

    @Override
    public void dialogCancel(MyAlertDialog myAlertDialog) {
        if (Objects.equals(myAlertDialog.getTag(), DENEME))
            MyAlertDialog.getInstance(DENEME_2, MyAlertDialog.DialogStyle.INPUT).show(getSupportFragmentManager(), DENEME_2);
        if (Objects.equals(myAlertDialog.getTag(), DENEME))
            MyAlertDialog.getInstance(DENEME_3, MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), DENEME_3);
    }
}
