package dominando.android.volley;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyJsonActivity extends AppCompatActivity
        implements Response.Listener<JSONObject>, Response.ErrorListener {


    EditText etNome;
    EditText etIdade;
    Button btnEnviar;
    Button btnVisualizar;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_json);

        final String url = "http://10.2.212.117/TesteVolley/post-volley.php";

        etNome = (EditText) findViewById(R.id.etNome);
        etIdade = (EditText) findViewById(R.id.etIdade);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnVisualizar = (Button) findViewById(R.id.btnVisualizar);
        txt = (TextView) findViewById(R.id.textView);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    /*
                    * {
                    *   "nome" : "joao",
                    *   "idade": 40
                    * }
                    * */

                    jsonObject.put("nome", etNome.getText().toString());
                    jsonObject.put("idade", Integer.parseInt(etIdade.getText().toString()));

                    Log.i("LogLS", "Json criado: " + jsonObject.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        url,
                        jsonObject,
                        VolleyJsonActivity.this,
                        VolleyJsonActivity.this);

                RequestQueue requestQueue = VolleySingleton.getInstance(VolleyJsonActivity.this).getRequestQueue();
                requestQueue.add(jsonObjectRequest);
                //Log.i("LogLS", "ResquestQueue: " + requestQueue.toString());
            }
        });

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.2.212.117/TesteVolley/ler-dados.php";

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                // ler os dados gerados
                                txt.setText(jsonObject.toString());
                            }
                        },
                        VolleyJsonActivity.this);

                RequestQueue requestQueue = VolleySingleton.getInstance(VolleyJsonActivity.this).getRequestQueue();
                requestQueue.add(jsonObjectRequest);

            }
        });

    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        // se tiver erro vem aqui
        Log.i("LogLS", "Erro: " + volleyError.toString());
        Snackbar.make(etNome, "Erro no Volley", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        // recebe o json de resposta do web service aqui
        try {
            String retorno = jsonObject.getString("retorno");
            txt.setText(retorno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
