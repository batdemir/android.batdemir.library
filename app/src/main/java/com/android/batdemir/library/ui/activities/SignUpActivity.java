package com.android.batdemir.library.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivitySignUpBinding;
import com.android.batdemir.mylibrary.tools.Tool;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context;
        ActivitySignUpBinding binding;
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_sign_up);
        binding.btnLogin.setOnClickListener(v -> Tool.getInstance(context).move(LoginActivity.class, false, false, null));
    }
}
