package com.example.luciano.testesantigo.ListView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuListView extends ListActivity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String opcoes[] = new String[] {
                "Activity ListView1",
                "Activity ListView2",
                "Activity ListView3",
                "Activity ListView4",
                "Activity ListView5",
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
                startActivity(new Intent(this, ListView1.class));
                break;
            case 1:
                startActivity(new Intent(this, ListView2.class));
                break;
            case 2:
                startActivity(new Intent(this, ListView3.class));
                break;
            case 3:
                startActivity(new Intent(this, ListView4.class));
                break;
            case 4:
                break;
            default:
                finish();
        }
    }
}