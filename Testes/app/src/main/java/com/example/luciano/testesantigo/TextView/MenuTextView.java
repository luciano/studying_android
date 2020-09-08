package com.example.luciano.testesantigo.TextView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuTextView extends ListActivity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String opcoes[] = new String[] {
                "Activity TextView1",
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
                startActivity(new Intent(this, TextView1.class));
                break;
            default:
                finish();
        }
    }
}