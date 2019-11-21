package com.android.batdemir.library.UI.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        FragmentNotificationsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false);
        notificationsViewModel.getText().observe(this, binding.textNotifications::setText);
        return binding.getRoot();
    }
}