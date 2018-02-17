package com.github.bilbobx182.phonewatchcommunication;

import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by CiaranLaptop on 17/02/2018.
 */

public class MessageListener extends WearableListenerService {
    private static final String START_ACTIVITY = "/setString";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("cIARAN", "CIARAN");
    }
}
