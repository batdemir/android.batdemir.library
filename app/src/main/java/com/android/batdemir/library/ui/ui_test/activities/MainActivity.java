package com.android.batdemir.library.ui.ui_test.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.library.ui.base.BaseActivity;
import com.android.batdemir.mydialog.listeners.MyAlertDialogEditTextListener;
import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mylibrary.components.spinner.SpinnerHelper;
import com.android.batdemir.mylibrary.components.spinner.SpinnerModel;
import com.android.batdemir.mylibrary.tools.Tool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends BaseActivity implements MyAlertDialogEditTextListener {

    private ActivityMainBinding binding;
    private Context context;

    private String tagEditor = "editor";
    private String tagEditor2 = "editor2";
    private String tagEditText = "editText";

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
        fillSpinner();
        setItemSpinner();
    }

    @SuppressLint("NewApi")
    @Override
    public void setListeners() {
        binding.btnNextPage.setOnClickListener(v -> Tool.getInstance(context).move(RecyclerActivity.class, true, true, null));

        binding.btnValidSpinner.setOnClickListener(v -> MyAlertDialog.getInstance(String.valueOf(binding.mySpinner.isValid(true)), MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), "valid"));

        binding.myEditText.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                MyAlertDialog.getInstance(String.valueOf(binding.myEditText.isValid(true)), MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), tagEditor);
                return true;
            }
            return false;
        });

        binding.mySpinner.getSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MyAlertDialog.getInstance(SpinnerHelper.getInstance(binding.mySpinner.getSpinner()).getSelectedItemModel(), MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), "selected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing
            }
        });

        binding.btnDialogDefault.setOnClickListener(v -> MyAlertDialog.getInstance(Html.fromHtml("<div style=\"text-align: start\"<p>Son Yapılan İşlem Başarız Oldu</p><p>Beklenmedik Hata Üzerine Kapatıldı.</p><p>Lütfen Yöneticiniz İle Görüşünüz.</p><p>Exception: baseUrl == null</p><p>------------------------------------------------------------</p><p>FileName: Utils.java</p><p>MethodName: checkNotNull</p><p>LineNumber: 304</p><p>------------------------------------------------------------</p><p>FileName: Retrofit.java</p><p>MethodName: baseUrl</p><p>LineNumber: 469</p><p>------------------------------------------------------------</p><p>FileName: RetrofitClient.java</p><p>MethodName: getInstance</p><p>LineNumber: 34</p><p>------------------------------------------------------------</p><p>FileName: RetrofitClient.java</p><p>MethodName: setBaseUrl</p><p>LineNumber: 46</p><p>------------------------------------------------------------</p><p>FileName: LoginActivity.java</p><p>MethodName: lambda$setListeners$0$LoginActivity</p><p>LineNumber: 59</p><p>------------------------------------------------------------</p><p>FileName: null</p><p>MethodName: onClick</p><p>LineNumber: 2</p><p>------------------------------------------------------------</p><p>FileName: View.java</p><p>MethodName: performClick</p><p>LineNumber: 7333</p><p>------------------------------------------------------------</p><p>FileName: TextView.java</p><p>MethodName: performClick</p><p>LineNumber: 14160</p><p>------------------------------------------------------------</p><p>FileName: View.java</p><p>MethodName: performClickInternal</p><p>LineNumber: 7299</p><p>------------------------------------------------------------</p><p>FileName: View.java</p><p>MethodName: access$3200</p><p>LineNumber: 846</p><p>------------------------------------------------------------</p><p>FileName: View.java</p><p>MethodName: run</p><p>LineNumber: 27773</p><p>------------------------------------------------------------</p><p>FileName: Handler.java</p><p>MethodName: handleCallback</p><p>LineNumber: 873</p><p>------------------------------------------------------------</p><p>FileName: Handler.java</p><p>MethodName: dispatchMessage</p><p>LineNumber: 99</p><p>------------------------------------------------------------</p><p>FileName: Looper.java</p><p>MethodName: loop</p><p>LineNumber: 214</p><p>------------------------------------------------------------</p><p>FileName: ActivityThread.java</p><p>MethodName: main</p><p>LineNumber: 6986</p><p>------------------------------------------------------------</p><p>FileName: RuntimeInit.java</p><p>MethodName: run</p><p>LineNumber: 494</p><p>------------------------------------------------------------</p><p>FileName: ZygoteInit.java</p><p>MethodName: main</p><p>LineNumber: 1445</p></div>", Html.FROM_HTML_MODE_LEGACY).toString(), MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), "default"));

        binding.btnDialogEditText.setOnClickListener(v -> {
            MyAlertDialog myAlertDialog = MyAlertDialog.getInstance("TEST", "EditText", MyAlertDialog.DialogStyle.INPUT);
            myAlertDialog.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            myAlertDialog.show(getSupportFragmentManager(), tagEditText);

        });

        binding.btnDialogAction.setOnClickListener(v -> MyAlertDialog.getInstance("Action", "ÇIK", "OK", MyAlertDialog.DialogStyle.ACTION).show(getSupportFragmentManager(), "action"));

        binding.btnDialogWarning.setOnClickListener(v -> MyAlertDialog.getInstance("Warning", MyAlertDialog.DialogStyle.WARNING).show(getSupportFragmentManager(), "warning"));

        binding.btnDialogSuccess.setOnClickListener(v -> MyAlertDialog.getInstance("Success", MyAlertDialog.DialogStyle.SUCCESS).show(getSupportFragmentManager(), "success"));

        binding.btnDialogFailed.setOnClickListener(v -> MyAlertDialog.getInstance("Failed", MyAlertDialog.DialogStyle.FAILED).show(getSupportFragmentManager(), "failed"));
    }

    @Override
    public void dialogOk(MyAlertDialog myAlertDialog) {
        super.dialogOk(myAlertDialog);
        if (Objects.equals(myAlertDialog.getTag(), tagEditor)) {
            MyAlertDialog.getInstance(String.valueOf(binding.myEditText.isValid(false)), MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), tagEditor2);
        }
    }

    @Override
    public void dialogCancel(MyAlertDialog myAlertDialog) {
        super.dialogCancel(myAlertDialog);
        if (Objects.equals(myAlertDialog.getTag(), tagEditor)) {
            MyAlertDialog.getInstance(String.valueOf(binding.myEditText.isValid(false)), MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), tagEditor2);
        }
    }

    private void fillSpinner() {
        List<SpinnerModel> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SpinnerModel spinnerModel = new SpinnerModel(i, "TEST" + i, null);
            list.add(spinnerModel);
        }
        try {
            binding.mySpinner.fillSpinner(list, SpinnerModel.class.getDeclaredField("id"), SpinnerModel.class.getDeclaredField("description"));
        } catch (Exception e) {
            Log.e(MainActivity.class.getSimpleName(), e.getMessage());
        }
    }

    private void setItemSpinner() {
        SpinnerModel spinnerModel = new SpinnerModel(2, "test2", null);
        SpinnerHelper.getInstance(binding.mySpinner.getSpinner()).setSelectedItemByModel(new Gson().toJson(spinnerModel));
    }

    @Override
    public void dialogOkEditText(MyAlertDialog myAlertDialog, EditText editText) {
        if (Objects.equals(myAlertDialog.getTag(), tagEditText)) {
            String editTextValue = editText.getText().toString();
            MyAlertDialog.getInstance(editTextValue, MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), "editTextIn");
        }
    }

    @Override
    public void dialogCancelEditText(MyAlertDialog myAlertDialog, EditText editText) {
        if (Objects.equals(myAlertDialog.getTag(), tagEditText)) {
            Log.v(editText.toString(), editText.getText().toString());
        }
    }
}
