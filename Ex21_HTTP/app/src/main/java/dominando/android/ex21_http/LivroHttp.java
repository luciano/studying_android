package dominando.android.ex21_http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luciano on 22/10/2015.
 */
public class LivroHttp {

    public static final String LIVROS_URL_JSON =
            "https://raw.githubusercontent.com/nglauber/dominando_android/master/livros_novatec.json";

    private static HttpURLConnection connectar(String urlArquivo) throws IOException {
        final int SEGUNDOS = 1000;
        URL url = new URL(urlArquivo);

        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setReadTimeout(10 * SEGUNDOS);
        conexao.setConnectTimeout(15 * SEGUNDOS);
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true);
        conexao.setDoOutput(false);
        conexao.connect();

        return conexao;
    }

    public static boolean temConexao(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    public static List<Livro> carregarLivrosJson() {
        try {
            HttpURLConnection conexao = connectar(LIVROS_URL_JSON);

            int resposta = conexao.getResponseCode();

            if (resposta == HttpURLConnection.HTTP_OK) {
                InputStream is = conexao.getInputStream();
                JSONObject jsonObject = new JSONObject(bytesParaString(is));
                return lerJsonLivros(jsonObject);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Livro> lerJsonLivros(JSONObject jsonObject) throws JSONException {

        List<Livro> listaDeLivros = new ArrayList<>();

        String categoriaAtual;

        JSONArray jsonNovatec = jsonObject.getJSONArray("novatec");
        for (int i = 0; i < jsonNovatec.length(); ++i) {
            JSONObject jsonCategoria = jsonNovatec.getJSONObject(i);
            categoriaAtual = jsonCategoria.getString("categoria");

            JSONArray jsonLivros = jsonCategoria.getJSONArray("livros");
            for (int j = 0; j < jsonLivros.length(); ++j) {
                JSONObject jsonLivro = jsonLivros.getJSONObject(j);

                Livro livro = new Livro(
                        jsonLivro.getString("titulo"),
                        categoriaAtual,
                        jsonLivro.getString("autor"),
                        jsonLivro.getInt("ano"),
                        jsonLivro.getInt("paginas"),
                        jsonLivro.getString("capa")
                );
                listaDeLivros.add(livro);
            }
        }

        return listaDeLivros;
    }

    private static String bytesParaString(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];

        //O bufferzao vai armazenar todos os butes lidos
        ByteArrayOutputStream bufferzao = new ByteArrayOutputStream();

        //precisa saber quantos bytes foram lidos
        int bytesLidos;

        // ler de 1KB por vez
        while ((bytesLidos = is.read(buffer)) != -1) {
            bufferzao.write(buffer, 0, bytesLidos);
        }

        return new String(bufferzao.toByteArray(), "UTF-8");
    }
}
