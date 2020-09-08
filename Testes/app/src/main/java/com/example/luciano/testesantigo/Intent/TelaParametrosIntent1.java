package com.example.luciano.testesantigo.Intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.luciano.testes.R;

public class TelaParametrosIntent1 extends Activity {

    protected static final String CATEGORIA = "depuracao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_parametros_intent1);

        Intent it = getIntent();

        Log.i(CATEGORIA,"Intent: " + getIntent());
        if(it != null) {

            String title = it.getStringExtra("title");
            Log.i(CATEGORIA, "title: " + title);
            final TextView TVtitle = (TextView) findViewById(R.id.TVtitle);
            TVtitle.setText(title);

            Bundle parametros = it.getExtras();
            Log.i(CATEGORIA, "Bundle: " + it.getExtras());

            if(parametros != null) {

                if(parametros.getString("string") != null) {
                    String s = parametros.getString("string");
                    Log.i(CATEGORIA, "String: " + parametros.getString("string"));
                    final TextView TVstring = (TextView) findViewById(R.id.TVstring);
                    TVstring.setText(s);
                }

                Log.i(CATEGORIA, "Double: " + parametros.getDouble("double"));
                Log.i(CATEGORIA, "Int: "    + parametros.getInt("int"));
                Log.i(CATEGORIA, "Char: "   + parametros.getChar("char"));


                Double d = parametros.getDouble("double");

                Integer i = parametros.getInt("int");

                //se o char nao receber argumento, a TextView da erro
                //entao seta um valor default
                Character c = parametros.getChar("char");
                Log.i(CATEGORIA, "c: "   + c);


                final TextView TVdouble = (TextView) findViewById(R.id.TVdouble);
                TVdouble.setText(d.toString());

                final TextView TVint = (TextView) findViewById(R.id.TVint);
                TVint.setText(i.toString());

                final TextView TVchar = (TextView) findViewById(R.id.TVchar);
                TVchar.setText(c.toString());
            }
        }
    }
    public void volta(View v) {
        finish();
    }
}
