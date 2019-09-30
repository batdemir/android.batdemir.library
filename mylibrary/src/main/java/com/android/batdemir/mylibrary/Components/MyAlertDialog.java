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
import androidx.fragment.app.FragmentManager;

import com.android.batdemir.mylibrary.R;
import com.android.batdemir.mylibrary.Tools.ButtonTools.OnTouchEvent;
import com.android.batdemir.mylibrary.Tools.Tool;
import com.android.batdemir.mylibrary.databinding.ViewMyAlertDialogBinding;
import com.google.gson.Gson;

public class MyAlertDialog extends DialogFragment {

    private static MyAlertDialog myAlertDialog = null;
    private ViewMyAlertDialogBinding binding;

    private static final String key_message = "KEY_MESSAGE";
    private String message;

    //Component Property
    private boolean isCancelable;
    private boolean showCancelButton;
    private boolean showEditText;
    private boolean autoDismiss;
    private int inputType;

    public static MyAlertDialog getInstance(String message) {
        if (myAlertDialog == null) {
            myAlertDialog = new MyAlertDialog();
        }
        Bundle bundle = new Bundle();
        bundle.putString(key_message, message);
        myAlertDialog.setArguments(bundle);
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
        message = getArguments().getString(key_message);
        new Tool(getContext()).animDialog(binding.cardView);
    }

    private void loadData() {

        myAlertDialog.setCancelable(isCancelable);

        if (showEditText) {
            binding.editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            binding.editText.setVisibility(View.VISIBLE);
            binding.txtEditMessage.setText(message);
        } else {
            String newLine = "\n";
            binding.editText.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
            binding.editText.setVisibility(View.INVISIBLE);
            binding.txtEditMessage.setText(String.format("%s%s", message, newLine));
        }

        if (showCancelButton) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            layoutParams.setMargins(8, 0, 8, 0);
            binding.btnCancel.setPadding(8, 8, 8, 8);
            binding.btnCancel.setLayoutParams(layoutParams);
        } else {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0);
            layoutParams.setMargins(0, 0, 0, 0);
            binding.btnCancel.setPadding(0, 0, 0, 0);
            binding.btnCancel.setLayoutParams(layoutParams);
        }

        if (inputType != -1) {
            binding.editText.setInputType(inputType);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
        binding.btnOkey.setOnTouchListener(new OnTouchEvent(binding.btnOkey));
        binding.btnCancel.setOnTouchListener(new OnTouchEvent(binding.btnCancel));

        binding.btnCancel.setOnClickListener(v -> {
            AlertClickListener clickCancel = (AlertClickListener) getActivity();
            assert clickCancel != null;
            clickCancel.alertCancel(myAlertDialog);
            if (autoDismiss)
                dismiss();
        });

        binding.btnOkey.setOnClickListener(v -> {
            AlertClickListener clickOkey = (AlertClickListener) getActivity();
            assert clickOkey != null;
            clickOkey.alertOkey(myAlertDialog);
            if (autoDismiss)
                dismiss();
        });
    }

    public void setIsCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
    }

    public void setShowCancelButton(boolean showCancelButton) {
        this.showCancelButton = showCancelButton;
    }

    public void setAutoDismiss(boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
    }

    public void setShowEditText(boolean showEditText) {
        this.showEditText = showEditText;
    }

    public void setEditTextInputType(int inputType) {
        this.inputType = inputType;
    }

    public String getMessage() {
        return message;
    }

    public EditText getEditText() {
        return binding.editText;
    }

    public interface AlertClickListener {
        void alertOkey(MyAlertDialog myAlertDialog);

        void alertCancel(MyAlertDialog myAlertDialog);
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        if (!myAlertDialog.isAdded())
            super.show(manager, tag);
    }
}
