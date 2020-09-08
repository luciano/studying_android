package dominando.android.helloactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends DebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button) findViewById(R.id.btLogin);
        login.setOnClickListener(onClickLogin());

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
                    // navegacao para proxima tela
                    Intent intent = new Intent(getContext(), BemVindoActivity.class);
                    Bundle params = new Bundle();
                    params.putString("nome", "Luciano Silva");
                    intent.putExtras(params);
                    startActivity(intent);
                } else {
                    alert("Login e senha incorretos.");
                }
            }
        };
    }

    // pega contexto atual
    private Context getContext() {
        return this;
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
