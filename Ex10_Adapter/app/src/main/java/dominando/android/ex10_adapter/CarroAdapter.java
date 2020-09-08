package dominando.android.ex10_adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter que sempre instancia novas views pra cada
 * item da lista, o que consome muito recurso
 *
 * O interessante é reutilizar os nao usados como é feito
 * aqui com a classe ViewHolder
 *
 * Created by Luciano on 25/08/2015.
 */
public class CarroAdapter extends BaseAdapter {

    Context context;
    List<Carro> carros;

    public CarroAdapter(Context context, List<Carro> carros) {
        this.context = context;
        this.carros = carros;
    }

    @Override
    public int getCount() {
        return carros.size();
    }

    @Override
    public Object getItem(int position) {
        return carros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1 passo
        Carro carro = carros.get(position);

        // 2 passo
        ViewHolder viewHolder = null;
        if (convertView == null) {
            Log.d("LogLS", "View nova => position: " + position);
            // nao tem view para ser reaproveitada

            convertView = LayoutInflater.from(context).inflate(R.layout.item_carro, null);

            viewHolder = new ViewHolder();

            viewHolder.imgLogo = (ImageView) convertView.findViewById(R.id.imgLogo);
            viewHolder.txtModelo = (TextView) convertView.findViewById(R.id.txtModelo);
            viewHolder.txtAno = (TextView) convertView.findViewById(R.id.txtAno);
            viewHolder.txtCombustivel = (TextView) convertView.findViewById(R.id.txtCombustivel);
            convertView.setTag(viewHolder);
        } else {
            // ja possui view para ser reaproveitada
            Log.d("LogLS", "View existente => position: " + position);
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 3 passo

        // 0=VW; 1=GM; 2=FIAT; 3=FORD
        Resources res = context.getResources();
        TypedArray typedArray = res.obtainTypedArray(R.array.logos);
        viewHolder.imgLogo.setImageDrawable(typedArray.getDrawable(carro.fabricante));

        viewHolder.txtModelo.setText(carro.modelo);
        viewHolder.txtAno.setText(String.valueOf(carro.ano));
        viewHolder.txtCombustivel.setText(
                (carro.gasolina ? "G" : "") +
                (carro.etanol ? "E": "")    );

        // 4 passo
        return convertView;
    }

    static class ViewHolder {
        ImageView imgLogo;
        TextView txtModelo;
        TextView txtAno;
        TextView txtCombustivel;
    }
}
