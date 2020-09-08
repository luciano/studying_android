package com.example.luciano.testesantigo.IntentFilter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class IntentFilter2 extends Activity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);

        LinearLayout layout = new LinearLayout(this);

        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        layout.setOrientation(LinearLayout.VERTICAL);

        layout.setBackgroundColor(Color.GREEN);

        final TextView texto = new TextView(this);
        final String msg = "\n\nActivity usa action ActivityIF1\n" + "\n\nUsando category DEFAULT\n";

        texto.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        texto.setText(msg);
        texto.setGravity(Gravity.CENTER);
        texto.setTextColor(Color.BLUE);
        layout.addView(texto);

        final Button bt = new Button(this);
        bt.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        bt.setText("Voltar");
        bt.setTextColor(Color.WHITE);
        bt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto.setText("Voltando a tela anterior");
                texto.setTextColor(Color.BLACK);
                texto.setBackgroundColor(Color.GRAY);
                finish();
            }
        });

        layout.addView(bt);

        setContentView(layout);
    }
}
