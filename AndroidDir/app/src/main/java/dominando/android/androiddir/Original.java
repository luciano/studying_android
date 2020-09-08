package dominando.android.androiddir;
/*
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Listar arquivos, tendo posse dos caminhos deles
 * abrir o arquivo e escreve-lo em um novo arquivo
 * no cartao de memoria e nomear com mesmo nome.
 *
 * Salva todos os arquivos tipo foto [.jpg, .jpeg, .png]
 * Salva todos os arquivos tipo audio [.mp3] maiores que 1MB
 * Videos nao mexer por enquanto.
 * Restante delete.
 *
 * Tipo .aac é arquivo de gravaçãod e audio no whatsApp
 * Tipo .jpg imagens
 *
 * /
public class Original extends AppCompatActivity {
//public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LogLS";
    private Context context = this;
    private String texto = "";
    private LinearLayout layout;

    File novo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (LinearLayout) findViewById(R.id.layout);


        // chama SD

        String state = Environment.getExternalStorageState();

        // SD card montado e pronto para leitura e escrita
        if(Environment.MEDIA_MOUNTED.equals(state)) {

            /**
             * Nem sempre o metodo
             * Environment.getExternalStorageDirectory().getAbsolutePath()
             * retorna o local correto onde esta o SD card.
             * Alguns fabricantes mudam o local original do cartao de memoria
             *
             * no Samsung o cartao esta em /storange/extSdCard
             *
             *  no emulador Nexus S /storange/sdcard
             *
             * /
            //File[] f = Environment.getExternalStorageDirectory().listFiles();
            //for (File l : f) {
            //  Log.i(TAG, "->" + l.getAbsolutePath());
            //}
            Log.i(TAG, Environment.getExternalStorageDirectory().getAbsolutePath());

//            File sd = Environment.getExternalStorageDirectory();
            //          Log.i(TAG, "SD: " + sd.getAbsolutePath());
            //        sd = Environment.getDataDirectory();
            //      Log.i(TAG, "SD dataDirectory: " + sd.getAbsolutePath());
            //    sd = Environment.getRootDirectory();
            //  Log.i(TAG, "SD rootDirectory: " + sd.getAbsolutePath());
            //sd = Environment.getExternalStoragePublicDirectory();
            //Log.i(TAG, "SD: " + sd.getAbsolutePath());

        } else {
            Log.e(TAG, "Erro: SD Card não montado.");
        }

        // avisa alteraoes
        //Intent.ACTION_MEDIA_CHECKING;
        //Intent.ACTION_MEDIA_SCANNER_STARTED;




        novo = new File("/storage/emulated/0/Novo");

        if (novo.mkdir() || novo.isDirectory()) {
            novo.setReadable(true);
            novo.setWritable(true);
            Log.i("LogLS", "Diretorio criado.");

            File f = new File(novo + "/testeCriacao.txt");
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        /*
        ScrollView sv = new ScrollView(this);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView text = new TextView(this);
        text.setText("Diretorios:\n");
        layout.addView(text);
        * /

        // /storage/extSdCard/
        // /storage/emulated/0/
        final File dir = new File("/storage/emulated/0/WhatsApp/");

        /*
        TextView text1 = new TextView(this);
        text1.setText("\"/storage/emulated/0/WhatsApp\"\n");
        layout.addView(text1);
        * /

        new Thread() {
            public void run() {
                listar(dir);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // funciona aqui so instanciar uma textView
                        // TextView text = new TextView(context);
                        // mas vou inflar por padrao dos outros
                        LayoutInflater inflater = LayoutInflater.from(context);
                        TextView text = (TextView) inflater.inflate(R.layout.activity_view, layout, false);
                        text.setText("Remoção de arquivos concluida.");
                        layout.addView(text);
                    }
                });

            }
        }.start();

    }

    private void listar(File file) {
        File[] diretorios = file.listFiles();

        for (final File diretorio : diretorios) {

            if(diretorio.isDirectory()) {
                /*
                Log.i("LogLS", "Dir -> " + diretorio.getAbsolutePath() + "\n");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LayoutInflater inflater = LayoutInflater.from(context);
                        TextView text = (TextView) inflater.inflate(R.layout.activity_view, layout, false);
                        text.setText("Dir -> " + diretorio.getAbsolutePath() + "\n");
                        layout.addView(text);
                    }
                });
                * /
                listar(diretorio);
            } else if(diretorio.isFile()) {
                /*
                Log.i("LogLS", "Arq: " + diretorio.getAbsolutePath() + "\n");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LayoutInflater inflater = LayoutInflater.from(context);
                        TextView text = (TextView) inflater.inflate(R.layout.activity_view, layout, false);
                        text.setText("Arq: " + diretorio.getAbsolutePath() + "\n");
                        layout.addView(text);
                    }
                });
                * /
                mover(diretorio, new File(novo.getAbsolutePath() + "/" + diretorio.getName()));
            }
        }

    }

    private void mover(File origem, File destino) {

        if( !destino.exists() ) {
            try {
                destino.createNewFile();

                try {
                    FileInputStream in = new FileInputStream(origem);
                    FileOutputStream out = new FileOutputStream(destino);

                    // alocar so quantia necessaria
                    //origem.length();
                    byte[] bytes = new byte[1024];

                    while(in.read(bytes) > -1) {
                        out.write(bytes);
                    }

                    Log.i("LogLS", "Moveu: " + origem.getName());
                    out.flush();
                    in.close();
                    out.close();

                    if(origem.delete()) {
                        Log.e("LogLS", "Deletou: " + origem.getName());
                    } else {
                        Log.e("LogLS", "Erro em deletar arquivos: " + origem.getName());
                    }

                } catch (IOException e) {
                    Log.e("LogLS", "Erro em mover arquivos: " + origem.getName());
                }

            } catch (IOException e) {
                Log.e("LogLS", "Arquivo já existente no diretorio: " + origem.getName());
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

*/

// versao 2

/*


package dominando.android.androiddir;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Listar arquivos, tendo posse dos caminhos deles
 * abrir o arquivo e escreve-lo em um novo arquivo
 * no cartao de memoria e nomear com mesmo nome.
 *
 * Salva todos os arquivos tipo foto [.jpg, .jpeg, .png]
 * Salva todos os arquivos tipo audio [.mp3] maiores que 1MB
 * Videos nao mexer por enquanto.
 * Restante delete.
 *
 * Tipo .aac é arquivo de gravaçãod e audio no whatsApp
 * Tipo .jpg imagens
 *
 * /

public class MainActivity extends AppCompatActivity {

    private static final int KB = 1024;
    private static final int MB = 1024 * KB;
    private static final String TAG = "LogLS";
    private Context context = this;
    private String texto = "";
    private LinearLayout layout;

    File novo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (LinearLayout) findViewById(R.id.layout);

        Button limpeza = (Button) findViewById(R.id.buttonLimpar);

        /**
         * Despois de tudo verificar se pasta do whatsapp tem 0KB
         * se sim excluir o diretorio com todo que esta lá.
         *
         * Tentar encontrar cartao de memoria.
         *
         *
         * /


        limpeza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Botao pressionado.");
                // faz a magia acontecer

                // chama SD
                String state = Environment.getExternalStorageState();

                // SD card montado e pronto para leitura e escrita
                if(Environment.MEDIA_MOUNTED.equals(state)) {

                    /**
                     * Nem sempre o metodo
                     * Environment.getExternalStorageDirectory().getAbsolutePath()
                     * retorna o local correto onde esta o SD card.
                     * Alguns fabricantes mudam o local original do cartao de memoria
                     *
                     * no Samsung o cartao esta em /storange/extSdCard
                     *
                     *  no emulador Nexus S /storange/sdcard
                     *
                     * /

                /* teste

                    Log.i(TAG, Environment.getExternalStorageDirectory().getAbsolutePath());
                    File teste = new File("/storange/extSdCard/AAAAAAA");
                    if(!teste.exists()) {
                        teste.mkdirs();
                        teste.setReadable(true);
                        teste.setWritable(true);
                        Log.i(TAG, "Teste depois do mkdir directory: " + teste.getAbsolutePath());

                        File[] fils = new File("/storange/extSdCard").listFiles();

                        for (File k :fils) {
                            Log.i(TAG, ">> " + k.getAbsolutePath());
                        }

                    } else
                        Log.i(TAG, "Teste directory: " + teste.getAbsolutePath());

                    //Log.i(TAG, Environment.getExternalStorageDirectory().getAbsolutePath());
* /
                    // tentar entrar no sd por forca bruta
                    //File[] sdCards = new File[] {
                    //    new File("/storange/extSdCard"),
                    //      new File("/storange/sdcard")
                    //};

                    //File sdCard = new File("/storange/extSdCard");
                    File sdCard = Environment.getExternalStorageDirectory();
                    Log.i(TAG, "File sdCard criado.");

                    // verifica se existe diretorio e se ele é maior que 1KB de memoria
                    //for (File sdCard : sdCards) {

                    //  if (sdCard.exists() || (sdCard.length() > KB)) {

                    File f = new File("/storage/emulated/0/WhatsApp/");
                    Log.i(TAG, "File sdCard criado.");

                    if (f.exists()) {
                        Log.i(TAG, "Entrou if f.exists()");
                        limpar(f.getAbsolutePath(), sdCard.getAbsolutePath());
                    } else {
                        Log.e(TAG, "Pasta WhatsApp não encontrada.");
                    }
                    //} else {
                    //  Log.d(TAG, "Nao existe ou menor que 1KB: " + sdCard.getAbsolutePath());
                    //}
                    //}



                    // fazer funcao que procura pasta com final sdcard ou SdCard

                } else {
                    Log.e(TAG, "Erro: SD Card não montado.");
                }

                // avisa alteraoes
                //Intent.ACTION_MEDIA_CHECKING;
                //Intent.ACTION_MEDIA_SCANNER_STARTED;


            }
        });// fim setOnClickListener


    }


    private void limpar(String pathWhatsApp, String pathSdCard) {

        Log.i(TAG, "Entrou funcao limpar");

        novo = new File(pathSdCard + "/Arquivos WhatsApp");

        Log.i(TAG, "File novo: " + novo.getAbsolutePath());


        if (novo.mkdir() || novo.isDirectory()) {

            novo.setReadable(true);
            novo.setWritable(true);
            Log.i(TAG, "Diretorio criado.");

            LayoutInflater inflater = LayoutInflater.from(context);
            TextView text = (TextView) inflater.inflate(R.layout.activity_view, layout, false);
            text.setText("\t\tAguarde termino da limpeza.");
            layout.addView(text);

        } else {
            Log.e(TAG, "Erro ao criar Diretorio.");
            LayoutInflater inflater = LayoutInflater.from(context);
            TextView text = (TextView) inflater.inflate(R.layout.activity_view, layout, false);
            text.setText("\t\tErro ao criar Diretorio.");
            layout.addView(text);
            return;
        }


        // Samsung
        // /storage/extSdCard/
        // /storage/emulated/0/

        // muda de aparelho pra aparelho?
        final File dir = new File(pathWhatsApp);

        new Thread() {
            public void run() {
                listar(dir);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        LayoutInflater inflater = LayoutInflater.from(context);
                        TextView text = (TextView) inflater.inflate(R.layout.activity_view, layout, false);
                        text.setText("\t\tLimpeza de arquivos concluida.");
                        layout.addView(text);
                    }
                });

            }
        }.start();

    }




    /**
     * Cuidado em informar a pasta raiz
     * Se for pasta do sistema a funcao exclui tudo
     * ate arquivos importantes que sao menores que 1MB
     * /
    private void listar(File file) {

        Log.i(TAG, "Entrou funcao listar");
        File[] diretorios = file.listFiles();

        for (final File diretorio : diretorios) {

            if(diretorio.isDirectory()) {
                listar(diretorio);
            } else if(diretorio.isFile()) {
                // move apenas fotos, arquivos acima de 1MB
                // exclui .db.crypt8
                String nameFile = diretorio.getName();

                if(!nameFile.endsWith(".db.crypt8") || !nameFile.endsWith(".tmp") || !nameFile.endsWith(".bkup")) {

                    if (nameFile.endsWith(".jpg") ||
                            nameFile.endsWith(".jpeg") ||
                            diretorio.length() > MB) {

                        separarMover(diretorio.getAbsoluteFile());

                    } else {

                        deletar(diretorio.getAbsoluteFile());

                    }
                } else {

                    deletar(diretorio.getAbsoluteFile());

                }
            }
        }

    }

    /**
     * vai separar em pastas
     * Fotos .jpg .jpeg
     * Audios .mp3 .3ga .m4a .aac
     * Videos .mp4 .3gp .avi
     * /
    private void separarMover(File file) {

        String name = file.getName();
        String destino = novo.getAbsolutePath();

        if(name.endsWith(".jpg") || name.endsWith(".jpeg")) {
            destino += "/Fotos";
        } else if (name.endsWith(".mp4") || name.endsWith(".3gp") || name.endsWith(".avi")) {
            destino += "/Videos";
        } else {
            destino += "/Audios";
        }

        File dest = new File(destino);
        dest.mkdir();
        mover(file, new File(dest, name));
    }

    private void deletar(File arquivo) {

        if(arquivo.delete()) {
            Log.e(TAG, "Deletou: " + arquivo.getName());
        } else {
            Log.e(TAG, "Erro em deletar arquivos: " + arquivo.getName());
        }
    }

    private void mover(File origem, File destino) {

        if(!destino.exists() ) {
            try {
                destino.createNewFile();

                try {
                    FileInputStream in = new FileInputStream(origem);
                    FileOutputStream out = new FileOutputStream(destino);

                    // alocar so quantia necessaria
                    //origem.length();
                    byte[] bytes = new byte[MB];

                    while(in.read(bytes) > -1) {
                        out.write(bytes);
                    }

                    Log.i("LogLS", "Moveu: " + origem.getName());
                    out.flush();
                    in.close();
                    out.close();

                    deletar(origem);

                } catch (IOException e) {
                    Log.e("LogLS", "Erro em mover arquivos: " + origem.getName());
                }

            } catch (IOException e) {
                Log.e("LogLS", "Arquivo já existente no diretorio: " + origem.getName());
            }
        } else {
            // arquivo ja exite.. nao sobrescrever... acrescentar indice

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


 */