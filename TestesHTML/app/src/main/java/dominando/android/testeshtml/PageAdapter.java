package dominando.android.testeshtml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter
 * Created by Luciano on 01/09/2015.
 */
public class PageAdapter extends BaseAdapter {

    private List<Page> list;
    private Context context;

    //private TextView texto;
    //private TextView data;

    public PageAdapter(Context context, List<Page> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Page page = list.get(position);

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.view_item, null);

            viewHolder.texto = (TextView) convertView.findViewById(R.id.textPrincipal);
            viewHolder.data = (TextView) convertView.findViewById(R.id.textData);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String title = page.getTitulo();
        String date = page.getData();

        viewHolder.texto.setText(title);
        viewHolder.data.setText(date);

        return convertView;
    }

    private static class ViewHolder {
        TextView texto;
        TextView data;
    }
}
