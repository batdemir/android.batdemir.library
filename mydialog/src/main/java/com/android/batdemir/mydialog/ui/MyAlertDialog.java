package com.android.batdemir.mydialog.ui;

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
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentManager;

import com.android.batdemir.mydialog.R;
import com.android.batdemir.mydialog.databinding.FragmentMyDialogBinding;
import com.android.batdemir.mydialog.listeners.MyAlertDialogButtonListener;
import com.android.batdemir.mydialog.listeners.MyAlertDialogCreator;
import com.android.batdemir.mydialog.listeners.MyAlertDialogEditTextListener;
import com.android.batdemir.mydialog.ui.base.BaseDialogFragment;

import java.util.Objects;

public class MyAlertDialog extends BaseDialogFragment {

    private static MyAlertDialog myAlertDialog = null;
    private static MyAlertDialogCreator myAlertDialogCreator = null;
    private static MyAlertDialogButtonListener myAlertDialogButtonListener = null;
    private static MyAlertDialogEditTextListener myAlertDialogEditTextListener = null;
    private static Builder builder = new Builder();

    private static final String KEY_TITLE_TEXT = "KEY_TITLE_TEXT";
    private static final String KEY_OK_TEXT = "KEY_OK_TEXT";
    private static final String KEY_CANCEL_TEXT = "KEY_CANCEL_TEXT";
    private static final String KEY_MESSAGE = "KEY_MESSAGE";
    private static final String KEY_STYLE = "KEY_STYLE";

    private int inputType = InputType.TYPE_CLASS_TEXT;

    private FragmentMyDialogBinding binding;
    private String title;
    private String okText;
    private String cancelText;
    private String message;
    private DialogStyle style;

    protected MyAlertDialog() {
    }

    private static synchronized MyAlertDialog init(Bundle bundle) {
        if (myAlertDialog == null) {
            if (myAlertDialogCreator == null) {
                myAlertDialog = new MyAlertDialog();
            } else {
                myAlertDialog = myAlertDialogCreator.create();
            }
        }
        myAlertDialog.setArguments(bundle);
        return myAlertDialog;
    }

    /**
     * @implNote Info Style
     * @param message Showing message
     */
    public static synchronized MyAlertDialog getInstance(String message) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_STYLE, DialogStyle.INFO.name());
        return init(bundle);
    }

    /**
     * @implNote Info Style
     * @param message Showing message text
     * @param okText Showing ok button text
     */
    public static synchronized MyAlertDialog getInstance(String message, String okText) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_OK_TEXT, okText);
        bundle.putString(KEY_STYLE, DialogStyle.INFO.name());
        return init(bundle);
    }

    /**
     * @param message Showing message text
     * @param dialogStyle Showing dialog style
     */
    public static synchronized MyAlertDialog getInstance(String message, DialogStyle dialogStyle) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_STYLE, dialogStyle.name());
        return init(bundle);
    }

    /**
     * @implNote Only working Action or Input Style
     * @param message Showing message text
     * @param okText Showing ok button text, override default ok name
     * @param cancelText Showing cancel button text, override default cancel name
     * @param dialogStyle Showing dialog style
     */
    public static synchronized MyAlertDialog getInstance(String message, String okText, String cancelText, DialogStyle dialogStyle) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_OK_TEXT, okText);
        bundle.putString(KEY_CANCEL_TEXT, cancelText);
        bundle.putString(KEY_STYLE, dialogStyle.name());
        return init(bundle);
    }

    /**
     * @param title Showing title text, override default title name
     * @param message Showing message text
     * @param dialogStyle Showing dialog style
     */
    public static synchronized MyAlertDialog getInstance(String title, String message, DialogStyle dialogStyle) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE_TEXT, title);
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_STYLE, dialogStyle.name());
        return init(bundle);
    }

    /**
     * @implNote Only working Action or Input Style
     * @param title Showing title text, override default title name
     * @param message Showing message text
     * @param okText Showing ok button text, override default ok name
     * @param cancelText Showing cancel button text, override default cancel name
     * @param dialogStyle Showing dialog style
     */
    public static synchronized MyAlertDialog getInstance(String title, String message, String okText, String cancelText, DialogStyle dialogStyle) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE_TEXT, title);
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_OK_TEXT, okText);
        bundle.putString(KEY_CANCEL_TEXT, cancelText);
        bundle.putString(KEY_STYLE, dialogStyle.name());
        return init(bundle);
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

    @Override
    public void getObjectReferences() {
        if (getArguments() == null)
            return;
        title = getArguments().getString(KEY_TITLE_TEXT);
        okText = getArguments().getString(KEY_OK_TEXT);
        cancelText = getArguments().getString(KEY_CANCEL_TEXT);
        message = getArguments().getString(KEY_MESSAGE);
        style = DialogStyle.valueOf(getArguments().getString(KEY_STYLE));
    }

    @Override
    public ViewDataBinding setBinding(LayoutInflater layoutInflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_my_dialog, container, false);
        return binding;
    }

    @Override
    public void loadData() {
        myAlertDialog.setCancelable(false);
        setComponentStyle();
        setComponentAnim();
    }

    @Override
    public void setListeners() {
        binding.editText.setOnEditorActionListener((v, actionId, event) -> {
            editTextListenerProcess(myAlertDialog);
            return false;
        });

        binding.editText.setOnKeyListener((v, keyCode, event) -> {
            editTextListenerProcess(myAlertDialog);
            return false;
        });

        binding.btnCancel.setOnClickListener(v -> {
            MyAlertDialog result = myAlertDialog;
            if (style == DialogStyle.INPUT) {
                myAlertDialog.dismiss();
                getMyAlertDialogEditTextListener(result).dialogCancelEditText(result, result.binding.editText);
            } else {
                myAlertDialog.dismiss();
                getMyAlertDialogButtonListener(result).dialogCancel(result);
            }
        });

        binding.btnOk.setOnClickListener(v -> {
            MyAlertDialog result = myAlertDialog;
            if (style == DialogStyle.INPUT) {
                editTextListenerProcess(myAlertDialog);
            } else {
                myAlertDialog.dismiss();
                getMyAlertDialogButtonListener(result).dialogOk(result);
            }
        });
    }

    //Functions

    private void editTextListenerProcess(MyAlertDialog result) {
        result.binding.editText.post(() -> {
            if (Objects.requireNonNull(result.binding.editText.getText()).toString().isEmpty()) {
                result.binding.editText.setError(builder.inputEmptyMessage);
            } else {
                myAlertDialog.dismiss();
                getMyAlertDialogEditTextListener(result).dialogOkEditText(result, result.binding.editText);
            }
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
            binding.rootMyDialog.startAnimation(animation);
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
            default:
                throw new IllegalStateException("Unexpected value: " + style);
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
            default:
                throw new IllegalStateException("Unexpected value: " + style);
        }
    }

    private void setShowMessage() {
        binding.txtEditMessage.setText(message);
        binding.txtEditMessage.post(() -> {
            if (binding.txtEditMessage.getLineCount() > 3) {
                binding.txtEditMessage.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
            }
        });
    }

    private void setShowEditText(DialogStyle style) {
        switch (style) {
            case INPUT:
                binding.inputLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                binding.editText.setVisibility(View.VISIBLE);
                binding.editText.requestFocus();
                binding.editText.setInputType(inputType != -1 ? getInputType() : inputType);
                break;
            case ACTION:
            case INFO:
            case FAILED:
            case SUCCESS:
            case WARNING:
            default:
                binding.inputLayout.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
                binding.editText.setVisibility(View.INVISIBLE);
                binding.editText.clearFocus();
                break;
        }
    }

    private void setShowCancelButton(DialogStyle style) {
        switch (style) {
            case INPUT:
            case ACTION:
                binding.btnCancel.setText(cancelText == null ? builder.cancelButtonText : cancelText);
                break;
            case INFO:
            case FAILED:
            case SUCCESS:
            case WARNING:
            default:
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                params.setMargins(0, 0, 0, 0);
                binding.btnCancel.setLayoutParams(params);
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                params1.setMargins(0, 0, 0, 0);
                binding.btnOk.setLayoutParams(params1);
                break;
        }
        binding.btnOk.setText(okText == null ? builder.okButtonText : okText);
    }

    //Component EditTextType

    private int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    //Component Properties

    public enum DialogStyle {
        WARNING,
        FAILED,
        SUCCESS,
        INPUT,
        ACTION,
        INFO
    }

    //Listeners

    private static MyAlertDialogButtonListener getMyAlertDialogButtonListener(MyAlertDialog result) {
        return myAlertDialogButtonListener == null ? (MyAlertDialogButtonListener) result.getActivity() : myAlertDialogButtonListener;
    }

    public static void setMyAlertDialogButtonListener(MyAlertDialogButtonListener myAlertDialogButtonListener) {
        MyAlertDialog.myAlertDialogButtonListener = myAlertDialogButtonListener;
    }

    private static MyAlertDialogEditTextListener getMyAlertDialogEditTextListener(MyAlertDialog result) {
        return myAlertDialogEditTextListener == null ? (MyAlertDialogEditTextListener) result.getActivity() : myAlertDialogEditTextListener;
    }

    public static void setMyAlertDialogEditTextListener(MyAlertDialogEditTextListener myAlertDialogEditTextListener) {
        MyAlertDialog.myAlertDialogEditTextListener = myAlertDialogEditTextListener;
    }

    public static void setMyAlertDialogCreator(MyAlertDialogCreator myAlertDialogCreator) {
        MyAlertDialog.myAlertDialogCreator = myAlertDialogCreator;
    }

    public static void setBuilder(Builder builder) {
        MyAlertDialog.builder = builder;
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
