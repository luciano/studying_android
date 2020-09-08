package com.example.luciano.testesantigo.Intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.luciano.testes.R;

public class Tela2Intent1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2_intent1);
    }

    public void buttonEnviar(View v) {
        Intent it1 = new Intent(this, TelaParametrosIntent1.class);

        final EditText ETchar = (EditText) findViewById(R.id.ETchar1);
        Log.i("dep", "ETchar1: " + ETchar.getText().toString());

        final EditText ETint = (EditText) findViewById(R.id.ETint1);
        Log.i("dep", "ETint1: " + ETint.getText().toString());

        final EditText ETdouble = (EditText) findViewById(R.id.ETdouble1);
        Log.i("dep", "ETdouble1: " + ETdouble.getText().toString());

        final EditText ETstring = (EditText) findViewById(R.id.ETstring1);
        Log.i("dep", "ETstring1: " + ETstring.getText().toString());

        Log.i("dep", "Pre if1.1");
        if(!ETint.getText().toString().isEmpty()) {
            Integer i = 0;
            Log.i("dep", "i_antes1: " + i);
            i = i.valueOf(ETint.getText().toString());
            Log.i("dep", "i_depois1: " + i);
            it1.putExtra("int", i);
        }

        Log.i("dep", "Pre if2.1");
        if(!ETdouble.getText().toString().isEmpty()) {
            Double d = 0.0;
            Log.i("dep", "d_antes1: " + d);
            d = d.valueOf(ETdouble.getText().toString());
            Log.i("ddep", "d_depois1: " + d);
            it1.putExtra("double", d);
        }

        Log.i("dep", "Pre if3.1");
        if(!ETchar.getText().toString().isEmpty()) {
            Character c = ETchar.getText().toString().charAt(0);
            Log.i("dep", "c1: " + ETchar.getText().toString().charAt(0));
            it1.putExtra("char", c);
        }

        Log.i("dep", "Pre if4.1");
        if(!ETstring.getText().toString().isEmpty()) {
            Log.i("dep", "s1: " + ETstring.getText().toString());
            it1.putExtra("string", ETstring.getText().toString());
        }

        it1.putExtra("title", "Enviando Parametros para Activity");
        Log.i("dep", "Chamou nova Activity1");
        startActivity(it1);
    }

    public void volta(View v) {
        finish();
    }
}
