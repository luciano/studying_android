package com.luciano.silva.oficinaandroid.textView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

import com.luciano.silva.oficinaandroid.R;

import java.util.regex.Pattern;

/**
 * Activity que possui autolinks e um deles foi personalisado
 * para chamar uma activity criada com determinado scheme personalizado
 *
 * Criada em: 09/10/2015
 *
 * Ultima modificação: 09/10/2015
 */

public class AutoLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_link);

        TextView mTextView = (TextView) findViewById(R.id.textViewPersonalizado);

        // fazendo autoLink personalizado com Pattern - Expressao Regular

        Pattern pattern = Pattern.compile("[Ll]uciano\\s[Ss]ilva");

        // vai ser responsavel por chamar uma activity que tem configurado intent-filter
        // data = scheme, acao = VIEW, categoria = DEFAULT
        String scheme = "luciano://";

        Linkify.addLinks(mTextView, pattern, scheme);

        // vai chegar o na activity chamada
        // getIntent().getDataString() = luciano://[texto que corresponde a expressao regular]
    }
}
