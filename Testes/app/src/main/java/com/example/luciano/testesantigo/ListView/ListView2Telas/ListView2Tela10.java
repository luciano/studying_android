package com.example.luciano.testesantigo.ListView.ListView2Telas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ListView2Tela10 extends Activity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        TextView TV = new TextView(this);
        TV.setText("\n\n\n\tEsta é a Tela 10!!!\n");

        setContentView(TV);
    }
}