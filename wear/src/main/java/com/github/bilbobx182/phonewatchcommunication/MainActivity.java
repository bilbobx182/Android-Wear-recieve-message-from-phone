package com.github.bilbobx182.phonewatchcommunication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;

import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;

import java.util.Set;

public class MainActivity extends WearableActivity implements MessageClient.OnMessageReceivedListener {

    private static final String SET_MESSAGE_CAPABILITY = "setMessage";
    public static final String SET_MESSAGE_PATH = "/setMessage";
    private static String transcriptionNodeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupVoiceTranscription();
    }

    private void setupVoiceTranscription() {

        // This example uses a Java 8 Lambda. Named or anonymous classes can also be used.
        CapabilityClient.OnCapabilityChangedListener capabilityListener =
                capabilityInfo -> {
                    updateTranscriptionCapability(capabilityInfo);
                };
        Wearable.getCapabilityClient(this).addListener(
                capabilityListener,
                SET_MESSAGE_CAPABILITY);
    }

    private void updateTranscriptionCapability(CapabilityInfo capabilityInfo) {
        Set<Node> connectedNodes = capabilityInfo.getNodes();

        transcriptionNodeId = pickBestNodeId(connectedNodes);
    }

    private String pickBestNodeId(Set<Node> nodes) {
        String bestNodeId = null;
        // Find a nearby node or pick one arbitrarily
        for (Node node : nodes) {
            if (node.isNearby()) {
                return node.getId();
            }
            bestNodeId = node.getId();
        }
        return bestNodeId;
    }


    @Override
    public void onMessageReceived(@NonNull MessageEvent messageEvent) {
//        if (messageEvent.getPath().equals(VOICE_TRANSCRIPTION_MESSAGE_PATH)) {
//            Intent startIntent = new Intent(this, MainActivity.class);
//            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startIntent.putExtra("VOICE_DATA", messageEvent.getData());
//            startActivity(startIntent);
        Log.d("CIARAN", "ciaran");
    }


}
