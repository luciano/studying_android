package br.com.liugsilva.semfogo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.liugsilva.semfogo.R;
import br.com.liugsilva.semfogo.activity.EmpresasActivity;
import br.com.liugsilva.semfogo.activity.FuncionamentoActivity;
import br.com.liugsilva.semfogo.activity.PerfilActivity;
import br.com.liugsilva.semfogo.activity.RegistrarOcorrenciaActivity;
import br.com.liugsilva.semfogo.activity.UltimosRegistrosActivity;

public class MenuActivityFragment extends Fragment {

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.menu_principal_layout, container, false);

        context = getActivity();

        TextView tvRegistrarOcorrencia = (TextView) layout.findViewById(R.id.tv_registrar_ocorrencia);
        tvRegistrarOcorrencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, RegistrarOcorrenciaActivity.class));
            }
        });

        TextView tvUltimasOcorrencias = (TextView) layout.findViewById(R.id.tv_ultimos_registros);
        tvUltimasOcorrencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, UltimosRegistrosActivity.class));
            }
        });

        TextView tvComoFunciona = (TextView) layout.findViewById(R.id.tv_como_funciona);
        tvComoFunciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, FuncionamentoActivity.class));
            }
        });

        TextView tvPerfil = (TextView) layout.findViewById(R.id.tv_perfil);
        tvPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, PerfilActivity.class));
            }
        });

        // banner
        View banner = layout.findViewById(R.id.empresa_banner);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banner(context);
            }
        });

        return layout;
    }

    protected static void banner(Context context) {
        context.startActivity(new Intent(context, EmpresasActivity.class));
    }
}
