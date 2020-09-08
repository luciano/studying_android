package com.example.luciano.testesantigo.Intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.luciano.testes.R;

public class Intent1 extends Activity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_intent1);

        final Button buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
        buttonEnviar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle parametros = new Bundle();

                final EditText ETchar = (EditText) findViewById(R.id.ETchar);
                Log.i("Dep", "ETchar: " + ETchar.getText().toString());

                final EditText ETint = (EditText) findViewById(R.id.ETint);
                Log.i("Dep", "ETint: " + ETint.getText().toString());

                final EditText ETdouble = (EditText) findViewById(R.id.ETdouble);
                Log.i("Dep", "ETdouble: " + ETdouble.getText().toString());

                final EditText ETstring = (EditText) findViewById(R.id.ETstring);
                Log.i("Dep", "ETstring: " + ETstring.getText().toString());

                Log.i("Dep", "Pre if1");
                if(!ETint.getText().toString().isEmpty()) {
                    Integer i = 0;
                    Log.i("Dep", "i_antes: " + i);
                    i = i.valueOf(ETint.getText().toString());
                    Log.i("Dep", "i_depois: " + i);
                    parametros.putInt("int", i);
                }

                Log.i("Dep", "Pre if2");
                if(!ETdouble.getText().toString().isEmpty()) {
                    Double d = 0.0;
                    Log.i("Dep", "d_antes: " + d);
                    d = d.valueOf(ETdouble.getText().toString());
                    Log.i("Dep", "d_depois: " + d);
                    parametros.putDouble("double", d);
                }

                Log.i("Dep", "Pre if3");
                if(!ETchar.getText().toString().isEmpty()) {
                    Character c = ETchar.getText().toString().charAt(0);
                    Log.i("Dep", "c: " + ETchar.getText().toString().charAt(0));
                    parametros.putChar("char", c);
                }

                Log.i("Dep", "Pre if4");
                if(!ETstring.getText().toString().isEmpty()) {
                    Log.i("Dep", "s: " + ETstring.getText().toString());
                    parametros.putString("string", ETstring.getText().toString());
                }

                //enviando para intent os parametros
                Intent it = new Intent(buttonEnviar.getContext(), TelaParametrosIntent1.class);
                it.putExtra("title", "Enviando Parametros para Activity");
                it.putExtras(parametros);
                Log.i("Dep", "Chamou nova Activity");
                startActivity(it);
            }
        });
    }
    public void chamaTela2(View v) {
        Intent it = new Intent(this, Tela2Intent1.class);
        startActivity(it);
    }
}