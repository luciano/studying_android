package dominando.android.ex12_autocomplete;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Luciano on 25/08/2015.
 */
public class MeuAutoCompleteAdapter extends ArrayAdapter<String>
                    implements Filterable {

    private List<String> listaCompleta;
    private List<String> resultados;
    private Filter meuFiltro;

    public MeuAutoCompleteAdapter(Context context, int layout, List<String> textos) {
        super(context, layout, textos);

        this.listaCompleta = textos;
        this.resultados = listaCompleta;
        this.meuFiltro = new MeuFiltro();
    }

    @Override
    public int getCount() {
        return resultados.size();
    }

    @Override
    public String getItem(int position) {
        if (resultados != null
            && resultados.size() > 0
            && position < resultados.size()) {
            return resultados.get(position);
        } else {
            return null;
        }
    }

    @Override
    public Filter getFilter() {
        return meuFiltro;
    }

    private class MeuFiltro extends Filter {

        FilterResults filterResults = new FilterResults();

        ArrayList<String> temp = new ArrayList<>();

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {


            if (constraint != null) {
                String term = Util.removeAcento(constraint.toString().trim().toLowerCase());

                String placeStr;

                for (String p : listaCompleta) {
                    placeStr = Util.removeAcento(p.toLowerCase());

                    if (placeStr.indexOf(term) > -1) {
                        temp.add(p);
                    }
                }
            }

            filterResults.values = temp;
            filterResults.count = temp.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            resultados = (ArrayList<String>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
