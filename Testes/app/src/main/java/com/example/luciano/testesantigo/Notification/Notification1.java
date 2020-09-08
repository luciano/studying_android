package com.example.luciano.testesantigo.Notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.PendingIntent;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.luciano.testes.R;

public class Notification1 extends Activity {

    private static final int IDENT_NOT1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView tv = new TextView(this);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        tv.setPadding(30, 45, 30, 45);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundColor(Color.MAGENTA);
        tv.setText("Exemplo de notificacao usando construtor da classe Notificacao (deprecated API 11)\n\nE usando setLatesEventInfo(contexto, titulo, texto, intent) tambem deprecated.");


        NotificationManager nmPricipal = (NotificationManager) getSystemService(Activity.NOTIFICATION_SERVICE);

        Notification notificacao = new Notification(R.drawable.ic_launcher, "Notificacao recebida", System.currentTimeMillis());

        Intent it = new Intent(this, Tela.class);
        //it.putExtra("identificador", IDENT_NOT1);
        PendingIntent pI = PendingIntent.getActivity(this, 0, it, 0);

        notificacao.setLatestEventInfo(this, "Notificacao Teste", "Texto da notificacao para o usuario visualizar", pI);

        nmPricipal.notify(IDENT_NOT1, notificacao);

        setContentView(tv);
    }
}
