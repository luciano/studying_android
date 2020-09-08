package com.example.luciano.testesantigo.ListView;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListView3 extends ListActivity {
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        //declara ArrayList que vai ser usado para setar os valores na lista do XML
        ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();

        //colocar chaves e valores no HashMap
        for(int i = 0; i < 20; ++i) {
            HashMap<String, String> novoItem = new HashMap<String, String>();
            novoItem.put("Titulo", "Campo " + (i + 1));
            novoItem.put("corpo", "Este é o campo " + (i + 1));

            Log.i("depuracao", "HashMap: " + novoItem.toString());

            lista.add(novoItem);
        }
        int setarEm[] = new int[] {android.R.id.text1, android.R.id.text2};
        String chavesHashMap[] = new String[] {"Titulo", "corpo"};
        int LayoutTela = android.R.layout.two_line_list_item;

        Log.i("depuracao", "ArrayList: " + lista.toString());

        setListAdapter(new SimpleAdapter(this, lista, LayoutTela, chavesHashMap, setarEm));
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Object o = this.getListAdapter().getItem(position);

        Toast.makeText(this,o.toString(), Toast.LENGTH_SHORT).show();
    }
}
