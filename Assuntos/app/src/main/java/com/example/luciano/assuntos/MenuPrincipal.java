package com.example.luciano.assuntos;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MenuPrincipal extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_principal);

        final ImageView img = (ImageView) findViewById(R.id.img);
        img.setImageResource(R.mipmap.ic_launcher1);

        String[] temas = new String[] {
                "Tema 1",
                "Tema 2",
                "Tema 3",
                "Tema 4",
                "Tema 5",
                "Tema 6",
                "Tema 7",
                "Tema 8",
                "Tema 9",
                "Tema 10",
                "Tema 11"
        };
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.lista_temas, temas);
        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temas);
        setListAdapter(adaptador);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        switch(position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                finish();
        }
    }
}
