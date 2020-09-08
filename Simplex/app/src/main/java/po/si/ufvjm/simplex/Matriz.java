package po.si.ufvjm.simplex;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/*
 * Vai ter outra abordagem
 *
 * Pegar matriz que usuario digitou no app
 *
 * Modificar para aceitar esse tipo de input
 *
 *
 */
public class Matriz {

    private Double[][] matriz;

    Matriz() {
        throw new IllegalArgumentException("Construtor deve ter endereço do arquivo com matrizes.");
    }

    Matriz(String endereco) {
        File file = new File(endereco);

        setMatriz(file);
    }

    public Double[][] getMatriz() {
        return matriz;
    }

    private void setMatriz(File file) {
        try {
            Scanner arquivo = new Scanner(file);

            int colunas = 0, linhas = 0;
            String linha;
            boolean control = true;

            while(control) {

                linha = arquivo.nextLine();
                Scanner l = new Scanner(linha);

                if(l.hasNextDouble()){
                    ++linhas;
                    colunas = 0;
                }
                else
                    control = false;

                while(l.hasNextDouble()) {
                    l.nextDouble();
                    ++colunas;
                }

                l.close();
            }//fim while(control)
            arquivo.close();

            matriz = new Double[linhas + 2][colunas + 2];

            Scanner arquivo1 = new Scanner(file);

            String texto = "";
            while(arquivo1.hasNextDouble())
                texto += arquivo1.nextLine() + "\n";

            Scanner in = new Scanner(texto);

            while(in.hasNextDouble()) {

                for(int i = 1; i < linhas + 1; ++i) {
                    for(int j = 1; j < colunas + 1; ++j) {
                        matriz[i][j] = Double.valueOf(in.nextDouble());
                    }
                }

                for(int i = 1; i < linhas + 1; ++i) {
                    matriz[i][colunas + 1] = Double.valueOf(in.nextDouble());
                }

                for(int i = 1; i < colunas + 1; ++i) {
                    matriz[linhas + 1][i] = Double.valueOf(in.nextDouble());
                }

                for(int i = 1; i < linhas + 1; ++i) {
                    matriz[i][0] = Double.valueOf(in.nextDouble());
                }

            }//fim while(in.hasNextDouble())

            arquivo1.close();
            in.close();
            texto = "";

            for(int i = 0; i < colunas + 1; ++i) {
                matriz[0][i] =  Double.valueOf(i);
            }
            matriz[0][colunas + 1] =  0.0;
            matriz[linhas + 1][0] = 0.0;
            matriz[linhas + 1][colunas + 1] = 0.0;

        } catch(IOException e) {
            System.out.println("Erro ao abrir o arquivo. " + e.getMessage());
        }// fim do try catch

    }//fim metodo setMatriz

    public void mostrarMatriz() {

        System.out.print("     ");
        for(int i = 1; i < matriz[0].length - 1; ++i) {
            System.out.printf("%5c%d", 'x', matriz[0][i].intValue());
        }
        System.out.printf("%5c", 'b');
        System.out.println();

        for(Double i[]: matriz) {
            for(Double j: i)
                System.out.printf("%6.1f", j);
            System.out.println();
        }
    }//fim metodo mostrarMatriz

}//fim class
