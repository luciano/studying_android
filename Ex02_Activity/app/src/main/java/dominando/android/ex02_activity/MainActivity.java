package dominando.android.ex02_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Exemplo do livro Dominando Android
 *
 * Criando em: 10/08/2015
 *
 * Ultima modificação: 10/08/2015
 */
public class MainActivity extends AppCompatActivity
                        implements View.OnClickListener {

    EditText edtTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTexto = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.outroBotao);
        Button buttonTela2 = (Button) findViewById(R.id.button2);

        // implementamos a interface diretamente na classe MainActivity
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        buttonTela2.setOnClickListener(this);

        // Uma activity não tem acesso à instancia de outra activity, então, se
        // quisermos passar informações de uma tela para outra, devemos informa-la
        // por meio de parametros no objeto Intent

        // Para passar objetos para outra intent tem duas maneiras
        // implementar interface Parcelable ou Serializable
        // Google recomenda implementar Parcelable pois é mais rapido e eficaz
        // apesar de que na Serializable não precisa implementar nada, apenas
        // herdar e interface (implements Serializable)
        // mas preferir implementar Parcelable

        // Pessoa implementa Serializable
        // Cliente implementa Parcelable

        Button buttonTela2Parcelable = (Button) findViewById(R.id.button3);
        Button buttonTela2Serializable = (Button) findViewById(R.id.button4);

        buttonTela2Parcelable.setOnClickListener(this);
        buttonTela2Serializable.setOnClickListener(this);

        Log.i("LogLS", "Tela01::onCreate("+ savedInstanceState + ")");
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

    // E se tivesse mais de um botao para identificar qual deles gerou o evento do clique
    // usa o id da View que e fornecida como parametro do metodo onClick para essa identificacao
    // Todos os componentes herdam direta ou indiretamente de android.view.View e o parametro
    // do metodo onCLick indica quem disparou a ação.
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button:
                String texto = edtTexto.getText().toString();
                Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
                break;
            case R.id.outroBotao:
                Toast.makeText(this, "O outro Botao foi clicado!", Toast.LENGTH_LONG).show();
                break;
            case R.id.button2:
                Intent it = new Intent(this, Tela2Activity.class);
                // passando informação para segunda tela
                it.putExtra("nome", "Luciano");
                it.putExtra("idade", 20);
                startActivity(it);
                break;
            case R.id.button3:
                Intent it2 = new Intent(this, Tela2Activity.class);
                it2.putExtra("cliente", new Cliente("Luciano", 1));
                startActivity(it2);
                break;
            case R.id.button4:
                Intent it3 = new Intent(this, Tela2Activity.class);
                it3.putExtra("pessoa", new Pessoa("Luciano", 20));
                startActivity(it3);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LogLS", "Tela01::onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("LogLS", "Tela01::onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LogLS", "Tela01::onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LogLS", "Tela01::onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LogLS", "Tela01::onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LogLS", "Tela01::onDestroy");
    }
}
