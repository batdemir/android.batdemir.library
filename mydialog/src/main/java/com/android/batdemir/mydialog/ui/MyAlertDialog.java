package com.android.batdemir.mydialog.ui;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.batdemir.mydialog.R;
import com.android.batdemir.mydialog.databinding.FragmentMyDialogBinding;
import com.android.batdemir.mydialog.listeners.MyAlertDialogButtonListener;
import com.android.batdemir.mydialog.listeners.MyAlertDialogCreator;
import com.android.batdemir.mydialog.listeners.MyAlertDialogEditTextListener;

import java.io.Serializable;

@SuppressWarnings({"java:S1948", "java:S1450", "UnusedReturnValue", "ConstantConditions"})
public class MyAlertDialog extends DialogFragment {
    private Activity activity;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.activity = getActivity();
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
            if (builder.getStyle() == MyDialogStyle.INPUT && (builder.getShowEditText() == null || (builder.getShowEditText() != null && builder.getShowEditText()))) {
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
        binding.editText.clearFocus();

        binding.txtEditMessage.setText(builder.getMessage());
        binding.txtEditMessage.post(() -> {
            if (binding.txtEditMessage.getLineCount() > 3) {
                binding.txtEditMessage.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
            }
        });

        switch (builder.getStyle()) {
            case INFO:
                break;
            case INPUT:
                showEditText(true);
                showCancelButton(true);
                break;
            case ACTION:
                showCancelButton(true);
                break;
            case WARNING:
                binding.imgDialog.setBackgroundColor(getContext().getColor(R.color.alert_dialog_warning));
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_warning);
                binding.txtEditTitle.setText(getContext().getString(R.string.warningTitle));
                binding.txtEditTitle.setTextColor(getContext().getColor(R.color.alert_dialog_warning));
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(R.color.alert_dialog_warning)));
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

        if (!builder.getTitle().isEmpty())
            binding.txtEditTitle.setText(builder.getTitle());
        if (builder.getTitleColor() != 0)
            binding.txtEditTitle.setTextColor(builder.getTitleColor());

        if (!builder.getOkText().isEmpty())
            binding.btnOk.setText(builder.getOkText());
        if (builder.getOkTextColor() != 0)
            binding.btnOk.setTextColor(builder.getOkTextColor());
        if (builder.getOkBtnColor() != null)
            binding.btnOk.setBackgroundTintList(builder.getOkBtnColor());

        if (!builder.getCancelText().isEmpty())
            binding.btnCancel.setText(builder.getCancelText());
        if (builder.getCancelTextColor() != 0)
            binding.btnCancel.setTextColor(builder.getCancelTextColor());
        if (builder.getCancelBtnColor() != null)
            binding.btnCancel.setBackgroundTintList(builder.getCancelBtnColor());

        if (builder.getImgColor() != 0)
            binding.imgDialog.setBackgroundColor(builder.getImgColor());

        if (builder.getMessageColor() != 0)
            binding.txtEditMessage.setTextColor(builder.getMessageColor());

        if (builder.getShowEditText() != null)
            showEditText(builder.getShowEditText());

        if (builder.getShowCancelButton() != null)
            showCancelButton(builder.getShowCancelButton());
    }

    private void setComponentAnim() {
        try {
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
            binding.rootMyDialog.startAnimation(animation);
        } catch (Exception e) {
            Log.e(MyAlertDialog.class.getSimpleName(), e.getMessage());
        }
    }

    private void showEditText(boolean isShow) {
        ((LinearLayout.LayoutParams) binding.inputLayout.getLayoutParams()).width = isShow ? LinearLayout.LayoutParams.MATCH_PARENT : 0;
        ((LinearLayout.LayoutParams) binding.inputLayout.getLayoutParams()).height = isShow ? LinearLayout.LayoutParams.WRAP_CONTENT : 0;
        ((LinearLayout.LayoutParams) binding.inputLayout.getLayoutParams()).setMargins(
                isShow ? getResources().getDimensionPixelOffset(R.dimen.margin) : 0,
                isShow ? getResources().getDimensionPixelOffset(R.dimen.margin) : 0,
                isShow ? getResources().getDimensionPixelOffset(R.dimen.margin) : 0,
                isShow ? getResources().getDimensionPixelOffset(R.dimen.margin) : 0);
        binding.editText.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
        binding.editText.setInputType(builder.getInputType());
    }

    private void showCancelButton(boolean isShow) {
        ((LinearLayout.LayoutParams) binding.btnCancel.getLayoutParams()).width = isShow ? LinearLayout.LayoutParams.MATCH_PARENT : 0;
        ((LinearLayout.LayoutParams) binding.btnCancel.getLayoutParams()).height = isShow ? LinearLayout.LayoutParams.WRAP_CONTENT : 0;
        ((LinearLayout.LayoutParams) binding.btnCancel.getLayoutParams()).setMarginStart(isShow ? getResources().getDimensionPixelOffset(R.dimen.marginStart) : 0);
        ((LinearLayout.LayoutParams) binding.btnCancel.getLayoutParams()).setMarginEnd(isShow ? getResources().getDimensionPixelOffset(R.dimen.marginEnd) : 0);
    }

    //Listeners

    private MyAlertDialogButtonListener getMyAlertDialogButtonListener() {
        try {
            if (builder.getMyAlertDialogButtonListener() == null)
                builder.setMyAlertDialogButtonListener((MyAlertDialogButtonListener) activity);
        } catch (Exception e) {
            Log.e(MyAlertDialog.class.getSimpleName(), e.getMessage());
        }
        return builder.getMyAlertDialogButtonListener();
    }

    private MyAlertDialogEditTextListener getMyAlertDialogEditTextListener() {
        try {
            if (builder.getMyAlertDialogButtonListener() == null)
                builder.setMyAlertDialogEditTextListener((MyAlertDialogEditTextListener) activity);
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
        private @ColorInt
        int titleColor;
        private @ColorInt
        int imgColor;
        private @ColorInt
        int messageColor;
        private @ColorInt
        int okTextColor;
        private @ColorInt
        int cancelTextColor;
        private ColorStateList okBtnColor;
        private ColorStateList cancelBtnColor;
        private int inputType;
        private MyDialogStyle style;
        private Boolean showEditText;
        private Boolean showCancelButton;

        public Builder() {
            this.title = "";
            this.okText = "";
            this.cancelText = "";
            this.message = "";
            this.errorMessage = "";
            this.style = MyDialogStyle.INFO;
            this.inputType = InputType.TYPE_CLASS_TEXT;
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

        @ColorInt
        public int getTitleColor() {
            return titleColor;
        }

        public Builder setTitleColor(@ColorInt int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        @ColorInt
        public int getImgColor() {
            return imgColor;
        }

        public Builder setImgColor(@ColorInt int imgColor) {
            this.imgColor = imgColor;
            return this;
        }

        @ColorInt
        public int getMessageColor() {
            return messageColor;
        }

        public Builder setMessageColor(@ColorInt int messageColor) {
            this.messageColor = messageColor;
            return this;
        }

        @ColorInt
        public int getOkTextColor() {
            return okTextColor;
        }

        public Builder setOkTextColor(@ColorInt int okTextColor) {
            this.okTextColor = okTextColor;
            return this;
        }

        @ColorInt
        public int getCancelTextColor() {
            return cancelTextColor;
        }

        public Builder setCancelTextColor(@ColorInt int cancelTextColor) {
            this.cancelTextColor = cancelTextColor;
            return this;
        }

        public ColorStateList getOkBtnColor() {
            return okBtnColor;
        }

        public Builder setOkBtnColor(ColorStateList okBtnColor) {
            this.okBtnColor = okBtnColor;
            return this;
        }

        public ColorStateList getCancelBtnColor() {
            return cancelBtnColor;
        }

        public Builder setCancelBtnColor(ColorStateList cancelBtnColor) {
            this.cancelBtnColor = cancelBtnColor;
            return this;
        }

        public int getInputType() {
            return inputType;
        }

        public Builder setInputType(int inputType) {
            if (inputType == 0)
                this.inputType = InputType.TYPE_CLASS_TEXT;
            else
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

        public Boolean getShowEditText() {
            return showEditText;
        }

        public Builder setShowEditText(Boolean showEditText) {
            this.showEditText = showEditText;
            return this;
        }

        public Boolean getShowCancelButton() {
            return showCancelButton;
        }

        public Builder setShowCancelButton(Boolean showCancelButton) {
            this.showCancelButton = showCancelButton;
            return this;
        }
    }

}
