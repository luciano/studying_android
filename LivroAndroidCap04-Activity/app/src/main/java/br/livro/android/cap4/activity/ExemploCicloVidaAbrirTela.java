package br.livro.android.cap4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Exemplos simples que gera logs nos metodos de ciclo de vida da Activity
 * 
 * Este exemplo demonstra a navegacao entre as telas, e logs sao impressos para monitorar os metodos chamados
 * 
 * @author ricardo
 *
 * Estudando em 02, 03 e 20 de Fevereiro de 2015
 *
 * Uma interface pode definir uma série de métodos, mas nunca conter implementação deles.
 * Ela só expõe o que o objeto deve fazer, e não como ele faz, nem o que ele tem.
 * Como ele faz vai ser definido em uma implementação dessa interface.
 *
 * No momento em que ele implementa essa interface, ele precisa escrever os métodos pedidos
 * pela interface (muito parecido com o efeito de herdar métodos abstratos, aliás, métodos de
 * uma interface são públicos e abstratos, sempre).
 * Para implementar usamos a palavra chave implements na classe
 *
 * implements => informa o metodo que vai ser implementado, a interface (classe original do
 * cabecalho) so possui o cabecalho do metodo e nao sua implementacao
 *
 * A interface define que todos vão saber o que o metodo faz, enquanto a implementação define
 * como exatamente vai ser feito (como o metodo faz).
 *
 * O que um objeto faz é mais importante do que como ele faz.
 *
 */
public class ExemploCicloVidaAbrirTela extends ExemploCicloVida implements OnClickListener {

    /*
     * Para navegar entre telas na aplicacao exitem dois metodos que podem ser utilizados para
     * iniciar uma outra activity, sao eles startActivity(intent) e startActivityForResult(intent, codigo)
     *
     * A diferenca entre os dois metodos e que startActivity(intent) apenas inicia uma outra
     * activity sem nenhum vinculo com a activity que a chamou.
     *
     * O metodo startActivityForResult(intent, codigo) recebe um parametro que indentifica essa
     * chamada para posteriormente essa segunda activity possa retornar alguma informacao para a
     * activity que a chamou. Esse metodo é usado quando a activity inicial que fez a chamada
     *  esteja interessada em ler o retorno quando a segunda activity terminar
     *
     *  Os metodos startActivity(intent) e starActivityForResult(intent, codigo) recebem um objeto
     *  do tipo android.content.Intent como parametro. Essa classe é o coracao do android.
     *
     * Uma intent representa uma intencao de realizar algo. Nesse caso a intencao e abrir uma tela.
     */

    public void onCreate(Bundle icicle) {
        /*
         * Vai demonstrar o ciclo de vida como a aplicacao anterios e tem um botao para chamar
         * a outra tela.
         *
         * Botao é definido pela classe Button é usado como a view principal da tela e nenhum
         * arquivo XML de layout foi usado. Ao clicar no botao o metodo onClick(view) sera chamado
         * Nesse metodo é criado um objeto da classe android.content.Intent informando a classe
         * da proxima activity que deve ser chamada. a classe Tela2 nesse caso.
         *
         * Ao chamar o metodo startActivity(intent) é enviada uma solicitacao para o sistema
         * operacional abrir uma nova tela.
         *
         */
		super.onCreate(icicle);

        //cria um botao na tela que sera usado como view principal
		Button b = new Button(this);
		b.setText("Clique aqui para abrir a tela."); //mensagem exibida na tela
		b.setOnClickListener(this);

		setContentView(b);
	}
	public void onClick(View v) {
		/* Adaptado para aceitar os dois modos de passagem de parametros
		 *
		 *       Cria variavel do tipo Intent, e informa a classe da activity que se deseja abrir
         * chama o metodo startActivity(intent) e passa como parametro a intent criada ja
         * configurada com a tela a ser aberta
         *
		 *                      Passagem de parametros para proxima tela
		 *
		 * Metodo 1: usando android.os.Bundle
		 *
		 *  A classe android.os.Bundle é usada para informar varios parametros no padrao chave = valor
		 *  o metodo putString(chave, valor) usado no exemplo para passar a mensagem para outra tela
		 *  No exemplo foi usado putString, mas existe metodos para cada tipo primitivo
		 *  putBoolean, putChar, putByteArray, putInt, putFloat, putLong, putDouble entre outros.
		 *
		 *  O metodo putExtras(Bundle) da classe android.content.Intent recebe o objeto Bundle que
		 *  sera passado como parametro para a outra tela.
		 *
		 *  A classe Intent é utilizada para navegar entre as telas da aplicacao e tambem pode conter
		 *  os paramentros a serem enviados de uma tela para outra. A traducao de intent é intencao
		 *  entao a classe intent representa nossa intencao de realizar uma tarefa. Essa é a maneira
		 *  de se comunicar com o sistema operacional.
		 *
		 * Metodo 2: usando android.content.Intent
		 *
		 *  A classe android.os.Bundle  armazena os parametros enviados de uma tepa para a outra
		 *  na forma de chave = valor.
		 *
		 *  Mas a classe intent que representa nossa intencao de navegar de uma tela para outra
		 *  contem o metodo putExtra(chave, valor) onde o valor pode ser qualquer tipo primitivo.
		 *
		 *
		 */
        Intent it = new Intent(this,Tela2.class);
		Bundle params = new Bundle();
		params.putString("msg", "Enviada da Activity Principal!\n Por meio das classes Bundle.putString e Intent.putExtras.");
		it.putExtras(params);
		//startActivity(it);

		//Intent it = new Intent(this,Tela2.class);
		it.putExtra("msg1", "Enviada da Activity Principal!\n Por meio da classe Intent.putExtra.");
		startActivity(it);
	}
}
