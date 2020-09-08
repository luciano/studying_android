package dominando.android.livroandroidcap7_view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Luciano on 08/09/2015.
 */
public class PlanetaPagerAdapter extends PagerAdapter {

    private final List<Planeta> planetas;
    private final Context context;

    public PlanetaPagerAdapter(Context context, List<Planeta> planetas) {
        this.context = context;
        this.planetas = planetas;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_imagem_gridview, container, false);


        Planeta planeta = planetas.get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.imgGrid);
        imageView.setImageResource(planeta.img);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return planetas != null ? planetas.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Planeta planeta = planetas.get(position);
        return planeta.nome;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
