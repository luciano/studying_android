package dominando.android.ex04_result;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

/**
 * Quando herda de ListActivity
 * nao precisa chamar o metodo setContentView() pois ele é chamado automaticamente
 * caso precise usar algum layout, ele obrigatoriamente deve ter um componete
 * ListView com id android:id="android:id/list"
 *
 */
public class TelaSelecaoActivity extends ListActivity {

    public static final String EXTRA_ESTADO = "estado";
    public static final String EXTRA_RESULTADO = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // recupera array definido no arquivo strings.xml
        String[] estados = getResources().getStringArray(R.array.estado);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, estados));

        String estado = getIntent().getStringExtra(EXTRA_ESTADO);

        if(estado != null) {
            int position = Arrays.asList(estados).indexOf(estado);
            getListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            getListView().setItemChecked(position, true);
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String result = l.getItemAtPosition(position).toString();
        Intent it = new Intent();

        it.putExtra(EXTRA_RESULTADO, result);
        setResult(RESULT_OK, it);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_selecao, menu);
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
