package com.android.batdemir.library.ui.ui_test.activities.material;

import android.annotation.SuppressLint;
import android.util.Log;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMaterialBinding;
import com.android.batdemir.library.models.User;
import com.android.batdemir.library.ui.base.activity.BaseActivity;
import com.android.batdemir.library.ui.ui_test.activities.barcode.BarcodeActivity;
import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.android.batdemir.mylibrary.tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class MaterialActivity extends BaseActivity<ActivityMaterialBinding, MaterialController> {

    @Override
    public void onCreated() {
        init(R.style.AppThemeActionBar, "Material", 16f, true);
    }

    @Override
    public void getObjectReferences() {
        //Nothing
    }

    @Override
    public void loadData() {
        fillSpinners();
    }

    @SuppressLint("PrivateApi")
    @Override
    public void setListeners() {
        String notFound = "NOT FOUND";

        getBinding().btnPreviousPage.setOnClickListener(v -> Tool.getInstance(getBinding().getRoot().getContext()).move(BarcodeActivity.class, false, false, null));

        getBinding().btnOutLinesValid.setOnClickListener(v ->
                new MyAlertDialog
                        .Builder()
                        .setStyle(MyDialogStyle.SUCCESS)
                        .setMessage(getBinding().autoCompleteOutLine.isValid(false) ? getBinding().autoCompleteOutLine.getSelectedItemDescription() : notFound)
                        .build()
                        .show(getSupportFragmentManager(), ""));

        getBinding().btnFilledValid.setOnClickListener(v ->
                new MyAlertDialog
                        .Builder()
                        .setStyle(MyDialogStyle.SUCCESS)
                        .setMessage(getBinding().autoCompleteFilled.isValid(true) ? getBinding().autoCompleteFilled.getSelectedItemDescription() : notFound)
                        .build()
                        .show(getSupportFragmentManager(), ""));

        getBinding().btnSpinnerValid.setOnClickListener(v ->
                new MyAlertDialog
                        .Builder()
                        .setStyle(MyDialogStyle.SUCCESS)
                        .setMessage(getBinding().spinner.isValid(true) ? getBinding().spinner.getSelectedItemDescription() : notFound)
                        .build()
                        .show(getSupportFragmentManager(), ""));

        getBinding().autoCompleteOutLine.setAutoOnItemSelected((model, position) -> Log.v("AutoItem", model.getDescription()));
    }

    private void fillSpinners() {
        String name = "bat";
        String pass = "demir";
        String email = "batdemir";
        List<User> userList = new ArrayList<>();
        for (int i = 10; i < 30; i++) {
            userList.add(new User(name + i, pass + i, email + i));
        }
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            strings.add(name + i);
        }
        try {
            getBinding().autoCompleteOutLine.fill(userList, User.class.getDeclaredField("username"), User.class.getDeclaredField("password"));
            getBinding().autoCompleteFilled.setTextAppearance(R.style.TextAppearance_AppCompat_Large);
            getBinding().autoCompleteFilled.fill(strings);
            getBinding().spinner.setTextAppearance(R.style.TextAppearance_AppCompat_Large);
            getBinding().spinner.fill(R.array.testing);

            getBinding().autoCompleteOutLine.setSelectByItemModel(userList.get(5));
        } catch (Exception e) {
            Log.e(MaterialActivity.class.getSimpleName(), e.getMessage());
        }
    }
}
