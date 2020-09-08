package br.com.liugsilva.semfogo.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import br.com.liugsilva.semfogo.R;

public class FuncionamentoActivityFragment extends Fragment {

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.como_funciona_layout, container, false);

        context = getActivity();

        ImageButton ibLigarBombeiros = (ImageButton) layout.findViewById(R.id.ib_ligar_bombeiros);
        ibLigarBombeiros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bombeiros(context);
            }
        });

        // banner
        View banner = layout.findViewById(R.id.empresa_banner);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivityFragment.banner(context);
            }
        });

        return layout;
    }

    protected static void bombeiros(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:193")));
    }
}
