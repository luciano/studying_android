package br.livro.android.cap3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Exemplo do LogCat.
 * 
 * Abra o LogCat View e crie um filtro para "ID"
 * 
 * @author ricardo
 *
 * Estudado em 27 de Janeiro de 2015
 * 
 */
public class Exemplo5 extends Activity {
	private static final String CATEGORIA = "livro";

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.main);

		// Verbose - Preto
		Log.v(CATEGORIA, "log de verbose");

		// Debug - Azul
		Log.d(CATEGORIA, "log de debug");

		// Info - Verde
		Log.i(CATEGORIA, "log de info");

		// Warn - alerta - Laranja
		Log.w(CATEGORIA, "log de alerta");

		// Error - Vermelho
		Log.e(CATEGORIA, "log de erro", new RuntimeException("teste de erro"));
	}
}
/*
 * Cada log pode ser escrito em uma determinada categoria para que posteriormente apenas as
 * mensagens desejadas sejam recuperadas
 *
 * Gera um log no LogCat
 *
 */
