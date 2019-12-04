package com.android.batdemir.mydialog.ui.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public abstract class BaseDialogFragment extends DialogFragment implements
        BaseActions {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setBinding(inflater, container).getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getActivity()).runOnUiThread(this::getObjectReferences);
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            loadData();
            setListeners();
        });
    }

    @Override
    public void onDestroyView() {
        Log.v("Dialog:", "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.v("Dialog:", "onDestroy");
        super.onDestroy();
    }
}
