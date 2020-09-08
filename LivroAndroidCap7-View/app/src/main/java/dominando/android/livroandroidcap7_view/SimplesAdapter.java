package dominando.android.livroandroidcap7_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 *
 * Created by Luciano on 08/09/2015.
 */
public class SimplesAdapter extends BaseAdapter {

    Context context;
    String[] planetas = new String[] {
            "Jupter", "Neturno", "Saturno", "Urano", "Venus"
    };

    public SimplesAdapter (Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        return planetas.length;
    }

    @Override
    public Object getItem(int position) {
        return planetas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String planeta = planetas[position];
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_simples, parent, false);
//        TextView t = new TextView(context);
//        float dip = 50;
//        float densidade = context.getResources().getDisplayMetrics().density;
//
//        int px = (int) (dip * densidade + 0.5f);
//        t.setHeight(px);
        TextView t = (TextView) view.findViewById(R.id.textAdapter);
        t.setText(planeta);

        return view;
    }
}
