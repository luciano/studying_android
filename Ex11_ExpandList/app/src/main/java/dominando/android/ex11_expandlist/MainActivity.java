package dominando.android.ex11_expandlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Exemplo livro Dominando Android
 *
 * Criando em: 25/08/2015
 *
 * Ultima modificação: 25/08/2015
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        List<String> listMG = new ArrayList<>();
        listMG.add("Belo Horizonte");
        listMG.add("Contagem");
        listMG.add("Curvelo");
        listMG.add("Diamantina");
        listMG.add("Montes Claros");
        listMG.add("Sete Lagoas");
        listMG.add("Ouro Preto");
        listMG.add("Três Marias");

        List<String> listSP = new ArrayList<>();
        listSP.add("Baurú");
        listSP.add("Campinas");
        listSP.add("São Paulo");

        Map<String, List<String>> dados = new HashMap<>();
        dados.put("MG", listMG);
        dados.put("SP", listSP);

        expandableListView.setAdapter(new MeuExpandableAdapter(dados));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
