package conversa.seria.talk;

import android.app.Activity;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Salva e le os itens no arquivo
 */

public class Arquivo extends Activity {

    private String titulo = "";
    private String texto = "";

    private void salvar(FileOutputStream fileOutputStream) throws IOException{
        String[] linhas = TextUtils.split(texto, "\n");

        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        for (String linha: linhas) {
            printWriter.println(linha);
        }

        Log.i(MainActivity.CATEGORIA, "Salvo no arquivo: " + Arrays.toString(linhas));
        printWriter.flush();
        printWriter.close();
        fileOutputStream.close();
    }

    private void carregar(FileInputStream fileInputStream) throws IOException{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        StringBuilder stringBuilder = new StringBuilder();
        String linha;

        while((linha = bufferedReader.readLine()) != null) {
            if(stringBuilder.length() != 0)
                stringBuilder.append("\n");

            stringBuilder.append(linha);
        }

        texto = stringBuilder.toString();

        bufferedReader.close();
        fileInputStream.close();
    }

    public String leitura(String titulo) {
        this.titulo = titulo;

        File arquivo = getFileStreamPath(titulo);

        if (arquivo.exists()) {

            try {

                FileInputStream fileInputStream = openFileInput(titulo);
                carregar(fileInputStream);

            } catch (IOException e) {
                Log.e(MainActivity.CATEGORIA, "Erro na leitura do arquivo.");
                return "";
            }

        } else {
            Log.e(MainActivity.CATEGORIA, "Arquivo nao existe.");
            return "";
        }

        return texto;
    }

    public void escrita(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;


        //FileOutputStream fileOutputStream = new FileOutputStream(arquivo);
        try {
            FileOutputStream fileOutputStream = openFileOutput(titulo, MODE_APPEND);
            salvar(fileOutputStream);


        } catch(IOException e) {
            Log.e(MainActivity.CATEGORIA, "Erro na escrita do arquivo.");
        }

    }


    /*

    public String leitura(String titulo) {
        this.titulo = titulo;

        String state = Environment.getExternalStorageState();

        // pronto para ler e escrever ou apenas para ler
        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {

            File meuDir = getExternalFilesDir(null);

            if (meuDir.exists()) {

                File arquivo = new File(meuDir, titulo);

                if (arquivo.exists()) {

                    try {

                        FileInputStream fileInputStream = new FileInputStream(arquivo);
                        carregar(fileInputStream);

                    } catch (IOException e) {
                        Log.e(MainActivity.CATEGORIA, "Erro na leitura do arquivo.");
                        return "";
                    }

                }
            }
        }
        return texto;
    }

    public void escrita(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
        String state = Environment.getExternalStorageState();

        // pronto para ler e escrever
        if(Environment.MEDIA_MOUNTED.equals(state)) {

            File meuDir = getExternalFilesDir(null);

            try {

                if(!meuDir.exists()) {
                  meuDir.mkdir();
                }

                File arquivo = new File(meuDir, titulo);

                if(!arquivo.exists()) {
                    arquivo.createNewFile();
                }


                FileOutputStream fileOutputStream = new FileOutputStream(arquivo);
                //FileOutputStream fileOutputStream = openFileOutput(arquivo, MODE_APPEND);
                salvar(fileOutputStream);


            } catch(IOException e) {
                Log.e(MainActivity.CATEGORIA, "Erro na escrita do arquivo.");
            }

        } else {
            Log.e(MainActivity.CATEGORIA, "Nao foi possivel escrever no SD Card.");
        }

    }


     */

}
