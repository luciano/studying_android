package dominando.android.livroandroidcap7_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 *
 * Created by Luciano on 08/09/2015.
 */
public class ImagemAdapter extends BaseAdapter {

    private Context context;
    private int[] imagens;

    public ImagemAdapter(Context context, int[] imagens) {
        this.context = context;
        this.imagens = imagens;
    }

    @Override
    public int getCount() {
        return imagens.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_imagem_gridview, parent, false);

        ImageView img = (ImageView) view.findViewById(R.id.imgGrid);
        img.setImageResource(imagens[position]);

        return view;
    }
}
