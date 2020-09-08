package com.example.luciano.testesantigo.Notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.luciano.testes.R;

public class Notification3 extends Activity {

    private static final int IDENT_NOT3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent it3 = new Intent(this, Tela.class);
        //it3.putExtra("identificador", IDENT_NOT3);

        PendingIntent p = PendingIntent.getActivity(this, 0, it3, 0);


        Notification.Builder nb = new Notification.Builder(this);
        nb.setTicker("Notificacao API 17");
        nb.setContentTitle("Testando API 17 acima");
        nb.setContentText("Notificacao para API acima da 17");
        nb.setContentIntent(p);
        nb.setSmallIcon(R.drawable.ic_launcher);
        nb.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));


        Notification.InboxStyle estilo = new Notification.InboxStyle();

        estilo.setBigContentTitle("Titulo estilo");

        String linhas[] = new String[] {"Linha1", "Linha2", "Linha3", "Linha4", "Linha5"};

        for(String linha: linhas){
            estilo.addLine(linha);
        }

        nb.setStyle(estilo);

        nb.setProgress(100, 30, true);

        NotificationManager nm = (NotificationManager) getSystemService(Activity.NOTIFICATION_SERVICE);

        Notification n = nb.build();
        nm.notify(IDENT_NOT3, n);

        final TextView tv = new TextView(this);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        tv.setPadding(30, 45, 30, 45);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundColor(Color.CYAN);
        tv.setText("Exemplo de notificacao usando Notificacao.Builder\n\nE usando Builder.build().");
        setContentView(tv);
    }
}
