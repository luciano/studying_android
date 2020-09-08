package com.example.luciano.testesantigo.CicloVidaActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuCicloVidaActivity extends ListActivity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String opcoes[] = new String[] {
                "Activity CicloVidaActivity1",
                "Activity CicloVidaActivity2",
                "Voltar"
        };

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes);

        setListAdapter(ad);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        switch (position) {
            case 0:
                startActivity(new Intent(this, CicloVidaActivity1.class));
                break;
            case 1:
                startActivity(new Intent(this, CicloVidaActivity2.class));
                break;
            default:
                finish();
        }
    }
}