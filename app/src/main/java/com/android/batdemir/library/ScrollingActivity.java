package com.android.batdemir.library;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.android.batdemir.library.databinding.ActivityScrollingBinding;
import com.android.batdemir.mylibrary.Tools.Tool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;
        ActivityScrollingBinding binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_scrolling);

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(view -> Tool.getInstance(context).move(BottomNavigationActivity.class, true, false, null));
    }
}
