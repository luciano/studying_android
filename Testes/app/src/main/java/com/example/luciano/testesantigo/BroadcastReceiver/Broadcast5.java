package com.example.luciano.testesantigo.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Broadcast5 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Broadcast registrado pela API", Toast.LENGTH_LONG).show();
    }
}
