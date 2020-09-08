package br.livro.android.cap6.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import br.livro.android.cap6.R;

/**
 * Exemplo do GridView para visualizar imagens
 * 
 * @author rlecheta
 *
 * Estudado em 26 de Março de 2015
 *
 * GridView: É utilizada para exibir outros componentes em uma
 * tabela, seu uso classico é para exibir varias imagens como
 * um album de fotos.
 * Na tag <Grid> sao definidos alguns parametros de espacamente
 * e numero de colunas. Os mais relevantes sao:
 *  columnWidht que éa largura de cada coluna no Grid, em pixels
 *  numColumns Numero de colunas no Grid. Informar o parametro
 *   auto_fit faz com que as colunas se ajustem automaticamente
 *   com base na largura da coluna.
 * Para exibir as imagens no GridView é necessario criar um
 * ListAdapter que retorne uma lista com as imagens necessarias,
 * e usar o metodo setAdapter(ListAdapter) no grid.
 * Para criar o ListAdapter é possivel utilizar a classe
 * android.widget.BaseAdapter como base e implementar o metodo
 * View getView(posicao, view, parent) conforme necessario.
 * No caso o metodo retornara uma ImageView correspondente a
 * imagem selecionada.
 * O metodo setOnListItemClickListener(listener) da classe GridView
 * pode ser utilizado para tratar os eventos gerados caso o usuario
 * selecione e pressione alguma imagem. No metodo
 * onItemClick(parent, view, posicao, id) é possivel recuperar a
 * imagem que foi clicada.
 */
public class ExemploGridView extends Activity {
	// array com os ids das imagens
	private int[] imagens = { R.drawable.smile1, R.drawable.smile2,
			R.drawable.smile1, R.drawable.smile2, R.drawable.smile1,
			R.drawable.smile2, R.drawable.smile1, R.drawable.smile2 };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.exemplo_grid);

		GridView grid = (GridView) findViewById(R.id.grid1);
		grid.setAdapter(new AdaptadorImagem(this, imagens));

		grid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int posicao,long id) {
				Toast.makeText(ExemploGridView.this,"Imagem selecionada: " + posicao, Toast.LENGTH_SHORT).show();
			}
		});
	}
}