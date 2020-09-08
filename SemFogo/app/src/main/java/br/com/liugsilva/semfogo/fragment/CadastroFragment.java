package br.com.liugsilva.semfogo.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

import br.com.liugsilva.semfogo.R;
import br.com.liugsilva.semfogo.activity.MenuActivity;
/**
 * http://stackoverflow.com/questions/5841710/get-user-image-from-facebook-graph-api
 *  Foto por url: http://graph.facebook.com/959034504190251/picture?type=large
 *
 *  http://stackoverflow.com/questions/32310878/how-to-get-facebook-profile-image-in-android
 *
 */
public class CadastroFragment extends Fragment {

    Context context;
    CallbackManager callbackManager;
    LoginButton loginButton;
    private String id;
    private String nome;
    private String email;
    private String picture;
    private Button btnEnviarCadatros;
    private EditText etCadatroNome;
    private EditText etCadatroEmail;
    private EditText etCadatroTelefone;
    private EditText etCadatroEndereco;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // aqui somente o cadastro propriamente dito
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.cadastro_layout, container, false);

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
                Log.d("LogLS", "Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                Log.d("LogLS", "Login attempt failed. Erro: " + e.toString());
            }
        });

        etCadatroNome = (EditText) layout.findViewById(R.id.et_cadastro_nome);
        etCadatroEmail = (EditText) layout.findViewById(R.id.et_cadastro_email);
        etCadatroTelefone = (EditText) layout.findViewById(R.id.et_cadastro_telefone);
        etCadatroEndereco = (EditText) layout.findViewById(R.id.et_cadastro_endereco);
        btnEnviarCadatros = (Button) layout.findViewById(R.id.btn_enviar_cadastro);
        btnEnviarCadatros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etCadatroNome.getText().toString();
                String email = etCadatroEmail.getText().toString();
                String telefone = etCadatroTelefone.getText().toString();
                String endereco = etCadatroEndereco.getText().toString();

                if (!nome.isEmpty() && !email.isEmpty() && !telefone.isEmpty() && !endereco.isEmpty()) {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("cadastro", FragmentActivity.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nome", nome);
                    editor.putString("email", email);
                    editor.putString("telefone", telefone);
                    editor.putString("endereco", endereco);
                    editor.commit();

                    Log.d("LogLS", "id: " + id);
                    Log.d("LogLS", "nome: " + nome);
                    Log.d("LogLS", "email: " + email);
                    Log.d("LogLS", "telefone: " + telefone);
                    Log.d("LogLS", "endereco: " + endereco);
                    Log.d("LogLS", "picture: " + picture);

                    finish();
                } else {
                    if (nome.isEmpty()) {
                        etCadatroNome.setError("Deve informar o nome");
                    }
                    if (email.isEmpty()) {
                        etCadatroEmail.setError("Deve informar o email");
                    }
                    if (telefone.isEmpty()) {
                        etCadatroNome.setError("Deve informar o telefone");
                    }
                    if (endereco.isEmpty()) {
                        etCadatroNome.setError("Deve informar o endereço");
                    }
                }
            }
        });

        return layout;
    }

    private void finish() {
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

                            Log.d("LogLS", "Object: " + object.toString());
                            Log.d("LogLS", "id: " + id);
                            Log.d("LogLS", "nome: " + nome);
                            Log.d("LogLS", "email: " + email);
                            Log.d("LogLS", "picture: " + picture);

                            salvarDados();
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


    private void salvarDados() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("cadastro", getActivity().MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nome", nome);
        editor.putString("email", email);
        editor.putString("id", id);
        editor.putString("picture", picture);
        editor.commit();
    }
}
