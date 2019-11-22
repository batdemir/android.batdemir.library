package com.android.batdemir.library.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.android.batdemir.library.R;
import com.android.batdemir.library.ui.adapters.AdapterSectionsPager;
import com.android.batdemir.library.databinding.ActivityTabbedBinding;
import com.android.batdemir.mylibrary.tools.Tool;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

public class TabbedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;
        ActivityTabbedBinding binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_tabbed);
        AdapterSectionsPager adapterSectionsPager = new AdapterSectionsPager(context, getSupportFragmentManager());
        binding.viewPager.setAdapter(adapterSectionsPager);
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.fab.setOnClickListener(view -> Tool.getInstance(context).move(MapsActivity.class, true, false, null));
    }
}