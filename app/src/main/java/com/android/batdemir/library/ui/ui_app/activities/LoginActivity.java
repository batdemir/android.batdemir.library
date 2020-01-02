package com.android.batdemir.library.ui.ui_app.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.android.batdemir.library.R;
import com.android.batdemir.library.api.todo.IUser;
import com.android.batdemir.library.api.todo.Operation;
import com.android.batdemir.library.api.todo.connections.SpecConnect;
import com.android.batdemir.library.api.todo.connections.SpecConnectService;
import com.android.batdemir.library.databinding.ActivityLoginBinding;
import com.android.batdemir.library.ui.base.BaseActivity;
import com.android.batdemir.mylibrary.connection.RetrofitClient;
import com.android.batdemir.mylibrary.tools.Tool;

public class LoginActivity extends BaseActivity implements SpecConnectService.ConnectionServiceListener {

    private Context context;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(true, false, "login", 0f, R.style.AppThemeNoActionBar);
    }

    @Override
    public void getObjectReferences() {
        context = this;
        if (binding == null)
            binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_login);
    }

    @Override
    public void loadData() {
        binding.editTextUsername.setText("");
        binding.editTextPassword.setText("");
    }

    @Override
    public void setListeners() {
        binding.btnLogin.setOnClickListener(v -> Tool.getInstance(context).move(MenuActivity.class, true, false, null));

        binding.btnSignUp.setOnClickListener(v -> Tool.getInstance(context).move(SignUpActivity.class, true, true, null));

        binding.forgot.setOnClickListener(v -> new SpecConnect().connect(context, RetrofitClient.getInstance().create(IUser.class).get(), Operation.USER_GET.name()));
    }

    @Override
    public void onSuccess(String operationType, Object model) {
        //Not Implemented
    }

    @Override
    public void onFailure(String operationType) {
        //Not Implemented
    }
}
