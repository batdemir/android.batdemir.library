package com.android.batdemir.mylibrary.components.spinner;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("StaticFieldLeak")
public class SpinnerHelper {

    private static SpinnerHelper ourInstance = null;
    private Spinner spinner;

    private SpinnerHelper() {

    }

    private void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    public static SpinnerHelper getInstance(Spinner spinner) {
        if (ourInstance == null) {
            ourInstance = new SpinnerHelper();
        }
        ourInstance.setSpinner(spinner);
        return ourInstance;
    }

    public Object getSelectedItemId() {
        Object result = null;
        try {
            SpinnerModel spinnerModel = (SpinnerModel) spinner.getSelectedItem();
            result = spinnerModel.getId();
        } catch (Exception e) {
            Log.e(SpinnerHelper.class.getSimpleName(), e.getMessage());
        }
        return result;
    }

    public String getSelectedItemDescription() {
        String result = null;
        try {
            SpinnerModel spinnerModel = (SpinnerModel) spinner.getSelectedItem();
            result = spinnerModel.getDescription();
        } catch (Exception e) {
            Log.e(SpinnerHelper.class.getSimpleName(), e.getMessage());
        }
        return result;
    }

    public String getSelectedItemModel() {
        String result = null;
        try {
            SpinnerModel spinnerModel = (SpinnerModel) spinner.getSelectedItem();
            result = spinnerModel.getModel();
        } catch (Exception e) {
            Log.e(SpinnerHelper.class.getSimpleName(), e.getMessage());
        }
        return result;
    }

    public void setSelectedItemById(Object id) {
        try {
            SpinnerAdapter spinnerAdapter = (SpinnerAdapter) spinner.getAdapter();
            for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
                if (spinnerAdapter.getModels().get(i).getId().equals(id)) {
                    spinner.setSelection(i);
                }
            }
        } catch (Exception e) {
            Log.e(SpinnerHelper.class.getSimpleName(), e.getMessage());
        }
    }

    public void setSelectedItemByDescription(String description) {
        try {
            SpinnerAdapter spinnerAdapter = (SpinnerAdapter) spinner.getAdapter();
            for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
                if (spinnerAdapter.getModels().get(i).getDescription().equals(description)) {
                    spinner.setSelection(i);
                }
            }
        } catch (Exception e) {
            Log.e(SpinnerHelper.class.getSimpleName(), e.getMessage());
        }
    }

    public void setSelectedItemByModel(String model) {
        try {
            SpinnerAdapter spinnerAdapter = (SpinnerAdapter) spinner.getAdapter();
            for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
                if (spinnerAdapter.getModels().get(i).getModel().equals(model)) {
                    spinner.setSelection(i);
                }
            }
        } catch (Exception e) {
            Log.e(SpinnerHelper.class.getSimpleName(), e.getMessage());
        }
    }

    public List<SpinnerModel> cast(List<?> sourceModel, Field id, Field description) {
        List<SpinnerModel> spinnerModels = new ArrayList<>();
        try {
            for (int i = 0; i < sourceModel.size(); i++) {
                Field mId = sourceModel.get(i).getClass().getDeclaredField(id.getName());
                Field mDesc = sourceModel.get(i).getClass().getDeclaredField(description.getName());
                mId.setAccessible(true);
                mDesc.setAccessible(true);
                spinnerModels.add(new SpinnerModel(mId.get(sourceModel.get(i)), (String) mDesc.get(sourceModel.get(i)), new Gson().toJson(sourceModel.get(i))));
            }
        } catch (Exception e) {
            Log.e(SpinnerHelper.class.getSimpleName(), e.getMessage());
        }
        return spinnerModels;
    }
}
