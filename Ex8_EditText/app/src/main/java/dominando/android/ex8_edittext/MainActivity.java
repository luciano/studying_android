package dominando.android.ex8_edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Exemplo livro Dominando Android
 *
 * Criando em: 24/08/2015
 *
 * Ultima modificação em: 24/08/2015
 */
public class MainActivity extends AppCompatActivity
        implements TextView.OnEditorActionListener {

    EditText edtNome, edtEmail, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtSenha.setOnEditorActionListener(this);

        // acrescentar mascara no editText para aceitar CEP
        final EditText edtCep = (EditText) findViewById(R.id.edtCep);

        edtCep.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Quando o texto é alterado o onTextChaged é chamado
                // Essa flag evita a chamada  infinita desse metodo
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                // Ao apagar, a máscara é removida
                // então o posicionamento do cursor precisa
                // saber se o texto atual tinha ou não mascara
                boolean hasMask =
                            s.toString().indexOf('.') > -1 ||
                            s.toString().indexOf('-') > -1;

                // remove o '.' e '-' da string
                String str = s.toString()
                        .replaceAll("[.]", "")
                        .replaceAll("[-]", "");

                // os parametros before e count dizem o tamanho
                // anterior e atual da String digitada, se count > before é
                // porque está digitando, caso contrário, está apagando
                if (count > before) {
                    // se tem mais de 5 caracteres (sem amscara)
                    // coloca o '.' e o '-'
                    if (str.length() > 5) {

                        str = str.substring(0, 2) + '.' +
                                str.substring(2, 5) + '-' +
                                str.substring(5);

                    // se tem mais de 2 coloca so o ponto
                    } else if (str.length() > 2) {
                        str = str.substring(0, 2) + '.' +
                                str.substring(2);
                    }

                    /// seta a flag para evitar chamada infinita
                    isUpdating = true;

                    // seta o novo texto
                    edtCep.setText(str);

                    // seta a posicao od cursor
                    edtCep.setSelection(edtCep.getText().length());
                } else {
                    isUpdating = true;
                    edtCep.setText(str);

                    // se estiver apagando posiciona o cursor
                    // no local correto. Isso trata a deleção
                    // dos caracteres da mascara
                    edtCep.setSelection(Math.max(0, Math.min(hasMask ? start - before : start, str.length())));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (v == edtSenha && actionId == EditorInfo.IME_ACTION_DONE) {
            String nome = edtNome.getText().toString();
            String email = edtEmail.getText().toString();
            String senha = edtSenha.getText().toString();
            boolean ok = true;

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtEmail.setError(getString(R.string.msg_erro_email));
                ok = false;
            }

            if (!senha.equals("123")) {
                edtSenha.setError(getString(R.string.msg_erro_senha));
                ok = false;
            }

            if (ok) {
                Toast.makeText(this, getString(R.string.msg_sucesso, nome, email), Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;
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
