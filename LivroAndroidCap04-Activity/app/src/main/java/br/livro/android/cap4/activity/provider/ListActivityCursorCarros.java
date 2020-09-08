package br.livro.android.cap4.activity.provider;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Precisa ter o projeto "LivroAndroidCap14-BancoDados" instalado
 * 
 * Demonstra como listar os carros, salvos no banco de dados de outro aplicativo.
 * 
 * Para isso utilizamos o conceito de content provider (que está no projeto que tem o cadastro dos carros)
 * 
 * @author ricardo
 *
 */
public class ListActivityCursorCarros extends ListActivity {
	private ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Consulta por content provider, o banco de dados de outro aplicativo
        Uri uri = Uri.parse("content://br.livro.android.provider.carro/carros");
		
		// Recupera o cursor dos carros
		Cursor c = getContentResolver().query(uri, null, null, null, null);

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

		while (c.moveToNext()) {
			String nome 	= c.getString(c.getColumnIndex("nome"));
			int ano 		= c.getInt(c.getColumnIndex("ano"));
			String placa 	= c.getString(c.getColumnIndex("placa"));
			adapter.add(String.format("Nome [%s], Placa [%s], Ano [%d]", nome, placa, ano));
		}

		setListAdapter(adapter);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long itemId) {
		super.onListItemClick(l, v, position, itemId);

		String s = adapter.getItem(position);

		Toast.makeText(this,"Carro selecionado: " + s,Toast.LENGTH_SHORT).show();
	}
}
