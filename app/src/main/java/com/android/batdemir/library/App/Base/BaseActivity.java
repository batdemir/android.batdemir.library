package com.android.batdemir.library.App.Base;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.batdemir.library.R;
import com.android.batdemir.mylibrary.Components.Dialog.MyAlertDialog;
import com.android.batdemir.mylibrary.Components.Dialog.MyAlertDialogListener;

public abstract class BaseActivity extends AppCompatActivity implements
        BaseActions,
        MyAlertDialogListener {

    private boolean isFirstActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getObjectReferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        setListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onBackPressed() {
        if (isFirstActivity) {
            MyAlertDialog.getInstance(getString(R.string.alert_dialog_key_exit), MyAlertDialog.DialogStyle.ACTION).show(getSupportFragmentManager(), getString(R.string.alert_dialog_key_exit));
            return;
        } else {
            finish();
        }
        super.onBackPressed();
    }

    @Override
    public void getObjectReferences() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void dialogOk(MyAlertDialog myAlertDialog) {
        if (myAlertDialog.getTag().equals(getString(R.string.alert_dialog_key_exit))) {
            finishAffinity();
            System.exit(0);
        }
    }

    @Override
    public void dialogCancel(MyAlertDialog myAlertDialog) {
        if (myAlertDialog.getTag().equals(getString(R.string.alert_dialog_key_exit))) {
            myAlertDialog.dismiss();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void init(boolean isFirstActivity, boolean showHomeButton, String title, float elevation) {
        this.isFirstActivity = isFirstActivity;
        setTheme(R.style.MenuTheme);
        getSupportActionBar();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setElevation(elevation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeButton);
    }
}
