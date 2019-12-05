package com.android.batdemir.library.ui.ui_test.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMaterialBinding;
import com.android.batdemir.library.ui.base.BaseActivity;

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
        String[] strings = new String[]{"item1", "item2", "item3", "item4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, strings);
        binding.autoCompleteOutLine.setAdapter(adapter);
        binding.autoCompleteFilled.setAdapter(adapter);
    }
}
