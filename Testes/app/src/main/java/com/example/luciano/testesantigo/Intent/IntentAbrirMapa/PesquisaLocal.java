package com.example.luciano.testesantigo.Intent.IntentAbrirMapa;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.luciano.testes.R;

public class PesquisaLocal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_local);

        Button buttonMapa1 = (Button) findViewById(R.id.buttonMapa1);
        buttonMapa1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                final EditText campoMapa = (EditText) findViewById(R.id.campoMapa1);
                String endereco = campoMapa.getText().toString();
                endereco.replace(' ', '+');
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + endereco)));
            }
        });
    }
}
