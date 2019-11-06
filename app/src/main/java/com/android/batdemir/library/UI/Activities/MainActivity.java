package com.android.batdemir.library.UI.Activities;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;

import androidx.databinding.DataBindingUtil;

import com.android.batdemir.library.App.Base.BaseActivity;
import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.mylibrary.Components.Dialog.MyAlertDialog;
import com.android.batdemir.mylibrary.Components.Dialog.MyAlertDialogListener;
import com.android.batdemir.mylibrary.Components.Spinner.SpinnerHelper;
import com.android.batdemir.mylibrary.Components.Spinner.SpinnerModel;
import com.android.batdemir.mylibrary.Tools.Tool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends BaseActivity implements
        MyAlertDialogListener {

    private ActivityMainBinding binding;
    private Context context;

    @Override
    public void getObjectReferences() {
        init(true, false, "Main", 16f);
        context = MainActivity.this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void loadData() {
        ArrayList<SpinnerModel> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SpinnerModel spinnerModel = new SpinnerModel(i, "test" + i, null);
            list.add(spinnerModel);
        }
        try {
            binding.mySpinner.fillSpinner(list, SpinnerModel.class.getDeclaredField("id"), SpinnerModel.class.getDeclaredField("description"));
        } catch (NoSuchFieldException e) {
            Log.e(MainActivity.class.getSimpleName(), e.getMessage());
        }

        SpinnerModel spinnerModel = new SpinnerModel(2, "test2", null);
        SpinnerHelper.getInstance(binding.mySpinner.getSpinner()).setSelectedItemByModel(new Gson().toJson(spinnerModel));
    }

    @Override
    public void setListeners() {
        binding.btnNextPage.setOnClickListener(v -> new Tool(context).move(RecyclerActivity.class, true, false, null));

        binding.btnDialogDefault.setOnClickListener(v -> MyAlertDialog.getInstance("Default", MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), "default"));

        binding.btnDialogEditText.setOnClickListener(v -> {
            MyAlertDialog myAlertDialog = MyAlertDialog.getInstance("EditText", MyAlertDialog.DialogStyle.INPUT);
            myAlertDialog.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            myAlertDialog.show(getSupportFragmentManager(), editText);

        });

        binding.btnDialogAction.setOnClickListener(v -> MyAlertDialog.getInstance("Action", MyAlertDialog.DialogStyle.ACTION).show(getSupportFragmentManager(), "action"));

        binding.btnValidSpinner.setOnClickListener(v -> MyAlertDialog.getInstance(String.valueOf(binding.mySpinner.isValid(true)), MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), "valid"));

        binding.myEditText.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                MyAlertDialog.getInstance(String.valueOf(binding.myEditText.isValid(true)), MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), editor);
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
    }

    String editor = "editor";
    String editor2 = "editor2";
    String editText = "editText";

    @Override
    public void dialogOk(MyAlertDialog myAlertDialog) {
        super.dialogOk(myAlertDialog);
        if (Objects.equals(myAlertDialog.getTag(), editor)) {
            MyAlertDialog.getInstance(String.valueOf(binding.myEditText.isValid(false)), MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), editor2);
        } else if (Objects.equals(myAlertDialog.getTag(), editText)) {
            String editTextValue = myAlertDialog.getEditText().getText().toString();
            MyAlertDialog.getInstance(editTextValue, MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), "editTextIn");
        }
    }

    @Override
    public void dialogCancel(MyAlertDialog myAlertDialog) {
        super.dialogCancel(myAlertDialog);
        if (Objects.equals(myAlertDialog.getTag(), editor)) {
            MyAlertDialog.getInstance(String.valueOf(binding.myEditText.isValid(false)), MyAlertDialog.DialogStyle.INFO).show(getSupportFragmentManager(), editor2);
        }
    }

    public void warningClick(View view) {
        MyAlertDialog.getInstance("Test", MyAlertDialog.DialogStyle.WARNING).show(getSupportFragmentManager(),"warning");
    }

    public void successClick(View view) {
        MyAlertDialog.getInstance("Test", MyAlertDialog.DialogStyle.SUCCESS).show(getSupportFragmentManager(),"success");
    }

    public void failedClick(View view) {
        MyAlertDialog.getInstance("Test", MyAlertDialog.DialogStyle.FAILED).show(getSupportFragmentManager(),"failed");
    }
}
