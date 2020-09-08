package com.example.luciano.testesantigo.CicloVidaActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.luciano.testes.R;

public class CicloVidaActivity1 extends Activity {

    protected final String CATEGORIA = "depuracao";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_ciclo_vida_activity1);

        Log.i(CATEGORIA, nomeClasse() + ": onCreate chamado.");
        Log.i(CATEGORIA, "Bundle parametro: " + icicle);
    }
    @Override
    protected void onStart() {
        //faz algo e logar
        Log.i(CATEGORIA, nomeClasse() + ": onStart chamado.");
        super.onStart();
    }
    @Override
    protected void onRestart() {
        //faz algo e logar
        Log.i(CATEGORIA, nomeClasse() + ": onRestart chamado.");
        super.onRestart();
    }
    @Override
    protected void onResume() {
        //faz algo e logar
        Log.i(CATEGORIA, nomeClasse() + ": onResume chamado.");
        super.onResume();
    }
    @Override
    protected void onPause() {
        //faz algo e logar
        Log.i(CATEGORIA, nomeClasse() + ": onPause chamado.");
        super.onPause();
    }
    @Override
    protected void onStop() {
        //faz algo e logar
        Log.i(CATEGORIA, nomeClasse() + ": onStop chamado.");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        //faz algo e logar
        Log.i(CATEGORIA, nomeClasse() + ": onDestroy chamado.");
        super.onDestroy();
    }
    private String nomeClasse() {

        String s = getClass().toString();//pega nome da classe completo
        return s.substring(s.lastIndexOf(".")); //faz uma string da string original comecando no ultimo ponto da string original
    }
}