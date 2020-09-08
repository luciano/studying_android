package dominando.android.androiddir;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
 *
 * Nem sempre o metodo
 * Environment.getExternalStorageDirectory().getAbsolutePath()
 * retorna o local correto onde esta o SD card.
 * Alguns fabricantes mudam o local original do cartao de memoria
 *
 * no Samsung o cartao esta em /storange/extSdCard
 *
 *  no emulador Nexus S /storange/sdcard
 *
 * no LG  storage/external_SD
 *
 * Criado em: 11/08/2015
 *
 * Ultima modificação: 08/10/2015
 */

public class MainActivity extends AppCompatActivity {

    private static final int KB = 1024;
    private static final int MB = 1024 * KB;
    private static final String TAG = "LogLS";
    private Context context = this;
    private LinearLayout layout;
    //private TextView text;
    private boolean buttonClicado = false;
    private long tempoInicial;

    private int numeroExcluidos;
    private int tamanhoExcluido;
    private int numeroMovidos;
    private long tamanhoMovido;

    File novo;
    private ProgressDialog progressDialog;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (LinearLayout) findViewById(R.id.layout);
        //tempoInicial = System.currentTimeMillis();
        text = (TextView) findViewById(R.id.textView);

        final Button limpeza = (Button) findViewById(R.id.buttonLimpar);

        limpeza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!buttonClicado) {
                    numeroExcluidos = 0;
                    numeroMovidos = 0;
                    tamanhoMovido = 0;
                    tamanhoExcluido = 0;
                    tempoInicial = System.currentTimeMillis();
                    buttonClicado = true;
                    Log.i(TAG, "Botao pressionado.");
                    // faz a magia acontecer

                    // chama SD
                    String state = Environment.getExternalStorageState();
                    // SD card montado e pronto para leitura e escrita
                    if (Environment.MEDIA_MOUNTED.equals(state)) {

                        // funcao de procurarSD
                        //File sdCard = Environment.getExternalStorageDirectory();
                        File sdCard = procuraSdCard();
                        //               Log.i(TAG, "File sdCard criado.");

                        // funcao pega whatsApp
                        File pathWhatsApp = new File("/storage/emulated/0/WhatsApp/");
                        //                 Log.i(TAG, "File sdCard criado.");

                        if (pathWhatsApp.exists()) {
                            //                    Log.i(TAG, "Entrou if f.exists()");
                            progressDialog = ProgressDialog.show(MainActivity.this, "WhatsApp Clear", "Limpando pastas, por favor, aguarde...", false, false);

                            limpar(pathWhatsApp.getAbsolutePath(), sdCard.getAbsolutePath());
                        } else {
                            buttonClicado = false;
                            Log.e(TAG, "Pasta WhatsApp não encontrada.");
                        }

                    } else {
                        buttonClicado = false;
                        Log.e(TAG, "Erro: SD Card não montado.");
                    }
                } else {// se botao ja foi clicado entao na faz nada
                    Log.e(TAG, "Botao ja clicado: " + buttonClicado);
                }
            }
        });// fim setOnClickListener


//        sendBroadcast(new Intent(Intent.ACTION_ATTACH_DATA));
  //      sendBroadcast(new Intent(Intent.ACTION_MEDIA_CHECKING));

    }


    private void limpar(String pathWhatsApp, final String pathSdCard) {

        Log.i(TAG, "Entrou funcao limpar");

        novo = new File(pathSdCard + "/Arquivos WhatsApp");

        Log.i(TAG, "File novo: " + novo.getAbsolutePath());

        // no LG deu erro aqui, mas programa que procura diretorio achou o correto
        // parece achar diretorio, mas nao pode escrever nele no LG
        // criaando manualmente a pasta o programa roda.... erro em escrever diretorios no LG
        if (novo.mkdir() || novo.isDirectory()) {

            novo.setReadable(true, false);
            novo.setWritable(true, false);
            Log.i(TAG, "Diretorio criado.");

        //    LayoutInflater inflater = LayoutInflater.from(context);
          //  text = (TextView) inflater.inflate(R.layout.activity_view, layout, false);

            text.setText("\t\tAguarde termino da limpeza.");
            //layout.addView(text);

        } else {
            Log.e(TAG, "Erro ao criar Diretorio.");
            //LayoutInflater inflater = LayoutInflater.from(context);
            //TextView text = (TextView) inflater.inflate(R.layout.activity_view, layout, false);
            text.setText("\t\tErro ao criar Diretorio.");
            //layout.addView(text);
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
                limpaDiretorio(dir);

                File wts = dir;

                if(!wts.exists()) {
                    wts.mkdir();
                    wts.setReadable(true, false);
                    wts.setWritable(true, false);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

              //          LayoutInflater inflater = LayoutInflater.from(context);
                //        TextView textV = (TextView) inflater.inflate(R.layout.activity_view, layout, false);

                  //      text.setVisibility(View.GONE);
                        progressDialog.dismiss();

                        text.setText("\t\tLimpeza de arquivos concluida.\n" +
                                "\n\t\tTempo decorrido: " + culculaTempoDecorrido(tempoInicial, System.currentTimeMillis()) + "\n" +
                                "\n\nNumero de arquivos movidos: " + numeroMovidos + "\nTamanho: " + size(tamanhoMovido) +
                                "\n\nNumero de arquivos excluidos: " + numeroExcluidos + "\nTamanho: " + size(tamanhoExcluido));
                        //layout.addView(textV);
                        buttonClicado = false;

                      // NAO FUNCIONOU.... OBJETIVO ERA NOTIFICAR GALERIA MAS NAO DEU
                        // avisa alteraoes
                        //Intent.ACTION_MEDIA_CHECKING;
                        //Intent.ACTION_MEDIA_SCANNER_STARTED;
                        //sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED));
                        //sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_STARTED));
                        //sendBroadcast(new Intent(Intent.ACTION_PROVIDER_CHANGED));
                        //sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).setData(Uri.parse(novo.getAbsolutePath())));
                    }
                });

                //FileObserver observer = new FileObserver();

            }
        }.start();

    }


    /**
     * Cuidado em informar a pasta raiz
     * Se for pasta do sistema a funcao exclui tudo
     * ate arquivos importantes que sao menores que 1MB
     */
    private void listar(File file) {

        Log.i(TAG, "Entrou funcao listar");
        File[] diretorios = file.listFiles();

        for (final File diretorio : diretorios) {

            if (diretorio.isDirectory()) {
                listar(diretorio);
            } else if (diretorio.isFile()) {

                // move apenas fotos, arquivos acima de 1MB
                // exclui .db.crypt8 .tmp .bkup
                String nameFile = diretorio.getName();

                if (!nameFile.endsWith(".crypt8") || !nameFile.endsWith(".tmp") || !nameFile.endsWith(".bkup")) {

                    if (nameFile.endsWith(".jpg") ||
                            nameFile.endsWith(".jpeg") ||
                            diretorio.length() > MB) {

                        separarMover(diretorio.getAbsoluteFile());

                    } else {
                        contarDeletados(diretorio.getAbsoluteFile());
                        deletar(diretorio.getAbsoluteFile());
                    }
                } else {
                    contarDeletados(diretorio.getAbsoluteFile());
                    deletar(diretorio.getAbsoluteFile());
                }
            }
        }

    }

    private void contarDeletados(File arquivo) {
        tamanhoExcluido += arquivo.length();
        ++numeroExcluidos;
    }

    /**
     * vai separar em pastas
     * Fotos .jpg .jpeg
     * Audios .mp3 .3ga .m4a .aac
     * Videos .mp4 .3gp .avi
     */
    private void separarMover(File file) {

        String name = file.getName();
        String destino = novo.getAbsolutePath();

        boolean aceito = false;

        if (name.endsWith(".jpg") || name.endsWith(".jpeg")) {
            destino += "/Imagens";
            aceito = true;
        } else if (name.endsWith(".mp4") || name.endsWith(".3gp") || name.endsWith(".avi")) {
            destino += "/Videos";
            aceito = true;
        } else if (name.endsWith(".mp3") || name.endsWith(".3ga") || name.endsWith(".m4a") || name.endsWith(".aac")) {
            destino += "/Audios";
            aceito = true;
        } else {
            aceito = false;
        }

        if (aceito) {
            ++numeroMovidos;
            tamanhoMovido += file.length();
            File dest = new File(destino);
            dest.mkdir();
            dest.setReadable(true, false);
            dest.setWritable(true, false);
            mover(file, new File(dest, name));
        } else {
            contarDeletados(file.getAbsoluteFile());
            deletar(file);
        }
    }

    private void deletar(File arquivo) {

        if (arquivo.delete()) {
            Log.e(TAG, "Deletou: " + arquivo.getName());
        } else {
            Log.e(TAG, "Erro em deletar arquivos: " + arquivo.getName());
        }
    }

    private void mover(File origem, File destino) {

        if (!destino.exists()) {
            try {
                destino.createNewFile();
                destino.setWritable(true, false);
                destino.setReadable(true, false);

                try {
                    FileInputStream in = new FileInputStream(origem);
                    FileOutputStream out = new FileOutputStream(destino);

                    // alocar so quantia necessaria
                    byte[] bytes = new byte[2 * MB];

                    int comprimento;
                    while ((comprimento = in.read(bytes)) > -1) {
                        out.write(bytes, 0, comprimento);
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
            Log.i(TAG, "Arquivo ja existe");
            renomearArquivo(origem, destino);
        }

    }

    private void renomearArquivo(File origem, File destino) {
        int indice = 0;
        File f = null;
        do {
            ++indice;

            Log.i(TAG, "Remomear: ");

            String index = " [" + indice + "]";
            String nome = destino.getName().substring(0, destino.getName().lastIndexOf("."));
            String extensao = destino.getName().substring(destino.getName().lastIndexOf("."));
            nome += index + extensao;
            f = new File(destino.getParentFile(), nome);

            Log.i(TAG, "Remomeado" + indice + ": " + nome);

        } while (f.exists());

        if (f != null)
            mover(origem, f);
    }

    // fazer buscar em mnt tambem e comparar os dois
    private File procuraSdCard() {
        File dirStorege = new File("/storage");

        long maior = 0;
        String pathMaior = "";
        File[] diretorios = dirStorege.listFiles();
        if (diretorios != null)
            for (File file : diretorios) {

                if( file.getTotalSpace() > maior) {
                    maior = file.getTotalSpace();
                    pathMaior = file.getAbsolutePath();
                }

            }
        return new File(pathMaior);
    }

    private String culculaTempoDecorrido(long timeInicial, long timeFinal) {
        //long timeInicial = System.currentTimeMillis();
        //long timeFinal = System.currentTimeMillis();
        final int MIL = 1000;
        final double minuto = 60.0;
        double tempoDecorrido = ((double)(timeFinal - timeInicial)/MIL);

        String quantificador = (tempoDecorrido >= minuto) ? "min" : "seg";

        if ("min".equals(quantificador)) {
            return String.format("%.2f min.", tempoDecorrido/minuto);
        } else {
            return String.format("%.2f seg.", tempoDecorrido);
        }


    }

    private void limpaDiretorio(File dir) {
        File[] diretorios = dir.listFiles();
        if (diretorios != null) {
            for (File f : diretorios) {
                if(f.isDirectory())
                    if (!f.delete())
                        limpaDiretorio(f);
            }
        }
        if(dir.isDirectory())
            dir.delete();
        return;
    }

    private String sizeFolder(File dir) {
        return size(tamanho(dir));
    }

    private long tamanho (File dir) {
        long ret = 0;
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                ret += tamanho (f);
            } else {
                ret += f.length();
            }
        }
        return ret;
    }

    private String procuraPasta(File dir, String pasta) {
/*
        if (new File(dir , pasta).exists()) {
            return dir.getAbsolutePath() + "/WhatsApp";
        }

        File[] d = dir.listFiles();
        if (d != null)
            for (File file : d) {

                if (new File(file , pasta).exists()) {
                    return file.getAbsolutePath() + "/WhatsApp";
                } else {
                    if(file.isFile())
                        continue;
                    procuraPasta(file, pasta);
                }
            }
*/
        return null;
    }

    private String size(long tamanho) {

        int KB = 1024;
        int MB = KB * KB;
        int GB = 1024 * MB;
        double result;

        String quantificador = (tamanho >= MB? "MB" : "KB");

        if("MB".equals(quantificador)) {
            quantificador = (tamanho >= GB ? "GB" : "MB");

            if ("MB".equals(quantificador)) {
                result = (double)tamanho / MB;
                return String.format("%.2f MB", result);
            }
            else {
                result = (double)tamanho / GB;
                return String.format("%.2f GB", result);
            }
        }
        result = (double)tamanho / KB;
        return String.format("%.2f KB", result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // /storage/emulated/0/Android/data/com.snapchat.android
        final String pastaSnap = "/storage/emulated/0/Android/data/com.snapchat.android";

        final String pasta = ".thumbnails";

        switch (item.getItemId()) {

            // limpar pasta DCIM/.thumbnails
            case R.id.action_settings:

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), pasta);
                        Log.d(TAG, "File: " + file.getAbsolutePath());
                        Log.d(TAG, "File exists: " + file.exists());
                        Log.d(TAG, "File read: " + file.canRead());
                        Log.d(TAG, "File write: " + file.canWrite());
                        Log.d(TAG, "File directory: " + file.isDirectory());

                        limpaDiretorioCompleto(file);

                        Log.d(TAG, "Terminou limpeza");
                        Log.d(TAG, "File exists: " + file.exists());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(text, "Limpeza Concluida!", Snackbar.LENGTH_LONG).show();
                            }
                        });
                    }
                }).start();

                break;
            case R.id.action_clear:

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        File file = new File(pastaSnap);
                        Log.d(TAG, "File: " + file.getAbsolutePath());
                        Log.d(TAG, "File exists: " + file.exists());
                        Log.d(TAG, "File read: " + file.canRead());
                        Log.d(TAG, "File write: " + file.canWrite());
                        Log.d(TAG, "File directory: " + file.isDirectory());

                        limpaDiretorioCompleto(file);

                        Log.d(TAG, "Terminou limpeza");
                        Log.d(TAG, "File exists: " + file.exists());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(text, "Limpeza Concluida!", Snackbar.LENGTH_LONG).show();
                            }
                        });
                    }
                }).start();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void limpaDiretorioCompleto(File file) {
        if (file.isDirectory()) {
            File[] arquivos = file.listFiles();
            for (File arquivo: arquivos) {
                if (arquivo.isFile()) {
                    deletar(arquivo);
                } else {
                    if (!arquivo.delete())
                        limpaDiretorioCompleto(arquivo);
                }
            }
        } else {
            deletar(file);
        }
    }
}
