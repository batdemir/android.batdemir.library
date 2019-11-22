package com.android.batdemir.mylibrary.components.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.batdemir.mylibrary.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<SpinnerModel> {

    private Context context;
    private List<SpinnerModel> models;
    private Integer textAppearance;

    public SpinnerAdapter(Context context, List<SpinnerModel> models) {
        super(context, R.layout.view_spinner_item);
        this.context = context;
        this.models = models;
    }

    public SpinnerAdapter(Context context, List<SpinnerModel> models, Integer textAppearance) {
        super(context, R.layout.view_spinner_item);
        this.context = context;
        this.models = models;
        this.textAppearance = textAppearance;
    }

    public List<SpinnerModel> getModels() {
        return models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Nullable
    @Override
    public SpinnerModel getItem(int position) {
        return models.get(position);
    }

    private class SpinnerViewHolder {
        TextView textView;

        SpinnerViewHolder(View v) {
            this.textView = v.findViewById(R.id.spinnerItem);
            textView.setTextAppearance(textAppearance != null ? textAppearance : R.style.ValueTextAppearance);
        }

        private void setData(SpinnerModel spinnerModel, boolean isDropDown) {
            if (isDropDown) {
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            textView.setText(spinnerModel.getDescription());
        }
    }

    @NotNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_spinner_item, parent, false);
        }
        new SpinnerViewHolder(convertView).setData(models.get(position), false);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_spinner_item, parent, false);
        }
        convertView.setPadding(0, 8, 0, 8);
        new SpinnerViewHolder(convertView).setData(models.get(position), true);
        return convertView;
    }


}
