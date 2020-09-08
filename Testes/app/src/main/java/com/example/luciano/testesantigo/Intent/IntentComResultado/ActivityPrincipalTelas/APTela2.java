package com.example.luciano.testesantigo.Intent.IntentComResultado.ActivityPrincipalTelas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luciano.testes.R;

public class APTela2 extends Activity {
    protected final int RESULTADO = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aptela2);
    }
    public void enviarOK(View v) {
        final EditText campo = (EditText) findViewById(R.id.campoTexto);
        String s = campo.getText().toString();

        if(s.isEmpty()){
            Toast.makeText(this, "Nada foi digitado. Digite uma frase", Toast.LENGTH_LONG).show();
        } else {
            Intent it = new Intent();
            it.putExtra("msg2", s);
            setResult(RESULTADO, it);
            finish();
        }
    }
}
