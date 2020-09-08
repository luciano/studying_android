package br.edu.ufvjm.projetoandroid.testersend;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String ACAO_ENVIADO = "sms_enviado";
    private static final String ACAO_ENTREGUE = "sms_entregue";

    Spinner spinner;
    EditText texto;
    private String[] nomes;
    private String[] numeros;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);
        texto = (EditText) findViewById(R.id.editText);

        configurarSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Posicao: " + position + " - Nome: " + parent.getSelectedItem(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "Nothing ", Toast.LENGTH_SHORT).show();
            }
        });


        //String nome = "Nome: " + spinner.getSelectedItem().toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mReceiver = new EnvioSmsReceiver();
        registerReceiver(mReceiver, new IntentFilter(ACAO_ENVIADO));
        registerReceiver(mReceiver, new IntentFilter(ACAO_ENTREGUE));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    private void configurarSpinner() {

        nomes = new String[] {
                "Luciano", "Luiz Felipe", "Reggis" };
        numeros = new String[] {
                "+55 38 9 9944 4287", "", "" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void enviarSMS(View view) {

        PendingIntent pitEnviado = PendingIntent.getBroadcast(this, 0, new Intent(ACAO_ENVIADO), 0);

        //PendingIntent pitEntregue = PendingIntent.getBroadcast(this, 0, new Intent(ACAO_ENTREGUE), 0);

        String numero = numeros[spinner.getSelectedItemPosition()];

        //String mensagem = getString(R.string.msg_bb);
        String mensagem = texto.getText().toString();
        mensagem = redirecionarMensagem(mensagem);
        if (mensagem == null || mensagem.isEmpty()) {
            mensagem = "msg nula";
        }
        Log.d("DebugApp", "MSg:" + mensagem);

        SmsManager smsManager = SmsManager.getDefault();
        //smsManager.sendTextMessage(numero, null, mensagem, pitEnviado, pitEntregue);
        smsManager.sendTextMessage(numero, null, mensagem, pitEnviado, null);
    }

    private String redirecionarMensagem(String mensagem) {
        if (mensagem.equals("bb1")) {
            return getString(R.string.msg_bb);

        } else if (mensagem.equals("bb2")) {
            return getString(R.string.msg_bb_dois);

        }  else if (mensagem.equals("bb3")) {
            return getString(R.string.msg_bb_tres);

        }  else if (mensagem.equals("bb4")) {
            return getString(R.string.msg_bb_four);

        }  else if (mensagem.equals("caixa1")) {
            return getString(R.string.msg_caixa_um);
        }  else if (mensagem.equals("caixa2")) {
            return getString(R.string.msg_caixa_dois);
        } else {
            return mensagem;
        }
    }

    public static class EnvioSmsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String mensagem = null;
            String acao = intent.getAction();
            int resultado = getResultCode();

            if (resultado == Activity.RESULT_OK) {
                if (ACAO_ENVIADO.equals(acao)) {
                    mensagem = "Enviado com sucesso.";
                } else if (ACAO_ENTREGUE.equals(acao)) {
                    mensagem = "Entregue com sucesso.";
                }
            } else {
                if (ACAO_ENVIADO.equals(acao)) {
                    mensagem = "Falha ao enviar: " + resultado;
                } else if (ACAO_ENTREGUE.equals(acao)) {
                    mensagem = "Falha ao entregar: " + resultado;
                }
            }
            Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
        }
    }
}
