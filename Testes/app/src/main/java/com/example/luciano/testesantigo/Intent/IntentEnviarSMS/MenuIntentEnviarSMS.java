package com.example.luciano.testesantigo.Intent.IntentEnviarSMS;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuIntentEnviarSMS extends ListActivity{

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String[] opcoes = new String[] {
                "Enviar SMS pre Definido1",
                "Enviar SMS pre Definido2",
                "Enviar SMS pre Definido3",
                "Enviar SMS",
                "Voltar"
        };

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        switch(position) {
            case 0:
                Intent enviarSMS = new Intent(Intent.ACTION_VIEW);
                //enviarSMS.putExtra("address","tel:03899444287");
                enviarSMS.putExtra("address","03899444287");
                enviarSMS.putExtra("sms_body","Mensagem a ser enviada pelo SMS");
                enviarSMS.setType("vnd.android-dir/mms-sms");
                startActivity(enviarSMS);
                break;
            case 1:
                Uri number = Uri.parse("sms:03899444287");
                Intent enviarSMS1 = new Intent(Intent.ACTION_VIEW, number); //erro com SEND
                //enviarSMS1.setData(Uri.parse("sms:03899444287"));
                enviarSMS1.putExtra("sms_body", "Enviando mensagem para APP com VIEW\n" +
                        ":)");
                startActivity(enviarSMS1);
                break;
            case 2:
                Intent enviarSMS2 = new Intent(Intent.ACTION_SENDTO);
                enviarSMS2.setData(Uri.parse("smsto:03899444287"));
                enviarSMS2.putExtra("sms_body", "Enviando mensagem para APP com SENDTO\n:)");
                startActivity(enviarSMS2);
                break;
            case 3:
                startActivity(new Intent(this, enviarSMS1.class));
            default:
                finish();
        }
    }
}
