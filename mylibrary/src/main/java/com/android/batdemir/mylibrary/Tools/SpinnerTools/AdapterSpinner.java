package com.android.batdemir.mylibrary.Tools.SpinnerTools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.batdemir.mylibrary.R;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;

public class AdapterSpinner<T> extends ArrayAdapter<T> {
    private String TAG = AdapterSpinner.class.getSimpleName();
    private Context context;
    private List<T> models;
    private Field fieldId;
    private Field fieldDescription;

    public AdapterSpinner(Context context,
                          List<T> models,
                          Field fieldId,
                          Field fieldDescription) {
        super(context, R.layout.view_my_spinner_item);
        this.context = context;
        this.models = models;
        this.fieldId = fieldId;
        this.fieldDescription = fieldDescription;
    }

    public List<T> getModels() {
        return models;
    }

    public Field getFieldDescription() {
        return fieldDescription;
    }

    public Field getFieldId() {
        return fieldId;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public T getItem(int position) {
        return models.get(position);
    }

    @NotNull
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.view_my_spinner_item, parent, false);
        TextView viewMySpinnerItem = row.findViewById(R.id.viewMySpinnerItem);

        try {
            Field fieldDescription = models.get(position).getClass().getDeclaredField(this.fieldDescription.getName());
            fieldDescription.setAccessible(true);
            Object descriptionValue = fieldDescription.get(models.get(position));
            viewMySpinnerItem.setText(descriptionValue.toString());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return viewMySpinnerItem;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NotNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.view_my_spinner_item, parent, false);
        TextView viewMySpinnerItem = row.findViewById(R.id.viewMySpinnerItem);

        try {
            Field fieldDescription = models.get(position).getClass().getDeclaredField(this.fieldDescription.getName());
            fieldDescription.setAccessible(true);
            Object descriptionValue = fieldDescription.get(models.get(position));
            viewMySpinnerItem.setText(descriptionValue.toString());
            viewMySpinnerItem.setPadding(0, 32, 0, 32);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return viewMySpinnerItem;
    }
}
