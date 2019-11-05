package com.android.batdemir.mylibrary.Components.Dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
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
import com.android.batdemir.mylibrary.databinding.ComponentAlertDialogBinding;

import java.util.Objects;

@SuppressLint("ClickableViewAccessibility")
public class MyAlertDialog extends DialogFragment {

    private static MyAlertDialog myAlertDialog = null;
    private static MyAlertDialogCreator myAlertDialogCreator = null;

    private static final String KEY_MESSAGE = "KEY_MESSAGE";
    private ComponentAlertDialogBinding binding;
    private String message;

    protected MyAlertDialog() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.component_alert_dialog, container, false);
        getObjectReferences();
        loadData();
        setListeners();
        return binding.getRoot();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        if (!myAlertDialog.isAdded())
            super.show(manager, tag);
    }

    @Override
    public void dismiss() {
        myAlertDialog.getEditText().setText("");
        myAlertDialog = null;
        super.dismiss();
    }

    public static synchronized MyAlertDialog getInstance(String message, DialogStyle dialogStyle) {
        if (myAlertDialog == null) {
            if (myAlertDialogCreator == null) {
                myAlertDialog = new MyAlertDialog();
            } else {
                myAlertDialog = myAlertDialogCreator.create();
            }
        }
        switch (dialogStyle) {
            case INPUT:
                myAlertDialog.setShowCancelButton(true);
                myAlertDialog.setShowEditText(true);
                break;
            case ACTION:
                myAlertDialog.setShowCancelButton(true);
                myAlertDialog.setShowEditText(false);
                break;
            case INFO:
            default:
                myAlertDialog.setShowCancelButton(false);
                myAlertDialog.setShowEditText(false);
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        myAlertDialog.setArguments(bundle);
        return myAlertDialog;
    }

    public static void setMyAlertDialogCreator(MyAlertDialogCreator myAlertDialogCreator) {
        MyAlertDialog.myAlertDialogCreator = myAlertDialogCreator;
    }

    private void getObjectReferences() {
        assert getArguments() != null;
        message = getArguments().getString(KEY_MESSAGE);
        new Tool(getContext()).animDialog(binding.cardView);
    }

    private void loadData() {
        myAlertDialog.setCancelable(false);

        if (myAlertDialog.isShowEditText()) {
            binding.editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            binding.editText.setVisibility(View.VISIBLE);
            binding.txtEditMessage.setText(message);
        } else {
            String newLine = "\n";
            binding.editText.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
            binding.editText.setVisibility(View.INVISIBLE);
            binding.txtEditMessage.setText(String.format("%s%s", message, newLine));
        }

        if (myAlertDialog.isShowCancelButton()) {
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

        if (myAlertDialog.getInputType() != -1) {
            binding.editText.setInputType(myAlertDialog.getInputType());
        }
    }

    private void setListeners() {
        binding.btnOkey.setOnTouchListener(new OnTouchEvent(binding.btnOkey));
        binding.btnCancel.setOnTouchListener(new OnTouchEvent(binding.btnCancel));

        binding.btnCancel.setOnClickListener(v -> {
            MyAlertDialog result = myAlertDialog;
            myAlertDialog.dismiss();
            MyAlertDialogListener clickCancel = (MyAlertDialogListener) getActivity();
            Objects.requireNonNull(clickCancel).dialogCancel(result);
        });

        binding.btnOkey.setOnClickListener(v -> {
            MyAlertDialog result = myAlertDialog;
            myAlertDialog.dismiss();
            MyAlertDialogListener clickOk = (MyAlertDialogListener) getActivity();
            Objects.requireNonNull(clickOk).dialogOk(result);
        });
    }

    //Component Set Props

    private boolean showCancelButton = false;
    private boolean showEditText = false;
    private int inputType = InputType.TYPE_CLASS_TEXT;

    private boolean isShowCancelButton() {
        return showCancelButton;
    }

    private boolean isShowEditText() {
        return showEditText;
    }

    private int getInputType() {
        return inputType;
    }

    public void setShowCancelButton(boolean showCancelButton) {
        this.showCancelButton = showCancelButton;
    }

    public void setShowEditText(boolean showEditText) {
        this.showEditText = showEditText;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    //Component Get Props

    public String getMessage() {
        return message;
    }

    public EditText getEditText() {
        return binding.editText;
    }

    //Component Props

    public enum DialogStyle {
        INPUT,
        ACTION,
        INFO;
    }

    public interface MyAlertDialogCreator {
        MyAlertDialog create();
    }
}
