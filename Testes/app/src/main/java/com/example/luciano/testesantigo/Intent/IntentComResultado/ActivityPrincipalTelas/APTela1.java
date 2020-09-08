package com.example.luciano.testesantigo.Intent.IntentComResultado.ActivityPrincipalTelas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class APTela1 extends Activity {
    protected final int RESULTADO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView tv = new TextView(this);
        tv.setText("\n\n\n\n\n\t\t\tEsta é a tela 1...");

        setContentView(tv);
    }
    @Override
    public void finish() {

        Intent it = new Intent();
        it.putExtra("msg", "Mensagem enviada pela Tela 1 por meio do metodo setResult");
        setResult(RESULTADO, it);

        super.finish();
    }
}
