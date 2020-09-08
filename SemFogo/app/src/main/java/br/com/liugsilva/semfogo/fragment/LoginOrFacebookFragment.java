package br.com.liugsilva.semfogo.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import br.com.liugsilva.semfogo.Conexao;
import br.com.liugsilva.semfogo.Debug;
import br.com.liugsilva.semfogo.R;
import br.com.liugsilva.semfogo.Usuario;
import br.com.liugsilva.semfogo.activity.MenuActivity;
import br.com.liugsilva.semfogo.service.EnviarDadosService;

/**
 * aqui vai ter layout com os 3 botoes de login
 */
public class LoginOrFacebookFragment extends Fragment {

    Context context;

    // Facebook
    CallbackManager callbackManager;
    LoginButton loginButton;

    // layouts
    private LinearLayout layoutPrincipal;
    private String id;
    private String nome;
    private String email;
    private String picture;
    private Button btnLogin;
    private Button btnCadastro;

    private LinearLayout layoutLogin;
    private EditText etEmail;
    private EditText etSenha;
    private Button btEntrar;
    private ProgressDialog progressDialog;

    private LinearLayout layoutCadastro;
    private Button btnEnviarCadatro;
    private EditText etCadatroNome;
    private EditText etCadatroEmail;
    private EditText etCadatroTelefone;
    private EditText etCadatroSenha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_login_or_facebook, container, false);

        ImageButton ibLigarBombeiros = (ImageButton) layout.findViewById(R.id.ib_ligar_bombeiros);
        ibLigarBombeiros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FuncionamentoActivityFragment.bombeiros(context);
            }
        });

        // exclui imagem
        deletaFoto();

        inicializarLayouts(layout);

        return layout;
    }

    private void deletaFoto() {
        File picsDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imageFile = new File(picsDir.getAbsolutePath() + "/SemFogo/Profile/", "perfil_picture.jpg");
        boolean delete = imageFile.delete();
        Debug.log("Delete: " + delete);
    }

    private void inicializarLayouts(View layout) {

        layoutPrincipal = (LinearLayout) layout.findViewById(R.id.layout_principal_logins);
        layoutLogin = (LinearLayout) layout.findViewById(R.id.layout_login_logins);
        layoutCadastro = (LinearLayout) layout.findViewById(R.id.layout_cadastro_logins);

        // layout principal
        inicializarLayoutPrincipal(layout);

        // layout login
        inicializarLayoutLogin(layout);

        // layout cadastro
        inicializarLayoutCadastro(layout);
    }

    private void inicializarLayoutPrincipal(View layout) {
        // facebook login
        loginButton = (LoginButton) layout.findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "email");
        loginButton.setFragment(this);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                requestData(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Debug.log("FacebookLogin: Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                Debug.log("FacebookLogin: Login attempt failed. Erro: " + e.toString());
            }
        });

        // chama outro login ou cadastro
        btnCadastro = (Button) layout.findViewById(R.id.btn_cadastrar_logins);
        btnLogin = (Button) layout.findViewById(R.id.btn_entrar_logins);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazerCadastro();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void inicializarLayoutCadastro(View layout) {
        etCadatroNome = (EditText) layout.findViewById(R.id.et_cadastro_nome);
        etCadatroEmail = (EditText) layout.findViewById(R.id.et_cadastro_email);
        etCadatroTelefone = (EditText) layout.findViewById(R.id.et_cadastro_telefone);
        etCadatroSenha = (EditText) layout.findViewById(R.id.et_cadastro_senha);
        btnEnviarCadatro = (Button) layout.findViewById(R.id.btn_enviar_cadastro);
    }

    private void inicializarLayoutLogin(View layout) {
        etEmail = (EditText) layout.findViewById(R.id.et_login_email);
        etSenha = (EditText) layout.findViewById(R.id.et_login_senha);
        btEntrar = (Button) layout.findViewById(R.id.btn_entrar_login);
    }

    private void finish() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        getActivity().finish();
        context.startActivity(new Intent(context, MenuActivity.class));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void requestData(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            id = object.getString("id");
                            nome = object.getString("name");
                            email = object.getString("email");
                            picture = object.getJSONObject("picture").getJSONObject("data").getString("url");

                            Usuario usuario = new Usuario("", nome, email, "", "", id, picture);
                            envirDados(usuario);

                            Debug.log("Object: " + object.toString());
                            Debug.log("id: " + id);
                            Debug.log("nome: " + nome);
                            Debug.log("email: " + email);
                            Debug.log("picture: " + picture);

                            finish();
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.type(large)");
        request.setParameters(parameters);
        request.executeAsync();
    }

//    private void salvarDados() {
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("cadastro", getActivity().MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("nome", nome);
//        editor.putString("email", email);
//        editor.putString("id", id);
//        editor.putString("picture", picture);
//        editor.commit();
//
//        salvarDadosService(nome, email, "", "", id, picture);
//    }

    private void login() {

        layoutPrincipal.setVisibility(View.GONE);
        layoutCadastro.setVisibility(View.GONE);
        layoutLogin.setVisibility(View.VISIBLE);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(getContext(), null, "Fazendo Login ...", true, false);
                progressDialog.onStart();

                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                if (email.isEmpty()) {
                    etEmail.setError("Deve informar email!");
                } else if (senha.isEmpty()) {
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
    }

    private void fazerCadastro() {

        layoutPrincipal.setVisibility(View.GONE);
        layoutCadastro.setVisibility(View.VISIBLE);
        layoutLogin.setVisibility(View.GONE);

        btnEnviarCadatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(getContext(), null, "Cadastrando ...", true, false);
                progressDialog.onStart();

                String nome = etCadatroNome.getText().toString();
                String email = etCadatroEmail.getText().toString();
                String telefone = etCadatroTelefone.getText().toString();
                String senha = etCadatroSenha.getText().toString();

                if (!nome.isEmpty() && !email.isEmpty() && !telefone.isEmpty() && !senha.isEmpty()) {

                    Usuario usuario = new Usuario("", nome, email, senha, telefone, "", "");
                    envirDados(usuario);

                    Debug.log("Cadastro: id = " + id);
                    Debug.log("Cadastro: nome = " + nome);
                    Debug.log("Cadastro: email = " + email);
                    Debug.log("Cadastro: telefone = " + telefone);
                    Debug.log("Cadastro: senha = " + senha);
                    Debug.log("Cadastro: picture = " + picture);

                    finish();
                } else {
                    progressDialog.dismiss();
                    if (nome.isEmpty()) {
                        etCadatroNome.setError("Deve informar o nome!");
                    }
                    if (email.isEmpty()) {
                        etCadatroEmail.setError("Deve informar o email!");
                    }
                    if (telefone.isEmpty()) {
                        etCadatroTelefone.setError("Deve informar o telefone!");
                    }
                    if (senha.isEmpty()) {
                        etCadatroSenha.setError("Deve informar uma senha!");
                    }
                }
            }
        });

    }

    private void envirDados(Usuario usuario) {
        Intent intent = new Intent(context, EnviarDadosService.class);
        intent.putExtra(Conexao.CADASTRO, usuario);
        getActivity().startService(intent);
    }

//    private void salvarDadosService(String nome, String email, String telefone, String senha, String id_facebook, String picture_facebook) {
//        Map<String, String> mapEnvio = new HashMap<String, String>();
//        mapEnvio.put("nome", (nome != null && !nome.isEmpty()) ? nome : SistemaArquivos.CARACTER_CAMPO_VAZIO);
//        mapEnvio.put("email", (email != null && !email.isEmpty()) ? email : SistemaArquivos.CARACTER_CAMPO_VAZIO);
//        mapEnvio.put("telefone", (telefone != null && !telefone.isEmpty()) ? telefone : SistemaArquivos.CARACTER_CAMPO_VAZIO);
//        mapEnvio.put("senha", (senha != null && !senha.isEmpty()) ? senha : SistemaArquivos.CARACTER_CAMPO_VAZIO);
//        mapEnvio.put("id_facebook", (id_facebook != null && !id_facebook.isEmpty()) ? id_facebook : SistemaArquivos.CARACTER_CAMPO_VAZIO);
//        mapEnvio.put("picture_facebook", (picture_facebook != null && !picture_facebook.isEmpty()) ? picture_facebook : SistemaArquivos.CARACTER_CAMPO_VAZIO);
//
//        //Conexao.enviarDados(this, Conexao.URL_USUARIO, mapEnvio);
//    }
}
