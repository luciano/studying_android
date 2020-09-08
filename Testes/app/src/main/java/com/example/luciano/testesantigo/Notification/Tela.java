package com.example.luciano.testesantigo.Notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tela extends Activity implements View.OnClickListener {

    protected static int IDENTIFICADOR;
    private Button btN, btA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent it = getIntent();

        //IDENTIFICADOR = it.getIntExtra("identificador", 0);

        //Log.i("Depuracao","Identificador = " + IDENTIFICADOR);

        LinearLayout layout = getLayout();

        setContentView(layout);
    }

    private LinearLayout getLayout() {
        LinearLayout l = new LinearLayout(this);

        l.setOrientation(LinearLayout.VERTICAL);
        l.setBackgroundColor(Color.BLUE);
        l.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        l.setPadding(15, 15, 15, 15);

        TextView tv = new TextView(this);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.WHITE);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv.setPadding(15, 30, 15, 30);
        tv.setText("A notificacao foi aberta!\nClique em um dos botoes para encerra-la.");
        tv.setTextSize(20);

        l.addView(tv);

        btN = new Button(this);
        btN.setText("Encerrar notificação");
        btN.setTextColor(Color.BLACK);
        btN.setPadding(15, 15, 15, 15);
        btN.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btN.setOnClickListener(this);

        l.addView(btN);

        btA = new Button(this);
        btA.setText("Encerrar activity");
        btA.setTextColor(Color.BLACK);
        btA.setPadding(15, 15, 15, 15);
        btA.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btA.setOnClickListener(this);

        l.addView(btA);

        return l;
    }

    @Override
    public void onClick(View v) {

        Log.i("Depuracao", "btN.isPressed() = " + btN.isPressed());
        Log.i("Depuracao", "btA.isPressed() = " + btA.isPressed());

        if(btN.isPressed()) {
            //Log.i("depuracao", "Entrou no if");

            NotificationManager nm = (NotificationManager) getSystemService(Activity.NOTIFICATION_SERVICE);
            //cancela de acordo com activity chamadora
            //nm.cancel(IDENTIFICADOR);

            nm.cancelAll();
        }
        else if(btA.isPressed()) {
            finish();
        }
    }
}
