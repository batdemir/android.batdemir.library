package com.android.batdemir.mydialog.ui;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.batdemir.mydialog.R;
import com.android.batdemir.mydialog.databinding.FragmentMyDialogBinding;
import com.android.batdemir.mydialog.listeners.MyAlertDialogButtonListener;
import com.android.batdemir.mydialog.listeners.MyAlertDialogCreator;
import com.android.batdemir.mydialog.listeners.MyAlertDialogEditTextListener;

import java.io.Serializable;

@SuppressWarnings({"java:S1948", "unused", "UnusedReturnValue", "ConstantConditions"})
public class MyAlertDialog extends DialogFragment {

    private static final String KEY_BUILDER = "KEY_BUILDER";
    private static MyAlertDialog myAlertDialog = null;
    private static MyAlertDialogCreator myAlertDialogCreator = null;
    private FragmentMyDialogBinding binding;
    private Builder builder;

    private static MyAlertDialog init(Bundle bundle) {
        myAlertDialog = myAlertDialogCreator == null ? new MyAlertDialog() : myAlertDialogCreator.create();
        myAlertDialog.setArguments(bundle);
        return myAlertDialog;
    }

    public static synchronized MyAlertDialog getInstance(Builder builder) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_BUILDER, builder);
        return init(bundle);
    }

    //Fragment Methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth);
        getObjectReferences();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        synchronized (this) {
            binding = FragmentMyDialogBinding.inflate(inflater, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
        setListeners();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (getFragmentManager() != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    //Initialize Methods

    private void getObjectReferences() {
        if (getArguments() == null)
            return;
        builder = (Builder) getArguments().get(KEY_BUILDER);
    }

    private void loadData() {
        setCancelable(false);
        setComponentStyle();
        setComponentAnim();
    }

    private void setListeners() {
        binding.editText.setOnEditorActionListener((v, actionId, event) -> {
            editTextListenerProcess(actionId, event);
            return false;
        });

        binding.editText.setOnKeyListener((v, keyCode, event) -> {
            editTextListenerProcess(keyCode, event);
            return false;
        });

        binding.btnCancel.setOnClickListener(v -> {
            if (builder.getStyle() == MyDialogStyle.INPUT) {
                MyAlertDialog temp = myAlertDialog;
                dismiss();
                temp.getMyAlertDialogEditTextListener().dialogCancelEditText(temp, temp.binding.editText);
            } else {
                MyAlertDialog temp = myAlertDialog;
                dismiss();
                temp.getMyAlertDialogButtonListener().dialogCancel(temp);
            }
        });

        binding.btnOk.setOnClickListener(v -> {
            if (builder.getStyle() == MyDialogStyle.INPUT) {
                editTextListenerProcess(KeyEvent.KEYCODE_ENTER, new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
            } else {
                MyAlertDialog temp = myAlertDialog;
                dismiss();
                temp.getMyAlertDialogButtonListener().dialogOk(temp);
            }
        });
    }

    //Functions

    private void editTextListenerProcess(int action, KeyEvent event) {
        binding.editText.post(() -> {
            if ((event == null && action == EditorInfo.IME_ACTION_DONE) || (event != null && event.getAction() == KeyEvent.ACTION_DOWN && action == KeyEvent.KEYCODE_ENTER)) {
                if (binding.editText.getText() == null) {
                    dismiss();
                    return;
                }
                if (binding.editText.getText().toString().isEmpty()) {
                    binding.editText.setError(builder.errorMessage.isEmpty() ? getContext().getString(R.string.inputEmptyMessage) : builder.getErrorMessage());
                } else {
                    MyAlertDialog temp = myAlertDialog;
                    dismiss();
                    temp.getMyAlertDialogEditTextListener().dialogOkEditText(temp, temp.binding.editText);
                }
            }
        });
    }

    //Component Style

    private void setComponentStyle() {
        binding.txtEditMessage.setText(builder.getMessage());
        binding.txtEditMessage.post(() -> {
            if (binding.txtEditMessage.getLineCount() > 3) {
                binding.txtEditMessage.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
            }
        });
        binding.editText.clearFocus();
        switch (builder.getStyle()) {
            case INFO:
                binding.imgDialog.setBackgroundColor(getContext().getColor(R.color.alert_dialog_info));
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_info);
                binding.txtEditTitle.setText(getContext().getString(R.string.informationTitle));
                binding.txtEditTitle.setTextColor(getContext().getColor(R.color.alert_dialog_info));
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(R.color.alert_dialog_info)));
                break;
            case INPUT:
                binding.imgDialog.setBackgroundColor(getContext().getColor(R.color.alert_dialog_info));
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_info);
                binding.txtEditTitle.setText(getContext().getString(R.string.informationTitle));
                binding.txtEditTitle.setTextColor(getContext().getColor(R.color.alert_dialog_info));
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(R.color.alert_dialog_info)));
                ((ConstraintLayout.LayoutParams) binding.btnCancel.getLayoutParams()).matchConstraintPercentWidth = 0.5f;
                ((ConstraintLayout.LayoutParams) binding.btnOk.getLayoutParams()).matchConstraintPercentWidth = 0.5f;
                ((ConstraintLayout.LayoutParams) binding.inputLayout.getLayoutParams()).height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                binding.editText.setVisibility(View.VISIBLE);
                binding.editText.requestFocus();
                binding.editText.setInputType(builder.getInputType());
                break;
            case ACTION:
                binding.imgDialog.setBackgroundColor(getContext().getColor(R.color.alert_dialog_info));
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_info);
                binding.txtEditTitle.setText(getContext().getString(R.string.informationTitle));
                binding.txtEditTitle.setTextColor(getContext().getColor(R.color.alert_dialog_info));
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(R.color.alert_dialog_info)));
                binding.btnOk.setTextColor(getContext().getColor(android.R.color.white));
                ((ConstraintLayout.LayoutParams) binding.btnCancel.getLayoutParams()).matchConstraintPercentWidth = 0.5f;
                ((ConstraintLayout.LayoutParams) binding.btnOk.getLayoutParams()).matchConstraintPercentWidth = 0.5f;
                break;
            case WARNING:
                binding.imgDialog.setBackgroundColor(getContext().getColor(R.color.alert_dialog_warning));
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_warning);
                binding.txtEditTitle.setText(getContext().getString(R.string.warningTitle));
                binding.txtEditTitle.setTextColor(getContext().getColor(R.color.alert_dialog_warning));
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(R.color.alert_dialog_warning)));
                binding.btnOk.setTextColor(getContext().getColor(android.R.color.white));
                break;
            case SUCCESS:
                binding.imgDialog.setBackgroundColor(getContext().getColor(R.color.alert_dialog_success));
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_success);
                binding.txtEditTitle.setText(getContext().getString(R.string.successTitle));
                binding.txtEditTitle.setTextColor(getContext().getColor(R.color.alert_dialog_success));
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(R.color.alert_dialog_success)));
                break;
            case FAILED:
                binding.imgDialog.setBackgroundColor(getContext().getColor(R.color.alert_dialog_failed));
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_failed);
                binding.txtEditTitle.setText(getContext().getString(R.string.failedTitle));
                binding.txtEditTitle.setTextColor(getContext().getColor(R.color.alert_dialog_failed));
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(R.color.alert_dialog_failed)));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + builder.getStyle());
        }
        binding.btnOk.setText(builder.getOkText().isEmpty() ? getContext().getString(R.string.okButtonText) : builder.getOkText());
        binding.btnCancel.setText(builder.getCancelText().isEmpty() ? getContext().getString(R.string.cancelButtonText) : builder.getCancelText());
    }

    private void setComponentAnim() {
        try {
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
            binding.rootMyDialog.startAnimation(animation);
        } catch (Exception e) {
            Log.e(MyAlertDialog.class.getSimpleName(), e.getMessage());
        }
    }

    //Listeners

    private MyAlertDialogButtonListener getMyAlertDialogButtonListener() {
        try {
            if (builder.getMyAlertDialogButtonListener() == null)
                builder.setMyAlertDialogButtonListener((MyAlertDialogButtonListener) getActivity());
        } catch (Exception e) {
            Log.e(MyAlertDialog.class.getSimpleName(), e.getMessage());
        }
        return builder.getMyAlertDialogButtonListener();
    }

    private MyAlertDialogEditTextListener getMyAlertDialogEditTextListener() {
        try {
            if (builder.getMyAlertDialogButtonListener() == null)
                builder.setMyAlertDialogEditTextListener((MyAlertDialogEditTextListener) getActivity());
        } catch (Exception e) {
            Log.e(MyAlertDialog.class.getSimpleName(), e.getMessage());
        }
        return builder.getMyAlertDialogEditTextListener();
    }

    public static void setMyAlertDialogCreator(MyAlertDialogCreator myAlertDialogCreator) {
        MyAlertDialog.myAlertDialogCreator = myAlertDialogCreator;
    }

    //Builder

    public static class Builder implements Serializable {
        private MyAlertDialogButtonListener myAlertDialogButtonListener;
        private MyAlertDialogEditTextListener myAlertDialogEditTextListener;
        private String title;
        private String okText;
        private String cancelText;
        private String message;
        private String errorMessage;
        private int titleColor;
        private int imgColor;
        private int messageColor;
        private int okTextColor;
        private int cancelTextColor;
        private int okBtnColor;
        private int cancelBtnColor;
        private int inputType;
        private MyDialogStyle style;

        public Builder() {
            this.title = "";
            this.okText = "";
            this.cancelText = "";
            this.message = "";
            this.errorMessage = "";
            this.style = MyDialogStyle.INFO;
        }

        public MyAlertDialogButtonListener getMyAlertDialogButtonListener() {
            return myAlertDialogButtonListener;
        }

        public Builder setMyAlertDialogButtonListener(MyAlertDialogButtonListener myAlertDialogButtonListener) {
            this.myAlertDialogButtonListener = myAlertDialogButtonListener;
            return this;
        }

        public MyAlertDialogEditTextListener getMyAlertDialogEditTextListener() {
            return myAlertDialogEditTextListener;
        }

        public Builder setMyAlertDialogEditTextListener(MyAlertDialogEditTextListener myAlertDialogEditTextListener) {
            this.myAlertDialogEditTextListener = myAlertDialogEditTextListener;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getOkText() {
            return okText;
        }

        public Builder setOkText(String okText) {
            this.okText = okText;
            return this;
        }

        public String getCancelText() {
            return cancelText;
        }

        public Builder setCancelText(String cancelText) {
            this.cancelText = cancelText;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public Builder setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public int getTitleColor() {
            return titleColor;
        }

        public Builder setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public int getImgColor() {
            return imgColor;
        }

        public Builder setImgColor(int imgColor) {
            this.imgColor = imgColor;
            return this;
        }

        public int getMessageColor() {
            return messageColor;
        }

        public Builder setMessageColor(int messageColor) {
            this.messageColor = messageColor;
            return this;
        }

        public int getOkTextColor() {
            return okTextColor;
        }

        public Builder setOkTextColor(int okTextColor) {
            this.okTextColor = okTextColor;
            return this;
        }

        public int getCancelTextColor() {
            return cancelTextColor;
        }

        public Builder setCancelTextColor(int cancelTextColor) {
            this.cancelTextColor = cancelTextColor;
            return this;
        }

        public int getOkBtnColor() {
            return okBtnColor;
        }

        public Builder setOkBtnColor(int okBtnColor) {
            this.okBtnColor = okBtnColor;
            return this;
        }

        public int getCancelBtnColor() {
            return cancelBtnColor;
        }

        public Builder setCancelBtnColor(int cancelBtnColor) {
            this.cancelBtnColor = cancelBtnColor;
            return this;
        }

        public int getInputType() {
            return inputType;
        }

        public Builder setInputType(int inputType) {
            this.inputType = inputType;
            return this;
        }

        public MyDialogStyle getStyle() {
            return style;
        }

        public Builder setStyle(MyDialogStyle style) {
            this.style = style;
            return this;
        }

        public MyAlertDialog build() {
            return getInstance(this);
        }
    }

}
