package com.android.batdemir.mylibrary.tools.spinner;

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

public class SpinnerAdapter extends ArrayAdapter {

    private Context context;
    private List<SpinnerModel> models;
    private Integer textAppearance;
    private SpinnerType spinnerType;

    public SpinnerAdapter(Context context, List<SpinnerModel> models) {
        super(context, R.layout.item_spinner);
        this.context = context;
        this.models = models;
        this.spinnerType = SpinnerType.SPINNER;
        this.textAppearance = null;
    }

    public SpinnerAdapter(Context context, List<SpinnerModel> models, SpinnerType spinnerType) {
        super(context, R.layout.item_spinner);
        this.context = context;
        this.models = models;
        this.spinnerType = spinnerType;
        this.textAppearance = null;
    }

    public SpinnerAdapter(Context context, List<SpinnerModel> models, Integer textAppearance) {
        super(context, R.layout.item_spinner);
        this.context = context;
        this.models = models;
        this.spinnerType = SpinnerType.SPINNER;
        this.textAppearance = textAppearance;
    }

    public SpinnerAdapter(Context context, List<SpinnerModel> models, SpinnerType spinnerType, Integer textAppearance) {
        super(context, R.layout.item_spinner);
        this.context = context;
        this.models = models;
        this.spinnerType = spinnerType;
        this.textAppearance = textAppearance;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        if (item instanceof SpinnerModel)
            return models.indexOf(item);
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public String getItem(int position) {
        return models.get(position).getDescription();
    }

    @NotNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SpinnerViewHolder spinnerViewHolder;
        View root = convertView;
        if (root == null) {
            root = LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false);
            spinnerViewHolder = new SpinnerViewHolder(root);
            root.setTag(spinnerViewHolder);
        } else {
            spinnerViewHolder = (SpinnerViewHolder) root.getTag();
        }
        spinnerViewHolder.setData(models.get(position), false);
        return root;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SpinnerViewHolder spinnerViewHolder;
        View root = convertView;
        if (root == null) {
            root = LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false);
            spinnerViewHolder = new SpinnerViewHolder(root);
            root.setTag(spinnerViewHolder);
        } else {
            spinnerViewHolder = (SpinnerViewHolder) root.getTag();
        }
        spinnerViewHolder.setData(models.get(position), true);
        return root;
    }

    public List<SpinnerModel> getModels() {
        return models;
    }

    public enum SpinnerType {
        SPINNER,
        AUTO_COMPLETE_TEXT_VIEW
    }

    private class SpinnerViewHolder {
        private TextView textView;

        private SpinnerViewHolder(View v) {
            this.textView = v.findViewById(R.id.spinnerItem);
            textView.setTextAppearance(textAppearance != null ? textAppearance : android.R.style.Widget_Material_TextView_SpinnerItem);
        }

        private void setData(SpinnerModel spinnerModel, boolean isDropDown) {
            if (isDropDown && spinnerType == SpinnerType.SPINNER) {
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setPadding(8, 16, 8, 16);
            } else if (!isDropDown && spinnerType == SpinnerType.AUTO_COMPLETE_TEXT_VIEW) {
                textView.setPadding(8, 16, 8, 16);
            }
            textView.setText(spinnerModel.getDescription());
        }
    }

}
