package br.livro.android.cap7.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import br.livro.android.cap7.R;

/**
 * Demonstra um alerta de confirma��o;
 * 
 * � poss�vel definir um "DialogInterface.OnClickListener" para executar o
 * c�digo dependendo se o usu�rio escolheu Sim ou N�o
 * 
 * @author ricardo
 * 
 */
public class ExemploAlertaConfirmacao extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.alert_dialog);

		// Exibe o alerta de confirma��o ao clicar no bot�o
		Button b = (Button) findViewById(R.id.b);

		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/** AlertDialog com Sim e N�o **/
				AlertDialog.Builder alerta = new AlertDialog.Builder(ExemploAlertaConfirmacao.this);
				alerta.setIcon(R.drawable.smile1);
				alerta.setTitle("Titulo");
				alerta.setMessage("Por favor escolha sim ou n�o, obrigado.");
				// M�todo executado se escolher Sim
				alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						Toast.makeText(ExemploAlertaConfirmacao.this, "Sim!", Toast.LENGTH_SHORT).show();
					}
				});
				// M�todo executado se escolher N�o
				alerta.setNegativeButton("N�o", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						Toast.makeText(ExemploAlertaConfirmacao.this, "N�o!", Toast.LENGTH_SHORT).show();
					}
				});
				// Exibe o alerta de confirma��o
				alerta.show();
			}
		});
	}
}
