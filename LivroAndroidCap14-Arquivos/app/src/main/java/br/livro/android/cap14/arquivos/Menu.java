package br.livro.android.cap14.arquivos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos salvar Arquivos
 * 
 * @author rlecheta
 * 
 */
public class Menu extends ListActivity {

	private static final String[] ops = new String[] { "Salvar no diretório restrito do aplicativo", "Salvar no sdcard","Backup Arquivo", "Sair" };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		int layout = android.R.layout.simple_list_item_1;
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, layout, ops);
		this.setListAdapter(adaptador);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
		case 0:
			startActivity(new Intent(this, ExemploSalvarArquivo.class));
			break;
		case 1:
			// Precisa de <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
			startActivity(new Intent(this, ExemploSalvarArquivoSdCard.class));
			break;
		case 2:
			startActivity(new Intent(this, ExemploSalvarArquivoBackup.class));
			break;
		default:
			finish();
		}
	}
}