package dominando.android.ex10_adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
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
 * no CarroAdapter
 *
 * Created by Luciano on 25/08/2015.
 */
public class CarroSimpleAdapter extends BaseAdapter {

    Context context;
    List<Carro> carros;

    public CarroSimpleAdapter(Context context, List<Carro> carros) {
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
        View linha = LayoutInflater.from(context).inflate(R.layout.item_carro, null);

        // 3 passo
        ImageView imgLogo = (ImageView) linha.findViewById(R.id.imgLogo);
        TextView txtModelo = (TextView) linha.findViewById(R.id.txtModelo);
        TextView txtAno = (TextView) linha.findViewById(R.id.txtAno);
        TextView txtCombustivel = (TextView) linha.findViewById(R.id.txtCombustivel);

        // 0=VW; 1=GM; 2=FIAT; 3=FORD
        Resources res = context.getResources();
        TypedArray typedArray = res.obtainTypedArray(R.array.logos);
        imgLogo.setImageDrawable(typedArray.getDrawable(carro.fabricante));

        txtModelo.setText(carro.modelo);
        txtAno.setText(String.valueOf(carro.ano));
        txtCombustivel.setText(
                (carro.gasolina ? "G" : "") +
                (carro.etanol ? "E": "")    );

        // 4 passo
        return linha;
    }
}
