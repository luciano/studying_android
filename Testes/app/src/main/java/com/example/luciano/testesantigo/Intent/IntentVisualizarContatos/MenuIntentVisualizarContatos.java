package com.example.luciano.testesantigo.Intent.IntentVisualizarContatos;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luciano.testes.R;

public class MenuIntentVisualizarContatos extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] opcoes = new String[] {
                "IntentVisualizarContato1",
                "IntentVisualizarContatos",
                "VizualizarContatosSelecionar",
                "Voltar"
        };

        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_menu_intent_ligar_telefone, opcoes));
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v , position, id);

        switch(position) {
            case 0:
                //pega primeiro contato
                Uri contato1 = Uri.parse("content://com.android.contacts/contacts/1");
                Intent it = new Intent(Intent.ACTION_VIEW, contato1);
                startActivity(it);
                break;
            case 1:
                //mostra todos os contatos
                Uri contatos = Uri.parse("content://com.android.contacts/contacts/");
                Intent it1 = new Intent(Intent.ACTION_PICK, contatos);
                startActivity(it1);
                break;
            case 2:
                startActivity(new Intent(this, VizualizarContatosSelecionar.class));
                break;
            default:
                finish();
        }
    }
}
