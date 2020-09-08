package br.com.liugsilva.semfogo;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Luciano on 05/02/2016.
 *
 * Vai ter um SharedPreference para os nomes dos arquivos a serem enviados
 * E o Shared com esses nomes preenchidos para saber quantos sao para enviar
 */
public class Conexao {

    public static final String URL_REGISTRO = "http://androidfogo.esy.es/registro/registro.php";
    public static final String URL_EMPRESA = "http://androidfogo.esy.es/empresa/empresa.php";
    public static final String URL_USUARIO = "http://androidfogo.esy.es/usuarios/usuarios.php";

    public static final String OCORRENCIA = "ocorrencia";
    public static final String CADASTRO = "cadastro";
    public static final String EMPRESA = "empresa";
    public static final String PERSISTENCIA = "persistencia";
    public static final String DADOS = "dados";
    // fazer o seguinte
    // se tem conexao envia os dados
    // caso contrario salva eles no SharedPreference e assim que
    // conectar a internet ele envia os dados e limpa o shared
    private static String resposta;
    //private static int counter = 0;

    public static boolean temConexao(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Debug.log("Conexao.temConexao(): rede online");
            return true;
        } else {
            Debug.log("Conexao.temConexao(): rede offline");
            return false;
        }
    }

    public static String enviarDados(final Context context, final String url, final Map<String, String> map) {
        resposta = "";

        if (temConexao(context)) {
            // envia via Post
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            resposta = resposta(response);
                            Debug.log("Conexão: Sucesso: Response = " + resposta);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Debug.log("Conexão: Error = " + error);
                            // se deu erro tambem salva dados na persistencia
                            // ta salvando redundancia caso nao consiga enviar os dados ele salva varias vezes
                            Conexao.salvarPersistencia(context, url, map);
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        } else {
            Debug.log("Conexão: sem internet. Salvar dados na Persistencia");
            // salva dados na persistencia se tiver sem internet
            Conexao.salvarPersistencia(context, url, map);
        }
        return resposta;
    }

    private static String resposta(String response) {
        return response;
    }

    public static void salvarPersistencia(Context context, String url, final Map<String, String> map) {
        int counter = 0;
        // salvar url em outro SP para ele indicar quanta informacao tem...
        SharedPreferences spPersistencias = context.getSharedPreferences(PERSISTENCIA, Context.MODE_PRIVATE);
        Debug.log("Conexao.salvarPersistencia: Map Persistencia = " + spPersistencias.getAll().keySet().toString());

        String nome = DADOS + counter;
        while (spPersistencias.contains(nome)) {
            ++counter;
            nome = DADOS + counter;
        }
        Debug.log("Conexao.salvarPersistencia: Nome = " + nome);

        SharedPreferences.Editor editorPersistencia = spPersistencias.edit();
        editorPersistencia.putString(nome, url);
        editorPersistencia.apply();
        editorPersistencia.commit();
        Debug.log("Conexao.salvarPersistencia: Nome = " + nome + " - url: " + url);
        Debug.log("Conexao.salvarPersistencia: Map Persistencia = " + spPersistencias.getAll().keySet().toString());

        // ================ DADOS =========================
        SharedPreferences sharedPreferences = context.getSharedPreferences(nome, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> keys = map.keySet();
        for (String key: keys) {
            editor.putString(key, map.get(key));
        }
        editor.apply();
        editor.commit();
        Debug.log("Conexao.salvarPersistencia: Map " + nome + " = " + sharedPreferences.getAll().keySet().toString());
    }

    public static void deletarPersistencia(Context context) {
        int counter = 0;
        SharedPreferences spPersistencias = context.getSharedPreferences(PERSISTENCIA, Context.MODE_PRIVATE);

        String nome = DADOS + counter;
        while (spPersistencias.contains(nome)) {

            SharedPreferences sharedPreferences = context.getSharedPreferences(nome, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear().clear();

            ++counter;
            nome = DADOS + counter;
        }

        SharedPreferences.Editor editor = spPersistencias.edit();
        editor.clear().apply();
        editor.commit();
    }

    //============================================= IMPLEMENTAR ========================================

    public static String enviarDadosServidor() {
        return "response";
    }

    public static String enviarCadastro() {
        return "response";
    }

    public static String enviarRegistro() {
        return "response";
    }

    public static String enviarEmpresa(Context context, String url, String nome, String email, String telefone, String cidade, String mensagem) {

        Map<String, String> mapEnvio = new HashMap<>();
        mapEnvio.put("nome", (nome == null ? "" : nome) );
        mapEnvio.put("email", (email == null ? "" : email));
        mapEnvio.put("telefone", (telefone == null ? "" : telefone));
        mapEnvio.put("cidade", (cidade == null ? "" : cidade));
        mapEnvio.put("mensagem", (mensagem == null ? "" : mensagem).replaceAll("\\n", "<br>"));

        return enviarDados(context, url, mapEnvio);
    }

    public static String enviarImagem(String titulo, String path) {
        // diretorio das imagens é predefinido
        // latitude-longitude/data-horario/fotonome.jpg

        String caminhoDoArquivoNoDispositivo = path;

        final String urlDoServidor = "http://192.168.1.106/semfogo.com.br/registro/imagem.php";
        final String fimDeLinha = "\r\n";
        final String menosMenos = "--";
        final String delimitador = "*****";

        FileInputStream fileInputStream = null;
        DataOutputStream outputStream = null;
        HttpURLConnection conexao = null;

        try {
            Debug.log("Conexao.enviarFoto(): entrou try;");
            URL url = new URL(urlDoServidor);
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setDoInput(true);
            conexao.setDoOutput(true);
            conexao.setUseCaches(false);
            conexao.setRequestMethod("POST");

            // Adicionando cabeçalhos
            conexao.setRequestProperty("Connection", "Keep-Alive");
            conexao.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + delimitador);
            conexao.connect();

            outputStream = new DataOutputStream(conexao.getOutputStream());

            // Adicionando campo titulo
            outputStream.writeBytes(menosMenos + delimitador + fimDeLinha);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"titulo\"");
            outputStream.writeBytes(fimDeLinha);
            outputStream.writeBytes(fimDeLinha);
            outputStream.writeBytes(titulo);
            outputStream.writeBytes(fimDeLinha);

            // Adicionando arquivo
            outputStream.writeBytes(menosMenos + delimitador + fimDeLinha);
            outputStream.writeBytes("Content-Disposition: form-data; "+
                    "name=\"arquivo\";filename=\""+
                    caminhoDoArquivoNoDispositivo + "\"" + fimDeLinha);
            outputStream.writeBytes(fimDeLinha);

            // Stream para ler o arquivo
            fileInputStream = new FileInputStream(
                    new File(caminhoDoArquivoNoDispositivo));

            byte[] buffer;
            int bytesRead, bytesAvailable, bufferSize;
            int maxBufferSize = 1024 * 1024; // 1MB

            // Preparando para escrever arquivo
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // Lendo arquivo e escrevendo na conexão
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            outputStream.writeBytes(fimDeLinha);
            outputStream.writeBytes(menosMenos + delimitador + menosMenos + fimDeLinha);

            int serverResponseCode = conexao.getResponseCode();
            String serverResponseMessage = conexao.getResponseMessage();

            if (serverResponseCode != HttpURLConnection.HTTP_OK){
                Debug.log("Conexao.enviarFoto(): " + serverResponseCode + " = " + serverResponseMessage);
//                throw new RuntimeException(
//                        "Error "+ serverResponseCode +": "+ serverResponseMessage);
            }

        } catch (IOException e) {
            Debug.log("Conexao.enviarFoto(): Erro Imagem: " + e.toString());
        } finally {
            try {
                if (fileInputStream != null) fileInputStream.close();
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                Debug.log("Erro Imagem: " + e.toString());
            }
        }

        return "response";
    }

    // ok receber e ler registros
    /**
     * Se pesquisar for true, pesquisa no Servidor
     */
    public static ArrayList<Registro> receberRegistros(Context context, boolean pesquisar) {
        ArrayList<Registro> registros;

        if (!Conexao.temConexao(context) || !pesquisar) {
            // se não tem internet ou não é pra pesquisar, busca dados internamente
            registros = SistemaArquivos.lerRegistrosInterno(context);
            return registros;
        }

        registros = lerRegistroService(context, "http://androidfogo.esy.es/registro/registro-dados.txt");

        if (registros.isEmpty()) {
            registros = SistemaArquivos.lerRegistrosInterno(context);
        } else {
            SistemaArquivos.gravarRegistrosInterno(context, registros);
        }

        return registros;
    }

    public static ArrayList<Registro> lerRegistroService(Context context, String urlDados) {

        ArrayList<Registro> armazem = new ArrayList<>();
        int numeroLinhas = 0;

        URL url = null;
        try {
            url = new URL(urlDados);
            URLConnection urlConnection = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            armazem = SistemaArquivos.ler(reader, numeroLinhas);

            Debug.log("SistemaArquivos.lerDadosService(): Leu: " + urlDados);
        } catch (IOException e) {
            Debug.log("SistemaArquivos.lerDadosService(): Erro em ler: " + urlDados + ".\nErro: " + e.toString());
            return new ArrayList<>();
        }

        Debug.log("SistemaArquivos.ler(): leu " + numeroLinhas + " dados.");
        return armazem;
    }

}