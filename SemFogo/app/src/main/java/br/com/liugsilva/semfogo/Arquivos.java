package br.com.liugsilva.semfogo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class Arquivos {

    final public static String PERFIL = "perfil";
    final public static String EMPRESA = "empresa";
    final public static String OCORRENCIA = "ocorrencia";
    final public static String ULTIMAS_OCORRENCIAS = "ultimas_ocorrencias";

    // desnecessario, ja ta salvo nas preferencias
    public static void salvarPerfil(Context context, String nome, String email, String telefone, String endereco) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nome", nome);
            jsonObject.put("email", email);
            jsonObject.put("telefone", telefone);
            jsonObject.put("endereco", endereco);

            salvarDados(context, PERFIL + "dados.dat", jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void salvarPicture(Context context, InputStream stream) {
        FileOutputStream outputStream = null;
        try {
            File picsDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM);
            File file = new File(picsDir, "SemFogo/perfil_picture.jpg");

            if(!file.exists()) {
                Log.d("LogLS", "File nao exite.");
                boolean create = file.createNewFile();
                Debug.log("escreveImagens(): nomedia nao exite.\n Create nomedia = " + create);
                //file.mkdir();
                file.setReadable(true, false);
                file.setWritable(true, false);
            }
            file.createNewFile();

            outputStream = new FileOutputStream(file, false);

            int dados = stream.available();
            byte[] bytes = new byte[dados];
            stream.read(bytes, 0, dados);
            outputStream.write(bytes, 0, dados);

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap lerPicture(Context context) {
        FileInputStream inputStream = null;
        Bitmap bitmap = null;
        try {
            File picsDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM);

            File imageFile = new File(picsDir.getAbsolutePath() + "/SemFogo/Profile/", "perfil_picture.jpg");

            Debug.log("Lendo: " + imageFile.getAbsoluteFile());

            inputStream = new FileInputStream(imageFile.getAbsolutePath());

//            int dados = inputStream.available();
//            byte[] bytes = new byte[dados];
//            int returned = inputStream.read(bytes);

//            Debug.log("Lendo returned: " + returned);

            bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

            if (bitmap == null)
                Debug.log("Lendo: Bitmap Null");

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private static void salvarDados(Context context, String nomeArquivo, String dados) {
        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(nomeArquivo, Context.MODE_PRIVATE);
            PrintWriter printWriter = new PrintWriter(outputStream);

            printWriter.print(dados);

            printWriter.flush();
            printWriter.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject lerPerfil (Context context) {
        return lerDados(context, PERFIL + "dados.dat");
    }

    private static JSONObject lerDados(Context context, String nomeArquivo) {
        JSONObject dados = null;
        try {
            FileInputStream inputStream =  context.openFileInput(nomeArquivo);
            int num = inputStream.available();
            byte[] bytes = new byte[num];
            inputStream.read(bytes, 0, num);

            dados = new JSONObject(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dados;
    }
}
