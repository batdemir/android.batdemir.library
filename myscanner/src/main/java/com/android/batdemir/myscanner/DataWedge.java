package com.android.batdemir.myscanner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class DataWedge {

    @SuppressLint("StaticFieldLeak")
    private static DataWedge ourInstance = null;
    private Context context;

    private DataWedge() {
    }

    private void setContext(Context context) {
        this.context = context;
    }

    public static DataWedge getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new DataWedge();
        ourInstance.setContext(context);
        return ourInstance;
    }

    public void init() {
        Bundle profile = getProfile();
        Bundle config = getConfig();
        config.putParcelableArray("PARAM_LIST", new Bundle[]{
                getScannerStatus(),
                getSimulScanStatus(),
                getVoiceInputStatus(),
                getKeyStrokeOutputStatus(),
                getIpOutputStatus(),
                getIntentOutputStatus()
        });

        profile.putBundle("PLUGIN_CONFIG", config);

        Intent intent = new Intent();
        intent.setAction(context.getString(R.string.data_wedge_default_action));
        intent.putExtra(context.getString(R.string.data_wedge_scanner_set_config), profile);
        context.sendBroadcast(intent);
    }

    private Bundle getProfile() {
        Bundle profileBundle = new Bundle();
        profileBundle.putString(context.getString(R.string.data_wedge_profile_name), context.getString(R.string.data_wedge_profile_name_value));
        profileBundle.putString(context.getString(R.string.data_wedge_profile_enabled), context.getString(R.string.data_wedge_profile_enabled_value));
        profileBundle.putString(context.getString(R.string.data_wedge_profile_config_mode), context.getString(R.string.data_wedge_profile_config_mode_value));
        return profileBundle;
    }

    private Bundle getConfig() {
        Bundle confingBundle = new Bundle();
        confingBundle.putString(context.getString(R.string.data_wedge_plugin_name), context.getString(R.string.data_wedge_plugin_name_value));
        confingBundle.putString(context.getString(R.string.data_wedge_reset_config), context.getString(R.string.data_wedge_reset_config_value));
        return confingBundle;
    }

    private Bundle getScannerStatus() {
        Bundle scannerStatus = new Bundle();
        scannerStatus.putString(context.getString(R.string.data_wedge_scanner_selection), context.getString(R.string.data_wedge_scanner_selection_value));
        scannerStatus.putString(context.getString(R.string.data_wedge_scanner_input_enabled), context.getString(R.string.data_wedge_scanner_input_enabled_value));
        return scannerStatus;
    }

    private Bundle getSimulScanStatus() {
        Bundle simulScanInputStatus = new Bundle();
        simulScanInputStatus.putString(context.getString(R.string.data_wedge_simulscan_input_enabled), context.getString(R.string.data_wedge_simulscan_input_enabled_disable));
        return simulScanInputStatus;
    }

    private Bundle getVoiceInputStatus() {
        Bundle voiceOutputStatus = new Bundle();
        voiceOutputStatus.putString(context.getString(R.string.data_wedge_voice_output_enabled), context.getString(R.string.data_wedge_voice_output_enabled_disable));
        return voiceOutputStatus;
    }

    private Bundle getKeyStrokeOutputStatus() {
        Bundle keyStokeOutputStatus = new Bundle();
        keyStokeOutputStatus.putString(context.getString(R.string.data_wedge_keystroke_output_enabled), context.getString(R.string.data_wedge_keystroke_output_enabled_disable));
        return keyStokeOutputStatus;
    }

    private Bundle getIpOutputStatus() {
        Bundle ipOutputStatus = new Bundle();
        ipOutputStatus.putString(context.getString(R.string.data_wedge_ip_output_enabled), context.getString(R.string.data_wedge_ip_output_enabled_disable));
        return ipOutputStatus;
    }

    private Bundle getIntentOutputStatus() {
        Bundle intentOutputStatus = new Bundle();
        intentOutputStatus.putString(context.getString(R.string.data_wedge_intent_output_enabled), context.getString(R.string.data_wedge_intent_output_enabled_enable));
        intentOutputStatus.putString(context.getString(R.string.data_wedge_intent_action), context.getString(R.string.data_wedge_intent_action_value));
        intentOutputStatus.putString(context.getString(R.string.data_wedge_intent_category), context.getString(R.string.data_wedge_intent_category_value));
        intentOutputStatus.putString(context.getString(R.string.data_wedge_intent_delivery), context.getString(R.string.data_wedge_intent_delivery_broadcast));
        return intentOutputStatus;
    }
}
