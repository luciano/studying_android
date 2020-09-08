package dominando.android.ex18_persistencia;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    EditText editTexto;
    TextView txtTexto;
    RadioGroup rgTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // obtem instancia da SharedPreference
        SharedPreferences mSharedPreferences = getSharedPreferences("configuracoes", MODE_PRIVATE);

        // salva valores na referencia
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("nome", "Luciano Silva");
        editor.putString("curso", "Sistemas de Informação");
        editor.putInt("periodo", 6);
        editor.putInt("idade", 20);
        editor.commit();

        // recuperando os valores
        String nome = mSharedPreferences.getString("nome", null);
        String curso = mSharedPreferences.getString("curso", null);
        int periodo = mSharedPreferences.getInt("periodo", 0);
        int idade = mSharedPreferences.getInt("idade", 0);

        Log.i("LogLS", "Nome: " + nome);
        Log.i("LogLS", "Idade: " + idade);
        Log.i("LogLS", "Curso:" + curso);
        Log.i("LogLS", "Periodo: " + periodo);

//================================== Lendo e escrevendo em arquivos ================================

        editTexto = (EditText) findViewById(R.id.editTexto);
        txtTexto = (TextView) findViewById(R.id.txtTexto);
        rgTipo = (RadioGroup) findViewById(R.id.rgTipo);

        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnLer).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean ler = false;

        if (v.getId() == R.id.btnLer) {
            ler = true;
        }

        int tipo = rgTipo.getCheckedRadioButtonId();

        if (ler) {
            switch (tipo) {
                case R.id.rbInterno:
                    carregarInterno();
                    break;
                case R.id.rbExternoPriv:
                    carregarDoSdCard(true);
                    break;
                case R.id.rbExternoPublic:
                    carregarDoSdCard(false);
                    break;
            }
        } else {
            switch (tipo) {
                case R.id.rbInterno:
                    salvarInterno();
                    break;
                case R.id.rbExternoPriv:
                    salvarDoSdCard(true);
                    break;
                case R.id.rbExternoPublic:
                    salvarDoSdCard(false);
                    break;
            }
        }
    }

    private void salvar(FileOutputStream outputStream) throws IOException {
        String[] linhas = TextUtils.split(editTexto.getText().toString(), "\n");

        PrintWriter printWriter = new PrintWriter(outputStream);

        for (String s: linhas) {
            printWriter.println(s);
        }

        printWriter.flush();
        printWriter.close();
        outputStream.close();
    }

    private void caregar(FileInputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder stringBuilder = new StringBuilder();
        String linha;

        while ((linha = bufferedReader.readLine()) != null) {
            if (stringBuilder.length() != 0) stringBuilder.append('\n');

            stringBuilder.append(linha);
        }

        bufferedReader.close();
        inputStream.close();

        txtTexto.setText(stringBuilder.toString());
    }

    private void salvarInterno() {
        try {
            FileOutputStream outputStream = openFileOutput("arquivo.txt", Context.MODE_PRIVATE);
            salvar(outputStream);
        } catch (IOException e) {
            Log.e("LogLS", "Erro ao salvar arquivo: " + e);
        }
    }

    private void carregarInterno() {
        try {
            FileInputStream inputStream = openFileInput("arquivo.txt");
            caregar(inputStream);
        } catch (IOException e) {
            Log.e("LogLS", "Erro ao carregar arquivo: " + e);
        }
    }

    private void salvarDoSdCard(boolean privado) {
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File meuDir = getExternalDir(privado);

            try {
                if (!meuDir.exists()) {
                    meuDir.mkdir();
                }

                File arquivoTxt = new File(meuDir, "arquivo.txt");

                if (!arquivoTxt.exists()) {
                    arquivoTxt.createNewFile();
                }
                FileOutputStream outputStream = new FileOutputStream(arquivoTxt);
                salvar(outputStream);

            } catch (IOException e) {
                Log.e("LogLS", "Erro ao salvar arquivo: " + e);
            }
        } else {
            Log.e("LogLS", "Não foi possivel escrever no SD Card");
        }
    }

    private void carregarDoSdCard(boolean privado) {
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {

            File meuDir = getExternalDir(privado);

            if (meuDir.exists()) {
                File arquivoTxt = new File(meuDir, "arquivo.txt");

                if (arquivoTxt.exists()) {
                    try {
                        //arquivoTxt.createNewFile();

                        FileInputStream inputStream = new FileInputStream(arquivoTxt);
                        caregar(inputStream);
                    } catch (IOException e) {
                        Log.e("LogLS", "Erro ao carregar arquivo: " + e);
                    }
                }
            }
        } else {
            Log.e("LogLS", "SD Card indisponível");
        }
    }

    private File getExternalDir(boolean privado) {

        if (privado) {
            // SDCard/Android/data/pacote.da.app/files
            return getExternalFilesDir(null);
        } else {
            // SDCard/DCIM
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        }
    }

}
