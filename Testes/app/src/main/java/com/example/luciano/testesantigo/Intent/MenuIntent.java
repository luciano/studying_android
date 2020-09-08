package com.example.luciano.testesantigo.Intent;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luciano.testesantigo.Intent.IntentAbrirBrowser.MenuIntentAbrirBrowser;
import com.example.luciano.testesantigo.Intent.IntentAbrirMapa.MenuIntentAbrirMapa;
import com.example.luciano.testesantigo.Intent.IntentComResultado.MenuIntentComResultado;
import com.example.luciano.testesantigo.Intent.IntentEnviarEmail.MenuIntentEnviarEmail;
import com.example.luciano.testesantigo.Intent.IntentEnviarSMS.MenuIntentEnviarSMS;
import com.example.luciano.testesantigo.Intent.IntentLigarTelefone.MenuIntentLigarTelefone;
import com.example.luciano.testesantigo.Intent.IntentVisualizarContatos.MenuIntentVisualizarContatos;

public class MenuIntent extends ListActivity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String opcoes[] = new String[] {
                "Activity Intent1",
                "IntentAbrirBrowser",
                "IntentLigarTelefone",
                "IntentVisualizarContatos",
                "IntentComResultado",
                "IntentAbrirMapa",
                "IntentEnviarEmail - ERRO",
                "IntentEnviarSMS",
                "Voltar"
        };

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes);

        setListAdapter(ad);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        switch (position) {
            case 0:
                startActivity(new Intent(this, Intent1.class));
                break;
            case 1:
                startActivity(new Intent(this, MenuIntentAbrirBrowser.class));
                break;
            case 2:
                startActivity(new Intent(this, MenuIntentLigarTelefone.class));
                break;
            case 3://visualiza automaticamente os contatos com app Nativo
                startActivity(new Intent(this, MenuIntentVisualizarContatos.class));
                break;
            case 4:
                startActivity(new Intent(this, MenuIntentComResultado.class));
                break;
            case 5://abre mapa do google
                startActivity(new Intent(this, MenuIntentAbrirMapa.class));
                break;
            case 6://chama app nativo de mandar email
                startActivity(new Intent(this, MenuIntentEnviarEmail.class));
                break;
            case 7://chama app nativo de mandar sms
                startActivity(new Intent(this, MenuIntentEnviarSMS.class));
                break;
            default:
                finish();
        }
    }
}