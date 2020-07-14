package com.android.batdemir.library.ui.ui_default.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.batdemir.library.databinding.FragmentHomeBinding;

public class HomeDialogFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeViewModel homeViewModel = new ViewModelProvider.NewInstanceFactory().create(HomeViewModel.class);
        homeViewModel.getText().observe(getViewLifecycleOwner(), binding.textHome::setText);
    }
}