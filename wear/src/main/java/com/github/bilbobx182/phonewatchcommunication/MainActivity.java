package com.github.bilbobx182.phonewatchcommunication;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;

public class MainActivity extends WearableActivity implements  MessageClient.OnMessageReceivedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Wearable.getMessageClient(this).addListener(this);
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("MAINACTIVITY","RECIEVED");
        TextView text = findViewById(R.id.myTextView);
        String income = new String(messageEvent.getData());
        text.setText(income);
    }

    @Override
    public void onResume() {
        super.onResume();
        Wearable.getMessageClient(this).addListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Wearable.getMessageClient(this).removeListener(this);
    }
}
