package br.livro.android.cap2;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author Ricardo Lecheta
 *
 * Estudado em 23 de Janeiro de 2015
 *
 * Herança de classes em Java
 * Usa a palavra reservada extends para informar ao compilador que a nossa classe herda
 * os atributos de uma outra classe. A classe mae.
 *
 * Assim a classe filha possui todos os atributos da classe mae.
 *
 * extends => palavra usada para informar que classe x herda todos os atributos de classe y
 *
 * referencia: http://www.caelum.com.br/apostila-java-orientacao-objetos/heranca-reescrita-e-polimorfismo/#7-4-polimorfismo
 */
public class PrimeiroExemplo extends Activity {
	/*
	 * Numa classe mae possui varios metodos,  e dependendo do problema tratado
	 *  é necessario modificar um desses metodos para fazer novas funcoes, mas sem alterar
	 *  a classe mae. Para isso se sobreescreve ou reescreve o metodo. Para informar ao compilador
	 *  que o metodo seguinte foi reescrito usa a notacao @Override
	 *
	 * @override => notacao usada para informar que metodo da classe mae foi reescrito
	 *
	 */
    @Override

    /*
     * Metodo onCreate(bundle) sempre deve existir na Activity,
     *  ele é responsavel por criar e iniciar a Activity
     *
     *  Metodo setContentView responsavel por ligar a activity com a view
     *   pois activity é uma tela mas ela nao sabe desenhar, e a view tem essa funcao
     *   desenhar coisas na tela, a interface grafica
     *  pode receber como paramentro uma referencia para um layout XML com auxilio da classe R
     *   ou receber uma view da API Java
     *
     */
	public void onCreate(Bundle savedInstanceState) {
		/*
		 * super. => acessa metodo da classe mae (superclasse)
		 *
		 */
        super.onCreate(savedInstanceState);

		// Configura o arquivo "/res/layout/main.xml" como a view da tela
		setContentView(R.layout.main);
	}
}

/*
 * Classe R.java
 *      Tem constantes para facilitar o acesso aos recursos do projeto como um arquivo XML,
 *       uma imagem localizada na pasta drawable
 *
 *      Sempre que um recurso e adicionado no projeto, a classe R se atualizada automaticamente
 *        Nunca deve alterar a classe R manualemtne.
 *      Ela possui inteiros que sao posicoes de momoria onde se encontram aqueles recursos.
 *       esses inteiros sao gerados automaticamente e servem de referencia para os recursos (res)
 *
 *  PrimeiroExemplo.jeva
 *   A classe android.app.Activity representa uma tela da aplicacao e é responsavel por controlar
 *     o estado e os eventos na tela. Para cada tela da aplicacao é necessario criar uma
 *     classe filha de Activity. O metodo onCreate(bundle) precisa ser implementado obrigariamente
 *     e é chamado de forma automatica pelo Android quando a tela é criada.
 *
 *   A classe android.app.Activity porem nao sabe desenhar nada na tela e para isso precisa da ajuda
 *     da classe android.view.View que por sua vez se encarrega de desenhar os componentes
 *     visuais, como campos de texto, botoes e imagens.
 *
 *   O metodo setContentView(view) responsavel por fazer a ligacao entre a activity e a view
 *     que sera responsavel por desenhar a interface grafica da tela. Deve sempre ser chamado
 *     durante a execucao do metodo onCreate(bundle) da classe Activity. O metodo setcontentView
 *     pode receber um arquivo XML referenciado atraves da classe R ou receber uma view por meio
 *     da API Java.
 *
 *   Uma Activity sempre sera responsavel por exibir uma tela ao usuario e tratar os seus eventos
 *
 *   Para cada tela da aplicacao deve se criar uma activity que controla os eventos e estado da tela
 *   Para desenhar a interface grafica e necessario informar um objeto do tipo View.
 *   Existem classes especializadas para desenhar cada tipo de componente como imagens botoes
 *
 */