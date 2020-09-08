package com.example.luciano.testesantigo.Intent.IntentAbrirBrowser;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luciano.testes.R;

public class MenuIntentAbrirBrowser extends ListActivity {

    private ArrayAdapter<String> adaptador;
    private String opcoes[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_intent_abrir_browser);

        opcoes= new String[] {
                "IntentAbrirBrowser1",
                "IntentAbrirBrowser2",
                "Voltar"
        };

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes);

        setListAdapter(adaptador);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        switch(position) {
            case 0:
                //abre diretamento o Google
                Uri uri = Uri.parse("https://www.google.com");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
                break;
            case 1:
                startActivity(new Intent(this, IntentAbrirBrowser2.class));
                break;
            case 2:
                startActivity(new Intent(MenuIntentAbrirBrowser.this, IntentAbrirBrowser3.class));
                break;
            default:
                finish();
        }
    }
}
