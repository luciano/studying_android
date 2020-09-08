package com.example.luciano.testesantigo.ListView;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.luciano.testes.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListView4 extends ListActivity{
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        ListAdapter adaptador = criarAdaptador();

        setListAdapter(adaptador);
    }
    private ListAdapter criarAdaptador() {
        ArrayList<HashMap<String, String>> Lista = new ArrayList<HashMap<String, String>>();

        for(int i = 1; i < 51; ++i) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("titulo", "Campo " + i);
            hm.put("corpo", "Este é o corpo do campo " + i);
            Lista.add(hm);
        }

        //usou layout customizado, sem ser default do android
        int [] setarNaTextView = new int[] {R.id.titulo, R.id.campoTexto};
        String [] chavesHashMap = new String[] {"titulo", "corpo"};
        int LayoutTela = R.layout.activity_list_view_4;

        SimpleAdapter adaptador = new SimpleAdapter(this, Lista, LayoutTela, chavesHashMap, setarNaTextView);
        return adaptador;
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(this, getListAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
    }
}
