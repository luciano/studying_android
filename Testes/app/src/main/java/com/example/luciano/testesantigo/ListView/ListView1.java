package com.example.luciano.testesantigo.ListView;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListView1 extends ListActivity {
    private ArrayAdapter<String> adaptador1;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String nomes[] = new String[] {"Primeiro Campo", "Segundo Campo", "Terceiro Campo",
                                        "Quarto Campo", "Quinto Campo", "Sexto Campo", "Setimo Campo",
                                        "Oitavo Campo", "Novo Campo", "Decimo Campo"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes);

        adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes);

        setListAdapter(adaptador);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String s = this.getListAdapter().getItem(position).toString();

        Log.i("depuracao", "Adaptador1: " + adaptador1.getItem(position));

        Toast.makeText(this, s +  " selecionado!", Toast.LENGTH_LONG).show();
    }
}