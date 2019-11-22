package com.android.batdemir.library.ui.ui_default.fragments.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.FragmentNotificationsBinding;
import com.android.batdemir.library.ui.base.BaseFragment;

public class NotificationsFragment extends BaseFragment {

    private FragmentNotificationsBinding binding;
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false);
        return binding.getRoot();
    }

    @Override
    public void getObjectReferences() {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
    }

    @Override
    public void loadData() {
        notificationsViewModel.getText().observe(this, binding.textNotifications::setText);
    }

    @Override
    public void setListeners() {
        //Nothings
    }
}