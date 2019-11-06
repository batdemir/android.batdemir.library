package com.android.batdemir.mylibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.batdemir.mylibrary.Components.Spinner.SpinnerAdapter;
import com.android.batdemir.mylibrary.Components.Spinner.SpinnerHelper;

import java.lang.reflect.Field;
import java.util.List;

public class MySpinner extends RelativeLayout {

    private TextView label;
    private Spinner spinner;

    // Constructor

    public MySpinner(Context context) {
        super(context);
        init(context, null);
    }

    public MySpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MySpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MySpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    // Initialize

    private void init(Context context, AttributeSet attributeSet) {
        inflate(context, R.layout.component_spinner, this);

        label = findViewById(R.id.label);
        spinner = findViewById(R.id.spinner);

        if (attributeSet == null) {
            return;
        }

        applyAttribute(context, attributeSet);
    }

    private void applyAttribute(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ComponentSpinner, 0, 0);

        try {
            int spinnerLabel = R.styleable.ComponentSpinner_spinnerLabel;
            int spinnerLabelTextAppearance = R.styleable.ComponentSpinner_spinnerLabelTextAppearance;


            if (typedArray.hasValue(spinnerLabel)) {
                setLabel(typedArray.getString(spinnerLabel));
            }

            if (typedArray.hasValue(spinnerLabelTextAppearance)) {
                setLabelTextAppearance(typedArray.getResourceId(spinnerLabelTextAppearance, R.style.TitleTextAppearance));
            }

        } catch (Exception e) {
            Log.e(MySpinner.class.getSimpleName(), e.getMessage());
        } finally {
            typedArray.recycle();
        }
    }

    // Initialize Methods

    private void setLabel(String labelName) {
        label.setText(labelName);
    }

    private void setLabelTextAppearance(int labelTextAppearance) {
        label.setTextAppearance(labelTextAppearance);
    }

    // Get Property

    public TextView getLabel() {
        return label;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    // Methods

    public void fillSpinner(List<?> sourceModel, Field id, Field description) {
        spinner.setAdapter(new SpinnerAdapter(getContext(), SpinnerHelper.getInstance(spinner).cast(sourceModel, id, description)));
    }

    public void fillSpinner(List<?> sourceModel, Field id, Field description, Integer textAppearance) {
        spinner.setAdapter(new SpinnerAdapter(getContext(), SpinnerHelper.getInstance(spinner).cast(sourceModel, id, description), textAppearance));
    }

    public boolean isValid(boolean isControlFirstItem) {
        if (isControlFirstItem)
            return spinner.getAdapter() != null && spinner.getSelectedItemPosition() != 0;
        else
            return spinner.getAdapter() != null;
    }
}
