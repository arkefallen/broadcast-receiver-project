package com.example.broadcastreceiverproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity<ACTION_CUSTOM_BROADCAST> extends AppCompatActivity {
    Button sendButton;
    private String ACTION_CUSTOM_BROADCAST = "ACTION_CUSTOM_BROADCAST";

    // Bikin broadcast
    private static CustomReceiver customReceiver = new CustomReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Definisikan trigger nya pakai intent filter
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        // Daftarkan dulu customreceiver kita dengan intent filter yang akan mentrigger
        registerReceiver(customReceiver,intentFilter);

        sendButton = findViewById(R.id.send_btn);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent broadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
                broadcastIntent.putExtra("data","This is custom broadcast");
                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(broadcastIntent);
            }
        });

        // Definisikan custom boradcast kita dengan intent filter
        IntentFilter customIntent = new IntentFilter(ACTION_CUSTOM_BROADCAST);

        // Daftarkan custom broadcast menggunakan LocalBroadCastManager
        LocalBroadcastManager.getInstance(this).registerReceiver(customReceiver,customIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(customReceiver);
    }
}