package dominando.android.planetas_fragment;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlanetasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_planetas, container, false);

        // ListView
        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(new PlanetaAdapter(getActivity()));
        listView.setOnItemClickListener(onItemClickPlaneta());

        return view;
    }

    private AdapterView.OnItemClickListener onItemClickPlaneta() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanetaAdapter adapter = (PlanetaAdapter) parent.getAdapter();

                String planeta = (String) adapter.getItem(position);

                Toast.makeText(getActivity(), "Planeta: " + planeta, Toast.LENGTH_SHORT).show();

                PlanetaFragment planetaFragment = (PlanetaFragment) getFragmentManager().findFragmentById(R.id.PlanetaFragment);

                boolean dualLayout = planetaFragment != null;

                if (dualLayout) {
                    planetaFragment.setPlaneta(planeta);
                } else {
                    Intent intent = new Intent(getActivity(), PlanetaActivity.class);
                    intent.putExtra("planeta", planeta);
                    startActivity(intent);
                }
            }
        };
    }


}
