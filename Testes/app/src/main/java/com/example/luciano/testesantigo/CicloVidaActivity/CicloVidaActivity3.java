package com.example.luciano.testesantigo.CicloVidaActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class CicloVidaActivity3 extends CicloVidaActivity1 {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView tela = new TextView(this);
        tela.setText("Verificar Logs");
        Intent it = getIntent();
        Log.i(CATEGORIA, "Bundle intent: " + it);
        Log.i(CATEGORIA, "Extras intent: " + it.getStringExtra("msg"));

        setContentView(tela);
    }
}
