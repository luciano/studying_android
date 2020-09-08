package android.teste.leitor.sms;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class Sms {

    public Sms() {

    }

    public void enviarMensagem(Context context, String destino, String mensagem) {

        try {

            SmsManager smsManager = SmsManager.getDefault();
            PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
            smsManager.sendTextMessage(destino, null, mensagem, pIntent, null);

        } catch (Exception e) {
            Log.e(ReceberSms.CATEGORIA, "Erro ao enviar o SMS: " + e.getMessage(), e);
        }

    }

    public SmsMessage receberMensagem(Intent intent) {

        SmsMessage[] mensagens = getMessagesFromIntent(intent);
        if(mensagens != null)
            return mensagens[0];
        else
            return null;
    }

    private SmsMessage[] getMessagesFromIntent(Intent intent) {
        Log.d(ReceberSms.CATEGORIA, "Sms.getMessagesFromIntent: " + intent.getAction());

        Object messages[] = (Object[]) (Object[]) intent.getSerializableExtra("pdus");
        byte pduObjs[][] = new byte[messages.length][];

        for(int i = 0; i < messages.length; ++i)
            pduObjs[i] = (byte[]) (byte[]) messages[i];

        byte pdus[][] = new byte[pduObjs.length][];

        if(pdus == null) {
            return null;
        }
        int pduCont = pdus.length;
        if(pduCont == 0)
            return null;

        SmsMessage msgs[] = new SmsMessage[pduCont];

        for(int i = 0; i < pduCont; ++i) {

            pdus[i] = pduObjs[i];
            msgs[i] = SmsMessage.createFromPdu(pdus[i]);
            String celular = msgs[0].getDisplayOriginatingAddress();
            String mensagem = msgs[0].getDisplayMessageBody();
            Log.d(ReceberSms.CATEGORIA, "Sms.mensagem: " + celular + " -> " + mensagem);

        }

        return msgs;

    }
}
