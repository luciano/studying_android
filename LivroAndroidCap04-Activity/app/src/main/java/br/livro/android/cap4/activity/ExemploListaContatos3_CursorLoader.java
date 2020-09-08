package br.livro.android.cap4.activity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Lista os contatos utilizando o CursorLoader
 * 
 * @author ricardo
 * 
 */
@SuppressLint("NewApi")
public class ExemploListaContatos3_CursorLoader extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {
	private SimpleCursorAdapter adaptador;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Create an empty adapter we will use to display the loaded data.
		adaptador = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null, new String[] { Contacts.DISPLAY_NAME,
				Contacts.CONTACT_STATUS }, new int[] { android.R.id.text1, android.R.id.text2 }, 0);
		setListAdapter(adaptador);

		// Dispara o cursor loader
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// recupera o cursor na posição selecionada
		Cursor c = (Cursor) adaptador.getItem(position);

		// recupera o Nome e Telefone
		String nomeColuna = ContactsContract.Contacts.DISPLAY_NAME;
		String nome = c.getString(c.getColumnIndexOrThrow(nomeColuna));

		Toast.makeText(this, "Contato selecionado: " + nome, Toast.LENGTH_SHORT).show();
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
		Uri uri = Contacts.CONTENT_URI;
		CursorLoader loader = new CursorLoader(this, uri, null, null, null, null);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adaptador.swapCursor(cursor);
		Toast.makeText(this, "Cursor carregado!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adaptador.swapCursor(null);
	}
}
