package dominando.android.ex04_result;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Exemplo livro Dominando Android
 *
 * Chama uma nova activity e ela envia ao ser finalizada
 * um bundle para intent chamadora.
 *
 * Criado em: 11/08/2015
 *
 * Ultima modificação: 11/08/2015
 */

public class MainActivity extends AppCompatActivity
                        implements View.OnClickListener {

    private static final int REQUEST_ESTADO = 1;
    private static final String STATE_ESTADO = "estado";

    Button botaoEstado;
    String estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoEstado = (Button) findViewById(R.id.btnState);
        botaoEstado.setOnClickListener(this);

        if(savedInstanceState != null ) {
            estado = savedInstanceState.getString(STATE_ESTADO);
            botaoEstado.setText(estado);
        }

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

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, TelaSelecaoActivity.class);
        it.putExtra(TelaSelecaoActivity.EXTRA_ESTADO, estado);
        startActivityForResult(it, REQUEST_ESTADO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUEST_ESTADO) {
            estado = data.getStringExtra(TelaSelecaoActivity.EXTRA_RESULTADO);
            if(estado != null) {
                botaoEstado.setText(estado);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_ESTADO, estado);
    }
}
