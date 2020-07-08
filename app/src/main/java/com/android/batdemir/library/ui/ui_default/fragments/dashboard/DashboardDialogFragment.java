package com.android.batdemir.library.ui.ui_default.fragments.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.batdemir.library.databinding.FragmentDashboardBinding;

public class DashboardDialogFragment extends Fragment {

    private FragmentDashboardBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DashboardViewModel dashboardViewModel = new ViewModelProvider.NewInstanceFactory().create(DashboardViewModel.class);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), binding.textDashboard::setText);
    }
}