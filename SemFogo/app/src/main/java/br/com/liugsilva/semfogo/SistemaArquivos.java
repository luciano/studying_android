package br.com.liugsilva.semfogo;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SistemaArquivos {

    public final static String DIRECTORIO_REGISTROS = "/Registros/";
    public final static String DB_FILE = "registros.txt";

    public final static String DELIMITADOR = ";";
    public final static String CARACTER_CAMPO_VAZIO = "#";

    //private int KB = 1024;
  //  private int MB = KB * KB;
//
 // Vai pegar as informações mineiradas e salvar no arquivo temporario

//    public static void escreverDados(Context context, Registro registro) {
//    // funcionando bem!
//        // File diretorio = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS).getAbsolutePath() + DIRECTORY_APP);
//        File dirExterno = context.getExternalFilesDir(null);
//        File diretorio = new File(dirExterno.getAbsolutePath() + DIRECTORIO_REGISTROS);
//        if (!diretorio.exists()) {
//            boolean mkdir = diretorio.mkdir();
//            Debug.log("SistemaArquivos.escreverDados(): mkdirs: " + mkdir);
//        }
//        File destino = new File(diretorio, DB_FILE);
//        try {
//            boolean create = destino.createNewFile();
//            Debug.log("SistemaArquivos.escreverDados(): create: " + create);
//        } catch (IOException e) {
//            Debug.log("SistemaArquivos.escreverDados(): " + e.toString());
//        }
//
//        // se algum campo for null coloca espaco ou alguma coisa pra ele escrever no arquivo
//        // caso contrario pode da erro quando recuperar
//
//        StringBuilder dados = new StringBuilder();
//
//        // dados para arquivar separados por delimitadores levando em conta campos vazios
//        // sao 11 campos e todos separados por ; e caso forem vazios ou nulos possuem #
//        dados.append((registro.getLatitude() != null && !registro.getLatitude().isEmpty()) ? registro.getLatitude() : CARACTER_CAMPO_VAZIO);
//        dados.append(DELIMITADOR);
//        dados.append((registro.getLongitude() != null && !registro.getLongitude().isEmpty()) ? registro.getLongitude() : CARACTER_CAMPO_VAZIO);
//        dados.append(DELIMITADOR);
//        dados.append((registro.getLocalizacao() != null && !registro.getLocalizacao().isEmpty()) ? registro.getLocalizacao() : CARACTER_CAMPO_VAZIO);
//        dados.append(DELIMITADOR);
//        dados.append((registro.getHorario() != null && !registro.getHorario().isEmpty()) ? registro.getHorario() : CARACTER_CAMPO_VAZIO);
//        dados.append(DELIMITADOR);
//        dados.append((registro.getData() != null && !registro.getData().isEmpty()) ? registro.getData() : CARACTER_CAMPO_VAZIO);
//        dados.append(DELIMITADOR);
//        dados.append((registro.getInformacao() != null && !registro.getInformacao().isEmpty()) ? registro.getInformacao() : CARACTER_CAMPO_VAZIO);
//        dados.append(DELIMITADOR);
//        dados.append((registro.getLinksImagem() != null && !registro.getLinksImagem().isEmpty()) ? registro.getLinksImagem() : CARACTER_CAMPO_VAZIO);
//        dados.append(DELIMITADOR);
//
//        escrever(destino, dados.toString());
//    }

    // **
//     * Le o arquivo temporario de SMS ainda não confirmadas para serem salvas, e retorna um array
//     * com todas as mensagens temporarias ainda não salvas definitivamente no Banco de Dados
//     *
//     * @return ArrayList com as informações mineradas dos SMS pendentes.
//     * /
//    public static ArrayList<Registro> lerDados(Context context) {
//        // pode dar erro igual o escrever de nao encontrar o diretorio
//        // testar isso
//        File dirExterno = context.getExternalFilesDir(null);
//        File diretorio = new File(dirExterno.getAbsolutePath() + DIRECTORIO_REGISTROS);
//        File destino = new File(diretorio, DB_FILE);
//
//        Integer mensagensPendentes = 0;
//        ArrayList<Registro> dados = ler(destino, mensagensPendentes);
//
//        Debug.log("SistemaArquivos.lerDados(): leu " + mensagensPendentes + " mensagens pendentes.");
//        Debug.log("SistemaArquivos.lerDados(): leu dados = " + dados);
//        return dados;
//    }

//    public static ArrayList<Registro> lerDadosService(Context context, String urlDados) {
//
//        ArrayList<Registro> armazem = new ArrayList<>();
//        int numeroLinhas = 0;
//
//        URL url = null;
//        try {
//            url = new URL(urlDados);
//            URLConnection urlConnection = url.openConnection();
//
//            // fazer outra funcao... SEM DUPLICAR CODIGO
//            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
//            //BufferedReader reader = new BufferedReader(new FileReader(destino));
//            String linhaDados;
//
//
//            while ((linhaDados = reader.readLine()) != null) {
//                if (!linhaDados.isEmpty()) {
//                    // faz retirada dos delimitadores
//                    ++numeroLinhas;
//                    String[] strings = linhaDados.split(DELIMITADOR);
//
//                    // salva cada um no arquivo certo
//                    // pode dar erros se tiver faltando alguma informacao
//                    // 11 é o numero de itens do objeto InformacaoSMS
//                    int tamanho = strings.length;
//                    Debug.log("SistemaArquivos.lerDadosService(): string.length: " + tamanho);
//
//                    for (int i = 0; i < tamanho; ++i) {
//                        strings[i] = strings[i].replace(CARACTER_CAMPO_VAZIO, "");
//                    }
//
//                    if (tamanho == 7) {
//                        armazem.add(new Registro(strings[0],
//                                strings[1], strings[2], strings[3], strings[4], strings[5].replaceAll("<br>", "\n"),
//                                new ArrayList<String>(Arrays.asList(strings[6]))));
//                        Debug.log("SistemaArquivos.lerDadosService(): criou objeto Registro.");
//                        // escrever
//                        escreverDados(context, armazem.get(armazem.size() - 1));
//                    } else if (tamanho == 6) {
//                        armazem.add(new Registro(strings[0],
//                                strings[1], strings[2], strings[3], strings[4], strings[5].replaceAll("<br>", "\n"),
//                                new ArrayList<String>()));
//                        Debug.log("SistemaArquivos.lerDadosService(): criou objeto Registro.");
//                        // escrever
//                        escreverDados(context, armazem.get( armazem.size() - 1 ));
//                    }
//                }
//            }
//
//            Debug.log("SistemaArquivos.lerDadosService(): Leu: " + urlDados);
//        } catch (IOException e) {
//            Debug.log("SistemaArquivos.lerDadosService(): Erro em escrever: " + urlDados + ".\nErro: " + e.toString());
//            return new ArrayList<>();
//        }
//
//        Debug.log("SistemaArquivos.ler(): leu " + numeroLinhas + " dados.");
//        return armazem;
//    }

    // funcionando bem!
    public static void escrever(File destino, String dados) {

        try {
            boolean create = destino.createNewFile();

            Debug.log("SistemaArquivos.escrever(): create: " + create);
            Debug.log("SistemaArquivos.escrever(): path: " + destino.getAbsolutePath());
            Debug.log("SistemaArquivos.escrever(): data: " + dados);

            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destino, true), "UTF-8"));
                writer.write(dados);
                writer.newLine();
                writer.flush();
                writer.close();

                Debug.log("SistemaArquivos.escrever(): Escreveu: " + destino.getAbsolutePath());
            } catch (IOException e) {
                Debug.log("SistemaArquivos.escrever(): Erro em escrever: " + destino.getAbsolutePath() + ".\nErro: " + e.toString());
            }

        } catch (IOException e) {
            Debug.log("SistemaArquivos.escrever(): Arquivo não criado ou já existente no diretorio: " + destino.getAbsolutePath() + ".\nErro: " + e.toString());
        }
    }

    public static ArrayList<Registro> ler(BufferedReader reader, Integer numeroLinhas) {

        ArrayList<Registro> armazem = new ArrayList<>();

        try {
            String linhaDados;

            while ((linhaDados = reader.readLine()) != null) {
                if (!linhaDados.isEmpty()) {

                    ++numeroLinhas;
                    String[] strings = linhaDados.split(DELIMITADOR);

                    int tamanho = strings.length;
                    Debug.log("SistemaArquivos.ler(): string.length: " + tamanho);

                    String[] dados = new String[6];
                    List<String> linkImagem = new ArrayList<>();

                    for (int i = 0; i < tamanho; ++i) {
                        strings[i] = strings[i].replace(CARACTER_CAMPO_VAZIO, "");

                        if (i < 6) {
                            dados[i] = strings[i];
                        } else {
                            String links = strings[i];
                            if (links.contains(",")) {
                                // possui links
                                linkImagem = new ArrayList<>(Arrays.asList(links.split(",")));
                            } else {
                                linkImagem.add(links);
                            }
                        }
                    }

                    for (int i = 0; i < dados.length; ++i) {
                        if (dados[i] == null) {
                            dados[i] = "";
                        }
                    }

                    Registro registro = new Registro(dados[0], dados[1], dados[2],
                                                     dados[3], dados[4], dados[5].replaceAll("<br>", "\n"),
                                                     linkImagem);
                    armazem.add(registro);
                    Debug.log("SistemaArquivos.ler(): criou objeto Registro.");
                }
            }

        } catch (IOException e) {
            Debug.log("SistemaArquivos.ler(): Erro em ler.\nErro: " + e.toString());
            return new ArrayList<>();
        }

        Debug.log("SistemaArquivos.ler(): leu " + numeroLinhas + " dados.");
        return armazem;
    }

    public static void gravarRegistrosInterno(Context context, ArrayList<Registro> registros) {
        excluirRegistrosInterno(context);

        File dirExterno = context.getExternalFilesDir(null);
        File diretorio = new File(dirExterno.getAbsolutePath() + DIRECTORIO_REGISTROS);
        if (!diretorio.exists()) {
            boolean mkdir = diretorio.mkdir();
            Debug.log("SistemaArquivos.gravarRegistrosInterno(): mkdirs: " + mkdir);
        }
        File destino = new File(diretorio, DB_FILE);
        try {
            boolean create = destino.createNewFile();
            Debug.log("SistemaArquivos.gravarRegistrosInterno(): create: " + create);
        } catch (IOException e) {
            Debug.log("SistemaArquivos.gravarRegistrosInterno(): " + e.toString());
        }

        for (Registro registro : registros) {
            StringBuilder dados = new StringBuilder();

            dados.append((registro.getLatitude() != null && !registro.getLatitude().isEmpty()) ? registro.getLatitude() : CARACTER_CAMPO_VAZIO);
            dados.append(DELIMITADOR);
            dados.append((registro.getLongitude() != null && !registro.getLongitude().isEmpty()) ? registro.getLongitude() : CARACTER_CAMPO_VAZIO);
            dados.append(DELIMITADOR);
            dados.append((registro.getLocalizacao() != null && !registro.getLocalizacao().isEmpty()) ? registro.getLocalizacao() : CARACTER_CAMPO_VAZIO);
            dados.append(DELIMITADOR);
            dados.append((registro.getHorario() != null && !registro.getHorario().isEmpty()) ? registro.getHorario() : CARACTER_CAMPO_VAZIO);
            dados.append(DELIMITADOR);
            dados.append((registro.getData() != null && !registro.getData().isEmpty()) ? registro.getData() : CARACTER_CAMPO_VAZIO);
            dados.append(DELIMITADOR);
            dados.append(((registro.getInformacao() != null && !registro.getInformacao().isEmpty()) ? registro.getInformacao() : CARACTER_CAMPO_VAZIO).replaceAll("\\n", "<br>"));
            dados.append(DELIMITADOR);
            dados.append((registro.getLinksImagem() != null && !registro.getLinksImagem().isEmpty()) ? registro.getLinksImagem() : CARACTER_CAMPO_VAZIO);
            dados.append(DELIMITADOR);

            escrever(destino, dados.toString());
        }
    }

    public static void excluirRegistrosInterno(Context context) {
        File file = pathRegistros(context);
        boolean delete = file.delete();
        Debug.log("SistemaArquivos.excluirRegistrosInterno(): delete = " + delete);
    }

    public static ArrayList<Registro> lerRegistrosInterno(Context context) {
        File file = pathRegistros(context);

        Integer dadosLidos = 0;
        ArrayList<Registro> registros;

        Debug.log("SistemaArquivos.lerRegistrosInterno(): path: " + file.getAbsolutePath());

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            registros = ler(reader, dadosLidos);
            Debug.log("SistemaArquivos.lerRegistrosInterno(): Leu: " + file.getAbsolutePath());
        } catch (IOException e) {
            Debug.log("SistemaArquivos.lerRegistrosInterno(): Erro em escrever: " + file.getAbsolutePath() + ".\nErro: " + e.toString());
            registros =  new ArrayList<>();
        }

        Debug.log("SistemaArquivos.lerRegistrosInterno(): leu " + dadosLidos + " dados.");
        Debug.log("SistemaArquivos.lerRegistrosInterno(): leu dados = " + registros);

        return registros;
    }

    public static File pathRegistros(Context context) {
        File dirExterno = context.getExternalFilesDir(null);
        String pathDirExterno = dirExterno.getAbsolutePath();
        File diretorio = new File(pathDirExterno + DIRECTORIO_REGISTROS);
        return new File(diretorio, DB_FILE);
    }

}
