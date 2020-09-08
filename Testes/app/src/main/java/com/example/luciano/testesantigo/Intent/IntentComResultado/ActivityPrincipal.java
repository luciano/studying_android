package com.example.luciano.testesantigo.Intent.IntentComResultado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.luciano.testesantigo.Intent.IntentComResultado.ActivityPrincipalTelas.APTela1;
import com.example.luciano.testesantigo.Intent.IntentComResultado.ActivityPrincipalTelas.APTela2;
import com.example.luciano.testesantigo.Intent.IntentComResultado.ActivityPrincipalTelas.APTela3;
import com.example.luciano.testes.R;

public class ActivityPrincipal extends Activity {
    private final int CODIGO_TELA1 = 1;
    private final int CODIGO_TELA2 = 2;
    private final int CODIGO_TELA3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_principal);

    }
    public void btIntent1(View v) {
        Intent it = new Intent(this, APTela1.class);
        startActivityForResult(it, CODIGO_TELA1);
    }
    public void btIntent2(View v) {
        Intent it = new Intent(this, APTela2.class);
        startActivityForResult(it, CODIGO_TELA2);
    }
    public void btIntent3(View v) {
        Intent it = new Intent(this, APTela3.class);
        startActivityForResult(it, CODIGO_TELA3);
    }
    @Override
    protected void onActivityResult(int codigo, int resultado, Intent it) {
        switch(codigo) {
            case CODIGO_TELA1:
                final TextView tv1 = (TextView) findViewById(R.id.TVretorno);
                tv1.setText(it.getStringExtra("msg"));
                Log.i("depuracao", "Resultado: " + resultado);
                break;
            case CODIGO_TELA2:
                final TextView tv2 = (TextView) findViewById(R.id.TVretorno);
                tv2.setText(it.getStringExtra("msg2"));
                Log.i("depuracao", "Resultado: " + resultado);
                break;
            case CODIGO_TELA3:
                Log.i("depuracao", "Resultado: " + resultado);
                switch (resultado) {
                    case 1:
                        final TextView tv31 = (TextView) findViewById(R.id.TVretorno);
                        tv31.setText("\n\n\n\tSeu primeiro nome é: " + it.getStringExtra("fist"));
                        break;
                    case 2:
                        final TextView tv32 = (TextView) findViewById(R.id.TVretorno);
                        tv32.setText("\n\n\n\tSeu sobrenome é: " + it.getStringExtra("last"));
                        break;
                    case 3:
                        final TextView tv33 = (TextView) findViewById(R.id.TVretorno);
                        tv33.setText("\n\n\n\tSeu nome completo é: " + it.getStringExtra("full"));
                        break;
                }
                break;
        }

    }

}
