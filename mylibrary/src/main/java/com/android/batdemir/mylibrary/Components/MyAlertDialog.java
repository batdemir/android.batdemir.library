package com.android.batdemir.mylibrary.Components;

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
import com.android.batdemir.mylibrary.databinding.ViewMyAlertDialogBinding;

@SuppressLint("ClickableViewAccessibility")
public class MyAlertDialog extends DialogFragment {

    private static MyAlertDialog myAlertDialog = null;
    private static MyAlertDialogCreator myAlertDialogCreator = null;

    private static final String KEY_MESSAGE = "KEY_MESSAGE";
    private ViewMyAlertDialogBinding binding;
    private ComponentProperty componentProperty;
    private String message;

    protected MyAlertDialog() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_my_alert_dialog, container, false);
        getObjectReferences();
        loadData();
        setListeners();
        return binding.getRoot();
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        if (!myAlertDialog.isAdded())
            super.show(manager, tag);
    }

    @Override
    public void dismiss() {
        if (myAlertDialog.isAdded())
            super.dismiss();
    }

    public static MyAlertDialog getInstance(String message) {
        if (myAlertDialog == null) {
            if (myAlertDialogCreator == null) {
                myAlertDialog = new MyAlertDialog();
            } else {
                myAlertDialog = myAlertDialogCreator.create();
            }
            if (myAlertDialog.getComponentProperty() == null) {
                myAlertDialog.setComponentProperty(new ComponentProperty(
                        true,
                        true,
                        false,
                        true,
                        InputType.TYPE_CLASS_TEXT
                ));
            }
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
        componentProperty = myAlertDialog.getComponentProperty();
        new Tool(getContext()).animDialog(binding.cardView);
    }

    private void loadData() {
        myAlertDialog.setCancelable(myAlertDialog.getComponentProperty().isCancelable());

        if (myAlertDialog.getComponentProperty().isShowEditText()) {
            binding.editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            binding.editText.setVisibility(View.VISIBLE);
            binding.txtEditMessage.setText(message);
        } else {
            String newLine = "\n";
            binding.editText.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
            binding.editText.setVisibility(View.INVISIBLE);
            binding.txtEditMessage.setText(String.format("%s%s", message, newLine));
        }

        if (myAlertDialog.getComponentProperty().isShowCancelButton()) {
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

        if (myAlertDialog.getComponentProperty().getInputType() != -1) {
            binding.editText.setInputType(myAlertDialog.getComponentProperty().getInputType());
        }
    }

    private void setListeners() {
        binding.btnOkey.setOnTouchListener(new OnTouchEvent(binding.btnOkey));
        binding.btnCancel.setOnTouchListener(new OnTouchEvent(binding.btnCancel));

        binding.btnCancel.setOnClickListener(v -> {
            MyAlertDialogListener clickCancel = (MyAlertDialogListener) getActivity();
            assert clickCancel != null;
            clickCancel.dialogCancel(myAlertDialog);
            if (myAlertDialog.getComponentProperty().isAutoDismiss())
                dismiss();
        });

        binding.btnOkey.setOnClickListener(v -> {
            MyAlertDialogListener clickOkey = (MyAlertDialogListener) getActivity();
            assert clickOkey != null;
            clickOkey.dialogOk(myAlertDialog);
            if (myAlertDialog.getComponentProperty().isAutoDismiss())
                dismiss();
        });
    }

    //Component Set Props

    public void setComponentProperty(ComponentProperty componentProperty) {
        this.componentProperty = componentProperty;
    }

    public void setIsCancelable(boolean isCancelable) {
        myAlertDialog.getComponentProperty().setCancelable(isCancelable);
    }

    public void setShowCancelButton(boolean showCancelButton) {
        myAlertDialog.getComponentProperty().setShowCancelButton(showCancelButton);
    }

    public void setAutoDismiss(boolean autoDismiss) {
        myAlertDialog.getComponentProperty().setAutoDismiss(autoDismiss);
    }

    public void setShowEditText(boolean showEditText) {
        myAlertDialog.getComponentProperty().setShowEditText(showEditText);
    }

    public void setEditTextInputType(int inputType) {
        myAlertDialog.getComponentProperty().setInputType(inputType);
    }

    //Component Get Props

    public String getMessage() {
        return message;
    }

    public EditText getEditText() {
        return binding.editText;
    }

    private ComponentProperty getComponentProperty() {
        return componentProperty;
    }

    //Component Props

    public static class ComponentProperty {
        private boolean isCancelable;
        private boolean showCancelButton;
        private boolean showEditText;
        private boolean autoDismiss;
        private int inputType;

        public ComponentProperty(boolean isCancelable, boolean showCancelButton, boolean showEditText, boolean autoDismiss, int inputType) {
            this.isCancelable = isCancelable;
            this.showCancelButton = showCancelButton;
            this.showEditText = showEditText;
            this.autoDismiss = autoDismiss;
            this.inputType = inputType;
        }

        private boolean isCancelable() {
            return isCancelable;
        }

        private void setCancelable(boolean cancelable) {
            isCancelable = cancelable;
        }

        private boolean isShowCancelButton() {
            return showCancelButton;
        }

        private void setShowCancelButton(boolean showCancelButton) {
            this.showCancelButton = showCancelButton;
        }

        private boolean isShowEditText() {
            return showEditText;
        }

        private void setShowEditText(boolean showEditText) {
            this.showEditText = showEditText;
        }

        private boolean isAutoDismiss() {
            return autoDismiss;
        }

        private void setAutoDismiss(boolean autoDismiss) {
            this.autoDismiss = autoDismiss;
        }

        private int getInputType() {
            return inputType;
        }

        private void setInputType(int inputType) {
            this.inputType = inputType;
        }
    }

    public static interface MyAlertDialogCreator {
        MyAlertDialog create();
    }
}
