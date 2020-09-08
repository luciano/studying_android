package br.livro.android.cap4.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import br.livro.android.cap4.R;

/**
 * Exemplo simples de ListActivity com SimpleAdapter
 * 
 * Layout nativo do Android
 * 
 * @author ricardo
 *
 * Estudado em 13 e 23 de Fevereiro de 2015
 *
 */
public class ExemploSimplesAdapter1 extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

        // Para customizar o ListView com nosso Layout.
        // Isto nao é obrigatorio se o layout do Android agradar voce.
		// Por exemplo para customizar a cor de fundo
        setContentView(R.layout.layout_listview_contatos);

		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		
		// Cada item de uma linha precisa ser um HashMap
		// O HashMap contem as chaves e valores necessarios para preencher os elementos da tela
		for (int i = 0; i < 10; i++) {
			HashMap<String,String> item = new HashMap<String,String>();
			item.put( "nome","Nome " + i );
			item.put( "fone","Fone " + i );
			list.add(item);
		}

		// Utiliza o adaptador SimpleAdapter, 

		// Array que define as chaves do HashMap
		String[] from = new String[] { "nome","fone" };

		// text1 e text2 sao padraes do android para o layout nativo "two_line_list_item"
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
		int layoutNativo = android.R.layout.two_line_list_item;

		setListAdapter(new SimpleAdapter(this,list,layoutNativo, from,to));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// Pega o item naquela posicaoo
		Object o = this.getListAdapter().getItem(position);

		String item = o.toString();

		//exibe um alerta
		Toast.makeText(this, "Voce selecionou: " + item, Toast.LENGTH_SHORT).show();
	}
}
