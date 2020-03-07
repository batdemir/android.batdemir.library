package com.android.batdemir.library.ui.ui_app.activities.login;

import com.android.batdemir.library.R;
import com.android.batdemir.library.api.todo.IUser;
import com.android.batdemir.library.api.todo.Operation;
import com.android.batdemir.library.api.todo.connections.SpecConnect;
import com.android.batdemir.library.api.todo.connections.SpecConnectService;
import com.android.batdemir.library.databinding.ActivityLoginBinding;
import com.android.batdemir.library.ui.base.activity.BaseActivity;
import com.android.batdemir.library.ui.ui_app.activities.menu.MenuActivity;
import com.android.batdemir.library.ui.ui_app.activities.signup.SignUpActivity;
import com.android.batdemir.mylibrary.connection.RetrofitClient;
import com.android.batdemir.mylibrary.tools.Tool;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginController> implements SpecConnectService.ConnectionServiceListener {

    @Override
    public void onCreated() {
        init(R.style.AppThemeActionBar, "login", 0f, false);
    }

    @Override
    public void getObjectReferences() {
        // Not Implemented
    }

    @Override
    public void loadData() {
        getBinding().editTextUsername.setText("");
        getBinding().editTextPassword.setText("");
    }

    @Override
    public void setListeners() {
        getBinding().btnLogin.setOnClickListener(v -> Tool.getInstance(getBinding().getRoot().getContext()).move(MenuActivity.class, true, false, null));

        getBinding().btnSignUp.setOnClickListener(v -> Tool.getInstance(getBinding().getRoot().getContext()).move(SignUpActivity.class, true, true, null));

        getBinding().forgot.setOnClickListener(v -> new SpecConnect().connect(getBinding().getRoot().getContext(), RetrofitClient.getInstance().create(IUser.class).get(), Operation.USER_GET.name()));
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
