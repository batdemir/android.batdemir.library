package com.android.batdemir.library.ui.base;

import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.android.batdemir.library.R;
import com.android.batdemir.mydialog.listeners.MyAlertDialogButtonListener;
import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;

import java.util.Objects;

public abstract class BaseActivity extends AppCompatActivity implements
        BaseActions,
        MyAlertDialogButtonListener {

    private boolean isFirstActivity;

    @Override
    protected void onStart() {
        super.onStart();
        runOnUiThread(this::getObjectReferences);
    }

    @Override
    protected void onResume() {
        super.onResume();
        runOnUiThread(() -> {
            loadData();
            setListeners();
        });
    }

    @Override
    public void onBackPressed() {
        if (isFirstActivity) {
            MyAlertDialog.getInstance(getString(R.string.alert_dialog_key_exit), MyDialogStyle.ACTION).show(getSupportFragmentManager(), getString(R.string.alert_dialog_key_exit));
            return;
        } else {
            finish();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(com.android.batdemir.mylibrary.R.anim.slide_in_right, com.android.batdemir.mylibrary.R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void dialogOk(MyAlertDialog myAlertDialog) {
        if (Objects.equals(myAlertDialog.getTag(), getString(R.string.alert_dialog_key_exit))) {
            finishAffinity();
            finishAndRemoveTask();
        }
    }

    @Override
    public void dialogCancel(MyAlertDialog myAlertDialog) {
        if (Objects.equals(myAlertDialog.getTag(), getString(R.string.alert_dialog_key_exit))) {
            Log.v(BaseActivity.class.getSimpleName(), "exitCancel");
        }
    }

    public void init(boolean isFirstActivity, boolean showHomeButton, String title, float elevation) {
        this.isFirstActivity = isFirstActivity;
        setTheme(R.style.AppThemeActionBar);
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setElevation(elevation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeButton);
    }

    public void init(boolean isFirstActivity, boolean showHomeButton, String title, float elevation, int theme) {
        this.isFirstActivity = isFirstActivity;
        setTheme(theme);
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setElevation(elevation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeButton);
    }
}
