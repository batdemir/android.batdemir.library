package com.android.batdemir.library.ui.base;

import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public abstract class BaseDialogFragment extends DialogFragment implements
        BaseActions {

    @Override
    public void onStart() {
        super.onStart();
        new Thread() {
            @Override
            public void run() {
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> getObjectReferences());
            }
        }.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread() {
            @Override
            public void run() {
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                    loadData();
                    setListeners();
                });
            }
        }.start();
    }
}
