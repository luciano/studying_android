package br.livro.android.cap6;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.livro.android.cap6.layout.ExemploScrollView;
import br.livro.android.cap6.layout.api.ExemploLinearLayoutAPI;
import br.livro.android.cap6.layout.api.ExemploTableLayoutAPI;

/*
 * LinearLayout: pode organizar os componetes na vertical ou horizontal
 * os componentes que ficarem pra fora do layout (caso do horizontal)
 * eles ficam como voando fora da tela e nao aparecem
 * atributo android:layout_gravity alinha os componentes de forma centralizada,
 * a esquerda ou  a direita da tela
 * Atribuir peso aos elementos fazem eles ocuparem espacos maiores na tela
 * de acordo com o peso atribuido sera o tamanho do componente na tela
 * o atributo responsavel por isso e android:layout_weight em que
 * é fornecido um valor numero, quanto maior o valor com relacao aos
 * outros componetnes mais sera o espaco desse componente na tela
 * ao usar o atributo android:layout_weight a altura
 * android:layout_height deve ser definida como 0dp
 *
 * TableLayout: cada linha da tabela é formada por um android.widget.TableRow
 * os atributos android:stretchColumns e android:shrinkColumns recebem os
 * indices das colunas separados por virgulas para altera-las
 * android:stretchColumns -> faz com que as colunas especificadas ocupem o
 *  espaco disponivel na tela, expandido-as
 * android:shrinkColumns -> faz com que as colunas especificadas sempre sejam
 *  exibidas na tela, caso o valor seja grande e fique pra fora da tela
 *  a linha é quebrada
 *
 * RelativeLayout: Pode posicionar os componentes ao lado, abaixo
 * ou acima de um outro componente ja existente, para isso é necessario
 * definir um id para cada componente da tela. Atributos usados:
 * android:layout_below -> posiciona abaixo do cmoponente indicado
 * android:layout_above -> posiciona acima do componente indicado
 * android:layout_toRightOf -> posiciona a direita do componente indicado
 * android:layout_toLeftOf -> posiciona a esquerda do componente indicado
 * android:layout_alignParentTop    -> alinha no topo do componente indicado
 * android:layout_alignParentBottom -> alinha abaixo do componente indicado
 * android:layout_marginTop -> utilizado para definir um espaco na margem superior do componente
 * android:layout_marginRight -> utilizado para definir um espaco a direita do componente
 * android:layout_marginLeft -> utilizado para definir um espaco a esquerda do componente
 * O RelativeLayout é flexivel ao construir telas mas pode aumentar a complexidade da construcao
 * E se mudar a localizacao de um componente pode quebrar toda a tela pois um componente esta
 * localizado com relacao a outro
 *
 * AbsoluteLayout (Deprecated):  permite controlar exatamente as posicoes
 * x e y do componente usando os atributos android:layout_x e android:layout_y
 *
 * ScrollView: tela contem muitos elementos e seja necessario fazer rolagem na tela
 * Recebe apenas um componente que vai ocupar a tela inteira. Geralmente é adicionado
 * outro layout aninhado dentro do ScrollView como o LinearLayout
 */
public class MenuLayouts extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] items = new String[] { 
				"FrameLayout background",
				"LinearLayout horizontal",
				"LinearLayout vertical",
				"LinearLayout gravity",
				"LinearLayout weight",
				"LinearLayout weight colors",
				"LinearLayout Form (weight)",
				"TableLayout shrink",
				"TableLayout stretch",
				"TableLayout Form",
				"RelativeLayout Form",
				"AbsoluteLayout Form",
				"LinearLayout aninhado",
				"ScrollView",
				"LinearLayout API",
				"TableLayout API"};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(R.layout.frame_layout_background);
				break;
			case 1:
				//LinearLayout (default)
				startActivity(R.layout.linear_layout_horizontal);
				break;
			case 2:
				//LinearLayout Vertical
				startActivity(R.layout.linear_layout_vertical);
				break;
			case 3:
				//LinearLayout aninhado para montar um Formul�rio
				startActivity(R.layout.linear_layout_gravity);
				break;
			case 4:
				startActivity(R.layout.linear_layout_weight_text_2);
				break;
			case 5:
				startActivity(R.layout.linear_layout_weight_colors);
				break;
			case 6:
				startActivity(R.layout.linear_layout_form);
				break;
			case 7:
				startActivity(R.layout.table_layout_shrink);
				break;
			case 8:
				startActivity(R.layout.table_layout_stretch);
				break;
			case 9:
				startActivity(R.layout.table_layout_form);
				break;
			case 10:
				//Formulario
				startActivity(R.layout.relative_layout_form);
				break;
			case 11:
				//Formulario
				startActivity(R.layout.absolute_layout_form);
				break;
			case 12:
				//Aninhado
				startActivity(R.layout.linear_layout_form_aninhado);
				break;
			case 13:
				startActivity(new Intent(this,ExemploScrollView.class));
				break;
			case 14:
				startActivity(new Intent(this,ExemploLinearLayoutAPI.class));
				break;
			case 15:
				startActivity(new Intent(this,ExemploTableLayoutAPI.class));
				break;
		}
	}

	private void startActivity(int layoutId) {
		Intent intent = new Intent(this,ActivityLayoutIdGenerica.class);
		intent.putExtra("layoutResId", layoutId);
		startActivity(intent);
	}

}