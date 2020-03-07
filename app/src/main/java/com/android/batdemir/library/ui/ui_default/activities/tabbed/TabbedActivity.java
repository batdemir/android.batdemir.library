package com.android.batdemir.library.ui.ui_default.activities.tabbed;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityTabbedBinding;
import com.android.batdemir.library.ui.base.activity.BaseActivity;
import com.android.batdemir.library.ui.ui_default.activities.scrolling.ScrollingActivity;
import com.android.batdemir.library.ui.ui_default.adapters.AdapterSectionsPager;
import com.android.batdemir.mylibrary.tools.Tool;

public class TabbedActivity extends BaseActivity<ActivityTabbedBinding, TabbedController> {

    @Override
    public void onCreated() {
        init(R.style.AppThemeNoActionBar, "Tabbed", 0f, true);
    }

    @Override
    public void getObjectReferences() {
        //Nothing
    }

    @Override
    public void loadData() {
        AdapterSectionsPager adapterSectionsPager = new AdapterSectionsPager(getBinding().getRoot().getContext(), getSupportFragmentManager());
        getBinding().viewPager.setAdapter(adapterSectionsPager);
        getBinding().tabs.setupWithViewPager(getBinding().viewPager);
    }

    @Override
    public void setListeners() {
        getBinding().fab.setOnClickListener(v -> Tool.getInstance(getBinding().getRoot().getContext()).move(ScrollingActivity.class, true, true, null));
        getBinding().fab2.setOnClickListener(v -> Tool.getInstance(getBinding().getRoot().getContext()).move(ScrollingActivity.class, true, false, null));
    }
}