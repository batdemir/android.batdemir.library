package com.android.batdemir.library;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.mylibrary.MyEditTextView;
import com.android.batdemir.mylibrary.MySpinnerView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.edittext.setConfirmativeCharCount(15);

        binding.btnOnOffSpinner.setSelected(false);

        binding.btnOnOffEditText.setSelected(false);

        binding.btnOnOffSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnOnOffSpinner.setSelected(!binding.btnOnOffSpinner.isSelected());
                binding.spinner.setEnableBorder(!binding.btnOnOffSpinner.isSelected());
                if (binding.btnOnOffSpinner.isSelected()) {
                    binding.btnOnOffSpinner.setText("Border Off Spinner");
                } else {
                    binding.btnOnOffSpinner.setText("Border On Spinner");
                }
            }
        });

        binding.btnOnOffEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnOnOffEditText.setSelected(!binding.btnOnOffEditText.isSelected());
                binding.edittext.setEnableBorder(!binding.btnOnOffEditText.isSelected());
                if(binding.btnOnOffEditText.isSelected()){
                    binding.btnOnOffEditText.setText("Border Off EditText");
                }else {
                    binding.btnOnOffEditText.setText("Border On EditText");
                }
            }
        });
        
    }
}
