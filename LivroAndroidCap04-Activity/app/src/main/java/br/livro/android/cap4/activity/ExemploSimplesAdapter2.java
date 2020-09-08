package br.livro.android.cap4.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import br.livro.android.cap4.R;

/**
 * Exemplo simples de ListActivity com SimpleAdapter
 * 
 * Layout customizado da aplicacao. Ver XML de layout
 * 
 * @author ricardo
 *
 * Estudado em 13 E 23 de Fevereiro de 2015
 * 
 */
public class ExemploSimplesAdapter2 extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Apenas para brincar... sobrescreve o layout do ListView default do Android
		setContentView(R.layout.layout_listview_contatos);

		ListAdapter adaptador = criaAdaptador();
		setListAdapter(adaptador);
	}

	// Cria o adaptador da lista para fornecer o conte�do
	private ListAdapter criaAdaptador() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Cada item na Lista � uma lista
		// Para determinar os valores existe um HashMap para cada linha
		for (int i = 0; i < 30; i++) {
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("nome", "Nome " + i);
			item.put("fone", "Fone " + i);
			list.add(item);
		}

		// Utiliza o adaptador SimpleAdapter,

		// Array que define as chaves do HashMap
		String[] from = new String[] { "nome", "fone" };

		// nome e fone sao definidos no layout_contatos
		int[] to = new int[] { R.id.nome, R.id.fone };

        //os parametros sao o contexto, lista preenchido (ArrayList), layout tela, nome das chames HashMap, id das TextView
		SimpleAdapter adaptador = new SimpleAdapter(this, list, R.layout.layout_contatos_fone, from, to);
		return adaptador;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// Pega o item naquela posi��o
		Object o = this.getListAdapter().getItem(position);

		String item = o.toString();

		// exibe um alerta
		Toast.makeText(this, "Voce selecionou: " + item, Toast.LENGTH_SHORT).show();
	}
}
