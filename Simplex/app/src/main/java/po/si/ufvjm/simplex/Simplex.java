package po.si.ufvjm.simplex;

import android.util.Log;

public class Simplex {

    protected int indiceLinhaPivo; //possui valor linha onde deve ser o pivo, coluna é fixa
    protected int colunaEntra;//variavel com maior custo reduzido
    protected int colunaSai;
    protected double maiorCusto;
    protected Double[][] matriz;
    protected String solucaoDetalhada = "";

    Simplex() {
        String s = "Entrou na classe";
        Log.i("Depuracao", s);
    }

    public void Solution() {
        throw new IllegalArgumentException("Construtor deve ter endereço do arquivo com matrizes.");
    }
    //fazendo edicoes para mostrar o passo a passo da resolucao
    //usando uma string que pega o processo de resolucao
    public void Solution(Double[][] matriz) {

        this.matriz = matriz;
        do {

            //edicao

            mostrarMatriz();

            custoReduzidoPositivo();

            if(maiorCusto > 0.0) {
                variavelSai();
                encontrarPivo();

                //edicao
                solucaoDetalhada += "Variável que entra:\tx"  + colunaEntra + "\n";
                solucaoDetalhada += "Variável que sai: \t\tx" + colunaSai   + "\n";
                solucaoDetalhada += "\nOperações realizadas: \n";

                canonizacaoColunaEntra();

            }
            else {

                //solucaoDetalhada += "\nSolução Otima encontrada.";
                //solucao otima
                //mostrarResult();
            }
        } while(maiorCusto > 0.0);

        Log.i("Depuracao", "saiu");
    }

    /*
     * encontra maior custo reduzido positivo da linha de custos reduzidos (ultima linha do quadro)
     * retorna o indice coluna da variavel que entra na base
     *
     * parametro linha de custos reduzidos, com cuidado pra nao pegar primeira posicao que
     * é invalida e ultima posicao que é valor da funcao objetivo
     *
     */
    protected void custoReduzidoPositivo() {
        maiorCusto = 0.0;
        int i = matriz.length - 1; //linha de custos reduzidos

        //tira posicao do valor da funcao objetivo e comeca de posicao valida
        for (int j = 1; j < matriz[i].length - 1; ++j) {
            if(matriz[i][j] > 0) {
                if(maiorCusto < matriz[i][j]) {
                    colunaEntra = j;
                    maiorCusto = matriz[i][j];
                }

            }

        }//fim for

    }//fim metodo custoReduzidoPositivo

    /*
     * Divide coluna do vetor b pela coluna da variavel que entra
     * divisao é feita linha por linha
     * colunaEntra / colunaB
     * se tiver valores iguais na divisao pega a ultima
     */
    protected void variavelSai() {

        int indiceLinhaBase = 0;
        int colunaB = matriz[0].length - 1;
        double resultadoDivisao;
        double menorDivisao;
        int k = 1;
        while(matriz[k][colunaEntra] <= 0.0) ++k;

        menorDivisao = matriz[k][colunaB] / matriz[k][colunaEntra];

        for(int i = 1; i < matriz.length - 1; ++i) {

            if(matriz[i][colunaEntra] > 0.0){

                resultadoDivisao = matriz[i][colunaB] / matriz[i][colunaEntra];

                if(menorDivisao >= resultadoDivisao) {
                    menorDivisao = resultadoDivisao;
                    indiceLinhaBase = i;//linha do vetor basicas q sai
                }

            }

        }
        //pega indice da coluna de variaveis basicas, esse indice é coluna q sai da base
        colunaSai = matriz[indiceLinhaBase][0].intValue();

        //coloca indice da coluna que entra na base
        matriz[indiceLinhaBase][0] = Double.valueOf(colunaEntra);
    }//fim metodo variavelSai

    /*
     *  coluna que sai era basica entao so tem 0s e unico 1, essa posicao onde
     *  esta o valor um é a posicao do pivo
     */
    protected void encontrarPivo() {

        for (int i = 1; i < matriz.length; ++i){
            if(matriz[i][colunaSai].intValue() == 1)
                indiceLinhaPivo = i;
        }
    }//fim metodo encontrarPivo

    /*
     * Transforma variavel que entra para ela ser igual a basica que saiu
     * transforma pivo em 1 dividindo linha pelo valor atual no indice
     */
    protected void canonizacaoColunaEntra() {
        int i, j;
        double pOld = matriz[indiceLinhaPivo][colunaEntra];
        double divisor = matriz[indiceLinhaPivo][colunaEntra];

        //solucaoDetalhada += String.format("L%d = L%d/%d\n", indiceLinhaPivo, indiceLinhaPivo, divisor);

        for (i = 1; i < matriz[indiceLinhaPivo].length; ++i) {
            matriz[indiceLinhaPivo][i] /= divisor;
        }

		/*
		 * transformar demais colunas da linha do pivo em zero
		 * Usar estrategia de transformar pivo e voltar ao normal depois
		 * usar somas, multiplica linha pivo pelo inverso do valor doindice do item a ser zerado
		 * e depois de fazer operacao volta pivo ao normal
		 *
		 * zera todos valores dos indices das linha que sao diferentes da linhaPivo
		 */

        for(i = 1; i < matriz.length; ++i) {
            divisor = (-1) * matriz[i][colunaEntra];

            if(i != indiceLinhaPivo){
                for (j = 1; j < matriz[indiceLinhaPivo].length; ++j) {
                    matriz[i][j] += (divisor * matriz[indiceLinhaPivo][j]);
                }
            }

            if(i != indiceLinhaPivo)
                solucaoDetalhada += String.format("L%d = L%d + (%.1f * L%d/%.1f)\n", i, i, divisor, indiceLinhaPivo, pOld);
            else
                if(pOld != 1.0)
                    solucaoDetalhada += String.format("L%d = L%d/%.1f\n", i, i, pOld);

        }
        //mostrarMatriz();

    }//fim metodo canonizacaoColunaEntra

    public Double[][] getMatriz() {
        return matriz;
    }

    //EDITAR
    //O algoritmo deverá retornar os valores das variáveis básicas e não básicas e o valor da função objetivo.
    public String mostrarResult() {

        Log.i("Depuracao", "Entrou metodo mostrarResult");

        String result = "Valor das variáveis básicas: \n";
        for(int i = 1; i < matriz.length - 1; ++i)
            result += String.format("\tx%d = %10.1f\n", matriz[i][0].intValue(), matriz[i][matriz[0].length - 1]);

        boolean control;
        result += String.format("\nValor das variáveis não básicas: \n");
        for(int i = 1; i < matriz[0].length - 1; ++i) {
            control = true;
            for(int j = 1; j < matriz.length - 1; ++j)
                if(matriz[0][i].intValue() == matriz[j][0].intValue()) {//nao basicas é zero
                    control = false;
                }
            if(control)
                result += String.format("\tx%d = %10.1f\n", matriz[0][i].intValue(), 0.0);
        }

        result += String.format("\nValor da função objetivo:\n\t z = %10.1f\n", (-1) * matriz[matriz.length - 1][matriz[0].length - 1]);

        //Log.i("Depuracao", "Dentro da Classe Simplex\n  ->String result: \n" + result);

        return result;
    }//fim metodo mostrarResult
// *
 // Editar as funcoes para chamarem resultado

    //EDITAR
    protected void mostrarMatriz() {
        solucaoDetalhada += String.format("\n %9c", ' ');
        for(int i = 1; i < matriz[0].length - 1; ++i) {
            solucaoDetalhada += String.format("%9c%d", 'x', matriz[0][i].intValue());
        }
        solucaoDetalhada += String.format("%9c\n", 'b');

        boolean mostra = false;
        for(Double i[]: matriz) {
            for(Double j: i) {
                if(mostra)
                    solucaoDetalhada += String.format(" %9.1f", j);
            }
            if(mostra)
                solucaoDetalhada += "\n";
            mostra = true;
        }
        solucaoDetalhada += "\n";
        Log.i("Depuracao", "Chamou funcao mostrarMatriz:\n " + solucaoDetalhada);
    }//fim metodo mostrarMatriz

    public String getSolucaoDetalhada() {
        return solucaoDetalhada;
    }
// */
}//fim class
