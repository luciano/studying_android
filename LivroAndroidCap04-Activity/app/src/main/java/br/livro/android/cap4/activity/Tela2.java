package br.livro.android.cap4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Simples activity que loga
 * 
 * @author ricardo
 *
 * Estudando em 02, 03 e 20 de Fevereiro de 2015
 *
 * Adaptado para aceitar os dois modos de passagem de parametros
 *
 */
public class Tela2 extends ExemploCicloVida {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

        /*
         * Utiliza a classe TextView para mostrar um texto na tela
         * a tela nao possui layout em XML apenas uma tela de texto
         *
         */
		TextView view = new TextView(this);
		view.setText("Esta é a tela 2");
		setContentView(view);

        /*
         *          Recepcao de parametros de uma tela
         *
         *   É necessario chamar o metodo getIntent() para recuperar a intent utilizada para chamar
         * essa nova activity.
         *
         * É necessario validar se a intent nao esta nula pois caso nessa activity tenha sido
         * iniciada pelo usuario e nao pela aplicacao isso pode ocorrer. Com a intent é possivel
         * chamar o metodo getExtras() que retorna o mesmo Bundle criado para enviar os parametros
         *  É necessario validar se o Bundle retornado nao esta nulo, porque se nenhum parametro foi
         *  enviado isso tambem pode acontecer.
         *
         *  Para ler os parametros passados pelo Bundle basta chamar os metodos getString(chave)
         *  getChar(chave), getBoolean(chave), getInt(chave), getByteArray(chave), entre outros
         *
         *  No exemplo a chave definida na tela principal é msg e msg1
         *
         *  Usando android.content.Intent para receber algum valor da outra tela
         *
         *  O metodo getStringExtra(chave) pode ser utilizado para ler o parametro enviado.
         *
         *  Existem outros metodos para cada tipo primitivo como getIntExtra(chave)
         *  getBooleanExtra(chave)
         *
         *
         */
		Intent it = getIntent();
		if(it != null){
			Bundle params = it.getExtras();
			if (params != null) {
				String msg = params.getString("msg");
				Log.i(CATEGORIA, "Mensagem: " + msg);
			}
		}

//		Intent it = getIntent();
		if(it != null){
			String msg = it.getStringExtra("msg1");
			if (msg != null) {
				Log.i(CATEGORIA, "Mensagem 1: " + msg);
			}
		}
	}
}
