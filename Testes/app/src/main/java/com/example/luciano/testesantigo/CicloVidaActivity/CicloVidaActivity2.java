package com.example.luciano.testesantigo.CicloVidaActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CicloVidaActivity2 extends CicloVidaActivity1 {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Button tela = new Button(this);
        tela.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(CicloVidaActivity2.this, CicloVidaActivity3.class);
                it.putExtra("msg", "Mensagem enviada via Bundle activity1.");
                Log.i(CATEGORIA, "Enviou Intent");
                startActivity(it);
            }
        });
        tela.setText("Chamar nova Tela");

        setContentView(tela);
    }
}
