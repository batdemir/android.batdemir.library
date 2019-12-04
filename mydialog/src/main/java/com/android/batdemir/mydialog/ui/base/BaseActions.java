package com.android.batdemir.mydialog.ui.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;

public interface BaseActions {

    void getObjectReferences();

    ViewDataBinding setBinding(LayoutInflater layoutInflater, ViewGroup container);

    void loadData();

    void setListeners();
}
