package com.example.luciano.testesantigo.IntentFilter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class IntentFilter4 extends Activity {

    private int n;

    @Override
    protected void onCreate(Bundle iclice) {
        super.onCreate(iclice);

        LinearLayout layoutTela = new LinearLayout(this);
        layoutTela.setOrientation(LinearLayout.VERTICAL);
        layoutTela.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layoutTela.setBackgroundColor(Color.WHITE);

        ViewGroup.LayoutParams viewP = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);

        final View v1 = new View(this);
        v1.setLayoutParams(viewP);
        v1.setPadding(20, 100, 20, 10);
        v1.setBackgroundColor(Color.BLUE);

        final View v2 = new View(this);
        v2.setLayoutParams(viewP);
        v2.setPadding(20, 20, 20, 10);
        v2.setBackgroundColor(Color.BLUE);

        final Button bt = new Button(this);
        bt.setText("Chamar Activity IntentFilter1");
        bt.setTextColor(Color.GREEN);
        bt.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent("ActivityIF1");
                startActivity(it);
                setN(1);
            }
        });
        bt.setPadding(10, 15, 10, 15);

        layoutTela.addView(v1);
        layoutTela.addView(bt);
        layoutTela.addView(v2);

        setContentView(layoutTela);
        setN(0);
    }

    public void finish() {
        setResult(getN(), new Intent());
        super.finish();
    }

    private int getN() {
        return n;
    }

    private void setN(int n) {
        this.n = n;
    }
}
