package com.android.batdemir.library.ui.ui_test.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.databinding.DataBindingUtil;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMaterialBinding;
import com.android.batdemir.library.models.User;
import com.android.batdemir.library.ui.base.BaseActivity;
import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mylibrary.tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class MaterialActivity extends BaseActivity {

    private Context context;
    private ActivityMaterialBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false, true, "Material", 16f, R.style.AppThemeActionBar);
    }

    @Override
    public void getObjectReferences() {
        context = this;
        if (binding == null)
            binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_material);
    }

    @Override
    public void loadData() {
        fillSpinners();
    }

    @SuppressLint("PrivateApi")
    @Override
    public void setListeners() {
        binding.btnPreviousPage.setOnClickListener(v -> Tool.getInstance(context).move(BarcodeActivity.class, false, false, null));
        binding.btnOutLinesValid.setOnClickListener(v -> MyAlertDialog.getInstance(binding.autoCompleteOutLine.isValid(false) ? binding.autoCompleteOutLine.getSelectedItemDescription() : "not found", MyAlertDialog.DialogStyle.SUCCESS).show(getSupportFragmentManager(), ""));
        binding.btnFilledValid.setOnClickListener(v -> MyAlertDialog.getInstance(binding.autoCompleteFilled.isValid(true) ? binding.autoCompleteFilled.getSelectedItemDescription() : "not found", MyAlertDialog.DialogStyle.SUCCESS).show(getSupportFragmentManager(), ""));
        binding.btnSpinnerValid.setOnClickListener(v -> MyAlertDialog.getInstance(binding.spinner.isValid(true) ? binding.spinner.getSelectedItemDescription() : "not found", MyAlertDialog.DialogStyle.SUCCESS).show(getSupportFragmentManager(), ""));
        binding.autoCompleteOutLine.setAutoOnItemSelected((model, position) -> Log.v("AutoItem", model.getDescription()));
    }

    private void fillSpinners() {
        String name = "bat";
        String pass = "demir";
        String email = "batdemir";
        List<User> userList = new ArrayList<>();
        for (int i = 10; i < 30; i++) {
            userList.add(new User(name + i, pass + i, email + i));
        }
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            strings.add(name + i);
        }
        try {
            binding.autoCompleteOutLine.fill(userList, User.class.getDeclaredField("username"), User.class.getDeclaredField("password"));
            binding.autoCompleteFilled.setTextAppearance(R.style.TextAppearance_AppCompat_Large);
            binding.autoCompleteFilled.fill(strings);
            binding.spinner.setTextAppearance(R.style.TextAppearance_AppCompat_Large);
            binding.spinner.fill(R.array.testing);

            binding.autoCompleteOutLine.setSelectByItemModel(userList.get(5));
        } catch (Exception e) {
            Log.e(MaterialActivity.class.getSimpleName(), e.getMessage());
        }
    }
}
