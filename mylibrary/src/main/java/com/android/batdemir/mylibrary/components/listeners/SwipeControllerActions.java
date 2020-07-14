package com.android.batdemir.mylibrary.components.listeners;

import android.graphics.Color;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public interface SwipeControllerActions {
    default void onLeftSwiped(int position, View rootView, String message, View.OnClickListener onClickListener) {
        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", onClickListener);
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
    }

    default void onRightSwiped(int position, View rootView, String message, View.OnClickListener onClickListener) {
        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", onClickListener);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();
    }
}
