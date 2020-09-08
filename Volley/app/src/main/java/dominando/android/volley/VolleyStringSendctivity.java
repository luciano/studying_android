package dominando.android.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class VolleyStringSendctivity extends AppCompatActivity {

    private static final String KEY_USERNAME = "username";
    private static final String REGISTER_URL = "http://192.168.1.118/teste-volley/volleyRegister.php";
    private static final String KEY_PASSWORD = "password";

    EditText nome;
    EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_string_sendctivity);

        nome = (EditText) findViewById(R.id.nome);
        texto = (EditText) findViewById(R.id.texto);
    }

    public void enviar(View view) {

        final String username = nome.getText().toString();
        final String text = texto.getText().toString();

        // envia via Post
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LogLS", "Sucesso: Response = " + response);
                        //Toast.makeText(VolleyStringSendctivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("LogLS", "Sucesso: Error = " + error);
                        //Toast.makeText(VolleyStringSendctivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME, username);
                params.put(KEY_PASSWORD, text);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
