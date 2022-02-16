package com.example.broadcastreceiverproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    String msg = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        // Untuk mengetahui event apa yang terjadi
        switch (intent.getAction()) {
            case Intent.ACTION_POWER_CONNECTED:
                msg = "The power is connected";
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                msg = "The power is disconnected";
                break;
            case "ACTION_CUSTOM_BROADCAST":
                msg = intent.getStringExtra("data");
                break;
            default:
                break;
        }

        // Memunculkan pesan/notifikasi
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}