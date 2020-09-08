package dominando.android.hellofragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *
 * Created by Luciano on 18/09/2015.
 */
public class Fragment1 extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);

        // o fragment é livre para ter qualquer lógica aqui
        return view;
    }
}
