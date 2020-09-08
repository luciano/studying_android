package com.example.luciano.testesantigo.IntentFilter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class IntentFilter6 extends Activity {

    @Override
    protected void onCreate(Bundle iclice) {
        super.onCreate(iclice);

        sendBroadcast(new Intent("Broadcast2"));

        setResult(1, null);
    }
}
