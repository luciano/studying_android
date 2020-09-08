package com.example.luciano.testesantigo.Intent.IntentLigarTelefone;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luciano.testes.R;

public class MenuIntentLigarTelefone extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] opcoes = new String[] {
                "IntentLigarTelefone1",
                "IntentLigarTelefone2",
                "IntentLigarTelefone3",
                "Voltar"
        };

        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_menu_intent_ligar_telefone, opcoes));
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v , position, id);

        switch(position) {
            case 0:
                //liga diretamente para numero
                Uri number = Uri.parse("tel:0153899444287");
                Intent it = new Intent(Intent.ACTION_CALL, number);
                startActivity(it);
                break;
            case 1:
                //mostra numero na tela para confirmar ligacao
                Uri number1 = Uri.parse("tel:0153899444287");
                Intent it1 = new Intent(Intent.ACTION_DIAL, number1);
                startActivity(it1);
                break;
            case 2:
                break;
            default:
                finish();
        }
    }
}
