package com.example.luciano.testesantigo.IntentFilter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class IntentFilter5 extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle iclice) {
        super.onCreate(iclice);

        Button bt = new Button(this);
        bt.setText("Tela da Activity IntentFilter5");
        bt.setTextColor(Color.MAGENTA);
        bt.setBackgroundColor(Color.BLUE);
        bt.setPadding(10, 15, 10, 15);
        bt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        bt.setOnClickListener(this);

        setContentView(bt);
    }

    public void onClick(View v) {
        setResult(1, null);
        finish();
    }
}
