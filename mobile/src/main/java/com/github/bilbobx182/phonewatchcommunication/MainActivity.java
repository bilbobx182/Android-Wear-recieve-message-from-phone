package com.github.bilbobx182.phonewatchcommunication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;

import java.util.Set;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final String SET_MESSAGE_CAPABILITY = "setString";
    public static final String SET_MESSAGE_PATH = "/setString";
    private static String transcriptionNodeId;
    GoogleApiClient mApiClient;
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupVoiceTranscription();
        enter = findViewById(R.id.sendButton);
        enter.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setupVoiceTranscription();
            }
        });

    }

    private void setupVoiceTranscription() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                CapabilityInfo capabilityInfo;
                try {
                    capabilityInfo = Tasks.await(
                            Wearable.getCapabilityClient(getBaseContext()).getCapability(
                                    SET_MESSAGE_CAPABILITY, CapabilityClient.FILTER_REACHABLE));
                    // capabilityInfo has the reachable nodes with the transcription capability
                    updateTranscriptionCapability(capabilityInfo);
                    requestTranscription("hello".getBytes());

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


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

    private void requestTranscription(final byte[] message) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final byte[] finalMessage = message;
                if (transcriptionNodeId != null) {
                    final Task<Integer> sendTask = Wearable.getMessageClient(getBaseContext()).sendMessage(transcriptionNodeId, SET_MESSAGE_PATH, finalMessage);

                    try {
                        Tasks.await(sendTask);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApiClient.disconnect();
    }
}
