package com.android.batdemir.library.ui.ui_test.activities;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityBarcodeBinding;
import com.android.batdemir.library.ui.base.BaseActivity;
import com.android.batdemir.myscanner.DataWedge;
import com.android.batdemir.myscanner.Receiver;
import com.android.batdemir.myscanner.ReceiverListener;

public class BarcodeActivity extends BaseActivity implements
        ReceiverListener {

    private Context context;
    private ActivityBarcodeBinding binding;
    private Receiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false, true, getString(R.string.title_barcode), R.style.AppThemeActionBar);
    }

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
    public void getObjectReferences() {
        context = this;
        if (binding == null)
            binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_barcode);
        DataWedge.getInstance(context).init();
    }

    @Override
    public void loadData() {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onReceived(String barcode) {
        binding.txtEditBarcode.setText(barcode);
    }
}
