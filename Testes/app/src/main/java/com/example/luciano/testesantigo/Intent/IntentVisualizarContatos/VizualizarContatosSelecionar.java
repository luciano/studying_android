package com.example.luciano.testesantigo.Intent.IntentVisualizarContatos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.luciano.testes.R;

public class VizualizarContatosSelecionar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setText("Abrir Contatos");
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Uri contatos = Uri.parse("content://com.android.contacts/contacts/");
                Intent it = new Intent(Intent.ACTION_PICK, contatos);
                startActivityForResult(it, 1);
            }
        });
        setContentView(button);
    }
    @Override
    protected void onActivityResult(int codigo, int resultado, Intent it) {
        if(codigo == 1) {
            if(it != null) {
                final Uri contato = it.getData();
                startActivity(new Intent(Intent.ACTION_VIEW, contato));
            }
            else {
                Toast.makeText(this, "Nenhum contato selecionado!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
