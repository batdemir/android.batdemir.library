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
import com.android.batdemir.mylibrary.tools.spinner.SpinnerAdapter;
import com.android.batdemir.mylibrary.tools.spinner.SpinnerHelper;

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
        binding.autoCompleteOutLine.setOnItemClickListener((parent, view, position, id) -> Log.v("outLineClickItem:", binding.autoCompleteOutLine.getAdapter().getItem(position).toString()));
        binding.autoCompleteFilled.setOnItemClickListener((parent, view, position, id) -> Log.v("filledClickItem:", binding.autoCompleteFilled.getAdapter().getItem(position).toString()));
    }

    private void fillSpinners() {
        String name = "bat";
        String pass = "demir";
        String email = "batdemir";
        List<User> userList = new ArrayList<>();
        for (int i = 10; i < 30; i++) {
            userList.add(new User(name + i, pass + i, email + i));
        }
        SpinnerAdapter adapter = null;
        try {
            adapter = new SpinnerAdapter(
                    context,
                    SpinnerHelper.getInstance().cast(
                            userList,
                            User.class.getDeclaredField("username"),
                            User.class.getDeclaredField("password")
                    ),
                    SpinnerAdapter.SpinnerType.AUTO_COMPLETE_TEXT_VIEW
            );
        } catch (Exception e) {
            Log.e(MaterialActivity.class.getSimpleName(), e.getMessage());
        }
        binding.autoCompleteOutLine.setAdapter(adapter);
        binding.autoCompleteFilled.setAdapter(adapter);
        binding.spinner.setAdapter(adapter);
    }
}
