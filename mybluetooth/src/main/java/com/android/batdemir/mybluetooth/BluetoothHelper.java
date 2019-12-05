package com.android.batdemir.mybluetooth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.Method;

@SuppressLint("MissingPermission")
public class BluetoothHelper {

    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter bluetoothAdapter;

    public BluetoothHelper(BluetoothAdapter bluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter;
    }

    public void requestBluetoothOn(Activity activity) {
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    public void requestBluetoothOff() {
        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
        }
    }

    public void pairUnPairDevice(BluetoothDevice device) {
        try {
            if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                Method method = device.getClass().getMethod("createBond", (Class[]) null);
                method.invoke(device, (Object[]) null);
            } else {
                Method method = device.getClass().getMethod("removeBond", (Class[]) null);
                method.invoke(device, (Object[]) null);
            }
        } catch (Exception e) {
            Log.e(BluetoothHelper.class.getSimpleName(), e.getMessage());
        }
    }
}
