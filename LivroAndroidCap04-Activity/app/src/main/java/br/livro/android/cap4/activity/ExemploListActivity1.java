package br.livro.android.cap4.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Exemplo simples de ListActivity com ArrayAdapter
 * 
 * @author ricardo
 *
 * Estudado em 11 e 21 de Fevereiro de 2015
 *
 * A classe android.app.ListActivity ja declara internamente um componente do tipo
 *  android.widget.ListView que desenha os componentes em uma lista vertical e com barra de
 *  rolagem(scroll) se necessario.
 * O metodo setContentView(view) é chamado automaticamente pela ListActivity informando o ListView
 *  de forma que nao é necessario preocupar com o View da tela mas com informar os dados que
 *  irao preencher a lista.
 *
 * Para preencher a lista com informacoes é necessario criar um objeto que implementa a interface
 *  android.widget.ListAdapter que faz a ligacao entre o componente ListView e os valores que
 *  a aplicacao deseja exibir na tela. ListAdpater é um adaptador
 *
 * O metodo onListItemClick(listView, view, posicao, id) é chamado automaticamente quando o
 *  usuario seleciona algum item, e o metodo getListAdapter().getItem(indice) pode ser
 *  utilizado para recuperar informacoes do item selecionado
 */
public class ExemploListActivity1 extends ListActivity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Array de Strings para visualizar na Lista
		String[] itens = new String[] { "Nome 1", "Nome 2", "Nome 3" };

        /*
         * android.R.layout.simple_list_item_1 usa a classe R do proprio android
         *
         * Esse layout ja é predefinido pelo Android
         *
         */
		// Utiliza o adaptador ArrayAdapter, para exibir o array de Strings na tela.
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);

		setListAdapter(adaptador);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// Pega o item naquela posicao
		Object o = this.getListAdapter().getItem(position);

		String item = o.toString();

		//Exibe um alerta
		Toast.makeText(this, "Você selecionou: " + item, Toast.LENGTH_SHORT).show();
	}
}
