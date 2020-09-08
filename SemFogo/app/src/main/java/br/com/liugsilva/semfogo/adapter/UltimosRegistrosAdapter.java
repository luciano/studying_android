package br.com.liugsilva.semfogo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.liugsilva.semfogo.R;
import br.com.liugsilva.semfogo.Registro;

/**
 * Created by Luciano on 08/02/2016.
 */
public class UltimosRegistrosAdapter extends BaseAdapter {

    boolean cor;
    Context context;
    List<Registro> list;

    public UltimosRegistrosAdapter(Context context, List<Registro> list)  {
        this.context = context;
        this.list = list;
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

        ViewHolder viewHolder;

        viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.ultimos_registros_item, parent, false);

            viewHolder.localizacao = (TextView) convertView.findViewById(R.id.tv_ultimos_registros_localizacao);
            viewHolder.horario = (TextView) convertView.findViewById(R.id.tv_ultimos_registros_horario);
            viewHolder.data = (TextView) convertView.findViewById(R.id.tv_ultimos_registros_data);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        convertView.setBackgroundResource((cor ? R.color.fundo_como_funciona_impar : R.color.fundo_como_funciona_par));
        cor = !cor;

        Registro registro = list.get(position);

        String localizacao = registro.getLocalizacao();
        String horario = registro.getHorario();
        String data = registro.getData();

        viewHolder.localizacao.setText(localizacao == null || localizacao.isEmpty() ? "Localização mostrada no Mapa!": localizacao);
        viewHolder.horario.setText(horario == null ? "": horario);
        viewHolder.data.setText(data == null ? "": data);

        return convertView;
    }

    static class ViewHolder {
        TextView localizacao;
        TextView horario;
        TextView data;
    }

}
