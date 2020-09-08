package br.livro.android.cap3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Exemplo que demonstra o <ImageView>
 * 
 * A primeira imagem e definida no XML, a segunda e defindia com o metodo
 * setImageResource(R.drawable.smile2);
 * 
 * @author ricardo
 *
 * Estudado em 27 de Janeiro de 2015
 *
 */
public class Exemplo3 extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Abre a tela layout_exemplo3.xml
		setContentView(R.layout.layout_exemplo3);

        /*
         * Em aplicacoes reais as imagens podem necessitar dde ser acessadas dinamicamente
         *  atraves de banco de dados ou da internet.
         * Nesse caso a aplicacao precisara recuperar o componente ImageView em tempo
         *  de execucao, e sera necessario informar manualmente qual deve ser a imagem
         *  exibida na tela.
         *
         * Exatamente para isso foi definido o indentificador (id) unico para o segundo ImageView
         *  utilizando o atributo android:id conforme no arquivo XML:
         *  <ImageView
         *      android:id="@+id/imagem2"
         *
         * Esse id pode ser utilizado para recuperar o componente dentro da aplicacao, e para isso
         *  é utilizado o metodo findViewById(id) esse metodo recebe o id do componente desejado e
         *  retorna uma subclasse android.view.View como as classes Button, ImageView, Textview, etc
         *
         *  ImageView img2 = (ImageView) findViewById(R.id.imagem2); a classe R e utilizada
         *  para informar onde a imagem vai ser inserida na tela (local e definido no XML)
         *  ele deixa um "espaco reservado" onde teria a segunda imagem, se a imagem/recurso for
         *   inserido. Caso nao seja inserido nada. E o ImageView com o id estiver no XML
         *   sem ser inserido nada, nao é mostrado nada na tela, e o recurso abaixo é mostrado
         *   na tela como se esse espaco reservado nao existisse. Caso seja inserido algo no
         *   espaco reservado ele vai ser mostrado na tela e o recurso que estava abaixo dele
         *   sera mostrado logo abaixo desse inserido.
         *
         *  Essa imagem e inserida com o metodo setImageResource
         *
         *  Foi recuperado o recurso XML ImageView e inserida uma imagem pelo codigo Java
         *
         */

		// Busca a ImageView pelo id
		ImageView img2 = (ImageView) findViewById(R.id.imagem2);

		img2.setImageResource(R.drawable.smile2);
	}
}
