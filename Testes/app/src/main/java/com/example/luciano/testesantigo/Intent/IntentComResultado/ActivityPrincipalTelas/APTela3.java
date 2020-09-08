package com.example.luciano.testesantigo.Intent.IntentComResultado.ActivityPrincipalTelas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luciano.testes.R;

public class APTela3 extends Activity {
    protected final int RESULTADO1 = 1;
    protected final int RESULTADO2 = 2;
    protected final int RESULTADO3 = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aptela3);
    }
    public void enviarOK1(View v) {
        final EditText campo1 = (EditText) findViewById(R.id.campoTexto1);
        final EditText campo2 = (EditText) findViewById(R.id.campoTexto2);
        String s1 = campo1.getText().toString();
        String s2 = campo2.getText().toString();

        if(s1.isEmpty() && s2.isEmpty()){

            Toast.makeText(this, "Nada foi digitado. Preencha os campos!", Toast.LENGTH_LONG).show();
        } else if (s2.isEmpty()) {

            Intent it = new Intent();
            it.putExtra("fist", s1);
            setResult(RESULTADO1, it);
            finish();
        } else if (s1.isEmpty()) {

            Intent it = new Intent();
            it.putExtra("last", s2);
            setResult(RESULTADO2, it);
            finish();
        } else {
            Intent it = new Intent();
            it.putExtra("full", s1 + " " + s2);
            setResult(RESULTADO3, it);
            finish();
        }
    }
}
