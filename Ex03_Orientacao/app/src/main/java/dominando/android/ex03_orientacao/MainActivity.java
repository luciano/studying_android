package dominando.android.ex03_orientacao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Exemplo do livro Dominando Android
 *
 * Se não for tratado a ação de orientação de tela
 * o ArrayList é destruida junto com a activity ao virar a tela
 * e a lista é limpa. Devemos tratar o evento de mudança de orientaçao de tela.
 *
 * Criado em: 11/08/2015
 *
 * Ultima modificaçao: 11/08/2015
 *
 */

public class MainActivity extends AppCompatActivity {

    EditText edt;
    ArrayList<String> nomes;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // o Bundle que esta como parametro de onCreate recebe o Bundle
        // que foi salvo em onsaveInstanceState
        // agora vamos recupera-lo
        if(savedInstanceState != null) {
            nomes = savedInstanceState.getStringArrayList("nomes");
        } else {
            nomes = new ArrayList<>();
        }

        edt = (EditText) findViewById(R.id.editText1);
        ListView listView = (ListView) findViewById(R.id.listView1);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes);

        listView.setAdapter(adapter);
    }

    // para salvar o estado antes de destruir a activity atual
    // para recuper os dados posteriormente
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("nomes", nomes);
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


    // Ao pressionar o botao, o texto do EditText é adicionado ao ArrayList
    // em seguida o adapter e atualizado ao chamar o metodo notifyDataChanged()
    // com isso a ListView é atualizada automaticamente.
    public void meuBotaoClick(View view) {
        nomes.add(edt.getText().toString());
        edt.setText("");
        adapter.notifyDataSetChanged();
    }
}
