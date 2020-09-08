package br.com.liugsilva.semfogo.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.com.liugsilva.semfogo.R;
import br.com.liugsilva.semfogo.activity.MenuActivity;

/**
 * login normal email e senha
 */
public class LoginFragment extends Fragment {

    private EditText etEmail;
    private EditText etSenha;
    private Button btEntrar;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_login, container, false);

        etEmail = (EditText) layout.findViewById(R.id.et_login_email);
        etSenha = (EditText) layout.findViewById(R.id.et_login_senha);
        btEntrar = (Button) layout.findViewById(R.id.btn_entrar_login);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(getContext(), null, "Fazendo Login ...", true, false);
                progressDialog.setProgress(50); // teste

                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                if (email.isEmpty()) {
                    etEmail.setError("Deve informar email!");
                } else if (senha.isEmpty()){
                    etSenha.setError("Deve informar senha!");
                } else {
                    // faz verificação no service e se receber os dados de volta grava dados na persistencia de cadastro
                    // se verificacao der certo o servidor envia os dados de Nome, Telefone e Foto

                    //SharedPreferences sharedPreferences = getActivity().getSharedPreferences("cadastro", FragmentActivity.MODE_PRIVATE);
                    //SharedPreferences.Editor editor = sharedPreferences.edit();
                    //editor.putString("nome", nome);
                    //editor.putString("email", email);
                    //editor.putString("telefone", telefone);
                    //editor.putString("senha", senha);
                    //editor.commit();

                    // caso loign incorreto informa ao usuario

                    finish();
                }
                progressDialog.dismiss();
            }
        });

        return layout;
    }

    private void finish() {
        progressDialog.dismiss();
        getActivity().finish();
        getActivity().startActivity(new Intent(getActivity(), MenuActivity.class));
    }
}
