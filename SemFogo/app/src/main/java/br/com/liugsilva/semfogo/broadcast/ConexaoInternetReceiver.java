package br.com.liugsilva.semfogo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import br.com.liugsilva.semfogo.Conexao;
import br.com.liugsilva.semfogo.service.EnviarDadosOffService;

/**
 * http://stackoverflow.com/questions/10733121/broadcastreceiver-when-wifi-or-3g-network-state-changed
 */

public class ConexaoInternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Conexao.temConexao(context)) {
            context.startService(new Intent(context, EnviarDadosOffService.class));
        }
    }
}
