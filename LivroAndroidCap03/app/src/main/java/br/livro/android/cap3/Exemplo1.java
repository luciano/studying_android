package br.livro.android.cap3;

import android.app.Activity;
import android.os.Bundle;

/**
 * Hello World
 * 
 * @author ricardo
 *
 * Estudado em 27 de Janeiro de 2015
 *
 */
public class Exemplo1 extends Activity {
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
        /*
         * Faz a ligacao entre  a activity e a view e recebe como parametro a view a ser
         *  exibida na tela. A chamada do metodo deve ser feita sempre no metodo onCreate(bundle)
         *  da activity.
         *
         *  O arquivo de layout main.xml passado como parametro vai ser usado como view
         *   da tela principal.
         *
         *   Para cada aplicacao existira uma activity para controlar seus estados e eventos
         *      mas para definir a interface grafica da tela é utilizada uma view. Dentro da
         *      activity é necessario chamar o metodo setContentView(view) para informar a view
         *      responsavel por desenha a interface da tela.
         *
         */
		setContentView(R.layout.main);
	}
}

/*
 * Classe R
 *
 *  Ela é gerada automaticamente pelo Android Studio e contem constantes para acessar os diversos
 *      recursos do projeto. Essa classe nunca deve ser alterada manualmente.
 *
 *  Sempre que um novo arquivo for adicionado em uma das pastas dentro da pasta res
 *      uma nova constante sera criada automaticamente na classe R. Rsses arquivos
 *      independentes do tipo sao chamados de recursos (resources).
 *
 *  Cada constante na classe R é do tipo int e contem numero unico dentro do projeto
 *
 *  Sempre para acessar o recurso o nome deve ser escrito em minusculo e nao deve conter espacos.
 *      caso um arquivo de nome invalido for fornecido a classe R nao sera compilada corretamente
 *
 *  Ao utilizar constantes da calsse R para acessar os recursos nao e necessario utilizar a
 *      extensao do arquivo
 *
 */