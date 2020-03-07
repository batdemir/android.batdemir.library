package com.android.batdemir.library.ui.ui_test.activities.main;

import android.annotation.SuppressLint;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.library.ui.base.activity.BaseActivity;
import com.android.batdemir.library.ui.ui_test.activities.recycler.RecyclerActivity;
import com.android.batdemir.mydialog.listeners.MyAlertDialogEditTextListener;
import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.android.batdemir.mylibrary.tools.Tool;

import java.util.Objects;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainController> implements
        MyAlertDialogEditTextListener {

    private String tagEditor = "editor";
    private String tagEditor2 = "editor2";
    private String tagEditText = "editTextNumber";
    private String tagEditText2 = "editTextString";

    @Override
    public void onCreated() {
        init(R.style.AppThemeActionBar, "Main", 16f, false, true);
    }

    @Override
    public void getObjectReferences() {
        //Nothing
    }

    @Override
    public void loadData() {
        checkException();
    }

    @SuppressLint("NewApi")
    @Override
    public void setListeners() {
        getBinding().btnNextPage.setOnClickListener(v -> Tool.getInstance(getBinding().getRoot().getContext()).move(RecyclerActivity.class, true, true, null));

        getBinding().btnDialogDefault.setOnClickListener(v -> MyAlertDialog.getInstance(Html.fromHtml("<div style=\"text-align: start\"<p>Son Yapılan İşlem Başarız Oldu</p><p>Beklenmedik Hata Üzerine Kapatıldı.</p><p>Lütfen Yöneticiniz İle Görüşünüz.</p><p>Exception: baseUrl == null</p><p>------------------------------------------------------------</p><p>FileName: Utils.java</p><p>MethodName: checkNotNull</p><p>LineNumber: 304</p><p>------------------------------------------------------------</p><p>FileName: Retrofit.java</p><p>MethodName: baseUrl</p><p>LineNumber: 469</p><p>------------------------------------------------------------</p><p>FileName: RetrofitClient.java</p><p>MethodName: getInstance</p><p>LineNumber: 34</p><p>------------------------------------------------------------</p><p>FileName: RetrofitClient.java</p><p>MethodName: setBaseUrl</p><p>LineNumber: 46</p><p>------------------------------------------------------------</p><p>FileName: LoginActivity.java</p><p>MethodName: lambda$setListeners$0$LoginActivity</p><p>LineNumber: 59</p><p>------------------------------------------------------------</p><p>FileName: null</p><p>MethodName: onClick</p><p>LineNumber: 2</p><p>------------------------------------------------------------</p><p>FileName: View.java</p><p>MethodName: performClick</p><p>LineNumber: 7333</p><p>------------------------------------------------------------</p><p>FileName: TextView.java</p><p>MethodName: performClick</p><p>LineNumber: 14160</p><p>------------------------------------------------------------</p><p>FileName: View.java</p><p>MethodName: performClickInternal</p><p>LineNumber: 7299</p><p>------------------------------------------------------------</p><p>FileName: View.java</p><p>MethodName: access$3200</p><p>LineNumber: 846</p><p>------------------------------------------------------------</p><p>FileName: View.java</p><p>MethodName: run</p><p>LineNumber: 27773</p><p>------------------------------------------------------------</p><p>FileName: Handler.java</p><p>MethodName: handleCallback</p><p>LineNumber: 873</p><p>------------------------------------------------------------</p><p>FileName: Handler.java</p><p>MethodName: dispatchMessage</p><p>LineNumber: 99</p><p>------------------------------------------------------------</p><p>FileName: Looper.java</p><p>MethodName: loop</p><p>LineNumber: 214</p><p>------------------------------------------------------------</p><p>FileName: ActivityThread.java</p><p>MethodName: main</p><p>LineNumber: 6986</p><p>------------------------------------------------------------</p><p>FileName: RuntimeInit.java</p><p>MethodName: run</p><p>LineNumber: 494</p><p>------------------------------------------------------------</p><p>FileName: ZygoteInit.java</p><p>MethodName: main</p><p>LineNumber: 1445</p></div>", Html.FROM_HTML_MODE_LEGACY).toString(), MyDialogStyle.INFO).show(getSupportFragmentManager(), "default"));

        getBinding().btnDialogEditTextNumber.setOnClickListener(v ->
                MyAlertDialog.getInstance("TEST", getString(R.string.large_text), MyDialogStyle.INPUT)
                        .setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL)
                        .show(getSupportFragmentManager(), tagEditText));

        getBinding().btnDialogEditTextString.setOnClickListener(v -> {
            MyAlertDialog.getInstance("TEST", getString(R.string.confirm_e_mail), MyDialogStyle.INPUT)
                    .setInputType(InputType.TYPE_CLASS_TEXT)
                    .show(getSupportFragmentManager(), "");
            MyAlertDialog.getInstance("TEST1", getString(R.string.common_signin_button_text), MyDialogStyle.WARNING).show(getSupportFragmentManager(), "");
            MyAlertDialog.getInstance("TEST2", getString(R.string.large_text), MyDialogStyle.FAILED).show(getSupportFragmentManager(), "test");
        });

        getBinding().btnDialogAction.setOnClickListener(v -> MyAlertDialog.getInstance("Action", "ÇIK", "OK", MyDialogStyle.ACTION).show(getSupportFragmentManager(), "action"));

        getBinding().btnDialogWarning.setOnClickListener(v -> MyAlertDialog.getInstance("Warning", MyDialogStyle.WARNING).show(getSupportFragmentManager(), "warning"));

        getBinding().btnDialogSuccess.setOnClickListener(v -> MyAlertDialog.getInstance("Success", MyDialogStyle.SUCCESS).show(getSupportFragmentManager(), "success"));

        getBinding().btnDialogFailed.setOnClickListener(v -> MyAlertDialog.getInstance("Failed", MyDialogStyle.FAILED).show(getSupportFragmentManager(), "failed"));
    }

    @Override
    public void dialogOk(MyAlertDialog myAlertDialog) {
        super.dialogOk(myAlertDialog);
        if (Objects.equals(myAlertDialog.getTag(), tagEditor)) {
            MyAlertDialog.getInstance("Test", MyDialogStyle.INFO).show(getSupportFragmentManager(), tagEditor2);
            return;
        }
    }

    @Override
    public void dialogCancel(MyAlertDialog myAlertDialog) {
        super.dialogCancel(myAlertDialog);
        MyAlertDialog.getInstance("Test", MyDialogStyle.INFO).show(getSupportFragmentManager(), tagEditor2);
    }

    @Override
    public void dialogCancelEditText(MyAlertDialog myAlertDialog, EditText editText) {
        Log.v(editText.toString(), editText.getText().toString());
    }

    @Override
    public void dialogOkEditText(MyAlertDialog myAlertDialog, EditText editText) {
        if (Objects.equals(myAlertDialog.getTag(), tagEditText)) {
            String editTextValue = editText.getText().toString();
            MyAlertDialog.getInstance(editTextValue, MyDialogStyle.INFO).show(getSupportFragmentManager(), "editTextIn");
            return;
        }
        if (Objects.equals(myAlertDialog.getTag(), tagEditText2)) {
            String editTextValue = editText.getText().toString();
            MyAlertDialog.getInstance(editTextValue, MyDialogStyle.INFO).show(getSupportFragmentManager(), "editTextIn");
        }
    }

    private void checkException() {
        String result = getIntent().getStringExtra("CRASH_REPORT");
        if (result != null && !result.isEmpty()) {
            MyAlertDialog.getInstance(result, MyDialogStyle.FAILED).show(getSupportFragmentManager(), "exception");
        }
    }
}
