package com.example.luciano.testesantigo.Notification;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuNotification extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = getLayout();

        ListView lista = new ListView(this);
        lista.setAdapter(getAdaptador());

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        startActivity(new Intent("Notification1").addCategory("Notification"));
                        break;
                    case 1:
                        startActivity(new Intent("Notification2").addCategory("Notification"));
                        break;
                    case 2:
                        startActivity(new Intent("Notification3").addCategory("Notification"));
                        break;
                    default:
                        finish();
                }
            }
        });



        layout.addView(lista);
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
        tv.setText("Usando Notificacoes");
        tv.setTextSize(13);

        l.addView(tv);

        ListView list = new ListView(this);
        l.addView(list);

        return l;
    }

    private ListAdapter getAdaptador() {
        String[] opcoes = new String[] {
                "Notificacao1",
                "Notificacao2",
                "Notificacao3",
                "Voltar"
        };

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes);

        return adaptador;
    }
}
