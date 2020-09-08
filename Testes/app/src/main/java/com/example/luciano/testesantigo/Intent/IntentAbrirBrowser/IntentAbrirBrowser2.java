package com.example.luciano.testesantigo.Intent.IntentAbrirBrowser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.luciano.testes.R;

public class IntentAbrirBrowser2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_abrir_browser2);

        Button buttonIr = (Button) findViewById(R.id.buttonIrbrowser);

        buttonIr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final EditText ETcampo = (EditText) findViewById(R.id.ETcampoBrowser);
                String enderecoURL = ETcampo.getText().toString();
                Uri url = Uri.parse(enderecoURL);

                Intent ir = new Intent(Intent.ACTION_VIEW, url);
                startActivity(ir);
            }
        });
    }
}
