package com.example.luciano.testesantigo.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Broadcast3 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String s = intent.getStringExtra("text");

        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}
