package com.android.batdemir.library.ui.ui_test.activities.barcode;

import android.content.IntentFilter;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityBarcodeBinding;
import com.android.batdemir.library.ui.base.activity.BaseActivity;
import com.android.batdemir.library.ui.ui_test.activities.material.MaterialActivity;
import com.android.batdemir.mylibrary.tools.Tool;
import com.android.batdemir.myscanner.DataWedge;
import com.android.batdemir.myscanner.Receiver;
import com.android.batdemir.myscanner.ReceiverListener;

public class BarcodeActivity extends BaseActivity<ActivityBarcodeBinding, BarcodeController> implements
        ReceiverListener {

    private Receiver receiver;

    @Override
    protected void onStart() {
        super.onStart();
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter();
        filter.addCategory(getResources().getString(R.string.data_wedge_default_category));
        filter.addAction(getResources().getString(R.string.data_wedge_intent_action_value));
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onCreated() {
        init(R.style.AppThemeActionBar, getString(R.string.title_barcode), 16f, true);
    }

    @Override
    public void getObjectReferences() {
        DataWedge.getInstance(getBinding().getRoot().getContext()).init();
    }

    @Override
    public void loadData() {
        getBinding().txtEditBarcode.setText("");
    }

    @Override
    public void setListeners() {
        getBinding().btnNextPage.setOnClickListener(v -> Tool.getInstance(getBinding().getRoot().getContext()).move(MaterialActivity.class, true, true, null));
    }

    @Override
    public void onReceived(String barcode) {
        getBinding().txtEditBarcode.setText(barcode);
    }
}
