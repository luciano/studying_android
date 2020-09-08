package com.example.luciano.testesantigo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luciano.testesantigo.Button.MenuButton;
import com.example.luciano.testesantigo.CicloVidaActivity.MenuCicloVidaActivity;
import com.example.luciano.testesantigo.EditText.MenuEditText;
import com.example.luciano.testesantigo.Intent.MenuIntent;
import com.example.luciano.testesantigo.ListView.MenuListView;
import com.example.luciano.testesantigo.TextView.MenuTextView;

public class Menu extends ListActivity {
    private static final String CATEGORIA = "depuracao";

    //PORQUE A ACTIVITY PRINCIPAL NAO MOSTRA O NOME E LABEL
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String opcoes[] = new String[] {
                "TextView",
                "EditText",
                "Button",
                "Ciclo Vida Activity",
                "ListView",
                "Intent",
                "Layout",
                "Intent-Filter",
                "Sair"
        };

        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes));

//        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcoes);

//        setListAdapter(adaptador);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Log.i(CATEGORIA, "onListItemClick( position: " + position + ", id: " + id + ")\n");

        switch(position) {
            case 0:
                Intent it = new Intent(this, MenuTextView.class);
                Log.i(CATEGORIA, "Case 0: Actvity MenuTextView chamada.");
                startActivity(it);
                break;
            case 1:
                Intent it1 = new Intent(this, MenuEditText.class);
                Log.i(CATEGORIA, "Case 1: Actvity MenuEditText chamada.");
                startActivity(it1);
                break;
            case 2:
                Intent it2 = new Intent(this, MenuButton.class);
                Log.i(CATEGORIA, "Case 2: Actvity MenuButton chamada.");
                startActivity(it2);
                break;
            case 3:
                Log.i(CATEGORIA, "Case 3: Actvity MenuCicloVidaActivity chamada.");
                startActivity(new Intent(this, MenuCicloVidaActivity.class));
                break;
            case 4:
                Log.i(CATEGORIA, "Case 4: Actvity MenuListView chamada.");
                startActivity(new Intent(this, MenuListView.class));
                break;
            case 5:
                Log.i(CATEGORIA, "Case 5: Actvity MenuIntent chamada.");
                Intent it3 = new Intent(this, MenuIntent.class);
                startActivity(it3);
                break;
            case 6:
                //Layout
                break;
            case 7:
                Log.i(CATEGORIA, "Case 6: Actvity MenuIntentFilter chamada.");
                Intent it4 = new Intent("MenuIntentFilter");
                it4.addCategory("MenuIt");
                startActivity(it4);
                break;
            default:
                finish();
        }
    }
}
