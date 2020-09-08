package android.teste.arquivos.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private static String CATEGORIA = "ViewsDep";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //usar para mudar tema em tempo de execucao PESQUISAR
        //setTheme();

        setContentView(R.layout.activity_main);

        Log.i(CATEGORIA, "Antes listView");
        final ListView listView = (ListView) findViewById(R.id.lista);

        String[] itens = new String[] {
            "Theme.AppCompat",
            "2",
            "3", "4", "5", "6", "7", "8"};

        Log.i(CATEGORIA, "Criou string");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);
        listView.setAdapter(adapter);

        Log.i(CATEGORIA, "Criou arrayadapter e setou");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("depuracao", "Click reconhecido");
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, Tema1.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, Tema2.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Tema3.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, Tema4.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, Tema5.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, Tema6.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, Tema7.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, Tema8.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, Tema9.class));
                        break;
                }
            }
        });


/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("depuracao","Click reconhecido");
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, Tema1.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, Tema2.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Tema3.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, Tema4.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, Tema5.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, Tema6.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, Tema7.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, Tema8.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, Tema9.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, Tema10.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, Tema11.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, Tema12.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, Tema13.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, Tema14.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, Tema15.class));
                        break;
                    case 15:
                        startActivity(new Intent(MainActivity.this, Tema16.class));
                        break;
                    case 16:
                        startActivity(new Intent(MainActivity.this, Tema17.class));
                        break;
                    case 17:
                        startActivity(new Intent(MainActivity.this, Tema18.class));
                        break;
                    case 18:
                        startActivity(new Intent(MainActivity.this, Tema19.class));
                        break;
                    case 19:
                        startActivity(new Intent(MainActivity.this, Tema20.class));
                        break;
                    case 20:
                        startActivity(new Intent(MainActivity.this, Tema21.class));
                        break;
                    case 21:
                        startActivity(new Intent(MainActivity.this, Tema22.class));
                        break;
                    case 22:
                        startActivity(new Intent(MainActivity.this, Tema23.class));
                        break;

                }

            }
        });
*/
    }

}
