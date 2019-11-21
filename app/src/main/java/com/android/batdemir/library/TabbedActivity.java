package com.android.batdemir.library;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.android.batdemir.library.UI.Activities.RecyclerActivity;
import com.android.batdemir.library.UI.main.SectionsPagerAdapter;
import com.android.batdemir.library.databinding.ActivityTabbedBinding;
import com.android.batdemir.mylibrary.Tools.Tool;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

public class TabbedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;
        ActivityTabbedBinding binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_tabbed);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(context, getSupportFragmentManager());
        binding.viewPager.setAdapter(sectionsPagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.fab.setOnClickListener(view -> Tool.getInstance(context).move(RecyclerActivity.class, true, false, null));
    }
}