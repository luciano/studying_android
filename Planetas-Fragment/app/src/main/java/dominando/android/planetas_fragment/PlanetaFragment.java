package dominando.android.planetas_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlanetaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planeta, container, false);

//        if (getArguments() != null) {
//            String planeta = getArguments().getString("planeta");
//            setPlaneta(planeta);
//        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // aqui se pode chamar o metodo getView que acessa a view principal do fragment
        // no metodo onCreateView esse metodo retorna null

        if (getArguments() != null) {
            String planeta = getArguments().getString("planeta");
            setPlaneta(planeta);
        }
    }

    public void setPlaneta(String planeta) {
        if (getView() != null) {
            TextView textView = (TextView) getView().findViewById(R.id.text);
            textView.setText(planeta);
        }
    }
}
