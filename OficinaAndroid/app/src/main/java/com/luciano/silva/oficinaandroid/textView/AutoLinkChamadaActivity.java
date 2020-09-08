package com.luciano.silva.oficinaandroid.textView;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.luciano.silva.oficinaandroid.R;
/**
 * Activity configurada com Intent Filter para receber scheme luciano
 *
 * Criada em: 09/10/2015
 *
 * Ultima modificação: 09/10/2015
 */
public class AutoLinkChamadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_link_chamada);

        String data = getIntent().getDataString();
        Uri uriData = getIntent().getData();

        String texto = "String recebida pela Intent: " + data +"\n\n\n" +
                "Uri: " + uriData.toString() + "\n" +
                "Uri.path: " + uriData.getPath() + "\n" +
                "Uri.host: " + uriData.getHost() + "\n" +
                "Uri.scheme: " + uriData.getScheme();

        TextView mTextView = (TextView) findViewById(R.id.textViewChamada);

        mTextView.setText(texto);

    }
}
