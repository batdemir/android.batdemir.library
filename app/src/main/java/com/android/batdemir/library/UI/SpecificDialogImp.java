package com.android.batdemir.library.UI;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.android.batdemir.mylibrary.Components.Dialog.MyAlertDialog;

public class SpecificDialogImp implements MyAlertDialog.MyAlertDialogCreator {
    @Override
    public MyAlertDialog create() {
        return new SpecificDialog();
    }

    public static class SpecificDialog extends MyAlertDialog {

        public SpecificDialog() {
            super();
        }

        @Override
        public void show(@NonNull FragmentManager manager, @Nullable String tag) {
            super.show(manager, tag);
            Log.v("show", "show");
        }

        @Override
        public void dismiss() {
            super.dismiss();
            Log.v("dismiss", "dismiss");
        }
    }
}
