package com.example.luciano.testesantigo.IntentFilter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class IntentFilter1 extends Activity {
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        final TextView text = new TextView(this);

        text.setText("\n\nActivity usa action ActivityIF1\n"
                   + "\nUsando category DEFAULT\n");

        text.setTextColor(Color.BLACK);
        text.setBackgroundColor(0xffff0000);

        text.setPadding(10, 10, 10, 10);

        setContentView(text);

    }
}
