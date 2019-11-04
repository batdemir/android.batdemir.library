package com.android.batdemir.library.UI.Activities;

import android.content.Context;
import android.text.InputType;

import androidx.databinding.DataBindingUtil;

import com.android.batdemir.library.App.Base.BaseActivity;
import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.mylibrary.Components.MyAlertDialog;
import com.android.batdemir.mylibrary.Components.MyAlertDialogListener;
import com.android.batdemir.mylibrary.Tools.Tool;

public class MainActivity extends BaseActivity implements
        MyAlertDialogListener {

    private ActivityMainBinding binding;
    private Context context;

    @Override
    public void getObjectReferences() {
        init(true, false, "Main", 16f);
        context = MainActivity.this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void loadData() {
        // Not Implemented
    }

    @Override
    public void setListeners() {
        binding.btnNextPage.setOnClickListener(v -> new Tool(context).move(RecyclerActivity.class, true, false, null));

        binding.btnDialogDefault.setOnClickListener(v -> MyAlertDialog.getInstance("Default", MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), "default"));

        binding.btnDialogEditText.setOnClickListener(v -> {
            MyAlertDialog myAlertDialog = MyAlertDialog.getInstance("EditText", MyAlertDialog.DialogStyle.INPUT);
            myAlertDialog.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            myAlertDialog.show(getSupportFragmentManager(), "editText");

        });

        binding.btnDialogAction.setOnClickListener(v -> {
            MyAlertDialog.getInstance("Action", MyAlertDialog.DialogStyle.ACTION).show(getSupportFragmentManager(), "action");
        });

    }
}
