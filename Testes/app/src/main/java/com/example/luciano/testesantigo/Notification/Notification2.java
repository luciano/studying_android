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

public class Notification2 extends Activity {

    private static final int IDENT_NOT2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Notification.Builder nb = new Notification.Builder(this)
                .setTicker("Notificacao Builder")
                .setContentTitle("Teste Builder")
                .setContentText("Usando Builder para API 11 ou maior")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));;

        Intent it2 = new Intent(this, Tela.class);
        //it2.putExtra("identificador", IDENT_NOT2);
        PendingIntent pI = PendingIntent.getActivity(this, 0, it2, 0);

        nb.setContentIntent(pI);

        nb.setProgress(100, 70, false);

        NotificationManager nm = (NotificationManager) getSystemService(Activity.NOTIFICATION_SERVICE);

        Notification n = nb.getNotification();
        nm.notify(IDENT_NOT2, n);//deprecated API 17

        final TextView tv = new TextView(this);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        tv.setPadding(30, 45, 30, 45);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundColor(Color.GREEN);
        tv.setText("Exemplo de notificacao usando Notificacao.Builder\n\nE usando Builder.getNotification() (Deprecated API 17).");
        setContentView(tv);
    }
}
