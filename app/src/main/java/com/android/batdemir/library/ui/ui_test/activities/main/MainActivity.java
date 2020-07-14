package com.android.batdemir.library.ui.ui_test.activities.main;

import android.content.res.ColorStateList;
import android.text.InputType;
import android.widget.EditText;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.library.ui.base.activity.BaseActivity;
import com.android.batdemir.library.ui.ui_test.activities.recycler.RecyclerActivity;
import com.android.batdemir.mydialog.listeners.MyAlertDialogEditTextListener;
import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.android.batdemir.mylibrary.tools.Tool;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainController> {

    @Override
    public void onCreated() {
        init(R.style.AppThemeActionBar, "Main", 16f, false, true);
    }

    @Override
    public void getObjectReferences() {
        //Nothing
    }

    @Override
    public void loadData() {
        checkException();
    }

    @Override
    public void setListeners() {
        getBinding().btnNextPage.setOnClickListener(v -> Tool.getInstance(getBinding().getRoot().getContext()).move(RecyclerActivity.class, true, true, null));

        getBinding().btnDialogDefaultShortText.setOnClickListener(v ->
                new MyAlertDialog
                        .Builder()
                        .setMessage(getString(R.string.informationTitle))
                        .build()
                        .show(getSupportFragmentManager(), "")
        );

        getBinding().btnDialogDefault.setOnClickListener(v ->
                new MyAlertDialog
                        .Builder()
                        .setMessage(getString(R.string.large_text))
                        .build()
                        .show(getSupportFragmentManager(), "")
        );

        getBinding().btnDialogEditTextNumber.setOnClickListener(v ->
                new MyAlertDialog
                        .Builder()
                        .setStyle(MyDialogStyle.INPUT)
                        .setMessage(getString(R.string.large_text))
                        .setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL)
                        .setMyAlertDialogEditTextListener(new MyAlertDialogEditTextListener() {
                            @Override
                            public void dialogOkEditText(MyAlertDialog myAlertDialog, EditText editText) {
                                Snackbar.make(getBinding().rootMain, editText.getText().toString(), Snackbar.LENGTH_SHORT).show();
                            }

                            @Override
                            public void dialogCancelEditText(MyAlertDialog myAlertDialog, EditText editText) {
                                Snackbar.make(getBinding().rootMain, getString(R.string.app_name), Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .build()
                        .show(getSupportFragmentManager(), "")
        );

        getBinding().btnDialogEditTextString.setOnClickListener(v ->
                new MyAlertDialog
                        .Builder()
                        .setStyle(MyDialogStyle.INPUT)
                        .setMessage(getString(R.string.large_text))
                        .setMyAlertDialogEditTextListener(new MyAlertDialogEditTextListener() {
                            @Override
                            public void dialogOkEditText(MyAlertDialog myAlertDialog, EditText editText) {
                                Snackbar.make(getBinding().rootMain, editText.getText().toString(), Snackbar.LENGTH_SHORT).show();
                            }

                            @Override
                            public void dialogCancelEditText(MyAlertDialog myAlertDialog, EditText editText) {
                                Snackbar.make(getBinding().rootMain, getString(R.string.app_name), Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .build()
                        .show(getSupportFragmentManager(), "")
        );

        getBinding().btnDialogAction.setOnClickListener(v ->
                new MyAlertDialog
                        .Builder()
                        .setStyle(MyDialogStyle.ACTION)
                        .setMessage(getString(R.string.large_text))
                        .build()
                        .show(getSupportFragmentManager(), "")
        );

        getBinding().btnDialogWarning.setOnClickListener(v ->
                new MyAlertDialog
                        .Builder()
                        .setStyle(MyDialogStyle.WARNING)
                        .setMessage(getString(R.string.large_text))
                        .build()
                        .show(getSupportFragmentManager(), "")
        );

        getBinding().btnDialogSuccess.setOnClickListener(v ->
                new MyAlertDialog
                        .Builder()
                        .setStyle(MyDialogStyle.SUCCESS)
                        .setMessage(getString(R.string.large_text))
                        .build()
                        .show(getSupportFragmentManager(), "")
        );

        getBinding().btnDialogFailed.setOnClickListener(v ->
                new MyAlertDialog
                        .Builder()
                        .setStyle(MyDialogStyle.FAILED)
                        .setMessage(getString(R.string.large_text))
                        .build()
                        .show(getSupportFragmentManager(), "")
        );

        getBinding().btnDialogCustom.setOnClickListener(v ->
                new MyAlertDialog
                        .Builder()

                        .setStyle(MyDialogStyle.INPUT)

                        .setTitle(getString(R.string.app_name))
                        .setTitleColor(getColor(R.color.agateBlue))

                        .setOkText(getString(R.string.app_name))
                        .setOkTextColor(getColor(R.color.carmineRed))
                        .setOkBtnColor(ColorStateList.valueOf(getColor(R.color.carrotOrange)))

                        .setCancelText(getString(R.string.app_name))
                        .setCancelTextColor(getColor(R.color.whiteSmoke))
                        .setCancelBtnColor(ColorStateList.valueOf(getColor(R.color.darkGunmetal_38)))

                        .setErrorMessage(getString(R.string.common_open_on_phone))

                        .setImgColor(getColor(R.color.tokyoDividerColor))

                        .setMessage(getString(R.string.elephant_path))
                        .setMessageColor(getColor(R.color.tokyoTextColorLink))

                        .setShowEditText(false)

                        .setShowCancelButton(false)

                        .setMyAlertDialogEditTextListener(new MyAlertDialogEditTextListener() {
                            @Override
                            public void dialogOkEditText(MyAlertDialog myAlertDialog, EditText editText) {
                                Snackbar.make(getBinding().rootMain, editText.getText().toString(), Snackbar.LENGTH_SHORT).show();
                            }

                            @Override
                            public void dialogCancelEditText(MyAlertDialog myAlertDialog, EditText editText) {
                                Snackbar.make(getBinding().rootMain, getString(R.string.app_name), Snackbar.LENGTH_SHORT).show();
                            }
                        })

                        .build()
                        .show(getSupportFragmentManager(), "")
        );
    }

    private void checkException() {
        String result = getIntent().getStringExtra("CRASH_REPORT");
        if (result != null && !result.isEmpty()) {
            new MyAlertDialog
                    .Builder()
                    .setStyle(MyDialogStyle.FAILED)
                    .setMessage(result)
                    .build()
                    .show(getSupportFragmentManager(), "");
        }
    }
}
