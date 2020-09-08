package com.luciano.silva.jwnotebook;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Luciano on 15/09/2015.
 */
public class TextoBiblico {

    private static List<String> mLivros = new ArrayList<>();;
    private static String path;
    private static String textoTitulo;

    private static void carregarLivros() {

        mLivros.add("Gênesis");
        mLivros.add("Êxodo");
        mLivros.add("Levítico");
        mLivros.add("Números");
        mLivros.add("Deuteronômio");
        mLivros.add("Josué");
        mLivros.add("Juízes");
        mLivros.add("Rute");
        mLivros.add("1 Samuel");
        mLivros.add("2 Samuel");
        mLivros.add("1 Reis");
        mLivros.add("2 Reis");
        mLivros.add("1 Crônicas");
        mLivros.add("2 Crônicas");
        mLivros.add("Esdras");
        mLivros.add("Neemias");
        mLivros.add("Ester");
        mLivros.add("Jó");
        mLivros.add("Salmos");
        mLivros.add("Provérbios");
        mLivros.add("Eclesiastes");
        mLivros.add("Cântico de Salomão");
        mLivros.add("Isaías");
        mLivros.add("Jeremias");
        mLivros.add("Lamentações");
        mLivros.add("Ezequiel");
        mLivros.add("Daniel");
        mLivros.add("Oseias");
        mLivros.add("Joel");
        mLivros.add("Amós");
        mLivros.add("Obadias");
        mLivros.add("Jonas");
        mLivros.add("Miqueias");
        mLivros.add("Naum");
        mLivros.add("Habacuque");
        mLivros.add("Sofonias");
        mLivros.add("Ageu");
        mLivros.add("Zacarias");
        mLivros.add("Malaquias");
        mLivros.add("Mateus");
        mLivros.add("Marcos");
        mLivros.add("Lucas");
        mLivros.add("João");
        mLivros.add("Atos");
        mLivros.add("Romanos");
        mLivros.add("1 Coríntios");
        mLivros.add("2 Coríntios");
        mLivros.add("Gálatas");
        mLivros.add("Efésios");
        mLivros.add("Filipenses");
        mLivros.add("Colossenses");
        mLivros.add("1 Tessalonicenses");
        mLivros.add("2 Tessalonicenses");
        mLivros.add("1 Timóteo");
        mLivros.add("2 Timóteo");
        mLivros.add("Tito");
        mLivros.add("Filêmon");
        mLivros.add("Hebreus");
        mLivros.add("Tiago");
        mLivros.add("1 Pedro");
        mLivros.add("2 Pedro");
        mLivros.add("1 João");
        mLivros.add("2 João");
        mLivros.add("3 João");
        mLivros.add("Judas");
        mLivros.add("Apocalipse");
    }

    public static String obterLivro(String versiculo) {

        carregarLivros();

        // v66_21_4
        String livro = versiculo.substring(1, versiculo.indexOf("_"));

        return mLivros.get(Integer.parseInt(livro) - 1) + versiculo.replaceAll("(v\\d{1,2}_)(\\d{1,3})_(\\d{1,3})", " $2:$3");
    }

    public static String encontrarLivro(String versiculo) {

        carregarLivros();

        List<String> auxLivros = new ArrayList<String>(mLivros);

        for (int i = mLivros.size() - 1; i >= 0; --i) {

            if (!limpaString(auxLivros.get(i).toLowerCase()).startsWith(limpaString(versiculo.toLowerCase()))) {
                auxLivros.remove(i);
            }

        }
        if (auxLivros.size() >= 1)
            return auxLivros.get(0);
        else
            return null;
    }

    public static String limpaString(String palavra) {

        palavra = palavra.trim().replaceAll("\\s{2,}", " ");

        palavra = palavra.replaceAll("(\\d)([a-z])", "$1 $2");

        if (palavra.equals("jo")) {
            return "joao";
        }

        if (palavra.equals("Jó") || palavra.equals("jó")) {
            return "jó";
        }

        if (palavra.toLowerCase().startsWith("re")) {
            return "ap";
        }

        return Util.removeAcento(palavra);

    }

    public static String[] padraoTexto(String palavra) {

        // mais interessante limpar aqui os espacos sobrando e faltando
        // pra ficar  Numero? Livro Capitulo:Versiculo

        // 1 pe. 1:1 com ponto vem nas publicacoes com ponto
        // 1 pe 1: 1 com espaco
        // 1 pe 1:1 sem ponto

        // ([[1-3][\\s]?]?[a-zA-Z]{2,}[.]?)(\\d{1,3}):(\\d{1,3})

        //String re = "([1-3][\\s]?)?([a-z]{2,}[.]?)(\\d{1,3}):(\\d{1,3})";
        //palavra= "1 cor. 5:3";

        // tira ponto
        palavra = palavra.replace('.', ' ');

        // tira mais espacos
        palavra = palavra.trim().replaceAll("\\s{2,}", " ");


        //palavra = palavra.replaceAll(re, "$1#$2#$3");

        // 1pe -> 1 pe
        palavra = palavra.replaceAll("(\\d)([a-zA-Z])", "$1 $2");

        System.out.println(palavra);
        String re = "(^[1-3][\\s]?)?([a-zó]{2,})\\s(\\d{1,3})[:](\\d{1,3})";

        String nova = palavra.replaceAll(re, "$1$2#$3_$4");
        System.out.println("Palavra: " + nova);

        String[] words = nova.split("#");

        Log.i("LogLS", "words: " + Arrays.toString(words));

        return words;
    }

    public static String texto(String busca) {

        Log.i("LogLS", "busca: " + busca);

        String[] s = padraoTexto(busca);

        Log.i("LogLS", "s: " + Arrays.toString(s));

        // ERRO DE VOLTAR nullsal ??? no java funciona mas como nao tem o $1 ele coloca null o.O
        s[0] = s[0].replaceAll("null",""); // temporario

        String k = encontrarLivro(s[0]);

        Log.i("LogLS", "k: " + k);

        textoTitulo = k + " " + s[1].replace('_', ':');
        path = "v"+ (mLivros.indexOf(k) + 1) + "_" + s[1];

        Log.i("LogLS", "textoTitulo: " + textoTitulo);
        Log.i("LogLS", "path: " + path);

        return textoTitulo;
    }

    public static String pathTexto() {
        //if (textoTitulo != null)
            return path;

        //return null;
    }
}
