package br.com.liugsilva.semfogo.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import br.com.liugsilva.semfogo.R;
import br.com.liugsilva.semfogo.activity.CadastroActivity;
import br.com.liugsilva.semfogo.activity.MenuActivity;

public class MainActivityFragment extends Fragment {

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.inicial_layout, container, false);

        context = getActivity();

        Button btnMenuPrincipal = (Button) layout.findViewById(R.id.btn_chamar_menu_principal);
        btnMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        ImageButton btnFacebook = (ImageButton) layout.findViewById(R.id.btn_facebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebook(context);
            }
        });

        ImageButton btnInstagram = (ImageButton) layout.findViewById(R.id.btn_instagram);
        btnInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instagram(context);
            }
        });

        ImageButton btnLinkedin = (ImageButton) layout.findViewById(R.id.btn_linkedin);
        btnLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkedin(context);
            }
        });

        return layout;
    }

    protected static void facebook(Context context) {
        //context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Sem-Fogo-1497738327189838")));
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/VCiOSb")));
    }

    protected static void instagram(Context context) {
        //context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/semfogo/")));
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/jTV5SH")));
    }

    protected static void linkedin(Context context) {
        //context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/sem-fogo")));
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/5yIURX")));
    }

    private void finish(boolean cadastro) {
        getActivity().finish();
        if (cadastro) {
            context.startActivity(new Intent(context, CadastroActivity.class));
        } else {
            // vai para menu
            context.startActivity(new Intent(context, MenuActivity.class));
        }
    }

}
