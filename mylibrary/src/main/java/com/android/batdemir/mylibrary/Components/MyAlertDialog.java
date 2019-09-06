package com.android.batdemir.mylibrary.Components;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.android.batdemir.mylibrary.R;
import com.android.batdemir.mylibrary.Tools.ButtonTools.OnTouchEvent;
import com.android.batdemir.mylibrary.Tools.Tool;
import com.android.batdemir.mylibrary.databinding.ViewMyAlertDialogBinding;
import com.google.gson.Gson;

public class MyAlertDialog extends DialogFragment {

    private ViewMyAlertDialogBinding binding;
    private static final String key_message = "KEY_MESSAGE";
    private static final String key_showCancelButton = "KEY_SHOWCANCELBUTTON";
    private String message;
    private boolean showCancelButton = false;
    private boolean showEditText = false;
    private MyAlertDialog myAlertDialog;

    public static MyAlertDialog newInstance(String message, boolean isCancelable, boolean showCancelButton) {
        MyAlertDialog myAlertDialog = new MyAlertDialog();
        Bundle bundle = new Bundle();
        bundle.putString(key_message, message);
        bundle.putBoolean(key_showCancelButton, showCancelButton);
        myAlertDialog.setArguments(bundle);
        myAlertDialog.setCancelable(isCancelable);
        return myAlertDialog;
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

    private void getObjectReferences() {
        assert getArguments() != null;
        myAlertDialog = this;
        message = getArguments().getString(key_message);
        showCancelButton = getArguments().getBoolean(key_showCancelButton);
        new Tool(getContext()).animDialog(binding.cardView);
    }

    private void loadData() {
        binding.txtEditMessage.setText(message);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
        binding.btnOkey.setOnTouchListener(new OnTouchEvent(binding.btnOkey));

        if (showCancelButton) {
            binding.btnCancel.setOnTouchListener(new OnTouchEvent(binding.btnCancel));
        } else {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0);
            layoutParams.setMargins(0, 0, 0, 0);
            binding.btnCancel.setLayoutParams(layoutParams);
            binding.btnCancel.setPadding(0, 0, 0, 0);
        }

        if (showEditText) {
            binding.editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        } else {
            binding.editText.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        }

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertClickListener clickCancel = (MyAlertDialog.AlertClickListener) getActivity();
                assert clickCancel != null;
                clickCancel.alertCancel(myAlertDialog);
                dismiss();
            }
        });

        binding.btnOkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertClickListener clickOkey = (MyAlertDialog.AlertClickListener) getActivity();
                assert clickOkey != null;
                clickOkey.alertOkey(myAlertDialog);
                dismiss();
            }
        });
    }

    public void setShowEditText(boolean showEditText) {
        this.showEditText = showEditText;
    }

    public String getMessage() {
        return message;
    }

    public EditText getEditText(){
        return binding.editText;
    }

    public interface AlertClickListener {
        void alertOkey(MyAlertDialog myAlertDialog);

        void alertCancel(MyAlertDialog myAlertDialog);
    }
}
