package com.android.batdemir.mylibrary.components.ui;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;

import androidx.annotation.ArrayRes;
import androidx.appcompat.widget.AppCompatSpinner;

import com.android.batdemir.mylibrary.components.adapters.SpinnerAdapter;
import com.android.batdemir.mylibrary.components.helper.SpinnerHelper;
import com.android.batdemir.mylibrary.components.models.SpinnerModel;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class MySpinner extends AppCompatSpinner {
    public MySpinner(Context context) {
        super(context);
    }

    public MySpinner(Context context, int mode) {
        super(context, mode);
    }

    public MySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MySpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
    }

    public MySpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, mode, popupTheme);
    }

    private boolean getControl() {
        if (getAdapter() == null)
            return false;
        return getAdapter().getCount() != 0;
    }

    private boolean setControl(Object item) {
        if (item == null)
            return false;
        if (getAdapter() == null)
            return false;
        return getAdapter().getCount() != 0;
    }

    private SpinnerModel getItemFromModel() {
        SpinnerAdapter spinnerAdapter = (SpinnerAdapter) getAdapter();
        SpinnerModel selectedItem = null;
        if (getControl()) {
            for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
                if (spinnerAdapter.getModels().get(i).getDescription().equals(spinnerAdapter.getItem(getSelectedItemPosition()))) {
                    selectedItem = spinnerAdapter.getModels().get(i);
                    break;
                }
            }
        }
        return selectedItem;
    }

    //Component Fill & Clear Functions

    public void fill(@ArrayRes int arrayId) {
        setAdapter(new SpinnerAdapter(getContext(), SpinnerHelper.getInstance().cast(Arrays.asList(getResources().getStringArray(arrayId)))));
    }

    @SuppressWarnings("unchecked")
    public void fill(List<?> models) {
        if (models.isEmpty())
            return;
        if (models.get(0) instanceof SpinnerModel) {
            setAdapter(new SpinnerAdapter(getContext(), (List<SpinnerModel>) models));
        } else if (models.get(0) instanceof String) {
            setAdapter(new SpinnerAdapter(getContext(), SpinnerHelper.getInstance().cast((List<String>) models)));
        }
    }

    public void fill(List<?> models, Field id, Field description) {
        setAdapter(new SpinnerAdapter(getContext(), SpinnerHelper.getInstance().cast(models, id, description)));
    }

    public void clear() {
        setAdapter(null);
    }

    //Component Item Functions

    public SpinnerModel getSelectedItem() {
        return getItemFromModel();
    }

    public Object getSelectedItemIdd() {
        if (getSelectedItem() == null)
            return null;
        return getSelectedItem().getId();
    }

    public String getSelectedItemDescription() {
        if (getSelectedItem() == null)
            return null;
        return getSelectedItem().getDescription();
    }

    public Object getSelectedItemModel(Class<?> classType) {
        if (getSelectedItem() == null)
            return null;
        return new Gson().fromJson(getSelectedItem().getModel(), classType);
    }

    public boolean setSelectByItem(SpinnerModel item) {
        SpinnerAdapter spinnerAdapter = (SpinnerAdapter) getAdapter();
        if (setControl(item)) {
            for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
                if (spinnerAdapter.getModels().get(i).equals(item)) {
                    setSelection(i);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public boolean setSelectByItemId(Object itemId) {
        SpinnerAdapter spinnerAdapter = (SpinnerAdapter) getAdapter();
        if (setControl(itemId)) {
            for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
                if (spinnerAdapter.getModels().get(i).getId().equals(itemId)) {
                    setSelection(i);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public boolean setSelectByItemDescription(String itemDescription) {
        SpinnerAdapter spinnerAdapter = (SpinnerAdapter) getAdapter();
        if (setControl(itemDescription)) {
            for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
                if (spinnerAdapter.getModels().get(i).getDescription().equals(itemDescription)) {
                    setSelection(i);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public boolean setSelectByItemModel(Object itemModel) {
        SpinnerAdapter spinnerAdapter = (SpinnerAdapter) getAdapter();
        if (setControl(itemModel)) {
            String itemModelStr = new Gson().toJson(itemModel);
            for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
                if (spinnerAdapter.getModels().get(i).getModel().equals(itemModelStr)) {
                    setSelection(i);
                    break;
                }
            }
            return true;
        }
        return false;
    }
}
