package com.example.luciano.testesantigo.Intent.IntentEnviarSMS;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luciano.testes.R;

public class enviarSMS1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_sms1);

        final Button smsEnviar = (Button) findViewById(R.id.smsEnviar);

        smsEnviar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                final EditText smsNumero = (EditText) findViewById(R.id.smsNumero);
                final EditText smsTexto = (EditText) findViewById(R.id.smsTexto);

                String numero = smsNumero.getText().toString();
                String texto = smsTexto.getText().toString();

                if(numero.isEmpty()) {
                    Toast.makeText(enviarSMS1.this, "Digite um numero de telefone para enviar.", Toast.LENGTH_SHORT).show();
                } else {
                    if(texto.isEmpty()) {
                        Toast.makeText(smsEnviar.getContext(), "Digite um texto para enviar na mensagem.", Toast.LENGTH_SHORT).show();
                    } else {
                        Uri number = Uri.parse("smsto:" + numero);
                        Intent it = new Intent(Intent.ACTION_SENDTO, number);
                        it.putExtra("sms_body", texto);
                        startActivity(it);
                    }
                }
            }
        });
    }
}
