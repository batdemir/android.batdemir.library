package com.android.batdemir.mylibrary.components.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.batdemir.mylibrary.components.adapters.SpinnerAdapter;
import com.android.batdemir.mylibrary.components.models.SpinnerModel;
import com.google.android.material.textview.MaterialAutoCompleteTextView;
import com.google.gson.Gson;

import java.util.Objects;

public class MyAutoCompleteTextView extends MaterialAutoCompleteTextView {

    private MyAutoCompleteTextView current = null;
    private SpinnerAdapter spinnerAdapter = null;

    public MyAutoCompleteTextView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyAutoCompleteTextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public MyAutoCompleteTextView(@NonNull Context context, @Nullable AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        init();
    }

    private void init() {
        current = this;
        current.setImeOptions(EditorInfo.IME_ACTION_DONE);
        current.setSingleLine(true);
        spinnerAdapter = (SpinnerAdapter) current.getAdapter();
    }

    private SpinnerModel getItem() {
        if (current.getText().toString().isEmpty()) {
            return null;
        } else {
            return getItemFromAdapter();
        }
    }

    private SpinnerModel getItemFromAdapter() {
        if (spinnerAdapter == null)
            return null;
        else if (spinnerAdapter.getCount() == 0)
            return null;
        else
            return getItemFromModel();
    }

    private SpinnerModel getItemFromModel() {
        SpinnerModel selectedItem = null;
        for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
            if (spinnerAdapter.getModels().get(i).getDescription().equals(current.getText().toString())) {
                selectedItem = spinnerAdapter.getModels().get(i);
                break;
            }
        }
        return selectedItem;
    }

    //Model Functions

    public SpinnerModel getSelectedItem() {
        SpinnerModel selectedItem = getItem();
        if (selectedItem == null)
            current.setError("Item Not Found");
        else
            current.setError(null);
        return selectedItem;
    }

    public Object getSelectedItemId() {
        SpinnerModel selectedItem = getItem();
        if (selectedItem == null)
            current.setError("Item Not Found");
        else
            current.setError(null);
        return Objects.requireNonNull(selectedItem).getId();
    }

    public String getSelectedItemDescription() {
        SpinnerModel selectedItem = getItem();
        if (selectedItem == null)
            current.setError("Item Not Found");
        else
            current.setError(null);
        return Objects.requireNonNull(selectedItem).getDescription();
    }

    public Object getSelectedItemModel(Class<?> classType) {
        SpinnerModel selectedItem = getItem();
        if (selectedItem == null)
            current.setError("Item Not Found");
        else
            current.setError(null);
        return new Gson().fromJson(Objects.requireNonNull(selectedItem).getModel(), classType);
    }

    public boolean setSelectByItem(SpinnerModel item) {
        if (item == null)
            return false;
        if (spinnerAdapter == null)
            return false;
        if (spinnerAdapter.getCount() == 0)
            return false;

        for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
            if (spinnerAdapter.getModels().get(i).equals(item)) {
                current.setText(spinnerAdapter.getModels().get(i).getDescription());
                break;
            }
        }
        return true;
    }

    public boolean setSelectByItemId(Object itemId) {
        if (itemId == null)
            return false;
        if (spinnerAdapter == null)
            return false;
        if (spinnerAdapter.getCount() == 0)
            return false;

        for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
            if (spinnerAdapter.getModels().get(i).getId().equals(itemId)) {
                current.setText(spinnerAdapter.getModels().get(i).getDescription());
            }
        }
        return true;
    }

    public boolean setSelectByItemDescription(String itemDescription) {
        if (itemDescription == null)
            return false;
        if (itemDescription.isEmpty())
            return false;
        if (spinnerAdapter == null)
            return false;
        if (spinnerAdapter.getCount() == 0)
            return false;

        for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
            if (spinnerAdapter.getModels().get(i).getDescription().equals(itemDescription)) {
                current.setText(spinnerAdapter.getModels().get(i).getDescription());
            }
        }
        return true;
    }

    public boolean setSelectByItemModel(Object itemModel) {
        if (itemModel == null)
            return false;
        if (spinnerAdapter == null)
            return false;
        if (spinnerAdapter.getCount() == 0)
            return false;

        String itemModelStr = new Gson().toJson(itemModel);

        for (int i = 0; i < spinnerAdapter.getModels().size(); i++) {
            if (spinnerAdapter.getModels().get(i).getModel().equals(itemModelStr)) {
                current.setText(spinnerAdapter.getModels().get(i).getDescription());
            }
        }
        return true;
    }
}
