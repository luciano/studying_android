package br.livro.android.cap4.activity;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Exemplo de ListActivity que utiliza faz uma query na agenda para exibir os contatos
 * 
 * @author ricardo
 *
 * Estudado em 13 e 23 de Fevereiro de 2015
 *
 * Para buscar na agenda os nomes vai se utilizar o metodo getContentResolver().query()
 * que retorna um Cursor. Usando um Cursor é possivel percorrer determinados registros
 * como a lista de contatos do celular ou ate resultados de uma consulta ao banco de dados.
 *
 * Foi necessario declarar permisao no AndroidManifest.xml para ler a agenda do celular
 *
 * <user-permission android:name="android.permission.READ_CONTACTS" />
 * interida antes da tag <activity>
 *
 */
public class ExemploListaContatos1 extends ListActivity {
	private ArrayAdapter<String> adaptador;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Uri para buscar os contatos
		Uri uri = ContactsContract.Contacts.CONTENT_URI;

		// Recupera o cursor dos contatos - cursor acessa os registros, le os contatos no caso
		Cursor c = getContentResolver().query(uri, null, null, null, null);

		// Utiliza o ArrayAdapter, para exibir o array de Strings na tela.
		adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        //anda pelo cursor e vai adicionando os contatos no adapter substituindo a string que era passada
		while (c.moveToNext()) {
			// Le a coluna nome e adiciona no adapter
			adaptador.add(c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
		}

		setListAdapter(adaptador);

		// Podemos fechar o cursor depois de utiliza-lo
		c.close();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		String s = (String) adaptador.getItem(position);

		Toast.makeText(this, "Contato selecionado: " + s, Toast.LENGTH_SHORT).show();
	}
}
