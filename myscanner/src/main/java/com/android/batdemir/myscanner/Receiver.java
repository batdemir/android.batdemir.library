package com.android.batdemir.myscanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import java.nio.channels.NotYetBoundException;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String receivedBarcode = intent.getStringExtra(Resources.getSystem().getString(R.string.data_wedge_default_data_string));
        ReceiverListener receiverListener = (ReceiverListener) context;
        if (receiverListener != null)
            receiverListener.onReceived(receivedBarcode);
        else
            throw new NotYetBoundException();
    }
}
