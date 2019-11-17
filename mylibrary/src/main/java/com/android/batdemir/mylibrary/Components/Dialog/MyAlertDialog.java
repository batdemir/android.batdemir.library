package com.android.batdemir.mylibrary.Components.Dialog;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.android.batdemir.mylibrary.R;
import com.android.batdemir.mylibrary.databinding.ComponentAlertDialogBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

@SuppressLint("ClickableViewAccessibility")
public class MyAlertDialog extends DialogFragment {

    private static MyAlertDialog myAlertDialog = null;
    private static MyAlertDialogCreator myAlertDialogCreator = null;

    private static final String KEY_TITLE = "KEY_TITLE";
    private static final String KEY_MESSAGE = "KEY_MESSAGE";
    private static final String KEY_STYLE = "KEY_STYLE";

    private ComponentAlertDialogBinding binding;
    private String title;
    private String message;
    private DialogStyle style;

    protected MyAlertDialog() {
    }

    public static synchronized MyAlertDialog getInstance(String title, String message, DialogStyle dialogStyle) {
        if (myAlertDialog == null) {
            if (myAlertDialogCreator == null) {
                myAlertDialog = new MyAlertDialog();
            } else {
                myAlertDialog = myAlertDialogCreator.create();
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_STYLE, dialogStyle.name());
        myAlertDialog.setArguments(bundle);
        return myAlertDialog;
    }

    public static synchronized MyAlertDialog getInstance(String message, DialogStyle dialogStyle) {
        if (myAlertDialog == null) {
            if (myAlertDialogCreator == null) {
                myAlertDialog = new MyAlertDialog();
            } else {
                myAlertDialog = myAlertDialogCreator.create();
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_STYLE, dialogStyle.name());
        myAlertDialog.setArguments(bundle);
        return myAlertDialog;
    }

    public static void setMyAlertDialogCreator(MyAlertDialogCreator myAlertDialogCreator) {
        MyAlertDialog.myAlertDialogCreator = myAlertDialogCreator;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.component_alert_dialog, container, false);
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
        myAlertDialog = null;
        super.dismiss();
    }

    //Initialize Methods

    private void getObjectReferences() {
        assert getArguments() != null;
        title = getArguments().getString(KEY_TITLE);
        message = getArguments().getString(KEY_MESSAGE);
        style = DialogStyle.valueOf(getArguments().getString(KEY_STYLE));
        setComponentStyle();
        setComponentAnim();
    }

    private void loadData() {
        myAlertDialog.setCancelable(false);

        if (myAlertDialog.getInputType() != -1) {
            binding.editText.setInputType(myAlertDialog.getInputType());
        }
    }

    private void setListeners() {
        binding.btnCancel.setOnClickListener(v -> {
            MyAlertDialog result = myAlertDialog;
            myAlertDialog.dismiss();
            MyAlertDialogListener clickCancel = (MyAlertDialogListener) getActivity();
            Objects.requireNonNull(clickCancel).dialogCancel(result);
        });

        binding.btnOk.setOnClickListener(v -> {
            MyAlertDialog result = myAlertDialog;
            if (style == DialogStyle.INPUT && result.getEditText().getText().toString().isEmpty())
                Snackbar.make(binding.rootDialog, getString(R.string.lutfen_deger_giriniz), Snackbar.LENGTH_SHORT).show();
            else
                myAlertDialog.dismiss();
            MyAlertDialogListener clickOk = (MyAlertDialogListener) getActivity();
            Objects.requireNonNull(clickOk).dialogOk(result);
        });
    }

    //Component Style

    private void setComponentStyle() {
        myAlertDialog.setImageDialog(style);
        myAlertDialog.setShowTitle(style);
        myAlertDialog.setShowMessage();
        myAlertDialog.setShowEditText(style);
        myAlertDialog.setShowCancelButton(style);
    }

    private void setComponentAnim() {
        try {
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
            binding.rootDialog.startAnimation(animation);
        } catch (Exception e) {
            Log.e(MyAlertDialog.class.getSimpleName(), e.getMessage());
        }
    }

    private void setImageDialog(DialogStyle style) {
        switch (style) {
            case INPUT:
            case ACTION:
            case INFO:
                binding.imgDialog.setBackgroundColor(getResources().getColor(R.color.alert_dialog_info, null));
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_info);
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.alert_dialog_info, null)));
                break;
            case WARNING:
                binding.imgDialog.setBackgroundColor(getResources().getColor(R.color.alert_dialog_warning, null));
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_warning);
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.alert_dialog_warning, null)));
                break;
            case SUCCESS:
                binding.imgDialog.setBackgroundColor(getResources().getColor(R.color.alert_dialog_success, null));
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_success);
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.alert_dialog_success, null)));
                break;
            case FAILED:
                binding.imgDialog.setBackgroundColor(getResources().getColor(R.color.alert_dialog_failed, null));
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_failed);
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.alert_dialog_failed, null)));
                break;
        }
    }

    private void setShowTitle(DialogStyle style) {
        switch (style) {
            case INPUT:
            case ACTION:
            case INFO:
                binding.txtEditTitle.setText(title == null ? getString(R.string.information) : title);
                binding.txtEditTitle.setTextColor(getResources().getColor(R.color.alert_dialog_info, null));
                break;
            case WARNING:
                binding.txtEditTitle.setText(title == null ? getString(R.string.warning) : title);
                binding.txtEditTitle.setTextColor(getResources().getColor(R.color.alert_dialog_warning, null));
                break;
            case SUCCESS:
                binding.txtEditTitle.setText(title == null ? getString(R.string.success) : title);
                binding.txtEditTitle.setTextColor(getResources().getColor(R.color.alert_dialog_success, null));
                break;
            case FAILED:
                binding.txtEditTitle.setText(title == null ? getString(R.string.failed) : title);
                binding.txtEditTitle.setTextColor(getResources().getColor(R.color.alert_dialog_failed, null));
                break;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 8, 8, 8);
        binding.txtEditTitle.setLayoutParams(layoutParams);
    }

    private void setShowMessage() {
        binding.txtEditMessage.setText(message);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(8, 8, 8, 8);
        binding.txtEditMessage.setLayoutParams(layoutParams);
        binding.txtEditMessage.post(() -> {
            if (binding.txtEditMessage.getLineCount() > 3) {
                binding.txtEditMessage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            }
        });
    }

    private void setShowEditText(DialogStyle style) {
        switch (style) {
            case INPUT:
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, 0);
                binding.editText.setLayoutParams(layoutParams);
                binding.editText.setVisibility(View.VISIBLE);
                break;
            case ACTION:
            case INFO:
            case FAILED:
            case SUCCESS:
            case WARNING:
            default:
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, 0);
                layoutParams2.setMargins(8, 8, 8, 8);
                binding.editText.setLayoutParams(layoutParams2);
                binding.editText.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void setShowCancelButton(DialogStyle style) {
        switch (style) {
            case INPUT:
            case ACTION:
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                layoutParams.setMargins(8, 8, 8, 8);
                binding.btnCancel.setLayoutParams(layoutParams);
                break;
            case INFO:
            case FAILED:
            case SUCCESS:
            case WARNING:
            default:
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0);
                layoutParams2.setMargins(0, 0, 0, 0);
                binding.btnCancel.setLayoutParams(layoutParams2);
                break;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        layoutParams.setMargins(8, 8, 8, 8);
        binding.btnOk.setLayoutParams(layoutParams);
    }

    //Component EditTextType

    private int inputType = InputType.TYPE_CLASS_TEXT;

    private int getInputType() {
        return inputType;
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

    //Component Properties

    public enum DialogStyle {
        WARNING,
        FAILED,
        SUCCESS,
        INPUT,
        ACTION,
        INFO;
    }

    public interface MyAlertDialogCreator {
        MyAlertDialog create();
    }
}
