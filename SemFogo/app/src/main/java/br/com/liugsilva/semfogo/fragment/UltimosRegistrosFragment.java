package br.com.liugsilva.semfogo.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.liugsilva.semfogo.Conexao;
import br.com.liugsilva.semfogo.Debug;
import br.com.liugsilva.semfogo.R;
import br.com.liugsilva.semfogo.Registro;
import br.com.liugsilva.semfogo.adapter.UltimosRegistrosAdapter;

/**
 * http://stackoverflow.com/questions/18206615/how-to-use-google-map-v2-inside-fragment
 */
public class UltimosRegistrosFragment extends Fragment {

    private static boolean pesquisar = true;

    private Context context;
    private ListView listView;
    private List<Registro> list;
    private ProgressDialog progressDialog;
    private UltimosRegistrosAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_ultimos_registros, container, false);
        context = getActivity();

        ImageButton ibLigarBombeiros = (ImageButton) layout.findViewById(R.id.ib_ligar_bombeiros);
        ibLigarBombeiros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FuncionamentoActivityFragment.bombeiros(context);
            }
        });

        listView = (ListView) layout.findViewById(R.id.ultimos_registros_list);

        list = new ArrayList<>();

        RecuperarDadosTask dadosTask = new RecuperarDadosTask();
        dadosTask.execute();

        adapter = new UltimosRegistrosAdapter(context, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Registro registro = (Registro) parent.getItemAtPosition(position);
                Debug.log("Registro[" + position + "]: " + registro.getLongitude() + ", " + registro.getLatitude());

                pesquisar = false;

                Activity activity = getActivity();
                if (activity instanceof OnItemListClick) {
                    OnItemListClick itemListClick = (OnItemListClick) activity;
                    itemListClick.onItemClick(registro);
                }
            }
        });

        return layout;
    }

    public class RecuperarDadosTask extends AsyncTask<Void, Void, List<Registro>> {
        @Override
        protected List<Registro> doInBackground(Void... params) {

            List<Registro> registros = Conexao.receberRegistros(context, pesquisar);

            pesquisar = true;
            return registros;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(context, null, "Buscando dados...", true, true);
            progressDialog.onStart();
        }

        @Override
        protected void onPostExecute(List<Registro> registros) {
            super.onPostExecute(registros);
            progressDialog.dismiss();
            list.clear();
            list.addAll(registros);
            adapter.notifyDataSetChanged();
        }
    }

    public interface OnItemListClick {
        void onItemClick(Registro registro);
    }

}
