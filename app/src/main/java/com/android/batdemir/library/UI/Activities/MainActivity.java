package com.android.batdemir.library.UI.Activities;

import android.content.Context;

import androidx.databinding.DataBindingUtil;

import com.android.batdemir.library.App.Base.BaseActivity;
import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.mylibrary.Components.MyAlertDialog;
import com.android.batdemir.mylibrary.Tools.Tool;

public class MainActivity extends BaseActivity implements
        MyAlertDialog.AlertClickListener {

    private ActivityMainBinding binding;
    private Context context;
    private static final String NON_AUTO_DISMISS = "nonAutoDismiss";

    @Override
    public void alertOkey(MyAlertDialog myAlertDialog) {
        super.alertOkey(myAlertDialog);
        if (myAlertDialog.getTag().equals(NON_AUTO_DISMISS)) {
            myAlertDialog.dismiss();
        }
    }

    @Override
    public void alertCancel(MyAlertDialog myAlertDialog) {
        super.alertCancel(myAlertDialog);
        if (myAlertDialog.getTag().equals(NON_AUTO_DISMISS))
            myAlertDialog.dismiss();
    }

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
            myAlertDialog.show(getSupportFragmentManager(), NON_AUTO_DISMISS);
        });

        binding.btnDialogAutoDismiss.setOnClickListener(v -> {
            MyAlertDialog myAlertDialog = MyAlertDialog.getInstance("NonEditTextCancelAutoDismiss");
            myAlertDialog.setAutoDismiss(true);
            myAlertDialog.setShowCancelButton(true);
            myAlertDialog.show(getSupportFragmentManager(), "autoDismiss");

        });
    }
}
