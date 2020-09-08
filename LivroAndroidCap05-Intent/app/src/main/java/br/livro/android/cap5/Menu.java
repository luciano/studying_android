package br.livro.android.cap5;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.livro.android.cap5.intent.AbrirMapaEnderecoActivity;
import br.livro.android.cap5.intent.EscolherContato_Activity1;
import br.livro.android.cap5.intent.ExemploAbrirBrowser;
import br.livro.android.cap5.intent.ExemploTelaSimNao;
import br.livro.android.cap5.intent.LigarParaTelefoneActivity;
import br.livro.android.cap5.intent.Tela2;
import br.livro.android.cap5.intent.VisualizarTodosContatos;

/**
 * Exemplos de Intents para abrir o browser, fazer ligacoes, etc
 * 
 * @author rlecheta
 * 
 */
public class Menu extends ListActivity {
	private static final String CATEGORIA = "livro";

	//Para identificar a chamada no metodo onActivityResult
	private static final int ACTIVITY_SIM_NAO = 1;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] mStrings = new String[] {
				"Abrir Activity c/ parametros",
				"Activity retorna informacao",
				"Abrir Browser",
				"Retornar resultado de uma Activity",
				"Abrir Mapa no endereco",
				"Ligar para telefone",
				"Visualizar Contato 1",
				"Visualizar Todos Contatos",
				"Enviar E-mail",
				"Enviar SMS",
				"Compartilhar",
				"Tirar Foto",
				"Gravar Video",
				"Sair"};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		try {
			switch (position) {
			case 0:
				Intent intent = new Intent(this,Tela2.class);
				intent.putExtra("mensagem", "Mensagem de teste para a Intent !");
				startActivity(intent);
				break;//ok
			case 1:
				//A ActivitySimNao retorna se clicou no botao Sim ou Nao
				//Utiliza o metodo startActivityForResult()
				//metodo
				startActivityForResult(new Intent(this,ExemploTelaSimNao.class),ACTIVITY_SIM_NAO);
				break;//ok
			case 2:
				startActivity(new Intent(this,ExemploAbrirBrowser.class));
				break; //ok
			case 3:
				startActivity(new Intent(this,EscolherContato_Activity1.class));
				break;
			case 4:
				startActivity(new Intent(this,AbrirMapaEnderecoActivity.class));
				break;
			case 5:
				startActivity(new Intent(this,LigarParaTelefoneActivity.class));
				break;//ok
			case 6:
				//Visualizar o Contato 1 da lista de contatos
				Uri uri = Uri.parse("content://com.android.contacts/contacts/1");

				//envia a mensagem ao sistema operacional
				startActivity(new Intent(Intent.ACTION_VIEW,uri));
				break;//ok
			case 7:
				//ACTION_PICK
				startActivity(new Intent(this,VisualizarTodosContatos.class));
				break;//ok
			case 8:
				//Email
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Titulo do email");
				emailIntent.putExtra(Intent.EXTRA_TEXT, "Ola");
				emailIntent.putExtra(Intent.EXTRA_EMAIL, "rlecheta@gmail.com");
				emailIntent.setType("message/rfc822");
				startActivity(emailIntent);
				break;
			case 9:
				//SMS
				Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		        smsIntent.putExtra("address","tel:9999999");
		        smsIntent.putExtra("sms_body", "Oi cara");
		        smsIntent.setType("vnd.android-dir/mms-sms");
		        startActivity(smsIntent);
				break;
			case 10:
				//Compartilhar
				Intent shareIntent = new Intent(Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Compartilhar");
				shareIntent.putExtra(Intent.EXTRA_TEXT, "Bla bla bla");
				startActivity(shareIntent);
				break;
			case 11:
				// Tirar foto
				// "android.media.action.IMAGE_CAPTURE
				Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(fotoIntent, 0);
				break;
			case 12:
				// Gravar V�deo
				// android.media.action.VIDEO_CAPTURE
				Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				startActivityForResult(videoIntent, 0);
				break;
			default:
				finish();
			}
		} catch (Exception e) {
			Toast.makeText(this, "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int codigo, int resultado,Intent it) {
		Log.i(CATEGORIA,"Menu.onActivityResult: " + codigo + ", resultado: " + resultado + " > " + it);
		if (codigo == ACTIVITY_SIM_NAO) {
			Bundle params = it != null ? it.getExtras(): null;
			if (params != null) {
				String msg = params.getString("msg");
				if (resultado == 1) {
					// Sim
					Toast.makeText(this, "Escolheu Sim: " + msg,Toast.LENGTH_SHORT).show();
				} else if (resultado == 2) {
					// Nao
					Toast.makeText(this, "Escolheu Nao: " + msg,Toast.LENGTH_SHORT).show();
				} else {
					// cancelado talvez..
					Toast.makeText(this, "Nao definido: " + msg,Toast.LENGTH_SHORT).show();
				}
			}
		}
	}
	
	protected void onStart() {
		super.onStart();
		Log.i(CATEGORIA, "onStart() chamado.");
	}

	protected void onRestart() {
		super.onRestart();
		Log.i(CATEGORIA, "onRestart() chamado.");
	}

	protected void onResume() {
		super.onResume();
		Log.i(CATEGORIA, "onResume() chamado.");
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.i(CATEGORIA, "onSaveInstanceState() chamado.");
	}

	protected void onPause() {
		super.onPause();
		Log.i(CATEGORIA, "onPause() chamado.");
	}

	protected void onStop() {
		super.onStop();
		Log.i(CATEGORIA, "onStop() chamado.");
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.i(CATEGORIA, "onDestroy() chamado.");
	}
}