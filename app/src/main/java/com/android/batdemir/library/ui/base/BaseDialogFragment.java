package com.android.batdemir.library.ui.base;

import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public abstract class BaseDialogFragment extends DialogFragment implements
        BaseActions {

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
}
