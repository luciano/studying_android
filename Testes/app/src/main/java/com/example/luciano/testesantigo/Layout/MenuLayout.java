package com.example.luciano.testesantigo.Layout;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuLayout extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] opcoes = new String[] {
                "FrameLayout",
                "LinearLayout",
                "TableLayout",
                "RelativeLayout",
                "AbsoluteLayout",
                "ScrollView",
                "GridView",
                "Volta"
        };

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes);
        setListAdapter(adaptador);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
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
            default:
                finish();
        }
    }
}
