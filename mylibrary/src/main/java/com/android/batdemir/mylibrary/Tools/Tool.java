package com.android.batdemir.mylibrary.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.cardview.widget.CardView;

import com.android.batdemir.mylibrary.R;

public class Tool {

    private Context context;

    public Tool(Context context) {
        this.context = context;
    }

    public void move(Class<?> to, boolean isLeft, boolean isHistory, Bundle bundle) {
        try {
            Activity activity = (Activity) context;
            Intent intent = new Intent(context, to);
            if (bundle != null)
                intent.putExtras(bundle);
            context.startActivity(intent);
            if (isLeft) {
                activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                if (!isHistory)
                    activity.finish();
            } else {
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                if (!isHistory)
                    activity.finish();
            }
        } catch (Exception e) {
            Log.e(Tool.class.getSimpleName(), e.getMessage());
        }
    }

    public void anim(View view) {
        try {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_down);
            view.startAnimation(animation);
        } catch (Exception e) {
            Log.e(Tool.class.getSimpleName(), e.getMessage());
        }
    }

    public void animDialog(CardView cardView) {
        try {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in);
            cardView.startAnimation(animation);
        } catch (Exception e) {
            Log.e(Tool.class.getSimpleName(), e.getMessage());
        }
    }
}
