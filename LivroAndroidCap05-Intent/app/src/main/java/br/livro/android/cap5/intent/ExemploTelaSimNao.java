package br.livro.android.cap5.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.livro.android.cap5.R;

/**
 * Exemplo de Activity que verifica se a Intent que a chamou informou algum par�metro
 * 
 * @author ricardo
 *
 * Estudado em 26 de Fevereiro de 2015
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
public class ExemploTelaSimNao extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.tela_sim_nao);

		//Sim
		Button sim = (Button) findViewById(R.id.btSim);
		sim.setText("Sim");
		sim.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				//Cria a Intent
				Intent it = new Intent();
				//Seta a mensagem de retorno
				it.putExtra("msg","Clicou em Sim !");
				//Seta o status do resultado e a Intent
				setResult(1,it);
				//Fim desta activity
				finish();
			}});

		//Nao
		Button nao = (Button) findViewById(R.id.btNao);
		nao.setText("Não");
		nao.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				//Cria a Intent
				Intent it = new Intent();
				//Seta a mensagem de retorno
				it.putExtra("msg","Clicou em Não !");
				//Seta o status do resultado e a Intent
				setResult(2,it);
				//Fim desta activity
				finish();
			}});
	}
}
