package com.example.luciano.testesantigo.ListView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luciano.testesantigo.ListView.ListView2Telas.ListView2Tela1;
import com.example.luciano.testesantigo.ListView.ListView2Telas.ListView2Tela10;
import com.example.luciano.testesantigo.ListView.ListView2Telas.ListView2Tela2;
import com.example.luciano.testesantigo.ListView.ListView2Telas.ListView2Tela3;
import com.example.luciano.testesantigo.ListView.ListView2Telas.ListView2Tela4;
import com.example.luciano.testesantigo.ListView.ListView2Telas.ListView2Tela5;
import com.example.luciano.testesantigo.ListView.ListView2Telas.ListView2Tela6;
import com.example.luciano.testesantigo.ListView.ListView2Telas.ListView2Tela7;
import com.example.luciano.testesantigo.ListView.ListView2Telas.ListView2Tela8;
import com.example.luciano.testesantigo.ListView.ListView2Telas.ListView2Tela9;

public class ListView2 extends ListActivity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String opcoes[] = new String[] {
                "Primeiro Campo", "Segundo Campo", "Terceiro Campo", "Quarto Campo",
                "Quinto Campo", "Sexta Campo", "Setimo Campo", "Oitavo Campo",
                "Nono Campo", "Decimo Campo", "Sair"
        };

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(ListView2.this, android.R.layout.simple_list_item_1, opcoes);

        this.setListAdapter(adaptador);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        switch(position) {
            case 0:
                Intent it = new Intent(this, ListView2Tela1.class);
                it.putExtra("string", this.getListAdapter().getItem(position).toString());
                Log.i("depuracao", "Intent ListView2: " + it);
                startActivity(it);
                break;
            case 1:
                startActivity(new Intent(this, ListView2Tela2.class));
                break;
            case 2:
                startActivity(new Intent(this, ListView2Tela3.class));
                break;
            case 3:
                startActivity(new Intent(this, ListView2Tela4.class));
                break;
            case 4:
                startActivity(new Intent(this, ListView2Tela5.class));
                break;
            case 5:
                startActivity(new Intent(this, ListView2Tela6.class));
                break;
            case 6:
                startActivity(new Intent(this, ListView2Tela7.class));
                break;
            case 7:
                startActivity(new Intent(this, ListView2Tela8.class));
                break;
            case 8:
                startActivity(new Intent(this, ListView2Tela9.class));
                break;
            case 9:
                startActivity(new Intent(this, ListView2Tela10.class));
                break;
            default:
                finish();

        }
    }

    @Override
    public void finish() {
        Toast.makeText(this, "Ate logo!", Toast.LENGTH_SHORT).show();
        super.finish();
    }
}
