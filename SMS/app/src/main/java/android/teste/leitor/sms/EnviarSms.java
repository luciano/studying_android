package android.teste.leitor.sms;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EnviarSms extends Activity {

    private BroadcastReceiver broadcastReceiver;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enviar_sms);

        String conteudoArquivo = ler();
        final TextView tela = (TextView) findViewById(R.id.tela);
        tela.setText(conteudoArquivo);
        tela.setTextColor(0xff000000);
        tela.setTextSize(18);


        //
        //ListView listView = (ListView) findViewById(R.id.lista);
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        //listView.setAdapter(adapter);



        Button btEnviar = (Button) findViewById(R.id.btEnviar);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText numeroText = (EditText) findViewById(R.id.numero);
                final EditText mensagemText = (EditText) findViewById(R.id.mensagem);
                String numero = numeroText.getText().toString();
                String mensagem = mensagemText.getText().toString();

                Log.i(ReceberSms.CATEGORIA, "Enviando SMS para [" + numero + "]: " + mensagem);

                Sms sms = new Sms();
                sms.enviarMensagem(EnviarSms.this, numero, mensagem);

                salvar(numero, mensagem);
                String conteudoArquivo = ler();
                Log.i("depuracao", "mensagem depois de ler: " + conteudoArquivo);



                final TextView tela = (TextView) findViewById(R.id.tela);
                tela.setText(conteudoArquivo);
                tela.setTextColor(0xff000000);
                tela.setTextSize(18);

                mensagemText.setText("");
            }
        });

        Button limpar = (Button) findViewById(R.id.btLimpar);
        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile("armazem.txt");

                try {
                    FileOutputStream out = openFileOutput("armazem.txt", Context.MODE_APPEND);
                    File f = new File("armazem.txt");
                    Log.i(ReceberSms.CATEGORIA, "Tentando abrir arquivo: " + f.getAbsolutePath());

                    out.write("".getBytes());
                    out.close();

                } catch (FileNotFoundException e) {
                    Log.e("depuracao", "Erro ao escrever em arquivo. " + "Class: " + getClass().toString().indexOf(".") + ". Erro: " + e.getMessage());
                } catch (IOException e) {
                    Log.e("depuracao", "Class: " + getClass().toString().indexOf(".") + ". Erro: " + e.getMessage());
                }

                String conteudoArquivo = ler();
                final TextView tela = (TextView) findViewById(R.id.tela);
                tela.setText(conteudoArquivo);
                tela.setTextColor(0xff000000);
                tela.setTextSize(18);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //Pega os dados enviados pelo SMSReceiver.
                Bundle msg = intent.getBundleExtra("msg");
                String sender = msg.getString("sender");
                String body = msg.getString("body");
                long timestamp = msg.getLong("timestamp");

                //formatando data para exibição.
                Date date = new Date(timestamp);
                SimpleDateFormat sdf;
                sdf = new SimpleDateFormat("HH:mm - dd/MM/yyyy");

                //gera uma string com as informações.
                String mensagem = sdf.format(date) + "\nDe: " + sender + "\nMensagem: " + body;

                //adiciona e atualiza o adapter.
                salvar(sender, body);
                String conteudoArquivo = ler();
                final TextView tela = (TextView) findViewById(R.id.tela);
                tela.setText(conteudoArquivo);
                tela.setTextColor(0xff000000);
                tela.setTextSize(18);

                //adapter.add(mensagem);
                //adapter.notifyDataSetChanged();

            }
        };

        //Cria um filtro baseado na ação configurada em nosso SMSReceiver.
        IntentFilter intentFilter = new IntentFilter("br.com.helpdev.smsreceiver.SMSRECEIVER");
        //Registra o broadcast.
        this.registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                boolean controle = true;

                //Pega os dados enviados pelo SMSReceiver.
                Bundle msg = intent.getBundleExtra("msg");
                String sender = msg.getString("sender");
                String body = msg.getString("body");
                long timestamp = msg.getLong("timestamp");

                //formatando data para exibição.
                Date date = new Date(timestamp);
                SimpleDateFormat sdf;
                sdf = new SimpleDateFormat("HH:mm - dd/MM/yyyy");

                //gera uma string com as informações.
                String mensagem = sdf.format(date) + "\nDe: " + sender + "\nMensagem: " + body;

                //adiciona e atualiza o adapter.
                if(controle) {
                    controle = false;
                    salvar(sender, "\nData: " + sdf.format(date) + body);
                    String conteudoArquivo = ler();
                    final TextView tela = (TextView) findViewById(R.id.tela);
                    tela.setText(conteudoArquivo);
                    tela.setTextColor(0xff000000);
                    tela.setTextSize(18);
                }
                //adapter.add(mensagem);
                //adapter.notifyDataSetChanged();

            }
        };

        //Cria um filtro baseado na ação configurada em nosso SMSReceiver.
        IntentFilter intentFilter = new IntentFilter("br.com.helpdev.smsreceiver.SMSRECEIVER");
        //Registra o broadcast.
        this.registerReceiver(broadcastReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.broadcastReceiver);
    }



    private String ler() {
        String arqConteudo;

        try {

            File f = getFileStreamPath("armazem.txt");
            Log.i(ReceberSms.CATEGORIA, "Tentando abrir arquivo: " + f.getAbsolutePath());

            if(f.exists()) {

                FileInputStream in = openFileInput("armazem.txt");
                int tamanho = in.available();
                byte bytes[] = new byte[tamanho];
                in.read(bytes);
                arqConteudo = new String(bytes);

                Log.i(ReceberSms.CATEGORIA, "Arquivo foi aberto ler().");
                return arqConteudo;

            } else {
                Log.e(ReceberSms.CATEGORIA, "Arquivo nao existe. Class: " + getClass().toString().indexOf("."));
                return "";
            }



        } catch (IOException e) {
            Log.e(ReceberSms.CATEGORIA, "Erro ao ler em arquivo. Class: " + getClass().toString().indexOf(".") + ". Erro: " + e.getMessage());
        }
        return "";

    }

    private void salvar(String numero, String mensagem) {

        try {


        FileOutputStream out = openFileOutput("armazem.txt", Context.MODE_APPEND);
        File f = new File("armazem.txt");
        Log.i(ReceberSms.CATEGORIA, "Tentando abrir arquivo: " + f.getAbsolutePath());

            out.write("\nNumero: ".getBytes());
            out.write(numero.getBytes());
            out.write("\nMensagem: ".getBytes());
            out.write(mensagem.getBytes());
            out.close();

            Log.i("depuracao", "Escrito no arquivo com sucesso.");
            //Toast.makeText(EnviarSms.this, "Escrito no arquivo com sucesso. " + f.getName(), Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            Log.e("depuracao", "Erro ao escrever em arquivo. " + "Class: " + getClass().toString().indexOf(".") + ". Erro: " + e.getMessage());
        } catch (IOException e) {
            Log.e("depuracao", "Class: " + getClass().toString().indexOf(".") + ". Erro: " + e.getMessage());
        }
    }

}
