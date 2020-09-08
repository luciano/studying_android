package dominando.android.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 *
 * Enviar parametros string com Volley
 * http://stackoverflow.com/questions/25948191/send-post-request-using-volley-and-receive-in-php
 *
 * https://www.simplifiedcoding.net/android-volley-post-request-tutorial/
 */
public class VolleyStringActivity extends AppCompatActivity
        implements Response.Listener<String>, Response.ErrorListener {

    TextView mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_string);

        String url = "https://raw.githubusercontent.com/LiuSilva/android/master/arquivo";

        mensagem = (TextView) findViewById(R.id.textView);
        RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, this, this);

        queue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        mensagem.setText("Erro ao carregar");
    }

    @Override
    public void onResponse(String s) {
        mensagem.setText(s);
    }
}