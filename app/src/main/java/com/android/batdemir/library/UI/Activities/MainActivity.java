package com.android.batdemir.library.UI.Activities;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.mylibrary.Tools.Tool;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btnNextPage.setOnClickListener(v -> new Tool(context).move(RecyclerActivity.class, true, false, null));
    }
}
