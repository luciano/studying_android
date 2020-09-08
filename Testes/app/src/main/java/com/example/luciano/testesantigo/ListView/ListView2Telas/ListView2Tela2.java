package com.example.luciano.testesantigo.ListView.ListView2Telas;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListView2Tela2 extends ListActivity {
    private ArrayAdapter<String> adaptador;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String nomes[] = new String[] { "Tela 2", "Voltar"};
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes);

        setListAdapter(adaptador);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch(position) {
            case 0:
                String s = adaptador.getItem(position);
                Toast.makeText(this, "Voce esta na " + s, Toast.LENGTH_SHORT).show();
            default:
                finish();
        }
    }
}