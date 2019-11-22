package com.android.batdemir.mylibrary.tools.button;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class OnTouchEvent implements View.OnTouchListener {

    private final View view;

    public OnTouchEvent(View view) {
        this.view = view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    int color = 0x88FFFFFF;//%88 Transparent White
                    view.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                    view.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    view.getBackground().clearColorFilter();
                    view.invalidate();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            Log.e(OnTouchEvent.class.getSimpleName(), e.getMessage());
        }
        return false;
    }
}
