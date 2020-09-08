package br.livro.android.cap10.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import br.livro.android.cap10.R;

/**
 * Activity que executa quando o usuario selecione a notificacao na barra de status
 * 
 * Activity simples que apenas exibe uma mensagem na tela
 * 
 * Aqui e feito o cancelamento da notificacao, para fechar a mesma depois de o usuurio abrir
 * 
 * @author ricardo
 *
 */
public class ExemploExecutaNotificacao extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Cancela a notifica��o
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		// Para cancelar precisa utilizar o mesmo id que foi utilizado para criar
		nm.cancel(R.drawable.ic_launcher);

		TextView text = new TextView(this);
		text.setText("Usuario selecionou a notificacao. E possivel executar algo agora.");
		setContentView(text);
	}
}
