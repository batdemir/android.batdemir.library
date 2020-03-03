package com.android.batdemir.library.ui.ui_default.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.android.batdemir.library.databinding.FragmentHomeBinding;
import com.android.batdemir.library.ui.base.BaseFragment;

public class HomeDialogFragment extends BaseFragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void getObjectReferences() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public void loadData() {
        homeViewModel.getText().observe(this, binding.textHome::setText);
    }

    @Override
    public void setListeners() {
        //Nothings
    }
}