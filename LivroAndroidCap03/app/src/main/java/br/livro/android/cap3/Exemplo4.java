package br.livro.android.cap3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Exemplo simples de listener ao clicar em um bot�o
 * 
 * @author ricardo
 *
 * Estudado em 27 de Janeiro de 2015
 * 
 */
public class Exemplo4 extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// abre a tela.xml
		setContentView(R.layout.layout_exemplo4_listener);

		// Busca o TextView pelo id
		final EditText textNome = (EditText) findViewById(R.id.campoNome);
		final TextView textResultado = (TextView) findViewById(R.id.campoResultado);

		Button button = (Button) findViewById(R.id.botaoOk);
		// Informa o listener...
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				String nome = textNome.getText().toString();

				// atualiza o texto
				textResultado.setText("Obrigado " + nome);
			}
		});
	}
}
/*
 * Para controlar os eventos d eum botao na tela é utilizado o metodo setOnClickListener(listener)
 *  esse metodo recebe como argumento uma instancia da interface android.view.View.OnClickListerner.
 *  A interface OnCLickListener define o metodo onClick(view) que é chamado automaticamente
 *  quando o evento ocorre, passando como argumento o objeto da view que gerou o evento, o botao.
 *
 */