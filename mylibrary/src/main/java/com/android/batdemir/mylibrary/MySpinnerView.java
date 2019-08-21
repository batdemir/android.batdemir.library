package com.android.batdemir.mylibrary;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class MySpinnerView extends RelativeLayout {

    private String TAG="MySpinnerView";
    private Spinner _spinner;
    private Boolean enableBorder;

    public MySpinnerView(Context context) {
        super(context);
        init();
    }

    public MySpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySpinnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MySpinnerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    //---properties---//

    public Spinner get_spinner() {
        return _spinner;
    }

    public Boolean getEnableBorder() {
        return enableBorder==null?true:enableBorder;
    }

    public void setEnableBorder(Boolean enableBorder) {
        this.enableBorder = enableBorder;
        if (getEnableBorder()) {
            _spinner.setBackground(getContext().getDrawable(R.drawable.gradient_unselected_spinner_box));
        } else {
            _spinner.setBackground(getContext().getDrawable(R.drawable.gradient_non_border_spinner_box));
        }
        _spinner.setOnItemSelectedListener(onItemSelectedListener(getEnableBorder()));
    }

    //---functions---//

    private void init(){
        try {
            inflate(getContext(),R.layout.view_my_spinner,this);
            _spinner = findViewById(R.id.viewMySpinner);
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

    private Spinner.OnItemSelectedListener onItemSelectedListener(Boolean enableBorder){
        Spinner.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(enableBorder){
                    if(parent.getCount()!=0){
                        if(parent.getSelectedItemPosition()==0){
                            _spinner.setBackground(getContext().getDrawable(R.drawable.gradient_unselected_spinner_box));
                        }else {
                            _spinner.setBackground(getContext().getDrawable(R.drawable.gradient_selected_spinner_box));
                        }
                    }
                }else {
                    _spinner.setBackground(getContext().getDrawable(R.drawable.gradient_non_border_box));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        return onItemSelectedListener;
    }

}
