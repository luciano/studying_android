package br.livro.android.cap3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Exemplo de layout pela API Java
 * 
 * @author ricardo
 *
 * Estudado em 27 de Janeiro de 2015
 * 
 */
public class Exemplo2 extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);


       //É possivel utilizar o arquivo XML para layout ou fazer com API Java

	    //setContentView(R.layout.layout_exemplo2);

		//Solicita ao Android para abrir o TextView
		TextView view = new TextView(this);
		view.setText("\t\tExemplo de Texto no Android\n\n\n\t\tUsando API Java\n");
		setContentView(view);
        /*
         * Toda a interface da tela pode ser feita atravez da API Java diretamente no codigo
         *
         * No entanto é melhor utilizar um arquivo XML que facilita a separacao entre
         *  interface grafica e logica de negocios.
         *
         *  Quando a tela fica mais complexa fica tambem mais trabalhoso fazer tudo com API Java
         *      e por isso um arquivo XML pode ser utilizado para separar as coisas.
         *      Interface grafica em um lugar e logica em outro.
         *
         */
	}
}