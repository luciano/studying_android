package com.example.luciano.testesantigo.ListView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luciano.testes.R;

public class ListView5 extends Activity implements OnItemClickListener {

    private ListView LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_view5);

        String opcoes[] = new String[] {
                "Campo 1", "Campo 2", "Campo 3", "Campo 4"
        };

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes);

        LV = (ListView) findViewById(android.R.id.list);
        LV.setAdapter(ad);

        LV.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {

       Object o = LV.getAdapter().getItem(posicao);

        String item = o.toString();

        Toast.makeText(this, "Voce selecionou: " + item, Toast.LENGTH_SHORT).show();
    }

}
