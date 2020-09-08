package br.com.liugsilva.semfogo.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import br.com.liugsilva.semfogo.Camera;
import br.com.liugsilva.semfogo.Conexao;
import br.com.liugsilva.semfogo.Debug;
import br.com.liugsilva.semfogo.Localizacao;
import br.com.liugsilva.semfogo.R;
import br.com.liugsilva.semfogo.Registro;
import br.com.liugsilva.semfogo.service.EnviarDadosService;

public class RegistrarOcorrenciaActivityFragment extends Fragment {

    private boolean btnLocalizacaoAtivo = false;

    private Button btnBuscarLocalizacao;
    private Button btnEnviarChamado;
    private EditText etInformacaoAdicional;
    private TextView tvInfo;
    private TextView tvNumero;
    private TextView tvOk;
    private ProgressBar progressBarLocalizacao;
    private ProgressBar progressBarImagem;
    private Localizacao mLocalizacao;
    private Context context;
    private Button btnEnviarImagem;
    private Registro registro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.registrar_ocorrencia_layout, container, false);

        context = getActivity();

        ImageButton ibLigarBombeiros = (ImageButton) layout.findViewById(R.id.ib_ligar_bombeiros);
        ibLigarBombeiros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FuncionamentoActivityFragment.bombeiros(context);
            }
        });

        mLocalizacao = Localizacao.newInstance(getContext());

        btnBuscarLocalizacao = (Button) layout.findViewById(R.id.btn_buscar_localizacao);
        progressBarLocalizacao = (ProgressBar) layout.findViewById(R.id.progressBarLocalizacao);

        etInformacaoAdicional = (EditText) layout.findViewById(R.id.et_informacao_adicional);

        btnEnviarImagem = (Button) layout.findViewById(R.id.btn_enviar_imagem);
        progressBarImagem = (ProgressBar) layout.findViewById(R.id.progressBarImagem);

        btnEnviarChamado = (Button) layout.findViewById(R.id.btn_enviar_chamado);

        tvInfo = (TextView) layout.findViewById(R.id.tv_descricao);
        tvNumero = (TextView) layout.findViewById(R.id.imagemCount);
        tvOk = (TextView) layout.findViewById(R.id.tv_ok);

        btnBuscarLocalizacao.setOnClickListener(onClickLocalizacao());
        btnEnviarImagem.setOnClickListener(onClickImagem());
        btnEnviarChamado.setOnClickListener(onClickEnviar());

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        progressBarImagem.setVisibility(View.GONE);
        // ele leu arquivo limpo no service e encheu de lixo
        // e da erro se tentar abrir... lidar com isso
        // da erro se nao tiver a pasta no diretorio ao chamar o numeroFotos
        int fotos = Camera.numeroFotos(); // deu erro aqui no cel do cristian
        if (fotos > 0) {
            tvNumero.setText("" + fotos);
        } else {
            tvNumero.setText("");
        }

        // ir pela Intent
        if (btnLocalizacaoAtivo && mLocalizacao.isGPSEnabled()) {
            localizar();
        }
    }

    private void localizar() {
        // faz localização
        btnLocalizacaoAtivo = false;
        tvOk.setVisibility(View.GONE);
        progressBarLocalizacao.setVisibility(View.VISIBLE);
        mLocalizacao.requestLocation(true);
        btnBuscarLocalizacao.setText("Localizando...");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLocalizacao.requestLocation(false);
                progressBarLocalizacao.setVisibility(View.GONE);
                tvOk.setVisibility(View.VISIBLE);
                btnBuscarLocalizacao.setText("Buscar Localização");

                if (mLocalizacao.getLatitude() == 0.0 && mLocalizacao.getLongitude() == 0.0) {
                    // erro ao pegar dados e repete GPS
                    alertaErro(null);
                }
                //else { PEGAR ISSO EM BACKGROUND AO ENVIAR
//                    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
//                    try {
//                        ArrayList<Address> list = (ArrayList<Address>) geocoder.getFromLocation(mLocalizacao.getLatitude(), mLocalizacao.getLongitude(), 1);
//                        if (!list.isEmpty()) {
//                            Address a = list.get(0); //erro aki
//                            resultAddress = "";
//                            for (int i = 0, tam = a.getMaxAddressLineIndex(); i < tam; i++) {
//                                resultAddress += a.getAddressLine(i);
//                                resultAddress += i < tam - 1 ? ", " : "";
//                            }
//                        }
//                    } catch (IOException e) {
//                        Debug.log("Caiu no primeiro Geoconder. Erro: " + e.toString());
//                    }
//                }
            }
        }, 5500);
    }

    private View.OnClickListener onClickEnviar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvOk.setVisibility(View.GONE);
                tvNumero.setVisibility(View.GONE);

                double latitude = 0;
                double longitude = 0;
                String localizacao = "";
                String informacoes = "";
                String horario = "";
                String data = "";

                latitude = mLocalizacao.getLatitude();
                longitude = mLocalizacao.getLongitude();
                informacoes = etInformacaoAdicional.getText().toString();

                if (latitude == 0.0 && longitude == 0.0) {
                    alertaErro(v);
                } else {
                    etInformacaoAdicional.setText("");

                    long miliSecundos = System.currentTimeMillis();
                    Date date = new Date(miliSecundos);
                    data = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date);
                    horario = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);
                    registro = new Registro(String.valueOf(latitude), String.valueOf(longitude), localizacao, horario, data, informacoes, null);

//                    BuscarLocalizacao buscarLocalizacao = new BuscarLocalizacao();
//                    buscarLocalizacao.execute(String.valueOf(latitude), String.valueOf(longitude));

                    // teste
                    //ArrayList<String> pathFotos = Camera.pathFotos();
                    //registro.setLinksImagem(pathFotos);
                    // fim teste

                    Intent intent = new Intent(context, EnviarDadosService.class);
                    intent.putExtra(Conexao.OCORRENCIA, registro);
                    getContext().startService(intent);

                    alertaSucesso();

//
//                    if (resultAddress != null && !resultAddress.isEmpty()) {
//                        localizacao = resultAddress.replace("null", "");
//                    }
//
//
//                    // salvar no arquivo
//                    if (mLocalizacao.getLocation() != null && !mLocalizacao.getLocation().isEmpty()) {
//
//                        new Thread() {
//                            @Override
//                            public void run() {
//                                super.run();
//
//                                // MUDAR AQUI
//                                // SE PEGAR ELE TENTA NO BACKGROUND QUANDO TIVER CONEXAO
//                                if (registro.getLocalizacao().isEmpty() && Conexao.temConexao(context)) {
//                                    Debug.log("Registro localização limpa!");
//                                    // AQUI se nao pegar tenta novamente em background, se nao der envia assim mesmo
//                                    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
//                                    try {
//                                        ArrayList<Address> list = (ArrayList<Address>) geocoder.getFromLocation(mLocalizacao.getLatitude(), mLocalizacao.getLongitude(), 1);
//                                        if (!list.isEmpty()) {
//                                            Address a = list.get(0); //erro aki
//                                            resultAddress = "";
//                                            for (int i = 0, tam = a.getMaxAddressLineIndex(); i < tam; i++) {
//                                                resultAddress += a.getAddressLine(i);
//                                                resultAddress += i < tam - 1 ? ", " : "";
//                                            }
//                                        }
//                                    } catch (IOException e) {
//                                        Debug.log("Caiu no segundo Geoconder. Erro: " + e.toString());
//                                    }
//                                    registro.setLocalizacao(resultAddress.replace("null", ""));
//                                    Debug.log("Registro localização: " + registro.getLocalizacao());
//                                }
//
//                                // CONSERTAR ISSO
//                                // SALVA NO enviarDados, salvar duas vezes da redundancia!!!!
//                                //SistemaArquivos.escreverDados(context, registro);
//
//
//                            }
//                        }.start();
//
//                    }

                }
            }
        };
    }

    private void alertaErro(final View v) {
        AlertDialog dialog = new  AlertDialog.Builder(context)
                .setTitle("Sem Fogo Alerta")
                .setMessage("Não foi possível conseguir sua localização via GPS! Deseja tentar novamente?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        idetificarGPS(v);
                    }
                })
                .setNegativeButton("Não", null)
                .create();
        dialog.show();
    }

    private void alertaSucesso() {
        AlertDialog dialog = new  AlertDialog.Builder(context)
                .setTitle("Sem Fogo")
                .setMessage("Obrigado por enviar sua ocorrência! Você está ajudando a cuidar do planeta e salvando vidas!")
                .setPositiveButton("OK", null)
                .create();
        dialog.show();
    }

    private View.OnClickListener onClickLocalizacao() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLocalizacaoAtivo = true;
                idetificarGPS(v);
            }
        };
    }

    private void idetificarGPS(View v) {
        boolean ativoGPS = mLocalizacao.isGPSEnabled();

        if (!ativoGPS) {
            if (v != null) {
                Snackbar.make(v, "O GPS está desativado!", Snackbar.LENGTH_INDEFINITE).setAction("Ativar GPS", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Localizacao.newInstance(getContext()).ligarGPSIntent();
                    }
                }).show();
            } else {
                Toast.makeText(getContext(), "O GPS está desativado!", Toast.LENGTH_SHORT).show();
            }
        } else {
            // caso esteja ativado
            localizar();
        }
    }

    private View.OnClickListener onClickImagem() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarImagem.setVisibility(View.VISIBLE);
                Camera.tirarFoto(getActivity());
            }
        };
    }

    // teste
    class BuscarLocalizacao extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String localizacao = "";
            String resultAddress = "";
            int loop = 0;
            if (Conexao.temConexao(context)) {
                while (localizacao.isEmpty() && loop < 10) { // teste com while, faz 5 tentativas
                    ++loop;
                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                    try {
                        ArrayList<Address> list = (ArrayList<Address>) geocoder.getFromLocation(Double.parseDouble(params[0]), Double.parseDouble(params[1]), 1);
                        if (!list.isEmpty()) {
                            Address a = list.get(0);
                            resultAddress = "";
                            for (int i = 0, tam = a.getMaxAddressLineIndex(); i < tam; i++) {
                                resultAddress += a.getAddressLine(i);
                                resultAddress += i < tam - 1 ? ", " : "";
                            }
                        }
                    } catch (IOException e) {
                        Debug.log("Erro no Geoconder do EnviarDadosService. Erro: " + e.toString());
                    }
                    localizacao = resultAddress.replace("null", "");
                }
            }

            // teste DEU ERRO DE NULL
            //ArrayList<String> pathFotos = Camera.pathFotos();
            //registro.setLinksImagem(pathFotos);
            // fim teste

            return localizacao;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            registro.setLocalizacao(s);

            Intent intent = new Intent(context, EnviarDadosService.class);
            intent.putExtra(Conexao.OCORRENCIA, registro);
            getContext().startService(intent);
        }
    }
}
