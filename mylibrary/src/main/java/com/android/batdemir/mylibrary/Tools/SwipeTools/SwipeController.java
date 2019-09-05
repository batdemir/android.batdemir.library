package com.android.batdemir.mylibrary.Tools.SwipeTools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeController extends ItemTouchHelper.Callback {
    private SwipeControllerActions swipeControllerActions;
    private boolean isDelete;
    private Drawable icon;

    public SwipeController(boolean isDelete, Drawable icon, SwipeControllerActions swipeControllerActions) {
        this.isDelete = isDelete;
        this.icon = icon;
        this.swipeControllerActions = swipeControllerActions;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (isDelete)
            return makeMovementFlags(0, ItemTouchHelper.LEFT);
        else
            return makeMovementFlags(0, ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        boolean isCancelled = dX == 0 && !isCurrentlyActive;
        if (isCancelled) {
            clearCanvas(c, viewHolder.itemView);
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            return;
        }

        ColorDrawable background = new ColorDrawable();
        int iconMargin = (viewHolder.itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconTop = viewHolder.itemView.getTop() + (viewHolder.itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + icon.getIntrinsicHeight();
        int iconLeft;
        int iconRight;
        if (isDelete) {
            background.setColor(Color.RED);
            background.setBounds((int) (viewHolder.itemView.getRight() + dX), viewHolder.itemView.getTop(), viewHolder.itemView.getRight(), viewHolder.itemView.getBottom());
            iconLeft = viewHolder.itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
            iconRight = viewHolder.itemView.getRight() - iconMargin;
        } else {
            background.setColor(Color.GREEN);
            background.setBounds((int) (viewHolder.itemView.getLeft() + dX), viewHolder.itemView.getTop(), viewHolder.itemView.getLeft(), viewHolder.itemView.getBottom());
            iconLeft = viewHolder.itemView.getLeft() + iconMargin;
            iconRight = viewHolder.itemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
        }
        background.draw(c);
        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
        icon.draw(c);

        if (isDelete) {
            if (-dX == c.getWidth()) {
                triggerActions(viewHolder, recyclerView);
            }
        } else {
            if (dX == c.getWidth()) {
                triggerActions(viewHolder, recyclerView);
            }
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.8f;
    }

    private void triggerActions(RecyclerView.ViewHolder viewHolder, RecyclerView recyclerView) {
        if (viewHolder.getAdapterPosition() != -1) {
            if (isDelete) {
                swipeControllerActions.onLeftSwiped(viewHolder.getAdapterPosition(), recyclerView.getRootView(), "Deleted", null);
            } else {
                swipeControllerActions.onRightSwiped(viewHolder.getAdapterPosition(), recyclerView.getRootView(), "Edited", null);
            }
        }
    }

    private void clearCanvas(Canvas c, View itemView) {
        Paint mClearPaint = new Paint();
        mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom(), mClearPaint);
    }
}
