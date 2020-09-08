package com.example.luciano.testesantigo.Intent.IntentAbrirMapa;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuIntentAbrirMapa extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String opcoes[] = new String[] {
                "AbrirMapaDiamantina",
                "PesquisarLocal",
                "CoordenadaPrecisa",
                "PercusoEntreCidadesDefinidas",
                "Voltar"
        };

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes);

        this.setListAdapter(adaptador);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch(position) {
            case 0:
                Uri diamantina = Uri.parse("geo:0,0?q=diamantina");
                startActivity(new Intent(Intent.ACTION_VIEW, diamantina));
                break;
            case 1:
                startActivity(new Intent(this, PesquisaLocal.class));
                break;
            case 2:
                //olhei no maps.google.com as coordenadas precisas de couto
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-18.0666619,-43.4166669")));
                break;
            case 3:
                String coutoPartida = "-18.0665819,-43.4536609";
                String diamantinaDestino = "-18.2417015,-43.6404995";
                Uri urlMapa = Uri.parse("http://maps.google.com/maps?f=d&saddr=" + coutoPartida + "&daddr=" + diamantinaDestino + "&hl=pt");
                startActivity(new Intent(Intent.ACTION_VIEW, urlMapa));
                break;
            default:
                finish();
        }
    }
}
