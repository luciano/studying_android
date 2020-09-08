package dominando.android.ex10_adapter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Exemplo livro Dominando Android
 *
 * Criado em: 25/08/2015
 *
 * Ultima modificação: 25/08/2015
 *
 */
public class MainActivity extends AppCompatActivity {

    List<Carro> carros;
//    CarroSimpleAdapter adapter; ineficiente se tiver que instanciar muitos itens
    CarroAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lista);
        listView.setEmptyView(findViewById(android.R.id.empty));

        carros = new ArrayList<>();
        // 0=VW; 1=GM; 2=FIAT; 3=FORD
//        carros.add(new Carro("Celta", 2010, 1, true, false));
//        carros.add(new Carro("Uno", 2012, 2, true, true));
//        carros.add(new Carro("Fiesta", 2009, 3, false, true));
//        carros.add(new Carro("Gol", 2014, 0, true, true));
//
//        carros.add(new Carro("Outro Celta", 2010, 1, true, false));
//        carros.add(new Carro("Outro Uno", 2012, 2, true, true));
//        carros.add(new Carro("Fiesta", 2009, 3, false, true));
//        carros.add(new Carro("Gol", 2014, 0, true, true));
//
//        carros.add(new Carro("Celta", 2010, 1, true, false));
//        carros.add(new Carro("Uno", 2012, 2, true, true));
//        carros.add(new Carro("Fiesta", 2009, 3, false, true));
//        carros.add(new Carro("Gol", 2014, 0, true, true));
//
//        carros.add(new Carro("Celta", 2010, 1, true, false));
//        carros.add(new Carro("Uno", 2012, 2, true, true));
//        carros.add(new Carro("Fiesta", 2009, 3, false, true));
//        carros.add(new Carro("Gol", 2014, 0, true, true));
//
//        carros.add(new Carro("Celta", 2010, 1, true, false));
//        carros.add(new Carro("Uno", 2012, 2, true, true));
//        carros.add(new Carro("Fiesta", 2009, 3, false, true));
//        carros.add(new Carro("Gol", 2014, 0, true, true));

        //adapter = new CarroSimpleAdapter(this, carros);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Carro carro = (Carro) parent.getItemAtPosition(position);

                Toast.makeText(MainActivity.this, carro.modelo + " - " + carro.ano, Toast.LENGTH_LONG).show();

            }
        });

        adapter = new CarroAdapter(this, carros);

        // adicionando cabecalho e rodape a list view

        final int PADDING = 8;

        TextView txtHeader = new TextView(this);
        txtHeader.setBackgroundColor(Color.GRAY);
        txtHeader.setTextColor(Color.WHITE);
        txtHeader.setText(R.string.texto_cabecalho);
        txtHeader.setPadding(PADDING, PADDING, 0, PADDING);
        txtHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        listView.addHeaderView(txtHeader);

        TextView txtFoorter = new TextView(this);
        txtFoorter.setText(getResources().getQuantityString(R.plurals.texto_rodape, adapter.getCount(), adapter.getCount()));
        txtFoorter.setBackgroundColor(Color.LTGRAY);
        txtFoorter.setGravity(Gravity.RIGHT);
        txtFoorter.setPadding(0, PADDING, PADDING, PADDING);
        txtFoorter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        listView.addFooterView(txtFoorter);

        listView.setAdapter(adapter);

    }

}
