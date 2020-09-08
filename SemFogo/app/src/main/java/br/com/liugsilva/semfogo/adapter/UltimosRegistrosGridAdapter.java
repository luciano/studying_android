package br.com.liugsilva.semfogo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import br.com.liugsilva.semfogo.R;
import br.com.liugsilva.semfogo.Registro;

public class UltimosRegistrosGridAdapter extends ArrayAdapter<Registro> {

    private ImageLoader mLoader;

    public UltimosRegistrosGridAdapter(Context context, List<Registro> objects) {
        super(context, 0, objects);
        mLoader = VolleySingleton.getInstance(context).getImageLoader();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context ctx = parent.getContext();

        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_ultimos_registros_grid, null);
        }

        NetworkImageView img = (NetworkImageView) convertView.findViewById(R.id.img);
        img.setDefaultImageResId(R.drawable.fire_background);
        img.setImageUrl("https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAAXoAAAAJGYyMWIyOGM3LTBhODktNGEzYy1hNzc4LWUyZjUzZGVmODgzNw.png", mLoader);

        return convertView;
    }
}
