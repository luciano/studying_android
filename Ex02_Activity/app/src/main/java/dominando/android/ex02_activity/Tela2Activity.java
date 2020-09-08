package dominando.android.ex02_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Tela2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        TextView txt = (TextView) findViewById(R.id.txtTexto);

        // Para ler os valores recebidos da tela que chamou essa.
        // Pegamos o objeto Intent enviado com o metodo getIntent()

        // Pegando objetos
        Intent it = getIntent();

        Cliente cliente = it.getParcelableExtra("cliente");
        Pessoa pessoa = (Pessoa) it.getSerializableExtra("pessoa");

        if(cliente != null) {

            String texto = String.format("Cliente: \n\tNome: %s \n\tCodigo: %d", cliente.getNome(), cliente.getCodigo());
            txt.setText(texto);

        } else if (pessoa != null) {

            String texto = String.format("Pessoa: \n\tNome: %s \n\tIdade: %d", pessoa.getNome(), pessoa.getIdade());
            txt.setText(texto);

        } else {
            String nome = it.getStringExtra("nome");
            int idade = it.getIntExtra("idade", -1);
            txt.setText(String.format("Nome: %s \nIdade: %d", nome, idade));
        }

        Log.i("LogLS", "Tela02::onCreate(" + savedInstanceState + ")");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela2, menu);
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
    protected void onStart() {
        super.onStart();
        Log.i("LogLS", "Tela02::onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("LogLS", "Tela02::onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LogLS", "Tela02::onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LogLS", "Tela02::onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LogLS", "Tela02::onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LogLS", "Tela02::onDestroy");
    }
}
