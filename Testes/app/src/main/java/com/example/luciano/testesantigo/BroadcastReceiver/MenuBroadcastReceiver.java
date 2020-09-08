package com.example.luciano.testesantigo.BroadcastReceiver;

import android.app.ListActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuBroadcastReceiver extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //registra broadcast
        registerReceiver(new Broadcast5(), new IntentFilter("Broadcast5"));

        ArrayList<HashMap<String, String>> lista = new ArrayList<>();

        getConteudoHashMap(lista);

        String chavesHashMap[] = new String[] {"titulo", "corpo"};

        int LayoutTela = android.R.layout.two_line_list_item;

        int setarEm[] = new int[] {android.R.id.text1, android.R.id.text2};

        setListAdapter(new SimpleAdapter(this, lista, LayoutTela, chavesHashMap, setarEm));

    }

    private void getConteudoHashMap(ArrayList<HashMap<String, String>> l) {

        HashMap<String, String> campo1 = new HashMap<>();
        HashMap<String, String> campo2 = new HashMap<>();
        HashMap<String, String> campo3 = new HashMap<>();
        HashMap<String, String> campo4 = new HashMap<>();
        HashMap<String, String> campo5 = new HashMap<>();

        campo1.put("titulo", "BroadcastReceiver1");
        campo1.put("corpo", "broadcast simples");
        l.add(campo1);

        campo2.put("titulo", "BroadcastReceiver2");
        campo2.put("corpo", "broadcast chama toast com layout");
        l.add(campo2);

        campo3.put("titulo", "BroadcastReceiver3");
        campo3.put("corpo", "broadcast com parametros");
        l.add(campo3);

        campo4.put("titulo", "BroadcastReceiver4");
        campo4.put("corpo", "broadcast chama activity");
        l.add(campo4);

        campo5.put("titulo", "BroadcastReceiver5");
        campo5.put("corpo", "broadcast pela API");
        l.add(campo5);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent it;

        switch(position) {

            case 0:
                it = new Intent("Broadcast1");
                sendBroadcast(it);
                break;
            case 1:
                it = new Intent("Broadcast2");
                sendBroadcast(it);
                break;
            case 2:
                it = new Intent("Broadcast3");
                it.putExtra("text", "Mensagem enviada pela Activity MenuBroadcastReceiver");
                sendBroadcast(it);
                break;
            case 3:
                it = new Intent("Broadcast4");
                sendBroadcast(it);
                break;
            case 4:
                sendBroadcast(new Intent("Broadcast5"));
                break;
            default:
                finish();
        }
    }
}
