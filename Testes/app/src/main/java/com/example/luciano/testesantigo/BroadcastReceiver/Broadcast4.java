package com.example.luciano.testesantigo.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Broadcast4 extends BroadcastReceiver {
    public Broadcast4() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent it = new Intent(context, AcB.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
    }
}
