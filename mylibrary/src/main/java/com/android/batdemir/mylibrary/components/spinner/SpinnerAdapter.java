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
        super(context, R.layout.item_spinner);
        this.context = context;
        this.models = models;
    }

    public SpinnerAdapter(Context context, List<SpinnerModel> models, Integer textAppearance) {
        super(context, R.layout.item_spinner);
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
        private TextView textView;

        private SpinnerViewHolder(View v) {
            this.textView = v.findViewById(R.id.spinnerItem);
            textView.setTextAppearance(textAppearance != null ? textAppearance : android.R.style.Widget_Material_TextView_SpinnerItem);
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
        View root = convertView;
        if (root == null) {
            root = LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false);
        }
        new SpinnerViewHolder(root).setData(models.get(position), false);
        return root;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root = convertView;
        if (root == null) {
            root = LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false);
        }
        new SpinnerViewHolder(root).setData(models.get(position), true);
        return root;
    }

}
