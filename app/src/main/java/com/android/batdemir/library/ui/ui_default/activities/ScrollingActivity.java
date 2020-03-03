package com.android.batdemir.library.ui.ui_default.activities;

import android.content.Context;
import android.os.Bundle;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityScrollingBinding;
import com.android.batdemir.library.ui.base.BaseActivity;
import com.android.batdemir.mylibrary.tools.Tool;

public class ScrollingActivity extends BaseActivity {

    private Context context;
    private ActivityScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(true, false, "scrolling", 0f, R.style.AppThemeNoActionBar);
    }

    @Override
    public void getObjectReferences() {
        context = this;
        if (binding == null) {
            binding = ActivityScrollingBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
        }

    }

    @Override
    public void loadData() {
        setSupportActionBar(binding.toolbar);
    }

    @Override
    public void setListeners() {
        binding.fab.setOnClickListener(view -> Tool.getInstance(context).move(BottomNavigationActivity.class, true, true, null));
    }
}
