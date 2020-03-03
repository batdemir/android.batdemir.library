package com.android.batdemir.mylibrary.components.helper;

import android.util.Log;

import com.android.batdemir.mylibrary.components.models.SpinnerModel;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SpinnerHelper {

    public static final int INVALID_POSITION = -1;
    public static final int FIRST_ITEM_POSITION = 0;
    private static SpinnerHelper ourInstance = null;

    private SpinnerHelper() {
    }

    public static SpinnerHelper getInstance() {
        if (ourInstance == null)
            ourInstance = new SpinnerHelper();
        return ourInstance;
    }

    public List<SpinnerModel> cast(List<String> strings) {
        List<SpinnerModel> models = new ArrayList<>();
        try {
            for (int i = 0; i < strings.size(); i++) {
                models.add(new SpinnerModel(i + 1, strings.get(i), new Gson().toJson(strings)));
            }
        } catch (Exception e) {
            Log.e(SpinnerHelper.class.getSimpleName(), e.getMessage());
        }
        return models;
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
