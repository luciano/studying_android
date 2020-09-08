package dominando.android.livroandroidcap7_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Luciano on 08/09/2015.
 */
public class PlanetaAdapter extends BaseAdapter {

    private final List<Planeta> planetas;
    private final Context context;

    public PlanetaAdapter(Context context, List<Planeta> planetas) {
        this.context = context;
        this.planetas = planetas;
    }

    @Override
    public int getCount() {
        return planetas != null ? planetas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return planetas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_planeta, parent, false);

        TextView t = (TextView) view.findViewById(R.id.tNomePlaneta);
        ImageView img = (ImageView) view.findViewById(R.id.imgPlaneta);

        Planeta planeta = planetas.get(position);
        t.setText(planeta.nome);
        img.setImageResource(planeta.img);

        return view;
    }
}
