package com.android.batdemir.library;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.mylibrary.Tools.Spinner.HelperSpinner;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = MainActivity.this;
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

        binding.btnFillCustomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillCustomList();
            }
        });

        binding.btnFillArrayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillArrayList();
            }
        });

        binding.btnFillStringList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillStringList();
            }
        });

        binding.btnChangeBorderColorSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.btnChangeBorderColorEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBorderColorEdittext();
            }
        });

        binding.spinner.getSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String message = "";
                message += "\n" + "Selected Item Id:\t" + binding.spinner.getSelectedItemId();
                message += "\n" + "Selected Item Value:\t" + binding.spinner.getSelectedItemValue();
                message += "\n" + "Selected Item Position:\t" + binding.spinner.getSelectedItemPosition();
                message += "\n";
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show();;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fillCustomList(){
        ArrayList<Object> testingModels = new ArrayList<>();

        testingModels.add(new TestingModel(123,"test1",1,null));
        testingModels.add(new TestingModel(234,"test2",null,"subTest2"));
        testingModels.add(new TestingModel(356,"test3",3,null));
        testingModels.add(new TestingModel(478,"test4",null,null));
        testingModels.add(new TestingModel(590,"test5",5,"subTest5"));
        testingModels.add(new TestingModel(612,"test6",null,null));
        testingModels.add(new TestingModel(723,"test7",7,null));
        testingModels.add(new TestingModel(834,"test8",8,"subTest8"));
        testingModels.add(new TestingModel(945,"test9",null,"subTest9"));

        try {
            new HelperSpinner<>(
                    context,binding.spinner,
                    true,
                    "Select"
            ).fill_spinnerCustomModel(
                    testingModels,
                    TestingModel.class.getDeclaredField("Id"),
                    TestingModel.class.getDeclaredField("Description")
            );
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void fillArrayList(){
        new HelperSpinner<>(
                context,
                binding.spinner,
                true,
                "Selam"
        ).fill_spinnerStringList(
                R.array.test
        );
    }

    private void fillStringList(){
        ArrayList<String> strings = new ArrayList<>();

        strings.add("StringTest1");
        strings.add("StringTest2");
        strings.add("StringTest3");
        strings.add("StringTest4");
        strings.add("StringTest5");
        strings.add("StringTest6");
        strings.add("StringTest7");
        strings.add("StringTest8");
        strings.add("StringTest9");
        strings.add("StringTest10");

        new HelperSpinner<>(
                context,
                binding.spinner,
                true,
                "Meyaba"
        ).fill_spinnerStringArray(
                strings
        );
    }

    private void changeBorderColorSpinner(){

    }

    private void changeBorderColorEdittext(){
        GradientDrawable gradientDrawable = (GradientDrawable) binding.edittext.get_editText().getBackground();
        gradientDrawable.setStroke(1, Color.CYAN);
        gradientDrawable.setCornerRadius(16F);
    }

    private class TestingModel implements Serializable {

        @SerializedName("Id")
        private Integer Id;

        @SerializedName("Description")
        private String Description;

        @SerializedName("SubId")
        private Integer SubId;

        @SerializedName("SubDescription")
        private String SubDescription;

        public TestingModel(Integer id, String description, Integer subId, String subDescription) {
            Id = id;
            Description = description;
            SubId = subId;
            SubDescription = subDescription;
        }

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public Integer getSubId() {
            return SubId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestingModel)) return false;

            TestingModel that = (TestingModel) o;

            if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null)
                return false;
            if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
                return false;
            if (getSubId() != null ? !getSubId().equals(that.getSubId()) : that.getSubId() != null)
                return false;
            return getSubDescription() != null ? getSubDescription().equals(that.getSubDescription()) : that.getSubDescription() == null;
        }

        @Override
        public int hashCode() {
            int result = getId() != null ? getId().hashCode() : 0;
            result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
            result = 31 * result + (getSubId() != null ? getSubId().hashCode() : 0);
            result = 31 * result + (getSubDescription() != null ? getSubDescription().hashCode() : 0);
            return result;
        }

        public void setSubId(Integer subId) {
            SubId = subId;
        }

        public String getSubDescription() {
            return SubDescription;
        }

        public void setSubDescription(String subDescription) {
            SubDescription = subDescription;
        }
    }
}
