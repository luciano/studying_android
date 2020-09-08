package com.luciano.silva.jwnotebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 *
 * Created by Luciano on 16/09/2015.
 */
public class DiscursoAdapter extends BaseAdapter {

    private List<Discurso> mList;
    private Context context;

    public DiscursoAdapter(Context context, List<Discurso> list) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_fragment, parent, false);

            viewHolder.discurso = (TextView) convertView.findViewById(R.id.textDiscursoTema);
            viewHolder.data = (TextView) convertView.findViewById(R.id.textData);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Discurso discurso = mList.get(position);

        viewHolder.discurso.setText(discurso.getTema());
        viewHolder.data.setText(discurso.getData());

        return convertView;
    }

    private static class ViewHolder {
        TextView discurso;
        TextView data;
    }
}
