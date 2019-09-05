package com.android.batdemir.mylibrary.Components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.android.batdemir.mylibrary.R;
import com.android.batdemir.mylibrary.Tools.ButtonTools.OnTouchEvent;
import com.android.batdemir.mylibrary.Tools.Tool;
import com.android.batdemir.mylibrary.databinding.ViewMyAlertDialogBinding;

public class MyAlertDialog extends DialogFragment {

    private ViewMyAlertDialogBinding binding;
    private static final String key_message = "KEY_MESSAGE";
    private static final String key_showCancelButton = "KEY_SHOWCANCELBUTTON";
    private String message;
    private Boolean showCancelButton;

    public static MyAlertDialog newInstance(String message, boolean isCancelable, boolean showCancelButton) {
        MyAlertDialog toolAlertDialog = new MyAlertDialog();
        Bundle bundle = new Bundle();
        bundle.putString(key_message, message);
        bundle.putBoolean(key_showCancelButton, showCancelButton);
        toolAlertDialog.setArguments(bundle);
        toolAlertDialog.setCancelable(isCancelable);
        return toolAlertDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_my_alert_dialog, null, false);
        getObjectReferences();
        loadData();
        setListeners();
        return binding.getRoot();
    }

    public void getObjectReferences() {
        assert getArguments() != null;
        message = getArguments().getString(key_message);
        showCancelButton = getArguments().getBoolean(key_showCancelButton);
        new Tool(getContext()).animDialog(binding.cardView);
    }

    public void loadData() {
        binding.txtEditMessage.setText(message);
    }

    public void setListeners() {
        binding.btnOkey.setOnTouchListener(new OnTouchEvent(binding.btnOkey));
        if (showCancelButton) {
            binding.btnCancel.setOnTouchListener(new OnTouchEvent(binding.btnCancel));
        } else {
            binding.btnCancel.setLayoutParams(new LinearLayout.LayoutParams(0, 0, 0));
        }

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertClickListener clickCancel = (MyAlertDialog.AlertClickListener) getActivity();
                assert clickCancel != null;
                clickCancel.alertCancel();
                dismiss();
            }
        });

        binding.btnOkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertClickListener clickOkey = (MyAlertDialog.AlertClickListener) getActivity();
                assert clickOkey != null;
                clickOkey.alertOkey();
                dismiss();
            }
        });
    }

    public interface AlertClickListener {
        void alertOkey();

        void alertCancel();
    }
}
