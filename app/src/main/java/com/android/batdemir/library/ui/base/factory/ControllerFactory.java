package com.android.batdemir.library.ui.base.factory;

import com.android.batdemir.library.databinding.ActivityBarcodeBinding;
import com.android.batdemir.library.databinding.ActivityBottomNavigationBinding;
import com.android.batdemir.library.databinding.ActivityLoginBinding;
import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.android.batdemir.library.databinding.ActivityMaterialBinding;
import com.android.batdemir.library.databinding.ActivityMenuBinding;
import com.android.batdemir.library.databinding.ActivityRecyclerBinding;
import com.android.batdemir.library.databinding.ActivityScrollingBinding;
import com.android.batdemir.library.databinding.ActivitySignUpBinding;
import com.android.batdemir.library.databinding.ActivityTabbedBinding;
import com.android.batdemir.library.ui.ui_app.activities.login.LoginController;
import com.android.batdemir.library.ui.ui_app.activities.menu.MenuController;
import com.android.batdemir.library.ui.ui_app.activities.signup.SignUpController;
import com.android.batdemir.library.ui.ui_default.activities.bottomnavigation.BottomNavigationController;
import com.android.batdemir.library.ui.ui_default.activities.scrolling.ScrollingController;
import com.android.batdemir.library.ui.ui_default.activities.tabbed.TabbedController;
import com.android.batdemir.library.ui.ui_test.activities.barcode.BarcodeController;
import com.android.batdemir.library.ui.ui_test.activities.main.MainController;
import com.android.batdemir.library.ui.ui_test.activities.material.MaterialController;
import com.android.batdemir.library.ui.ui_test.activities.recycler.RecyclerController;

@SuppressWarnings({"squid:S00119", "squid:S1121", "unchecked"})
public class ControllerFactory {

    private static ControllerFactory instance;

    private ControllerFactory() {

    }

    public static synchronized ControllerFactory getInstance() {
        return instance = instance == null ? new ControllerFactory() : instance;
    }

    public <Controller, Binding> Controller getController(String controller, Binding binding) {
        if (controller == null)
            throw new NullPointerException("Controller Not Found");

        //APP
        if (controller.equalsIgnoreCase("Login")) {
            return (Controller) new LoginController((ActivityLoginBinding) binding);
        }
        if (controller.equalsIgnoreCase("Menu")) {
            return (Controller) new MenuController((ActivityMenuBinding) binding);
        }
        if (controller.equalsIgnoreCase("SignUp")) {
            return (Controller) new SignUpController((ActivitySignUpBinding) binding);
        }
        //DEFAULT
        if (controller.equalsIgnoreCase("BottomNavigation")) {
            return (Controller) new BottomNavigationController((ActivityBottomNavigationBinding) binding);
        }
        if (controller.equalsIgnoreCase("Maps")) {
            //
        }
        if (controller.equalsIgnoreCase("Scrolling")) {
            return (Controller) new ScrollingController((ActivityScrollingBinding) binding);
        }
        if (controller.equalsIgnoreCase("Tabbed")) {
            return (Controller) new TabbedController((ActivityTabbedBinding) binding);
        }
        //TEST
        if (controller.equalsIgnoreCase("Barcode")) {
            return (Controller) new BarcodeController((ActivityBarcodeBinding) binding);
        }
        if (controller.equalsIgnoreCase("Main")) {
            return (Controller) new MainController((ActivityMainBinding) binding);
        }
        if (controller.equalsIgnoreCase("Material")) {
            return (Controller) new MaterialController((ActivityMaterialBinding) binding);
        }
        if (controller.equalsIgnoreCase("Recycler")) {
            return (Controller) new RecyclerController((ActivityRecyclerBinding) binding);
        }


        throw new NullPointerException("Controller Not Found");
    }
}
