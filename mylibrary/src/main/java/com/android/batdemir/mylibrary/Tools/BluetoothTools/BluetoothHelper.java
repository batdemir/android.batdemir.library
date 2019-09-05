package com.android.batdemir.mylibrary.Tools.BluetoothTools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import java.lang.reflect.Method;

@SuppressLint("MissingPermission")
public class BluetoothHelper {

    private int REQUEST_ENABLE_BT = 1;
    private int REQUEST_DISABLE_BT = 2;
    private BluetoothAdapter bluetoothAdapter;

    public BluetoothHelper(BluetoothAdapter bluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter;
    }

    public void request_bluetoothOn(Activity activity) {
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    public void request_bluetoothOff() {
        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
        }
    }

    public void pairUnPair_Device(BluetoothDevice device) {
        try {
            if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                Method method = device.getClass().getMethod("createBond", (Class[]) null);
                method.invoke(device, (Object[]) null);
            } else {
                Method method = device.getClass().getMethod("removeBond", (Class[]) null);
                method.invoke(device, (Object[]) null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
