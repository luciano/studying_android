package dominando.android.ex21_http;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luciano on 11/11/2015.
 */
public class LivrosGridFragment extends Fragment
        implements Response.Listener<JSONObject>, Response.ErrorListener {

    List<Livro> mLivros;
    GridView mGridView;
    TextView mTextMensagem;
    ProgressBar mProgressBar;
    ArrayAdapter<Livro> mAdapter;

    boolean mIsRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_livros_grid, null);
        mProgressBar = (ProgressBar) layout.findViewById(R.id.progressBar);
        mTextMensagem = (TextView) layout.findViewById(android.R.id.empty);
        mGridView = (GridView) layout.findViewById(R.id.gridview);
        mGridView.setEmptyView(mTextMensagem);

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mLivros == null) {
            mLivros = new ArrayList<>();
        }

        mAdapter = new LivrosGridAdapter(getContext(), mLivros);
        mGridView.setAdapter(mAdapter);

        if (!mIsRunning) {
            if (LivroHttp.temConexao(getActivity())) {
                iniciarDonwload();
            } else {
                mTextMensagem.setText("Sem conexão");
            }
        } else {
            exibirProgress(true);
        }
    }

    private void exibirProgress(boolean exibir) {
        if (exibir) {
            mTextMensagem.setText("Baixando informações dos livros...");
        }
        mTextMensagem.setVisibility(exibir ? View.VISIBLE : View.GONE);
        mProgressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    public void iniciarDonwload() {
        mIsRunning = true;
        exibirProgress(true);
        RequestQueue queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, // Requisicao via HTTP_GET
                LivroHttp.LIVROS_URL_JSON, // url da requisicao
                null, // JSONObject a ser enviado via POST
                this, // Response.Listener
                this // Response.ErrorListerner
        );
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        mIsRunning = false;
        exibirProgress(false);
        mTextMensagem.setText("Falha ao obter livros");
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        mIsRunning = true;
        exibirProgress(false);
        try {
            List<Livro> livros = LivroHttp.lerJsonLivros(jsonObject);
            mLivros.clear();
            mLivros.addAll(livros);
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
