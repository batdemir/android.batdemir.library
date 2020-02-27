package com.android.batdemir.library.ui.ui_test.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.library.ui.base.BaseActivity;
import com.android.batdemir.mydialog.listeners.MyAlertDialogEditTextListener;
import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.android.batdemir.mylibrary.tools.Tool;

import java.util.List;
import java.util.Objects;

public class MainActivity extends BaseActivity implements
        MyAlertDialogEditTextListener {

    private ActivityMainBinding binding;
    private Context context;

    private String tagEditor = "editor";
    private String tagEditor2 = "editor2";
    private String tagEditText = "editTextNumber";
    private String tagEditText2 = "editTextString";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(true, false, "Main", 16f, R.style.AppThemeActionBar);
    }

    @Override
    public void getObjectReferences() {
        context = MainActivity.this;
        if (binding == null)
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void loadData() {
        checkException();
    }

    public static boolean hasOpenedDialogs(FragmentActivity activity) {
        List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof MyAlertDialog) {
                return true;
            }
        }

        return false;
    }

    @SuppressLint("NewApi")
    @Override
    public void setListeners() {
        binding.btnNextPage.setOnClickListener(v -> Tool.getInstance(context).move(RecyclerActivity.class, true, true, null));

        binding.btnDialogDefault.setOnClickListener(v -> MyAlertDialog.getInstance(Html.fromHtml("<div style=\"text-align: start\"<p>Son Yapılan İşlem Başarız Oldu</p><p>Beklenmedik Hata Üzerine Kapatıldı.</p><p>Lütfen Yöneticiniz İle Görüşünüz.</p><p>Exception: baseUrl == null</p><p>------------------------------------------------------------</p><p>FileName: Utils.java</p><p>MethodName: checkNotNull</p><p>LineNumber: 304</p><p>------------------------------------------------------------</p><p>FileName: Retrofit.java</p><p>MethodName: baseUrl</p><p>LineNumber: 469</p><p>------------------------------------------------------------</p><p>FileName: RetrofitClient.java</p><p>MethodName: getInstance</p><p>LineNumber: 34</p><p>------------------------------------------------------------</p><p>FileName: RetrofitClient.java</p><p>MethodName: setBaseUrl</p><p>LineNumber: 46</p><p>------------------------------------------------------------</p><p>FileName: LoginActivity.java</p><p>MethodName: lambda$setListeners$0$LoginActivity</p><p>LineNumber: 59</p><p>------------------------------------------------------------</p><p>FileName: null</p><p>MethodName: onClick</p><p>LineNumber: 2</p><p>------------------------------------------------------------</p><p>FileName: View.java</p><p>MethodName: performClick</p><p>LineNumber: 7333</p><p>------------------------------------------------------------</p><p>FileName: TextView.java</p><p>MethodName: performClick</p><p>LineNumber: 14160</p><p>------------------------------------------------------------</p><p>FileName: View.java</p><p>MethodName: performClickInternal</p><p>LineNumber: 7299</p><p>------------------------------------------------------------</p><p>FileName: View.java</p><p>MethodName: access$3200</p><p>LineNumber: 846</p><p>------------------------------------------------------------</p><p>FileName: View.java</p><p>MethodName: run</p><p>LineNumber: 27773</p><p>------------------------------------------------------------</p><p>FileName: Handler.java</p><p>MethodName: handleCallback</p><p>LineNumber: 873</p><p>------------------------------------------------------------</p><p>FileName: Handler.java</p><p>MethodName: dispatchMessage</p><p>LineNumber: 99</p><p>------------------------------------------------------------</p><p>FileName: Looper.java</p><p>MethodName: loop</p><p>LineNumber: 214</p><p>------------------------------------------------------------</p><p>FileName: ActivityThread.java</p><p>MethodName: main</p><p>LineNumber: 6986</p><p>------------------------------------------------------------</p><p>FileName: RuntimeInit.java</p><p>MethodName: run</p><p>LineNumber: 494</p><p>------------------------------------------------------------</p><p>FileName: ZygoteInit.java</p><p>MethodName: main</p><p>LineNumber: 1445</p></div>", Html.FROM_HTML_MODE_LEGACY).toString(), MyDialogStyle.INFO).show(getSupportFragmentManager(), "default"));

        binding.btnDialogEditTextNumber.setOnClickListener(v ->
                MyAlertDialog.getInstance("TEST", getString(R.string.large_text), MyDialogStyle.INPUT)
                        .setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL)
                        .show(getSupportFragmentManager(), tagEditText));

        binding.btnDialogEditTextString.setOnClickListener(v -> {
            MyAlertDialog.getInstance("TEST", getString(R.string.confirm_e_mail), MyDialogStyle.INPUT)
                    .setInputType(InputType.TYPE_CLASS_TEXT)
                    .show(getSupportFragmentManager(), "");
            MyAlertDialog.getInstance("TEST1", getString(R.string.common_signin_button_text), MyDialogStyle.WARNING).show(getSupportFragmentManager(), "");
            MyAlertDialog.getInstance("TEST2", getString(R.string.large_text), MyDialogStyle.FAILED).show(getSupportFragmentManager(), "test");
        });

        binding.btnDialogAction.setOnClickListener(v -> MyAlertDialog.getInstance("Action", "ÇIK", "OK", MyDialogStyle.ACTION).show(getSupportFragmentManager(), "action"));

        binding.btnDialogWarning.setOnClickListener(v -> MyAlertDialog.getInstance("Warning", MyDialogStyle.WARNING).show(getSupportFragmentManager(), "warning"));

        binding.btnDialogSuccess.setOnClickListener(v -> MyAlertDialog.getInstance("Success", MyDialogStyle.SUCCESS).show(getSupportFragmentManager(), "success"));

        binding.btnDialogFailed.setOnClickListener(v -> MyAlertDialog.getInstance("Failed", MyDialogStyle.FAILED).show(getSupportFragmentManager(), "failed"));
    }

    @Override
    public void dialogOk(MyAlertDialog myAlertDialog) {
        super.dialogOk(myAlertDialog);
        if (Objects.equals(myAlertDialog.getTag(), tagEditor)) {
            MyAlertDialog.getInstance("Test", MyDialogStyle.INFO).show(getSupportFragmentManager(), tagEditor2);
            return;
        }
        if (Objects.equals(myAlertDialog.getTag(), "test")) {
            hasOpenedDialogs(this);
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
