package br.livro.android.cap6;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplo demonstrando a utilizaçao do wrap_content e fill_parent
 * 
 * @author ricardo
 *
 * Estudado em 23 de Mraço de 2015
 *
 * Frame layout: o componente inserido sempre sera posicionado no canto
 * esquerdo superior e dependendo do seu tamanho ocupará a tela inteira
 * E possivel inserir mais de um componete no FrameLayout mas sempre
 * os ultimos ficam em cima dos primeiros. Pode ser util para colocar imagem
 * de fundo e outros componentes inseridos depois ficam em cima dela
 * É muito usado para colocar ProgressBar ou algum tipo de informacao
 *
 */
public class MenuBasicoLayouts extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] items = new String[] { "wrap_content","fill_parent","wrap_content img","fill_parent img wrap_content","fill_parent img fill_parent"};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
                /*
                 * Layout usa apenas espaco necessario e TextView tambem
                 * Fundo cinza e TextView preta
                 */
				startActivity(R.layout.frame_layout_wrap_content);
				break;
			case 1:
                /*
                 * Layout ocupa tela toda (cinza), mas o TextView apenas o necessário.
                 */
				startActivity(R.layout.frame_layout_fill_parent);
				break;
			case 2:
                /*
                 * Fundo cinza com layout wrap_content usa apenas necessario
                 * e imagem wrap_content usando apenas espaco necessario.
                 */
				startActivity(R.layout.frame_layout_wrap_content_img);
				break;
			case 3:
                /*
                 * Layout ocupa tela toda (cinza) => fill_parent
                 * imagem ocupa apenas espaco necessario => wrap_content
                 */
				startActivity(R.layout.frame_layout_fill_parent_img_wrap);
				break;
			case 4:
                /*
                 * Layout ocupa tela toda (cinza) => fill_parent
                 * imagem ocupa toda a tela => fill_parent
                 */
				startActivity(R.layout.frame_layout_fill_parent_img_fill);
				break;
		}
	}

    /*
     * O importante é olhar os arquivos XML
     * Activity chamada apenas mostra  o arquivo de layout respectivo na tela.
     */
	private void startActivity(int layoutId) {
		Intent intent = new Intent(this,ActivityLayoutIdGenerica.class);
		intent.putExtra("layoutResId", layoutId);
		startActivity(intent);//sobrecarga de metodo, chama o metodo certo
	}

}