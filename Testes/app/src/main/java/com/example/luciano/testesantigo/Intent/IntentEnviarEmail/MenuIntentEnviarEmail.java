package com.example.luciano.testesantigo.Intent.IntentEnviarEmail;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luciano.testes.R;

public class MenuIntentEnviarEmail extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String opcoes[] = new String[] {
                "Enviar email pre definido",
                "Voltar"
        };

        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes));
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        switch(position) {
            case 0:
                //Email - ERRO
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Titulo do email teste");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Ola msg enviada pela Intent.ACTION_SEND Android");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, "lucianodtna@gmail.com");
                //emailIntent.setType("message/rfc822");
                startActivity(emailIntent);
                break;
            default:
                finish();
        }
    }
}
