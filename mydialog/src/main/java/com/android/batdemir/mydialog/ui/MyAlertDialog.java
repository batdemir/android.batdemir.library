package com.android.batdemir.mydialog.ui;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.android.batdemir.mydialog.R;
import com.android.batdemir.mydialog.databinding.FragmentMyDialogBinding;
import com.android.batdemir.mydialog.listeners.MyAlertDialogButtonListener;
import com.android.batdemir.mydialog.listeners.MyAlertDialogCreator;
import com.android.batdemir.mydialog.listeners.MyAlertDialogEditTextListener;

import java.util.Objects;

import static com.android.batdemir.mydialog.ui.MyDialogConstant.KEY_CANCEL_TEXT;
import static com.android.batdemir.mydialog.ui.MyDialogConstant.KEY_MESSAGE;
import static com.android.batdemir.mydialog.ui.MyDialogConstant.KEY_OK_TEXT;
import static com.android.batdemir.mydialog.ui.MyDialogConstant.KEY_STYLE;
import static com.android.batdemir.mydialog.ui.MyDialogConstant.KEY_TITLE_TEXT;
import static com.android.batdemir.mydialog.ui.MyDialogDefault.cancelButtonText;
import static com.android.batdemir.mydialog.ui.MyDialogDefault.failedTitle;
import static com.android.batdemir.mydialog.ui.MyDialogDefault.failedTitleColor;
import static com.android.batdemir.mydialog.ui.MyDialogDefault.informationTitle;
import static com.android.batdemir.mydialog.ui.MyDialogDefault.informationTitleColor;
import static com.android.batdemir.mydialog.ui.MyDialogDefault.inputEmptyMessage;
import static com.android.batdemir.mydialog.ui.MyDialogDefault.okButtonText;
import static com.android.batdemir.mydialog.ui.MyDialogDefault.successTitle;
import static com.android.batdemir.mydialog.ui.MyDialogDefault.successTitleColor;
import static com.android.batdemir.mydialog.ui.MyDialogDefault.warningTitle;
import static com.android.batdemir.mydialog.ui.MyDialogDefault.warningTitleColor;

public class MyAlertDialog extends DialogFragment {

    private static MyAlertDialog myAlertDialog = null;
    private static MyAlertDialogCreator myAlertDialogCreator = null;

    private FragmentMyDialogBinding binding;
    private String title;
    private String okText;
    private String cancelText;
    private String message;
    private MyDialogStyle style;
    private int inputType = InputType.TYPE_CLASS_TEXT;
    private MyAlertDialogButtonListener myAlertDialogButtonListener = null;
    private MyAlertDialogEditTextListener myAlertDialogEditTextListener = null;

    private static MyAlertDialog init(Bundle bundle) {
        myAlertDialog = myAlertDialogCreator == null ? new MyAlertDialog() : myAlertDialogCreator.create();
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
        bundle.putString(KEY_STYLE, MyDialogStyle.INFO.name());
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
        bundle.putString(KEY_STYLE, MyDialogStyle.INFO.name());
        return init(bundle);
    }

    /**
     * @param message Showing message text
     * @param dialogStyle Showing dialog style
     */
    public static synchronized MyAlertDialog getInstance(String message, MyDialogStyle dialogStyle) {
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
    public static synchronized MyAlertDialog getInstance(String message, String okText, String cancelText, MyDialogStyle dialogStyle) {
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
    public static synchronized MyAlertDialog getInstance(String title, String message, MyDialogStyle dialogStyle) {
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
    public static synchronized MyAlertDialog getInstance(String title, String message, String okText, String cancelText, MyDialogStyle dialogStyle) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE_TEXT, title);
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_OK_TEXT, okText);
        bundle.putString(KEY_CANCEL_TEXT, cancelText);
        bundle.putString(KEY_STYLE, dialogStyle.name());
        return init(bundle);
    }

    //Fragment Methods

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth);
        getObjectReferences();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        synchronized (this) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_my_dialog, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
        setListeners();
    }

    //Initialize Methods

    private void getObjectReferences() {
        if (getArguments() == null)
            return;
        title = getArguments().getString(KEY_TITLE_TEXT);
        okText = getArguments().getString(KEY_OK_TEXT);
        cancelText = getArguments().getString(KEY_CANCEL_TEXT);
        message = getArguments().getString(KEY_MESSAGE);
        style = MyDialogStyle.valueOf(getArguments().getString(KEY_STYLE));
        getMyAlertDialogButtonListener();
        getMyAlertDialogEditTextListener();
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
            if (style == MyDialogStyle.INPUT) {
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
            if (style == MyDialogStyle.INPUT) {
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
                if (Objects.requireNonNull(binding.editText.getText()).toString().isEmpty()) {
                    binding.editText.setError(inputEmptyMessage);
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
        setImageDialog(style);
        setShowTitle(style);
        setShowMessage();
        setShowEditText(style);
        setShowCancelButton(style);
    }

    private void setComponentAnim() {
        try {
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
            binding.rootMyDialog.startAnimation(animation);
        } catch (Exception e) {
            Log.e(MyAlertDialog.class.getSimpleName(), e.getMessage());
        }
    }

    private void setImageDialog(MyDialogStyle style) {
        switch (style) {
            case INPUT:
            case ACTION:
            case INFO:
                binding.imgDialog.setBackgroundColor(informationTitleColor);
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_info);
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(informationTitleColor));
                break;
            case WARNING:
                binding.imgDialog.setBackgroundColor(warningTitleColor);
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_warning);
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(warningTitleColor));
                break;
            case SUCCESS:
                binding.imgDialog.setBackgroundColor(successTitleColor);
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_success);
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(successTitleColor));
                break;
            case FAILED:
                binding.imgDialog.setBackgroundColor(failedTitleColor);
                binding.imgDialog.setImageResource(R.drawable.ic_dialog_failed);
                binding.btnOk.setBackgroundTintList(ColorStateList.valueOf(failedTitleColor));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + style);
        }
    }

    private void setShowTitle(MyDialogStyle style) {
        switch (style) {
            case INPUT:
            case ACTION:
            case INFO:
                binding.txtEditTitle.setText(title == null ? informationTitle : title);
                binding.txtEditTitle.setTextColor(informationTitleColor);
                break;
            case WARNING:
                binding.txtEditTitle.setText(title == null ? warningTitle : title);
                binding.txtEditTitle.setTextColor(warningTitleColor);
                break;
            case SUCCESS:
                binding.txtEditTitle.setText(title == null ? successTitle : title);
                binding.txtEditTitle.setTextColor(successTitleColor);
                break;
            case FAILED:
                binding.txtEditTitle.setText(title == null ? failedTitle : title);
                binding.txtEditTitle.setTextColor(failedTitleColor);
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

    private void setShowEditText(MyDialogStyle style) {
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

    private void setShowCancelButton(MyDialogStyle style) {
        switch (style) {
            case INPUT:
            case ACTION:
                binding.btnCancel.setText(cancelText == null ? cancelButtonText : cancelText);
                break;
            case INFO:
            case FAILED:
            case SUCCESS:
            case WARNING:
            default:
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0);
                params.setMargins(0, 0, 0, 0);
                binding.btnCancel.setLayoutParams(params);
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                params1.setMargins(0, 0, 0, 0);
                binding.btnOk.setLayoutParams(params1);
                break;
        }
        binding.btnOk.setText(okText == null ? okButtonText : okText);
    }

    //Component EditTextType

    private int getInputType() {
        return inputType;
    }

    public MyAlertDialog setInputType(int inputType) {
        this.inputType = inputType;
        return this;
    }

    //Listeners

    private MyAlertDialogButtonListener getMyAlertDialogButtonListener() {
        if (myAlertDialogButtonListener == null)
            myAlertDialogButtonListener = (MyAlertDialogButtonListener) getActivity();
        return myAlertDialogButtonListener;
    }

    private MyAlertDialogEditTextListener getMyAlertDialogEditTextListener() {
        if (myAlertDialogEditTextListener == null)
            myAlertDialogEditTextListener = (MyAlertDialogEditTextListener) getActivity();
        return myAlertDialogEditTextListener;
    }

    public static void setMyAlertDialogCreator(MyAlertDialogCreator myAlertDialogCreator) {
        MyAlertDialog.myAlertDialogCreator = myAlertDialogCreator;
    }
}
