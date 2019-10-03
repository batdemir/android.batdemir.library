package com.android.batdemir.library.UI.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.mylibrary.Components.MyAlertDialog;
import com.android.batdemir.mylibrary.Tools.Tool;

public class MainActivity extends AppCompatActivity implements MyAlertDialog.AlertClickListener {

    private ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btnNextPage.setOnClickListener(v -> new Tool(context).move(RecyclerActivity.class, true, false, null));

        binding.btnDialogDefault.setOnClickListener(v -> MyAlertDialog.getInstance("Default").show(getSupportFragmentManager(), "default"));

        binding.btnDialogEditText.setOnClickListener(v -> {
            MyAlertDialog myAlertDialog = MyAlertDialog.getInstance("EditText");
            myAlertDialog.setShowEditText(true);
            myAlertDialog.setShowCancelButton(false);
            myAlertDialog.show(getSupportFragmentManager(), "editText");
        });

        binding.btnDialogNonEditText.setOnClickListener(v -> {
            MyAlertDialog myAlertDialog = MyAlertDialog.getInstance("NonEditTextNonCancelButton");
            myAlertDialog.setShowEditText(false);
            myAlertDialog.show(getSupportFragmentManager(), "nonEditText");
        });

        binding.btnDialogNonAutoDismiss.setOnClickListener(v -> {
            MyAlertDialog myAlertDialog = MyAlertDialog.getInstance("NonEditTextNonCancelButtonNonAutoDismiss");
            myAlertDialog.setAutoDismiss(false);
            myAlertDialog.show(getSupportFragmentManager(), "nonAutoDismiss");
        });

        binding.btnDialogAutoDismiss.setOnClickListener(v -> {
            MyAlertDialog myAlertDialog = MyAlertDialog.getInstance("NonEditTextCancelAutoDismiss");
            myAlertDialog.setAutoDismiss(true);
            myAlertDialog.setShowCancelButton(true);
            myAlertDialog.show(getSupportFragmentManager(), "autoDismiss");

        });
    }

    @Override
    public void alertOkey(MyAlertDialog myAlertDialog) {
        if (myAlertDialog.getTag().equals("nonAutoDismiss"))
            myAlertDialog.dismiss();
    }

    @Override
    public void alertCancel(MyAlertDialog myAlertDialog) {
        if (myAlertDialog.getTag().equals("nonAutoDismiss"))
            myAlertDialog.dismiss();
    }
}
