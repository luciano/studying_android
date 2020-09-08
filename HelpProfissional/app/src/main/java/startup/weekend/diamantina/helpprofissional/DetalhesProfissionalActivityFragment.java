package startup.weekend.diamantina.helpprofissional;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetalhesProfissionalActivityFragment extends Fragment {

    public DetalhesProfissionalActivityFragment() {
    }

    public static DetalhesProfissionalActivityFragment newInstance(Bundle bundle) {

        DetalhesProfissionalActivityFragment fragment = new DetalhesProfissionalActivityFragment();

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_profissional, container, false);


        TextView mTextView = (TextView) view.findViewById(R.id.textView);
        TextView mTextView1 = (TextView) view.findViewById(R.id.textView2);

        String contato = "Rua " +
                         "(38) 999-42" +
                         "";

        Bundle bundle = getArguments();

        if (bundle != null) {

            String nome = bundle.getString("PROFISSIONAL");
            mTextView.setText(nome);
            //mTextView1.setText(contato);

        }

        return view;
    }
}
