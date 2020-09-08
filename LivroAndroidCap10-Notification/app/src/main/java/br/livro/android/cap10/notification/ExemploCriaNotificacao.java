package br.livro.android.cap10.notification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import br.livro.android.cap10.R;
import br.livroandroid.utils.NotificationUtil;

/**
 * Exemplo de Activity que cria uma notificacao
 * 
 * @author ricardo
 * 
 */
public class ExemploCriaNotificacao extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		TextView text = new TextView(this);
		text.setText("Uma notificacao foi disparada.");
		setContentView(text);

		// Texto com a chamada para a notificacao (barra de status)
		String tickerText = "Nova mensagem";

		// Detalhes da mensagem, quem enviou e o texto
		String titulo = "Ricardo";
		String mensagem = "Exemplo de notificacao";

		// Exibe a notificacao para abrir a RecebeuMensagemActivity
		criarNotificacao(tickerText, titulo, mensagem, ExemploExecutaNotificacao.class);
	}

	// Exibe a notificacao
	protected void criarNotificacao(String tickerText, String title,String message, Class<?> activity) {
		Intent intent=  new Intent(this, ExemploExecutaNotificacao.class);
		NotificationUtil.create(this, tickerText, title, message, R.drawable.ic_launcher, R.drawable.ic_launcher, intent);
	}
}
