package br.livro.android.cap4;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.livro.android.cap4.activity.ExemploCicloVidaAbrirTela;
import br.livro.android.cap4.activity.ExemploListActivity1;
import br.livro.android.cap4.activity.ExemploListView;
import br.livro.android.cap4.activity.ExemploListaContatos1;
import br.livro.android.cap4.activity.ExemploListaContatos3_CursorLoader;
import br.livro.android.cap4.activity.ExemploSimplesAdapter1;
import br.livro.android.cap4.activity.ExemploSimplesAdapter2;
import br.livro.android.cap4.activity.provider.ListActivityCursorCarros;
import br.livro.android.cap4.activity.smile.ExemploSmileAdapter;

/**
 * Exemplos de Activity (ListActivity, etc)
 * 
 * Demonstra também um ListActivity que utiliza o Content Provider de Carros
 * para obter o Cursor
 * 
 * Obs: Para funcionar o pacote do ContentProvider de carros precisa estar
 * instalado
 * 
 * @author rlecheta
 *
 * Estudado em 11 e 22 de Fevereiro de 2015
 *
 * Essa classe define a lista com o nome de cada exemplo do capitulo 4, e quando algum item da lista
 * for selecionado, o metodo onListItemClick(ListView, View, Posision, Id) e chamado pela
 * ListActivity para recuperar a posicao do item selecionado. Dessa forma é possivel iniciar a
 * activity do exemplo desejado com o metodo startActivity(intent)
 *
 */
public class Menu extends ListActivity {
	private static final String[] nomes = new String[] { "Ciclo de Vida", "ArrayAdapter", "SimpleAdapter1", "SimpleAdapter2",
			"ExemploListView", "Contatos - ArrayAdapter 1", "Contatos - CursorLoader", "Cursor de Carros", "Customizado - Smile", "Sair" };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			startActivity(new Intent(this, ExemploCicloVidaAbrirTela.class));
			break;
		case 1:
			startActivity(new Intent(this, ExemploListActivity1.class));
			break;
		case 2:
			startActivity(new Intent(this, ExemploSimplesAdapter1.class));
			break;
		case 3:
			startActivity(new Intent(this, ExemploSimplesAdapter2.class));
			break;
		case 4:
			startActivity(new Intent(this, ExemploListView.class));
			break;
		case 5:
			startActivity(new Intent(this, ExemploListaContatos1.class));
			break;
		case 6:
			startActivity(new Intent(this, ExemploListaContatos3_CursorLoader.class));
			break;
		case 7:
			startActivity(new Intent(this, ListActivityCursorCarros.class));
			break;
		case 8:
			startActivity(new Intent(this, ExemploSmileAdapter.class));
			break;
		default:
			// Encerra a activity (encerra o ciclo de vida)
			finish();
		}
	}
}