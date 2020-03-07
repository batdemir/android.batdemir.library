package com.android.batdemir.library.ui.ui_app.activities.signup;

import android.util.Log;

import com.android.batdemir.library.R;
import com.android.batdemir.library.api.todo.IUser;
import com.android.batdemir.library.api.todo.Operation;
import com.android.batdemir.library.api.todo.connections.SpecConnect;
import com.android.batdemir.library.api.todo.connections.SpecConnectService;
import com.android.batdemir.library.databinding.ActivitySignUpBinding;
import com.android.batdemir.library.models.todo.UserModel;
import com.android.batdemir.library.ui.base.activity.BaseActivity;
import com.android.batdemir.mylibrary.connection.RetrofitClient;

import java.util.Objects;
import java.util.UUID;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding, SignUpController> implements
        SpecConnectService.ConnectionServiceListener {

    @Override
    public void onCreated() {
        init(R.style.AppThemeActionBar, "signUp", 0f, true);
    }

    @Override
    public void getObjectReferences() {
        //Nothing
    }

    @Override
    public void loadData() {
        getBinding().editTextUsername.setText("");
        getBinding().editTextPassword.setText("");
        getBinding().editTextRePassword.setText("");
        getBinding().editTextMail.setText("");
        getBinding().editTextReMail.setText("");
    }

    @Override
    public void setListeners() {
        getBinding().btnLogin.setOnClickListener(v -> finish());
        getBinding().btnRegister.setOnClickListener(v -> register());
    }

    private void register() {
        UserModel userModel = new UserModel();
        userModel.setId(UUID.randomUUID());
        userModel.setName(Objects.requireNonNull(getBinding().editTextUsername.getText()).toString() + " " + Objects.requireNonNull(getBinding().editTextPassword.getText()).toString());
        userModel.setActive(true);
        new SpecConnect().connect(getBinding().getRoot().getContext(), RetrofitClient.getInstance().create(IUser.class).insert(userModel), Operation.USER_INSERT.name());
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
