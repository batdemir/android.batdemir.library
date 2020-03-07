package com.android.batdemir.library.ui.ui_default.activities.bottomnavigation;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.ActivityBottomNavigationBinding;
import com.android.batdemir.library.ui.base.activity.BaseActivity;
import com.android.batdemir.library.ui.ui_default.activities.tabbed.TabbedActivity;
import com.android.batdemir.mylibrary.tools.Tool;

public class BottomNavigationActivity extends BaseActivity<ActivityBottomNavigationBinding, BottomNavigationController> {

    @Override
    public void onCreated() {
        init(R.style.AppThemeActionBar, "bottomNavigation", 0f, true);
    }

    @Override
    public void getObjectReferences() {
        //Nothing
    }

    @Override
    public void loadData() {
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).build();
        NavController navController = Navigation.findNavController((Activity) getBinding().getRoot().getContext(), R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) getBinding().getRoot().getContext(), navController, appBarConfiguration);
        NavigationUI.setupWithNavController(getBinding().navView, navController);
    }

    @Override
    public void setListeners() {
        getBinding().fab.setOnClickListener(v -> Tool.getInstance(getBinding().getRoot().getContext()).move(TabbedActivity.class, true, true, null));
    }
}
