package com.example.luciano.testesantigo.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class Broadcast2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        LinearLayout layoutTela = new LinearLayout(context);
        layoutTela.setOrientation(LinearLayout.VERTICAL);
        layoutTela.setPadding(10, 10, 10, 10);
        layoutTela.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layoutTela.setBackgroundColor(Color.BLUE);

        TextView tv = new TextView(context);
        tv.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        tv.setText("\nUsando a API para fazer um layout");
        tv.setTextColor(Color.MAGENTA);
        tv.setGravity(Gravity.CENTER);

        layoutTela.addView(tv);

        Toast t = new Toast(context);
        t.setView(layoutTela);
        t.setDuration(Toast.LENGTH_LONG);
        t.show();

    }
}
