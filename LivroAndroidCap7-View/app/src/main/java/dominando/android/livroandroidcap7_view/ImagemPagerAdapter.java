package dominando.android.livroandroidcap7_view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 *
 * Created by Luciano on 08/09/2015.
 */
public class ImagemPagerAdapter extends PagerAdapter {

    private Context context;
    private final int[] imagens;

    public ImagemPagerAdapter(Context context, int[] imagens) {
        this.context = context;
        this.imagens = imagens;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_imagem_gridview, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.imgGrid);
        imageView.setImageResource(imagens[position]);

        ((ViewGroup) container).addView(view); // adiciona ao layout ViewGroup
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // remove a view do container
        ((ViewGroup) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return imagens != null ? imagens.length : 0 ;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // determina se a view informada é igual ao object retornado pelo instantiateItem
        return view == object;
    }
}
