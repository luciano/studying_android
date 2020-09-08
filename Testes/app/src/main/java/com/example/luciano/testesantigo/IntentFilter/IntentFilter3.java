package com.example.luciano.testesantigo.IntentFilter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class IntentFilter3 extends Activity {

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        final TextView text = new TextView(this);

        Intent it = getIntent();
        Bundle extra = it.getExtras() != null ? it.getExtras() : null;
        if(extra != null) {
            String s = extra.getString("msg");

            text.setText(s);
            text.setTextColor(Color.BLACK);
            text.setBackgroundColor(Color.MAGENTA);
            text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            text.setGravity(Gravity.CENTER);
        } else {
            text.setText("Nenhum paramentro foi passado para a Intent");
            text.setTextColor(Color.YELLOW);
            text.setBackgroundColor(Color.RED);
            text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            text.setGravity(Gravity.CENTER);
        }

        setContentView(text);
    }
}
