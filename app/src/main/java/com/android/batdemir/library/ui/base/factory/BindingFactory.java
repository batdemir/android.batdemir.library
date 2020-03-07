package com.android.batdemir.library.ui.base.factory;

import android.app.Activity;
import android.view.LayoutInflater;

import com.android.batdemir.library.databinding.ActivityBarcodeBinding;
import com.android.batdemir.library.databinding.ActivityBottomNavigationBinding;
import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.library.databinding.ActivityMapsBinding;
import com.android.batdemir.library.databinding.ActivityMaterialBinding;
import com.android.batdemir.library.databinding.ActivityMenuBinding;
import com.android.batdemir.library.databinding.ActivityRecyclerBinding;
import com.android.batdemir.library.databinding.ActivityScrollingBinding;
import com.android.batdemir.library.databinding.ActivitySignUpBinding;
import com.android.batdemir.library.databinding.ActivityTabbedBinding;

@SuppressWarnings({"squid:S00119", "squid:S1121", "unchecked"})
public class BindingFactory {

    private static BindingFactory instance;

    private BindingFactory() {

    }

    public static synchronized BindingFactory getInstance() {
        return instance = instance == null ? new BindingFactory() : instance;
    }

    public <Binding> Binding getBinding(String strBinding, LayoutInflater inflater) {
        if (strBinding == null)
            throw new NullPointerException("Binding Not Found");

        //APP
        if (strBinding.equalsIgnoreCase("Login")) {
            com.android.batdemir.library.databinding.ActivityLoginBinding binding = com.android.batdemir.library.databinding.ActivityLoginBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (Binding) binding;
        }
        if (strBinding.equalsIgnoreCase("Menu")) {
            ActivityMenuBinding binding = ActivityMenuBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (Binding) binding;
        }
        if (strBinding.equalsIgnoreCase("SignUp")) {
            ActivitySignUpBinding binding = ActivitySignUpBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (Binding) binding;
        }
        //DEFAULT
        if (strBinding.equalsIgnoreCase("BottomNavigation")) {
            ActivityBottomNavigationBinding binding = ActivityBottomNavigationBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (Binding) binding;
        }
        if (strBinding.equalsIgnoreCase("Maps")) {
            ActivityMapsBinding binding = ActivityMapsBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (Binding) binding;
        }
        if (strBinding.equalsIgnoreCase("Scrolling")) {
            ActivityScrollingBinding binding = ActivityScrollingBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (Binding) binding;
        }
        if (strBinding.equalsIgnoreCase("Tabbed")) {
            ActivityTabbedBinding binding = ActivityTabbedBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (Binding) binding;
        }
        //TEST
        if (strBinding.equalsIgnoreCase("Barcode")) {
            ActivityBarcodeBinding binding = ActivityBarcodeBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (Binding) binding;
        }
        if (strBinding.equalsIgnoreCase("Main")) {
            ActivityMainBinding binding = ActivityMainBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (Binding) binding;
        }
        if (strBinding.equalsIgnoreCase("Material")) {
            ActivityMaterialBinding binding = ActivityMaterialBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (Binding) binding;
        }
        if (strBinding.equalsIgnoreCase("Recycler")) {
            ActivityRecyclerBinding binding = ActivityRecyclerBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (Binding) binding;
        }


        throw new NullPointerException("Controller Not Found");
    }
}
