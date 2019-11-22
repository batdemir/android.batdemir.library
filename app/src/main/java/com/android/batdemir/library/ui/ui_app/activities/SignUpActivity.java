package com.android.batdemir.library.ui.ui_app.activities;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivitySignUpBinding;
import com.android.batdemir.library.ui.base.BaseActivity;
import com.android.batdemir.mylibrary.tools.Tool;

public class SignUpActivity extends BaseActivity {

    private Context context;
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false, true, "signUp", 0f, R.style.AppThemeActionBar);
    }

    @Override
    public void getObjectReferences() {
        context = this;
        if (binding == null)
            binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_sign_up);
    }

    @Override
    public void loadData() {
        binding.editTextUsername.setText("");
        binding.editTextPassword.setText("");
        binding.editTextRePassword.setText("");
        binding.editTextMail.setText("");
        binding.editTextReMail.setText("");
    }

    @Override
    public void setListeners() {
        binding.btnLogin.setOnClickListener(v -> finish());
        binding.btnRegister.setOnClickListener(v -> Tool.getInstance(context).move(MenuActivity.class, true, false, null));
    }
}
