package com.example.luciano.testesantigo.ListView.ListView2Telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ListView2Tela1 extends Activity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        Log.i("depuracao", "Intent ListView2Tela1: " + getIntent());

        Intent it = getIntent();

        Toast.makeText(this, it.getStringExtra("string") + " selecionado!", Toast.LENGTH_LONG).show();

        TextView TV = new TextView(this);
        TV.setText("\n\n\n\tEsta é a Tela 1!!!\n");

        setContentView(TV);
    }
}