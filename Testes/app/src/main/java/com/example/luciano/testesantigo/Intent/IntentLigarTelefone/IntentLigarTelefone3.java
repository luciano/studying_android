package com.example.luciano.testesantigo.Intent.IntentLigarTelefone;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.luciano.testes.R;

public class IntentLigarTelefone3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_ligar_telefone3);
    }

    public void ligarTel(View v) {
        final EditText tel = (EditText) findViewById(R.id.campoNumero);
        Uri numero = Uri.parse("tel:" + tel.getText().toString());

        startActivity(new Intent(Intent.ACTION_CALL, numero));
    }
}
