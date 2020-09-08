package br.com.liugsilva.semfogo.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import br.com.liugsilva.semfogo.Conexao;
import br.com.liugsilva.semfogo.Debug;
import br.com.liugsilva.semfogo.Empresa;
import br.com.liugsilva.semfogo.Registro;
import br.com.liugsilva.semfogo.SistemaArquivos;
import br.com.liugsilva.semfogo.Usuario;

public class EnviarDadosService extends IntentService {

    public EnviarDadosService() {
        super("Enviar Dados Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            // pode ser: cadastro, ocorrencia, empresa

            if ( bundle.containsKey(Conexao.OCORRENCIA) ) {
                Registro registro = (Registro) bundle.getSerializable(Conexao.OCORRENCIA);

                if (registro != null) {

                    // erro vindo daqui
                    String resultAddress = "";
                    int loop = 0;
                    if (Conexao.temConexao(this)) {
                        while (registro.getLocalizacao().isEmpty() && loop < 15) { // teste com while, faz 5 tentativas
                            ++loop;
                            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                            try {
                                ArrayList<Address> list = (ArrayList<Address>) geocoder.getFromLocation(Double.parseDouble(registro.getLatitude()), Double.parseDouble(registro.getLongitude()), 1);
                                Debug.log("Geoconder do EnviarDadosService. list: " + list.toString());
                                if (!list.isEmpty()) {
                                    Address a = list.get(0);
                                    // a.getPostalCode(); Pega CEP
                                    resultAddress = "";
                                    for (int i = 0, tam = a.getMaxAddressLineIndex(); i < tam; i++) {
                                        resultAddress += a.getAddressLine(i);
                                        resultAddress += i < tam - 1 ? ", " : "";
                                    }
                                }
                            } catch (IOException e) {
                                Debug.log("Erro no Geoconder do EnviarDadosService. Erro: " + e.toString());
                            }
                            registro.setLocalizacao(resultAddress.replace("null", ""));
                        }
                    }

                    Map<String, String> mapEnvio = new HashMap<>();
                    mapEnvio.put(Registro.LATITUDE, (registro.getLatitude() != null && !registro.getLatitude().isEmpty()) ? registro.getLatitude() : SistemaArquivos.CARACTER_CAMPO_VAZIO);
                    mapEnvio.put(Registro.LONGITUDE, (registro.getLongitude() != null && !registro.getLongitude().isEmpty()) ? registro.getLongitude() : SistemaArquivos.CARACTER_CAMPO_VAZIO);
                    mapEnvio.put(Registro.LOCALIZACAO, ((registro.getLocalizacao() != null && !registro.getLocalizacao().isEmpty()) ? registro.getLocalizacao() : SistemaArquivos.CARACTER_CAMPO_VAZIO).replaceAll("\\n", "<br>"));
                    mapEnvio.put(Registro.HORARIO, (registro.getHorario() != null && !registro.getHorario().isEmpty()) ? registro.getHorario() : SistemaArquivos.CARACTER_CAMPO_VAZIO);
                    mapEnvio.put(Registro.DATA, (registro.getData() != null && !registro.getData().isEmpty()) ? registro.getData() : SistemaArquivos.CARACTER_CAMPO_VAZIO);
                    mapEnvio.put(Registro.INFORMACAO, ((registro.getInformacao() != null && !registro.getInformacao().isEmpty()) ? registro.getInformacao() : SistemaArquivos.CARACTER_CAMPO_VAZIO).replaceAll("\\n", "<br>"));

                    Conexao.enviarDados(this, Conexao.URL_REGISTRO, mapEnvio);

                    // teste
//                    for (String path: registro.getLinksImagem()) {
//                        Debug.log("Teste envio: " + path);
//                        //Conexao.enviarImagem(path.substring(path.lastIndexOf("/")) , path);
//                    }

                    // fim teste
                }
                bundle.remove(Conexao.OCORRENCIA);
            }

            if ( bundle.containsKey(Conexao.CADASTRO) ) {
                Usuario usuario = (Usuario) bundle.getSerializable(Conexao.CADASTRO);

                if (usuario != null) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Conexao.CADASTRO, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Usuario.ID_USUARIO, usuario.getIdUsuario());
                    editor.putString(Usuario.NOME, usuario.getNome());
                    editor.putString(Usuario.EMAIL, usuario.getEmail());
                    editor.putString(Usuario.TELEFONE, usuario.getTelefone());
                    editor.putString(Usuario.SENHA, usuario.getSenha());
                    editor.putString(Usuario.ID_FACEBOOK, usuario.getIdFacebook());
                    editor.putString(Usuario.PICTURE_FACEBOOK, usuario.getPictureFacebook());
                    editor.apply();
                    editor.commit();

                    Map<String, String> mapEnvio = new HashMap<String, String>();
                    mapEnvio.put(Usuario.NOME, (usuario.getNome() != null && !usuario.getNome().isEmpty()) ? usuario.getNome() : SistemaArquivos.CARACTER_CAMPO_VAZIO);
                    mapEnvio.put(Usuario.EMAIL, (usuario.getEmail() != null && !usuario.getEmail().isEmpty()) ? usuario.getEmail() : SistemaArquivos.CARACTER_CAMPO_VAZIO);
                    mapEnvio.put(Usuario.TELEFONE, (usuario.getTelefone() != null && !usuario.getTelefone().isEmpty()) ? usuario.getTelefone() : SistemaArquivos.CARACTER_CAMPO_VAZIO);
                    mapEnvio.put(Usuario.SENHA, (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) ? usuario.getSenha() : SistemaArquivos.CARACTER_CAMPO_VAZIO);
                    mapEnvio.put(Usuario.ID_FACEBOOK, (usuario.getIdFacebook() != null && !usuario.getIdFacebook().isEmpty()) ? usuario.getIdFacebook() : SistemaArquivos.CARACTER_CAMPO_VAZIO);
                    mapEnvio.put(Usuario.PICTURE_FACEBOOK, (usuario.getPictureFacebook() != null && !usuario.getPictureFacebook().isEmpty()) ? usuario.getPictureFacebook() : SistemaArquivos.CARACTER_CAMPO_VAZIO);

                    String id = Conexao.enviarDados(this, Conexao.URL_USUARIO, mapEnvio);
                    Debug.log("Service id = " + id);
                }
                bundle.remove(Conexao.CADASTRO);
            }

            if ( bundle.containsKey(Conexao.EMPRESA) ) {
                Empresa empresa = (Empresa) bundle.getSerializable(Conexao.EMPRESA);

                if (empresa != null) {
                    Map<String, String> mapEnvio = new HashMap<>();
                    mapEnvio.put(Empresa.NOME, (empresa.getNome() == null ? "" : empresa.getNome()) );
                    mapEnvio.put(Empresa.EMAIL, (empresa.getEmail() == null ? "" : empresa.getEmail()));
                    mapEnvio.put(Empresa.TELEFONE, (empresa.getTelefone() == null ? "" : empresa.getTelefone()));
                    mapEnvio.put(Empresa.ENDERECO, (empresa.getEndereco() == null ? "" : empresa.getEndereco()));
                    mapEnvio.put(Empresa.MENSAGEM, (empresa.getMensagem() == null ? "" : empresa.getMensagem()).replaceAll("\\n", "<br>"));

                    Conexao.enviarDados(this, Conexao.URL_EMPRESA, mapEnvio);
                }
                bundle.remove(Conexao.EMPRESA);
            }
        }

    }
}
