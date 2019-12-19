package com.android.batdemir.library.ui.ui_app.activities;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.batdemir.library.R;
import com.android.batdemir.library.api.todo.IUser;
import com.android.batdemir.library.api.todo.Operation;
import com.android.batdemir.library.api.todo.connections.SpecConnect;
import com.android.batdemir.library.api.todo.connections.SpecConnectService;
import com.android.batdemir.library.databinding.ActivitySignUpBinding;
import com.android.batdemir.library.models.todo.UserModel;
import com.android.batdemir.library.ui.base.BaseActivity;
import com.android.batdemir.mylibrary.connection.RetrofitClient;

import java.util.Objects;
import java.util.UUID;

public class SignUpActivity extends BaseActivity implements
        SpecConnectService.ConnectionServiceListener {

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
        binding.btnRegister.setOnClickListener(v -> register());
    }

    private void register() {
        UserModel userModel = new UserModel();
        userModel.setId(UUID.randomUUID());
        userModel.setName(Objects.requireNonNull(binding.editTextUsername.getText()).toString() + " " + Objects.requireNonNull(binding.editTextPassword.getText()).toString());
        userModel.setActive(true);
        new SpecConnect().connect(context, RetrofitClient.getInstance().create(IUser.class).insert(userModel), Operation.USER_INSERT.name());
    }

    @Override
    public void onSuccess(String operationType, Object model) {
        Log.v(SignUpActivity.class.getSimpleName(), "success:" + operationType);

    }

    @Override
    public void onFailure(String operationType) {
        Log.v(SignUpActivity.class.getSimpleName(), "fail:" + operationType);
    }
}
