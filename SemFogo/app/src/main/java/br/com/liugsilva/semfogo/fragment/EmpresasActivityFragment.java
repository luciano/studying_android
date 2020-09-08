package br.com.liugsilva.semfogo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import br.com.liugsilva.semfogo.Conexao;
import br.com.liugsilva.semfogo.Empresa;
import br.com.liugsilva.semfogo.R;
import br.com.liugsilva.semfogo.service.EnviarDadosService;

public class EmpresasActivityFragment extends Fragment {

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.empresa_layout, container, false);

        context = getActivity();

        final EditText etNome = (EditText) layout.findViewById(R.id.et_empresa_nome);
        final EditText etEmail = (EditText) layout.findViewById(R.id.et_empresa_email);
        final EditText etTelefone = (EditText) layout.findViewById(R.id.et_empresa_telefone);
        final EditText etEndereco = (EditText) layout.findViewById(R.id.et_empresa_endereco);
        final EditText etMensagem = (EditText) layout.findViewById(R.id.et_empresa_mensagem);

        ImageButton ibEnviarDados = (ImageButton) layout.findViewById(R.id.ib_enviar_dados_empresa);
        ibEnviarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                String email = etEmail.getText().toString();
                String telefone = etTelefone.getText().toString();
                String endereco = etEndereco.getText().toString();
                String mensagem = etMensagem.getText().toString();

                if (nome.isEmpty()) {
                    etNome.setError("Deve informar nome!");
                } else if (email.isEmpty()){
                    etEmail.setError("Deve informar email!");
                } else {
                    Empresa empresa = new Empresa(nome, email, telefone, endereco, mensagem);

                    Intent intent = new Intent(context, EnviarDadosService.class);
                    intent.putExtra(Conexao.EMPRESA, empresa);
                    getActivity().startService(intent);

                    alertaAgradecimento();

                    etNome.setText("");
                    etEmail.setText("");
                    etTelefone.setText("");
                    etEndereco.setText("");
                    etMensagem.setText("");
                }
            }
        });

        return layout;
    }

    private void alertaAgradecimento() {
        AlertDialog dialog = new  AlertDialog.Builder(context)
                .setTitle("Sem Fogo")
                .setMessage("Enviado com sucesso! Entraremos em contato com você o mais breve possível!")
                .setPositiveButton("OK", null)
                .create();
        dialog.show();
    }
}
