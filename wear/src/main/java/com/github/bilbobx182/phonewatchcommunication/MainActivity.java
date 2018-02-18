package com.github.bilbobx182.phonewatchcommunication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;

public class MainActivity extends WearableActivity implements DataClient.OnDataChangedListener, MessageClient.OnMessageReceivedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Wearable.getDataClient(this).addListener(this);
        Wearable.getMessageClient(this).addListener(this);
    }

    @Override
    public void onDataChanged(@NonNull DataEventBuffer dataEventBuffer) {
        Log.d("MainActivity","MessageRecieved");
        updateText();
    }

    private void updateText() {
        TextView text = findViewById(R.id.myTextView);
        text.setText("IT WORKED!");
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("MAINACTIVITY","RECIEVED");
        TextView text = findViewById(R.id.myTextView);
        String income = new String(messageEvent.getData());
        text.setText(income);


        // Check to see if the message is to start an activity
    }

    @Override
    public void onResume() {
        super.onResume();
        Wearable.getDataClient(this).addListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Wearable.getDataClient(this).removeListener(this);
    }
}
