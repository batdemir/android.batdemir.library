package com.android.batdemir.mylibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.android.batdemir.mylibrary.Tools.SpinnerTools.AdapterSpinner;

import java.lang.reflect.Field;

public class MySpinner extends RelativeLayout {

    private String TAG = MySpinner.class.getSimpleName();
    private Spinner _spinner;

    private GradientDrawable gradientDrawableStyle;
    private LayerDrawable layerDrawableIcon;
    private boolean enableBorder = false;
    private int borderWidth = 1;
    private int borderColor = Color.BLACK;
    private int confirmativeBorderColor = Color.GREEN;
    private int nonConfirmativeBorderColor = Color.RED;
    private float borderRadius = 0F;
    private int solidColor = Color.TRANSPARENT;

    private Drawable spinnerArrowIcon = getResources().getDrawable(R.drawable.ic_spinner_arrow_mini, null);
    private boolean addFirstItem = false;
    private String firstItemName = "";

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

    public boolean isEnableBorder() {
        return enableBorder;
    }

    public void setEnableBorder(boolean enableBorder) {
        this.enableBorder = enableBorder;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        gradientDrawableStyle.setStroke(getBorderWidth(), getBorderColor());
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        gradientDrawableStyle.setStroke(getBorderWidth(), getBorderColor());
    }

    public int getConfirmativeBorderColor() {
        return confirmativeBorderColor;
    }

    public void setConfirmativeBorderColor(int confirmativeBorderColor) {
        this.confirmativeBorderColor = confirmativeBorderColor;
    }

    public int getNonConfirmativeBorderColor() {
        return nonConfirmativeBorderColor;
    }

    public void setNonConfirmativeBorderColor(int nonConfirmativeBorderColor) {
        this.nonConfirmativeBorderColor = nonConfirmativeBorderColor;
    }

    public float getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(float borderRadius) {
        this.borderRadius = borderRadius;
        gradientDrawableStyle.setCornerRadius(getBorderRadius());
    }

    public int getSolidColor() {
        return solidColor;
    }

    public void setSolidColor(int solidColor) {
        this.solidColor = solidColor;
        gradientDrawableStyle.setColor(getSolidColor());
    }

    public Drawable getSpinnerArrowIcon() {
        return spinnerArrowIcon;
    }

    public void setSpinnerArrowIcon(Drawable spinnerArrowIcon) {
        this.spinnerArrowIcon = spinnerArrowIcon;
        layerDrawableIcon.setDrawableByLayerId(R.id.spinnerIcon, spinnerArrowIcon);
    }

    public boolean isAddFirstItem() {
        return addFirstItem;
    }

    public void setAddFirstItem(boolean addFirstItem) {
        this.addFirstItem = addFirstItem;
    }

    public String getFirstItemName() {
        return firstItemName;
    }

    public void setFirstItemName(String firstItemName) {
        this.firstItemName = firstItemName;
    }

    //---functions---//

    private void init() {
        try {
            inflate(getContext(), R.layout.view_my_spinner, this);
            _spinner = findViewById(R.id.viewMySpinner);
            _spinner.setOnItemSelectedListener(onItemSelectedListener());
            StateListDrawable stateListDrawable = (StateListDrawable) _spinner.getBackground();
            DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState) stateListDrawable.getConstantState();
            Drawable[] drawables = drawableContainerState.getChildren();
            LayerDrawable layerDrawable = (LayerDrawable) drawables[0];
            gradientDrawableStyle = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.spinnerStyle);
            layerDrawableIcon = (LayerDrawable) drawables[0];
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private AdapterView.OnItemSelectedListener onItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isEnableBorder() && isAddFirstItem() && _spinner.getCount() != 0) {
                    if (getSelectedItemPosition() == 0) {
                                gradientDrawableStyle.setStroke(getBorderWidth(), getNonConfirmativeBorderColor());
                            } else {
                                gradientDrawableStyle.setStroke(getBorderWidth(), getConfirmativeBorderColor());
                        }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public void setAdapter(AdapterSpinner adapter) {
        _spinner.setAdapter(adapter);
    }

    public SpinnerAdapter getAdapter() {
        return _spinner.getAdapter();
    }

    public int getSelectedItemPosition() {
        return _spinner.getSelectedItemPosition();
    }

    public Object getItemAtPosition(int position) {
        return _spinner.getItemAtPosition(position);
    }

    public Object getSelectedItemId() {
        try {
            AdapterSpinner adapterSpinner = (AdapterSpinner) _spinner.getAdapter();
            Field fieldId = adapterSpinner.getModels().get(getSelectedItemPosition()).getClass().getDeclaredField(adapterSpinner.getFieldId().getName());
            fieldId.setAccessible(true);
            return fieldId.get(adapterSpinner.getModels().get(getSelectedItemPosition()));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    public String getSelectedItemValue() {
        try {
            AdapterSpinner adapterSpinner = (AdapterSpinner) _spinner.getAdapter();
            Field fieldDescription = adapterSpinner.getModels().get(getSelectedItemPosition()).getClass().getDeclaredField(adapterSpinner.getFieldDescription().getName());
            fieldDescription.setAccessible(true);
            return String.valueOf(fieldDescription.get(adapterSpinner.getModels().get(getSelectedItemPosition())));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    public void setSelectionByPosition(int position) {
        _spinner.setSelection(position);
    }

    public void setSelectionById(Object id) {
        try {
            AdapterSpinner adapterSpinner = (AdapterSpinner) _spinner.getAdapter();
            for (int i = 0; i < adapterSpinner.getModels().size(); i++) {
                Field fieldId = adapterSpinner.getModels().get(i).getClass().getDeclaredField(adapterSpinner.getFieldId().getName());
                fieldId.setAccessible(true);
                if (fieldId.get(adapterSpinner.getModels().get(i)).equals(id)) {
                    _spinner.setSelection(i);
                }
            }
        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
        }
    }

    public void setSelectionByValue(String value) {
        try {
            AdapterSpinner adapterSpinner = (AdapterSpinner) _spinner.getAdapter();
            for (int i = 0; i < adapterSpinner.getModels().size(); i++) {
                Field fieldDescription = adapterSpinner.getModels().get(i).getClass().getDeclaredField(adapterSpinner.getFieldDescription().getName());
                fieldDescription.setAccessible(true);
                if (fieldDescription.get(adapterSpinner.getModels().get(i)).equals(value)) {
                    _spinner.setSelection(i);
                }
            }
        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
        }
    }
}
