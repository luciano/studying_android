package conversa.seria.talk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Retorna string para ser salva no arquivo
 */
public class AddActivity extends Arquivo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void salvarInfo(View v) {

        final EditText etTitulo = (EditText) findViewById(R.id.titulo);
        final EditText etMensagem = (EditText) findViewById(R.id.mensagem);

        boolean tituloLimpo = etTitulo.getText().toString().isEmpty();
        boolean mensagemLimpo = etMensagem.getText().toString().isEmpty();

        if(tituloLimpo || mensagemLimpo) {

            if (tituloLimpo) {
                etTitulo.setError("Insira um tema!");
            }
            if (mensagemLimpo) {
                etMensagem.setError("Insira uma estratégia!");
            }

            Log.i(MainActivity.CATEGORIA, "Titulo ou Mensagem vazios!");

        } else {

            String titulo = etTitulo.getText().toString();
            String mensagem = etMensagem.getText().toString();

            Intent it = getIntent();
            String tabela = it.getStringExtra("tabela");

            escrita(tabela, titulo);
            escrita(titulo, mensagem);

            etTitulo.setText("");
            etMensagem.setText("");

            Log.i(MainActivity.CATEGORIA, "Salvo com sucesso!");
        }

    }
}
