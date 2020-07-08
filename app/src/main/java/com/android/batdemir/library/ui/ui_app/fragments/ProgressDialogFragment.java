package com.android.batdemir.library.ui.ui_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.batdemir.library.R;
import com.android.batdemir.library.databinding.FragmentProgressBarBinding;
import com.richpath.RichPath;
import com.richpathanimator.RichPathAnimator;

public class ProgressDialogFragment extends DialogFragment {

    private FragmentProgressBarBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProgressBarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCancelable(false);
        notificationAnimate();
        morphingAnimate();
        setListeners();
    }

    public void setListeners() {
        binding.btnClose.setOnClickListener(v -> dismiss());
    }

    private void notificationAnimate() {
        RichPath top = binding.richPathView.findRichPathByName("top");
        RichPath bottom = binding.richPathView.findRichPathByName("bottom");
        RichPathAnimator.animate(top)
                .interpolator(new DecelerateInterpolator())
                .rotation(0, 20, -20, 10, -10, 5, -5, 2, -2, 0)
                .duration(4000)
                .andAnimate(bottom)
                .interpolator(new DecelerateInterpolator())
                .rotation(0, 10, -10, 5, -5, 2, -2, 0)
                .startDelay(50)
                .duration(4000)
                .repeatModeSet(RichPathAnimator.RESTART)
                .repeatCountSet(RichPathAnimator.INFINITE)
                .start();
    }

    private void morphingAnimate() {
        String hippoPathData = getString(R.string.hippo_path);
        String elephantPathData = getString(R.string.elephant_path);
        String bullPathData = getString(R.string.bull_path);

        RichPath richPath = binding.richPathView2.findFirstRichPath();

        RichPathAnimator.animate(richPath)
                .pathData(elephantPathData)
                .duration(600)

                .thenAnimate(richPath)
                .pathData(bullPathData)
                .duration(600)

                .thenAnimate(richPath)
                .pathData(hippoPathData)
                .duration(600)

                .repeatModeSet(RichPathAnimator.REVERSE)
                .repeatCountSet(RichPathAnimator.INFINITE)

                .start();
    }
}
