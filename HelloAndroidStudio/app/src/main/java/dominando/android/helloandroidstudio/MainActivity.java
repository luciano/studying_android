package dominando.android.helloandroidstudio;

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
 * Exemplo do livro Google Android
 *
 * Criando em: 11/08/2015
 *
 * Ultima modificação: 11/08/2015
 */
public class MainActivity extends AppCompatActivity {

    private final String TAG = "LogLS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button) findViewById(R.id.btLogin);
        login.setOnClickListener(onClickLogin());

        Button log = (Button) findViewById(R.id.btLog);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "log de verbose");
                Log.d(TAG, "log de debug");
                Log.i(TAG, "log de info");
                Log.w(TAG, "log de warnning(alerta)");
                Log.e(TAG, "log de erro: " + new RuntimeException("teste de erro"));
            }
        });

    }

    private View.OnClickListener onClickLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etLogin = (EditText) findViewById(R.id.etLogin);
                EditText etSenha = (EditText) findViewById(R.id.etSenha);

                String login = etLogin.getText().toString();
                String senha = etSenha.getText().toString();

                if("Luciano".equals(login) && "12345".equals(senha)) {
                    alert("Bem vindo, login realizado com sucesso.");
                } else {
                    alert("Login e senha incorretos.");
                }
            }
        };
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
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
