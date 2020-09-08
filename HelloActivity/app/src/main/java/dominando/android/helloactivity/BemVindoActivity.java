package dominando.android.helloactivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class BemVindoActivity extends DebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        // recebe o nome enviado por parametro
        Bundle args = getIntent().getExtras();
        String name = args.getString("nome");

        // atualizar o texto do TextView com uma mensagem de bem vindo

        TextView text = (TextView) findViewById(R.id.text);
        text.setText(name + ", seja bem vindo!");

        /*
        * O botao up navegation vai muito alem do que simplismente voltar
        * ele pode subir na hierarquia de telas.
        * Enquanto o botao voltar normal volta pra activity anterior a atual
        * que esta na pilha de activities, o botao up navegation pode ir para
        * uma activity especifica.
        *
        * Exemplo:
        * Na pilha de activities tem as seguintes telas
        *
        * A > B > C > D > E
        *
        * Se pressionar o botoa voltar ele vai voltar de uma a uma ate chegar em A
        *
        * Mas podemos configurar o Up navegation para ir diretamente para A
        *
        * Assim todas as activities entre elas vao ser encerradas. (B, C, D)
        *
        */

        // adicionando up navegation
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bem_vindo, menu);
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
        if (id == android.R.id.home) {
            // encerra essa activity
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
