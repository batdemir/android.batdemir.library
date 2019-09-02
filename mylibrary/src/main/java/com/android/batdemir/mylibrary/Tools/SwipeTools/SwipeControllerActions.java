package com.android.batdemir.mylibrary.Tools.SwipeTools;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

public abstract class SwipeControllerActions {
    public void onLeftSwiped(int position, View rootView, String message, View.OnClickListener onClickListener) {
        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", onClickListener);
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
    }

    public void onRightSwiped(int position, View rootView, String message, View.OnClickListener onClickListener) {
        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", onClickListener);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();
    }
}
