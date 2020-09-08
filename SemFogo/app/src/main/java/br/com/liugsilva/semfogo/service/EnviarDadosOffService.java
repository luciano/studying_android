package br.com.liugsilva.semfogo.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import br.com.liugsilva.semfogo.Conexao;
import br.com.liugsilva.semfogo.Debug;
import br.com.liugsilva.semfogo.Registro;

public class EnviarDadosOffService extends IntentService {

    public EnviarDadosOffService() {
        super("Enviar Dados Offline Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Debug.log("ServiceOff: Entrou no Service");
        // enviar dados salvos e depois apaga-los
        int counter = 0;
        SharedPreferences spPersistencias = getSharedPreferences(Conexao.PERSISTENCIA, Context.MODE_PRIVATE);
        Debug.log("ServiceOff: Map Persistencia = " + spPersistencias.getAll().keySet().toString());

        String nome = Conexao.DADOS + counter;
        while (spPersistencias.contains(nome)) {

            SharedPreferences sharedPreferences = getSharedPreferences(nome, Context.MODE_PRIVATE);

            String url = spPersistencias.getString(nome, "");
            Map<String, String> map = (Map<String, String>) sharedPreferences.getAll();
            Debug.log("ServiceOff: Map " + nome + " = " + sharedPreferences.getAll().keySet().toString());

            Debug.log("ServiceOff: entrou no while. Nome = " + nome + "\nUrl = " + url);

            sharedPreferences.edit().clear().apply();
            spPersistencias.edit().remove(nome).apply();

            boolean localiza = map.containsKey(Registro.LOCALIZACAO);

            Debug.log("Service: Localiza = " + localiza);
            if (localiza) { // OTIMIZAR ISSO, AO INVES DELE FAZER TUDO, CHAMA O EnviarDadosService
                String localizacao = map.get(Registro.LOCALIZACAO).equals("#") ? "" : map.get(Registro.LOCALIZACAO);
                String latitude = map.get(Registro.LATITUDE);
                String longitude = map.get(Registro.LONGITUDE);

                Debug.log("ServiceOff: Localizacao antes do while = " + localizacao);

                // problema de nao da pra chamar Geocoder fora de activity
                // erro por usar no service
                String resultAddress = "";
                int loop = 0;
                while (localizacao.isEmpty() && loop < 6) { // teste com while, faz 5 tentativas
                    Debug.log("ServiceOff: Entrou while localizacao.isEmpty");
                    ++loop;
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        ArrayList<Address> list = (ArrayList<Address>) geocoder.getFromLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1);
                        Debug.log("Geoconder do EnviarDadosOffService. list: " + list.toString());
                        if (!list.isEmpty()) {
                            Address a = list.get(0);
                            resultAddress = "";
                            for (int i = 0, tam = a.getMaxAddressLineIndex(); i < tam; i++) {
                                resultAddress += a.getAddressLine(i);
                                resultAddress += i < tam - 1 ? ", " : "";
                            }
                        }
                    } catch (IOException e) {
                        Debug.log("Erro no Geoconder do EnviarDadosOffService. Erro: " + e.toString());
                    }
                    localizacao = resultAddress.replace("null", "");
                }
                map.remove(Registro.LOCALIZACAO);
                map.put(Registro.LOCALIZACAO, localizacao.isEmpty()? "#" : localizacao);
                Debug.log("ServiceOff: Localizacao depois do while = " + localizacao);
            }

            String retorno = Conexao.enviarDados(this, url, map);

            Debug.log("ServiceOff: retorno = " + retorno);

            ++counter;
            nome = Conexao.DADOS + counter;
        }
        Debug.log("ServiceOff: saiu do while. Nome = " + nome);
    }

}
