package dominando.android.ex17_navegacao;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SegundoNivelFragment extends Fragment {

    private static final String EXTRA_TEXTO = "texto";
    private static final String EXTRA_COR_BG = "corBg";
    private static final String EXTRA_COR_TEXTO = "corTexto";

    public static SegundoNivelFragment novaInstancia(String texto, int background, int textColor) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TEXTO, texto);
        bundle.putInt(EXTRA_COR_BG, background);
        bundle.putInt(EXTRA_COR_TEXTO, textColor);

        SegundoNivelFragment segundoNivelFragment = new SegundoNivelFragment();
        segundoNivelFragment.setArguments(bundle);

        return segundoNivelFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        String texto = bundle.getString(EXTRA_TEXTO);
        int bgColor = bundle.getInt(EXTRA_COR_BG);
        int textColor = bundle.getInt(EXTRA_COR_TEXTO);

        View layout = inflater.inflate(R.layout.fragment_segundo_nivel, container, false);
        layout.setBackgroundColor(bgColor);

        TextView txt = (TextView) layout.findViewById(R.id.textViewSegundo);
        txt.setText(texto);
        txt.setTextColor(textColor);

        return layout;
    }

}
