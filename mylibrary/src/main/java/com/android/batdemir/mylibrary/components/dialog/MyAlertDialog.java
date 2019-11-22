package com.android.batdemir.mylibrary.components.dialog;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
    private static Builder builder = new Builder();

    private static final String KEY_TITLE_TEXT = "KEY_TITLE_TEXT";
    private static final String KEY_OK_TEXT = "KEY_OK_TEXT";
    private static final String KEY_CANCEL_TEXT = "KEY_CANCEL_TEXT";
    private static final String KEY_MESSAGE = "KEY_MESSAGE";
    private static final String KEY_STYLE = "KEY_STYLE";

    private ComponentAlertDialogBinding binding;
    private String title;
    private String okText;
    private String cancelText;
    private String message;
    private DialogStyle style;

    protected MyAlertDialog() {
    }

    /**
     * Info Style
     * Has not input view
     * Has not cancel button
     * Settable Message
     */
    public static synchronized MyAlertDialog getInstance(String message) {
        if (myAlertDialog == null) {
            if (myAlertDialogCreator == null) {
                myAlertDialog = new MyAlertDialog();
            } else {
                myAlertDialog = myAlertDialogCreator.create();
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_STYLE, DialogStyle.INFO.name());
        myAlertDialog.setArguments(bundle);
        return myAlertDialog;
    }

    /**
     * Info Style
     * Has not input view
     * Has not cancel button
     * Settable Message
     * Settable Ok Button Text
     */
    public static synchronized MyAlertDialog getInstance(String message, String okText) {
        if (myAlertDialog == null) {
            if (myAlertDialogCreator == null) {
                myAlertDialog = new MyAlertDialog();
            } else {
                myAlertDialog = myAlertDialogCreator.create();
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_OK_TEXT, okText);
        bundle.putString(KEY_STYLE, DialogStyle.INFO.name());
        myAlertDialog.setArguments(bundle);
        return myAlertDialog;
    }

    /**
     * Settable Message
     * Settable Dialog Style
     */
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

    /**
     * Settable Message
     * Settable Dialog Style
     * Settable Ok Button Text
     * Settable Cancel Button Text
     */
    public static synchronized MyAlertDialog getInstance(String message, String okText, String cancelText, DialogStyle dialogStyle) {
        if (myAlertDialog == null) {
            if (myAlertDialogCreator == null) {
                myAlertDialog = new MyAlertDialog();
            } else {
                myAlertDialog = myAlertDialogCreator.create();
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_OK_TEXT, okText);
        bundle.putString(KEY_CANCEL_TEXT, cancelText);
        bundle.putString(KEY_STYLE, dialogStyle.name());
        myAlertDialog.setArguments(bundle);
        return myAlertDialog;
    }

    /**
     * Settable Title Text
     * Settable Message
     * Settable Dialog Style
     */
    public static synchronized MyAlertDialog getInstance(String title, String message, DialogStyle dialogStyle) {
        if (myAlertDialog == null) {
            if (myAlertDialogCreator == null) {
                myAlertDialog = new MyAlertDialog();
            } else {
                myAlertDialog = myAlertDialogCreator.create();
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE_TEXT, title);
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_STYLE, dialogStyle.name());
        myAlertDialog.setArguments(bundle);
        return myAlertDialog;
    }

    /**
     * Settable Title Text
     * Settable Message
     * Settable Dialog Style
     * Settable Ok Button Text
     * Settable Cancel Button Text
     */
    public static synchronized MyAlertDialog getInstance(String title, String message, String okText, String cancelText, DialogStyle dialogStyle) {
        if (myAlertDialog == null) {
            if (myAlertDialogCreator == null) {
                myAlertDialog = new MyAlertDialog();
            } else {
                myAlertDialog = myAlertDialogCreator.create();
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE_TEXT, title);
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_OK_TEXT, okText);
        bundle.putString(KEY_CANCEL_TEXT, cancelText);
        bundle.putString(KEY_STYLE, dialogStyle.name());
        myAlertDialog.setArguments(bundle);
        return myAlertDialog;
    }

    public static void setMyAlertDialogCreator(MyAlertDialogCreator myAlertDialogCreator) {
        MyAlertDialog.myAlertDialogCreator = myAlertDialogCreator;
    }

    public static void setBuilder(Builder builder) {
        MyAlertDialog.builder = builder;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.component_alert_dialog, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        new Thread() {
            @Override
            public void run() {
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> getObjectReferences());
            }
        }.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread() {
            @Override
            public void run() {
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                    loadData();
                    setListeners();
                });
            }
        }.start();
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
        title = getArguments().getString(KEY_TITLE_TEXT);
        okText = getArguments().getString(KEY_OK_TEXT);
        cancelText = getArguments().getString(KEY_CANCEL_TEXT);
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
                Snackbar.make(binding.rootDialog, builder.inputEmptyMessage, Snackbar.LENGTH_SHORT).show();
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
                binding.imgDialog.setBackgroundColor(builder.informationTitleColor);
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_info);
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(builder.informationTitleColor));
                break;
            case WARNING:
                binding.imgDialog.setBackgroundColor(builder.warningTitleColor);
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_warning);
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(builder.warningTitleColor));
                break;
            case SUCCESS:
                binding.imgDialog.setBackgroundColor(builder.successTitleColor);
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_success);
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(builder.successTitleColor));
                break;
            case FAILED:
                binding.imgDialog.setBackgroundColor(builder.failedTitleColor);
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_failed);
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(builder.failedTitleColor));
                break;
        }
    }

    private void setShowTitle(DialogStyle style) {
        switch (style) {
            case INPUT:
            case ACTION:
            case INFO:
                binding.txtEditTitle.setText(title == null ? builder.informationTitle : title);
                binding.txtEditTitle.setTextColor(builder.informationTitleColor);
                break;
            case WARNING:
                binding.txtEditTitle.setText(title == null ? builder.warningTitle : title);
                binding.txtEditTitle.setTextColor(builder.warningTitleColor);
                break;
            case SUCCESS:
                binding.txtEditTitle.setText(title == null ? builder.successTitle : title);
                binding.txtEditTitle.setTextColor(builder.successTitleColor);
                break;
            case FAILED:
                binding.txtEditTitle.setText(title == null ? builder.failedTitle : title);
                binding.txtEditTitle.setTextColor(builder.failedTitleColor);
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
                binding.btnCancel.setText(cancelText == null ? builder.cancelButtonText : cancelText);
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
        binding.btnOk.setText(okText == null ? builder.okButtonText : okText);
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

    //Builder

    public static class Builder {
        private String informationTitle = "Bilgi";
        private int informationTitleColor = Color.parseColor("#4E55EB");
        private String warningTitle = "Uyarı";
        private int warningTitleColor = Color.parseColor("#FF9D00");
        private String successTitle = "Başarılı";
        private int successTitleColor = Color.parseColor("#03C9B8");
        private String failedTitle = "Başarısız";
        private int failedTitleColor = Color.parseColor("#FF0A00");
        private String inputEmptyMessage = "Lütfen Değer Giriniz.";
        private String cancelButtonText = "İptal";
        private String okButtonText = "Tamam";

        public Builder build() {
            return this;
        }

        public Builder setInformationTitle(String informationTitle) {
            this.informationTitle = informationTitle;
            return this;
        }

        public Builder setInformationTitleColor(int informationTitleColor) {
            this.informationTitleColor = informationTitleColor;
            return this;
        }

        public Builder setWarningTitle(String warningTitle) {
            this.warningTitle = warningTitle;
            return this;
        }

        public Builder setWarningTitleColor(int warningTitleColor) {
            this.warningTitleColor = warningTitleColor;
            return this;
        }

        public Builder setSuccessTitle(String successTitle) {
            this.successTitle = successTitle;
            return this;
        }

        public Builder setSuccessTitleColor(int successTitleColor) {
            this.successTitleColor = successTitleColor;
            return this;
        }

        public Builder setFailedTitle(String failedTitle) {
            this.failedTitle = failedTitle;
            return this;
        }

        public Builder setFailedTitleColor(int failedTitleColor) {
            this.failedTitleColor = failedTitleColor;
            return this;
        }

        public Builder setInputEmptyMessage(String inputEmptyMessage) {
            this.inputEmptyMessage = inputEmptyMessage;
            return this;
        }

        public Builder setCancelButtonText(String cancelButtonText) {
            this.cancelButtonText = cancelButtonText;
            return this;
        }

        public Builder setOkButtonText(String okButtonText) {
            this.okButtonText = okButtonText;
            return this;
        }
    }
}
