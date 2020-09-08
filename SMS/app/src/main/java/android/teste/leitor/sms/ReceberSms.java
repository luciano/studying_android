package android.teste.leitor.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class ReceberSms extends BroadcastReceiver {

    public static final String CATEGORIA = "depuracao";

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }

        Object[] pdus = (Object[]) extras.get("pdus");

        for (int i = 0; i < pdus.length; i++) {
            //Mensagem interceptada
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            //Criando um Bundle para passar as informações por broadcast.
            Bundle msg = new Bundle();
            //quem enviou.
            msg.putString("sender", smsMessage.getDisplayOriginatingAddress());
            //mensagem.
            msg.putString("body", smsMessage.getDisplayMessageBody());
            //data e hora da mensagem.
            msg.putLong("timestamp", smsMessage.getTimestampMillis());

            /**
             * Crio um intent, a String passada é uma ação, no caso, quem for
             * receber essa mensagem, deve recebe-la pela mesma ação enviada.
             *
             * Você pode definir a ação que quiser. Mas lembre-se de manter um
             * padrão.
             *
             */
            Intent it = new Intent("br.com.helpdev.smsreceiver.SMSRECEIVER");
            //armazeno o Bundle na ação.
            it.putExtra("msg", msg);

            //Envio um broadcast com a ação e dados do Intent que configuramos.
            context.sendBroadcast(it);
        }






















        Log.i(CATEGORIA, "> " + intent.getAction());

        Sms sms = new Sms();

        SmsMessage msg = sms.receberMensagem(intent);
        String celular = msg.getDisplayOriginatingAddress();
        String mensagem = msg.getDisplayMessageBody();
        long tempo = msg.getTimestampMillis();

        responderMensagem(context, celular, mensagem);
    }

    private void responderMensagem(Context context, String destino, String mensagem) {

        Sms sms = new Sms();
        String resposta = respostaMensagem(mensagem);
        if(!resposta.isEmpty()) {
            sms.enviarMensagem(context, destino, resposta);
            Toast.makeText(context, "Mensagem respondida:\n" + resposta, Toast.LENGTH_LONG).show();

        } else {

        }

    }

    private String respostaMensagem(String mensagem) {
        String resposta = "";

        Dicionario dicionario = new Dicionario();

        resposta = dicionario.getResposta(mensagem);

        return resposta;
    }
}
