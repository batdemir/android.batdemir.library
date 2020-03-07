package com.android.batdemir.library.ui.ui_default.activities.scrolling;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityScrollingBinding;
import com.android.batdemir.library.ui.base.activity.BaseActivity;
import com.android.batdemir.library.ui.ui_default.activities.bottomnavigation.BottomNavigationActivity;
import com.android.batdemir.mylibrary.tools.Tool;

public class ScrollingActivity extends BaseActivity<ActivityScrollingBinding, ScrollingController> {

    @Override
    public void onCreated() {
        init(R.style.AppThemeNoActionBar, "scrolling", 0f, false, true);
    }

    @Override
    public void getObjectReferences() {
        //Nothing
    }

    @Override
    public void loadData() {
        setSupportActionBar(getBinding().toolbar);
    }

    @Override
    public void setListeners() {
        getBinding().fab.setOnClickListener(view -> Tool.getInstance(getBinding().getRoot().getContext()).move(BottomNavigationActivity.class, true, true, null));
    }
}
