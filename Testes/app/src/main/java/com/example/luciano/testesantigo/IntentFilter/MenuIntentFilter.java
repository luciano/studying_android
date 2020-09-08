package com.example.luciano.testesantigo.IntentFilter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuIntentFilter extends ListActivity {

    private static final int CODIGO_IF4 = 4;
    private static final int CODIGO_IF5 = 5;
    private static final int CODIGO_IF6 = 6;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        String opcoes[] = new String[] {
                "IntentFilter1 - categoria Default",
                "IntentFilter2 - categoria IF2",
                "IntentFilter3 - categoria IF3 e parametros",
                "IntentFilter4 - chama IntentFilter1",
                "IntentFilter5",
                "IntentFilter6 - chama broadcast2",
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
                //startActivity(new Intent(this, IntentFilter1.class));
                //usando categoria padrao
                Intent it1 = new Intent("ActivityIF1");
                startActivity(it1);
                break;
            case 1:
                Intent it2 = new Intent("ActivityIF2");
                it2.addCategory("IF2");
                startActivity(it2);
                break;
            case 2:
                Intent it3 = new Intent("ActivityIF3");
                it3.addCategory("IF3");
                it3.putExtra("msg", "Mensagem enviada via Bundle atráves do Menu IntentFilter");
                startActivity(it3);
                break;
            case 3:
                Intent it4 = new Intent("ActivityIF4");
                it4.addCategory("IF4");
                startActivityForResult(it4, CODIGO_IF4);
                break;
            case 4:
                Intent it5 = new Intent("ActivityIF5");
                it5.addCategory("IF5");
                startActivityForResult(it5, CODIGO_IF5);
                break;
            case 5:
                Intent it6 = new Intent("ActivityIF6");
                it6.addCategory("IF6");
                startActivityForResult(it6, CODIGO_IF6);
                break;
            default:
                finish();
        }
    }

    @Override
    protected void onActivityResult(int codigo, int resultado, Intent dados) {
        super.onActivityResult(codigo, resultado, dados);

        switch(codigo) {
            case CODIGO_IF4:
                avisoTela(CODIGO_IF4, resultado);
                break;
            case CODIGO_IF5://falta
                avisoTela(CODIGO_IF5, resultado);
                break;
            case CODIGO_IF6://falta
                avisoTela(CODIGO_IF6, resultado);
                break;
        }
    }

    private void avisoTela(int n, int result) {
        Toast.makeText(this ,"Activity que respondeu " + n + "\nResultado enviado " + result, Toast.LENGTH_LONG).show();
    }
}