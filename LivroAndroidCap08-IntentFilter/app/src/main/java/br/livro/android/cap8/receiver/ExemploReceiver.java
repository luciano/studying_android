package br.livro.android.cap8.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.widget.Toast;

/**
 * Exemplo de como receber o broadcast de uma Intent
 * 
 * @author ricardo
 *
 */
public class ExemploReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context c, Intent intent) {
	
		// Voce tem 5 segundos para executar este código

		// Se precisar voce tem 2 opções classicas
		// 1) Criar uma Notification para chamar a atençao
		// 2) Utilizar um Service e disparar um processamento demorado
		
		// E lembre-se, aqui no receiver voce precisa ser rapido
		
		Toast.makeText(c, "BroadcastReceiver chamado com sucesso !!!", Toast.LENGTH_SHORT).show();
	}
}
