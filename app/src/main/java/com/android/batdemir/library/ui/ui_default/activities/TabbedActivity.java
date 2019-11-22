package com.android.batdemir.library.ui.ui_default.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.android.batdemir.library.R;
import com.android.batdemir.library.ui.base.BaseActivity;
import com.android.batdemir.library.ui.ui_default.adapters.AdapterSectionsPager;
import com.android.batdemir.library.databinding.ActivityTabbedBinding;
import com.android.batdemir.mylibrary.tools.Tool;

import androidx.databinding.DataBindingUtil;

public class TabbedActivity extends BaseActivity {

    private Context context;
    private ActivityTabbedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false, false, "Tabbed", 0f, R.style.AppThemeNoActionBar);
    }

    @Override
    public void getObjectReferences() {
        context = this;
        if (binding == null)
            binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_tabbed);
    }

    @Override
    public void loadData() {
        AdapterSectionsPager adapterSectionsPager = new AdapterSectionsPager(context, getSupportFragmentManager());
        binding.viewPager.setAdapter(adapterSectionsPager);
        binding.tabs.setupWithViewPager(binding.viewPager);
    }

    @Override
    public void setListeners() {
        binding.fab.setOnClickListener(v -> Tool.getInstance(context).move(ScrollingActivity.class, true, true, null));
        binding.fab2.setOnClickListener(v -> Tool.getInstance(context).move(ScrollingActivity.class, true, false, null));
    }
}