package com.android.batdemir.library.ui.ui_default.fragments.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.FragmentDashboardBinding;
import com.android.batdemir.library.ui.base.BaseFragment;

public class DashboardDialogFragment extends BaseFragment {

    private FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        return binding.getRoot();
    }

    @Override
    public void getObjectReferences() {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
    }

    @Override
    public void loadData() {
        dashboardViewModel.getText().observe(this, binding.textDashboard::setText);
    }

    @Override
    public void setListeners() {
        //Nothings
    }
}