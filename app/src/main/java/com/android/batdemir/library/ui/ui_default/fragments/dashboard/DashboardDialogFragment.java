package com.android.batdemir.library.ui.ui_default.fragments.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.android.batdemir.library.databinding.FragmentDashboardBinding;
import com.android.batdemir.library.ui.base.BaseFragment;

public class DashboardDialogFragment extends BaseFragment {

    private FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
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