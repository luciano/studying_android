package com.example.luciano.testesantigo.BroadcastReceiver;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class AcB extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView t = new TextView(this);
        t.setText("Nova activity chamada pelo Broadcast");
        t.setGravity(Gravity.CENTER);
        t.setBackgroundColor(Color.WHITE);
        t.setTextColor(Color.RED);

        setContentView(t);
    }

}
