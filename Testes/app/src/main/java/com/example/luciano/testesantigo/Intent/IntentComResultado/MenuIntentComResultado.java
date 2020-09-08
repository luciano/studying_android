package com.example.luciano.testesantigo.Intent.IntentComResultado;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luciano.testes.R;

/*
 * Para abrir uma tela e depois recuperar o valor de retorno é necessaro usar o metodo
 * startActivityForResult(intent, codigo)
 * intent -> intent para abrir a tela desejada
 * codigo -> codigo numerico utilizado para identificar a activity que sera iniciada
 *           chamado de requisitCode no Android. O mesmo valor sera passado ao metodo
 *           onActivityResult(codigo, resultado, intent) quando a activity chamada
 *           finalizar. Se mais de uma activity for utilizada no codigo essa constante
 *           deve ser utilizada para diferenciar cada tipo de retorno.
 *
 * O metodo onActivityResult(codigo, resultado, intent) recebe os parametros:
 *  codigo -> mesmo valor do codigo que originou a chamada com o metodo startActivityForResult(codigo, intent)
 *  resultado -> constante que indica se o resultado foi bem sucedido ou nao. Pode ser qualquer
 *               constante definida na aplicacao
 *  intent -> Intent que originou o retorno. Com essa intent é possivel recuperar o bundle para
 *              ler os parametros retornados.
 *
 * setResult(resultado, intent) recebe a intent com parametros de retorno e uma constante
 * com o codigo de resultado. Logo depois se deve chamar metodo finish() para encerrar activity
 *
 * metodo onActivityResult(codigo, resultado, intent)
 *
 * int codigo -> Constante utilizada para chamar a activity no metodo startActivityForResult(codigo, intent)
 *                O mesmo valor sera passaado ao metodo onActivityResult(codigo, resultado, intent)
 *                quando a tela chamada for finalizada. Se mais de uma intent for chamada na mesma
 *                aplicacao essa constante deve ser utilizada para diferenciar cada tela.
 * int resultado -> codigo  de resultado informado no metodo setResult(resultado, intent) na segunda
 *                  activity. Os valores informados podem ser nativos do Android como
 *                  RESULT_CANCELED e RESULT_OK ou podem ser qualquer outra constante definida
 * intent -> Intent de retorno informada no metodo setResult(resultado, intent) da segunda activity
 *            Varios parametros podem ser passados como retorno utilizando o bundle associado a intent
 */

public class MenuIntentComResultado extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_intent_com_resultado);

        String[] opcoes = new String[] {
                "ActivityPrincipal",
                "Voltar"
        };

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes));
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v , position, id);

        switch(position) {
            case 0:
                startActivity(new Intent(this, ActivityPrincipal.class));
                break;
            default:
                finish();
        }
    }
}
