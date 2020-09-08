package br.livro.android.cap4.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Exemplos simples que gera logs nos metodos de ciclo de vida da Activity
 * 
 * @author ricardo
 *
 *  Estudando em 30 de Janeiro e 20 de Fevereiro de 2015
 *
 *
 *                  Ciclo de vida de uma Activity
 *      A activity pode esta executando, temporariamente interrompida em segundo plano ou destruida.
 *  e muito importante entender como o sistema operacional lida com esse ciclo de vida e usar isso
 *  para criar aplicacoes mais robustas. Uma activity representa uma tela da aplicacao e quando o
 *  sistema operacional decide encerra-la, se tem uma chance de salvar algumas informacoes.
 *
 *      Uma activity tem um ciclo de vida bem definido. Cada activity iniciada é inserida no topo
 *  de uma pilha, chamada de "activity stack". A activity que esta no topo da pilha é a activity
 *  que esta em execucao e as demais podem estar executando em segundo plano, estar no estado
 *  pausado ou totalmente paradas.
 *
 *      Sempre que uma activity esta pausada o sistema operacional pode decidir encerrar o processo
 *  por algum motivo, podendo ser ate mesmo para liberar memoria para outras aplicacoes.
 *
 *                  Metodos que controlam os estados da Aplicacao
 *      Os metodos em relacao ao ciclo de vida sao: onCreate(bundle), onStart(), onRestart()
 *  onResume(), onPause(), onStop(), onDestroy()
 *
 *      Ciclo de vida tem inicio, meio e fim. Existem tres subniveis do ciclo de vida principal.
 *  Sao eles:
 *
 *  - Entire lifetime => ciclo de vida completo entre o inicio e destruicao da activiry
 *          Ocorre apenas UMA VEZ e define o tempo de vida completo de uma activity. Ocorre entre
 *          as chamadas do metodo onCreate(bundle) e onDestroy(), os quais sao chamados UMA UNICA
 *          vez quando a activity é criada e destruida. No metodo onCreate(bundle) a activity ainda
 *          nao esta visivel para o usuario, pois é nesse metodo que deve-se criar uma view e chamar
 *          o metodo setcontetView(view) para construir a interface grafica
 *
 *  - Visible lifetime => activity esta iniciada mas pode estar no topo da pilha interagindo com o
 *          usuario ou temporariamente parada em segundo plano. Ocorre entre os metodos onStart() e
 *          onStop(). O metodo onStart() é chamado logo depois do metodo onCreate(bundle), o metodo
 *          onCreate(bundle) é chamado apenas uma vez. Durante esse periodo o usuario pode visualizar
 *          a activity na tela mas ela pode nao estar necessariamente em primeiro plano e interagindo
 *          com o usuario. Talvez outra activity pode estar no topo da pilha e executando.
 *          Uma activity so ocupa o topo da pilha quando o metodo onResume() é executado. Portanto
 *          esse ciclo de vida inclui Todo o tempo em que a activity esta no topo da pilha ou
 *          esta em segundo plano esperando outra activity terminar. O metodo onStart() e onStop()
 *          sao chamados varias vezes sempre que a activity fica visivel ou escondida do usuario.
 *          Sempre que o metodo onStart() é chamado o metodo onResume() é chamado automaticamente.
 *
 *  - Foreground lifetime => activity esta no topo da pilha e interagindo com o usuario
 *          Ocorre entre os metodos onResume() e onPause(), e durante este tempo a activity esta no
 *          topo da pilha e interagindo com o usuario. Uma activity pode frequentemente alternar
 *          entre os estados executando e pausado. É recomendado que o codigo que executa nesses
 *          dois metodos sejam bem leves e rapidos sendo que podem ser executados varias vezes.
 *
 *  DUTANTE OS METODOS onPause(), onStop(), onDestroy() O PROCESSO DA ACTIVITY PODE SER DESTRUIDO(KILL)
 *  PELO SISTEMA OPERACIONAL CASO AS CONDICOES DE MEMORIA ESTEJAM CRITICAS.
 *
 */

public class ExemploCicloVida extends Activity {
	protected static final String CATEGORIA = "livro";

	public void onCreate(Bundle icicle) {

        /*
         *      O metodo onCreate(bundle) é obrigatorio e chamado UMA UNICA VEZ. Nesse  metodo deve-se
         *  criar uma view e chamar o metodo setContentView(view) para exibir algo na tela. Logo
         *  depois que o metodo onCreate(bundle) for finalizado o metodo onStart() e chamado
         *  automaticamente para iniciar o ciclo de vida visivel da activity.
         *
         * Fluxograma:
         *  onCreate(bundle) inicia quando usuario clica no icone da aplicacao
         *  ao ser encerrado automaticamente é chamado metodo onStart()
         *  onCreate(bundle) chama apenas o metodo onStart()
         */

        super.onCreate(icicle);

		Log.i(CATEGORIA, getClassName() + ".onCreate() chamado: " + icicle);

		TextView t = new TextView(this);
		t.setText("Exemplo do ciclo de vida.\nConsulte os logs no LogCat.");
		setContentView(t);
	}
	protected void onStart() {

		/*
		 *      É chamado quando a activity esta ficando visivel ao usuario e ja tem uma view. Pode
		 *   ser chamado depois dos metodos onCreate(bundle) ou onRestart(). Depois do metodo
		 *   onStart() sempre é chamado o metodo onResume()
		 *
		 * Fluxograma:
		 *  onStart() chamado quando encerra o metodo onCreate, esse metodo chama apenas o metodo
		 *  onResume
		 */

        super.onStart();
		Log.i(CATEGORIA, getClassName() + ".onStart() chamado.");
	}
	protected void onRestart() {

        /*
         *  Chamado quando a activity for parada temporariamente e esta sendo inciada outra vez.
         *  O metodo onRestart() chama automaticamente o metodo onStart();
         *
         * Fluxograma:
         *   É chamado pelo metodo onStop() e esse metodo chama o metodo onStat() para reiniciar a
         *   aplicacao que foi interrompida por algum motivo e ficou fora do topo da pilha
         */

        super.onRestart();
		Log.i(CATEGORIA, getClassName() + ".onRestart() chamado.");
	}
	protected void onResume() {

        /*
         *      Chamado quando a activity esta no topo da pilha activity stack e dessa forma ja
         *   esta executando como a activity principal e pronta para interagir com o usuario. Podemos
         *   dizer que o metodo onResume() representa o estado de que a activity esta executando
         *   Metodo sempre é chamado depois do metodo onStart()
         *
         *   Fluxograma:
         *      É chamado pelo  metodo onStart() e aplicacao esta no topo da pilha,
         *      caso outra activity for iniciada ou usuario
         *      atender uma ligacao por exemplo, o metodo onResume chama o metodo onPause()
         */

        super.onResume();
		Log.i(CATEGORIA, getClassName() + ".onResume() chamado.");
	}
	protected void onPause() {

        /*
         *      Se algum evento ocorrer como o celular entrar em modo de espera/dormir para
         *  economizar enerfia ou uma Intent esta sendo processada a activity do topo da pilha
         *  que esta executando pode ser temporariamente interrompida. Para isso o metodo onPause()
         *  e chamado para salvar o estado da aplicacao para que posteriomente quando a activity
         *  voltar a executar tudo possa ser recuperado se necessario no metodo onResume().
         *
         *  Fluxograma:
         *    chamado pelo metodo onResume()
         *    Se usuario nao ve mais a tela chama o metodo onStop()
         *    se o sistema precisa liberar recursos chama o metodo onDestroy()
         *    se o usuario volta a executar a activity e chamado o metodo onResume()
         */

		super.onPause();
		Log.i(CATEGORIA, getClassName() + ".onPause() chamado.");
	}
	protected void onStop() {

        /*
         *      Chamado quando a activity esta sendo encerrada e nao esta mais visivel ao usuario
         *   o que pode ocorrer quando outra activity é iniciada, por exemplo. Depois de parada a
         *   activity pode ser reiniciada se necessario. Caso isso ocorra o metodo onRestart() é
         *   chamando para reiniciar a activity. Caso a activity fique muito tempo parada em segundo
         *   plano e o sistema operacional precisar limpar os recursos para liberar memoria
         *   o metodo onDestroy() pode ser automaticamente chamad para remover comletamente a
         *   activity da pilha.
         *
         *   Fluxograma:
         *     Chamado pelo metodo onPause() quando activity noa esta mais visivel para usuario
         *     Pode chamar metodo onDestroy() se o sistema precisa liberar recursos
         *     se usuario voltar a executar a activity ele volta a ser visivel e ocupa o topo da pilha
         *     e o metodo onRestat() é chamado.
         */

        super.onStop();
		Log.i(CATEGORIA, getClassName() + ".onStop() chamado.");
	}
	protected void onDestroy() {

        /*
         *      Literalmente encerra a execucao de uma activity. Pode ser chamado automaticamente
         *  pelo sistema operacional para liberar recursos ou pode ser chamado pela aplicacao
         *  pelo metodo finish() da classe Activity. Depois do metodo onDestroy() ser chamado
         *  a activity é totalmente removida da pilha e seu processo no sistema operacional é
         *  totalmente encerrado.
         */

		super.onDestroy();
		Log.i(CATEGORIA, getClassName() + ".onDestroy() chamado.");
	}
	private String getClassName() {
		//Retorna o nome da classe sem o pacote
		String s = getClass().getName();
		return s.substring(s.lastIndexOf("."));
	}
}

/*
 *                                     Activity
 *      Deve herdar de android.app.Activity ou alguma subclasse desta. Geralmente representa uma tela
 *  da aplicacao e é responsavel por tratar os eventos gerados nessa tela como pressionar botoes etc.
 *
 *      A classe da activity deve implementar o metodo onCreate(Bundle) que é obrigatorio e
 *  responsavel por realizar a inicializacao necessaria para executar a aplicacao. Chamar o metodo
 *  setContentView(View) para definir a interface de ususario onde um objeto do tipo
 *  android.view.View é informado e é responsavel por desenhar os elementos graficos na tela.
 *
 *      Cada activity deve ser obrigatoriamente declarada no arquivo AndroidManifest.xml para poder
 *   ser usada e reconhecida pela aplicacao. Isso é feito pela tag <activity>
 *
 *       É possivel abreviar o local da activite por meio do ponto, ou escrever o caminho dela
 *   completo. Caso esteja em outro pacote a sintaxe é .pacote.MinhaClasseActivity
 *
 *   Tomar cuidado pois o AndroidManifest.xml é case sensitive entao tomar cuidado para escrever
 *   corretamente o nome da classe activity
 *
 */