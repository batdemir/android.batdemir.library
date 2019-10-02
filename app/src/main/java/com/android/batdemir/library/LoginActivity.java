package com.android.batdemir.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.batdemir.library.databinding.ActivityLoginBinding;
import com.android.batdemir.mylibrary.Tools.Tool;

public class LoginActivity extends AppCompatActivity {

    private Context context;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_login);
        binding.btnSignUp.setOnClickListener(v -> new Tool(context).move(SignUpActivity.class, true, false, null));
        binding.btnLogin.setOnClickListener(v -> new Tool(context).move(MenuActivity.class, true, false, null));
    }

}
