package startup.weekend.diamantina.helpprofissional;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class CadastroActivityFragment extends Fragment {

    public CadastroActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);


        Button btEnviar = (Button) view.findViewById(R.id.btnEnviar);
        btEnviar.setOnClickListener(onEnviarListener());

        return view;
    }

    private View.OnClickListener onEnviarListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] addresses = new String[] {"contato@pappum.com"};
                composeEmail(addresses, "Contratar serviços", null);
            }
        };
    }

    public void composeEmail(String[] addresses, String subject, Uri attachment) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setData(Uri.parse("mailto:contato@autonomoapp.com"));
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //intent.putExtra(Intent.EXTRA_STREAM, attachment);
        //intent.putExtra(Intent.EXTRA_TEXT, "Dados do autonomo");

        //intent.setType("text/html");
        //intent.putExtra(Intent.EXTRA_EMAIL, "contato@autonomoapp.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pappum Cadastro Autonomo");
        intent.putExtra(Intent.EXTRA_TEXT, "Dados do autonomo");


        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Enviar Cadastro"));
        }
    }
}
