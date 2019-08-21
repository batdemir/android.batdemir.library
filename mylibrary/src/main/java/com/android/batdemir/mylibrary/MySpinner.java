package com.android.batdemir.mylibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.android.batdemir.mylibrary.Tools.Spinner.AdapterSpinner;

import java.lang.reflect.Field;

public class MySpinner extends RelativeLayout{

    private String TAG = MySpinner.class.getSimpleName();
    private Spinner _spinner;
    private Boolean enableBorder;

    public MySpinner(Context context) {
        super(context);
        init();
    }

    public MySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MySpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    //---properties---//

    public Spinner getSpinner() {
        return _spinner;
    }

    public Boolean getEnableBorder() {
        return enableBorder==null?false:enableBorder;
    }

    public void setEnableBorder(Boolean enableBorder) {
        this.enableBorder = enableBorder;
        if (getEnableBorder()) {
            _spinner.setBackground(getContext().getDrawable(R.drawable.gradient_unselected_spinner_box));
        } else {
            _spinner.setBackground(getContext().getDrawable(R.drawable.gradient_non_border_spinner_box));
        }
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

    public void setAdapter(AdapterSpinner adapter){
        _spinner.setAdapter(adapter);
    }

    public SpinnerAdapter getAdapter(){
        return _spinner.getAdapter();
    }

    public int getSelectedItemPosition(){
        return _spinner.getSelectedItemPosition();
    }

    public Object getItemAtPosition(int position){
        return _spinner.getItemAtPosition(position);
    }

    public Object getSelectedItemId(){
        try {
            AdapterSpinner adapterSpinner = (AdapterSpinner) _spinner.getAdapter();
            Field fieldId = adapterSpinner.getModels().get(getSelectedItemPosition()).getClass().getDeclaredField(adapterSpinner.getFieldId().getName());
            fieldId.setAccessible(true);
            return fieldId.get(adapterSpinner.getModels().get(getSelectedItemPosition()));
        } catch (NoSuchFieldException e) {
            Log.e(TAG,e.getMessage());
            return null;
        } catch (IllegalAccessException e) {
            Log.e(TAG,e.getMessage());
            return null;
        }
    }

    public String getSelectedItemValue(){
        try {
            AdapterSpinner adapterSpinner = (AdapterSpinner) _spinner.getAdapter();
            Field fieldDescription = adapterSpinner.getModels().get(getSelectedItemPosition()).getClass().getDeclaredField(adapterSpinner.getFieldDescription().getName());
            fieldDescription.setAccessible(true);
            return String.valueOf(fieldDescription.get(adapterSpinner.getModels().get(getSelectedItemPosition())));
        } catch (NoSuchFieldException e) {
            Log.e(TAG,e.getMessage());
            return null;
        } catch (IllegalAccessException e) {
            Log.e(TAG,e.getMessage());
            return null;
        }
    }

    public void setSelectionByPosition(int position){
        _spinner.setSelection(position);
    }

    public void setSelectionById(Object id){
        try {
            AdapterSpinner adapterSpinner = (AdapterSpinner) _spinner.getAdapter();
            for(int i=0;i<adapterSpinner.getModels().size();i++){
                Field fieldId = adapterSpinner.getModels().get(i).getClass().getDeclaredField(adapterSpinner.getFieldId().getName());
                fieldId.setAccessible(true);
                if(fieldId.get(adapterSpinner.getModels().get(i)).equals(id)){
                    _spinner.setSelection(i);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void setSelectionByValue(String value){
        try {
            AdapterSpinner adapterSpinner = (AdapterSpinner) _spinner.getAdapter();
            for(int i=0;i<adapterSpinner.getModels().size();i++){
                Field fieldDescription = adapterSpinner.getModels().get(i).getClass().getDeclaredField(adapterSpinner.getFieldDescription().getName());
                fieldDescription.setAccessible(true);
                if(fieldDescription.get(adapterSpinner.getModels().get(i)).equals(value)){
                    _spinner.setSelection(i);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
